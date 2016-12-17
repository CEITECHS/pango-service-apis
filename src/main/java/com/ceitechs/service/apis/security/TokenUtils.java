package com.ceitechs.service.apis.security;

import com.ceitechs.domain.service.util.DateConvertUtility;
import com.ceitechs.domain.service.util.Hex;
import com.ceitechs.domain.service.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @author iddymagohe on 10/30/16.
 * @since 1.0
 */
public class TokenUtils {

    public static String createToken(PangoUserDetails userDetails) {
        String audience = userDetails.getUsername().substring(userDetails.getUsername().indexOf("@"));
        String compactJws = JwtTokenUtil.generateToken(userDetails.getUsername(),audience,userDetails.getVerificationCode());
        return compactJws;
    }


    public static String getUserNameFromToken(String authToken) {
        if (null == authToken) {
            return null;
        }

        String[] parts = authToken.split(":");
        return new String(Hex.decode(parts[0]));
    }

    public static boolean validateToken(String authToken, PangoUserDetails userDetails) {
        String[] parts = authToken.split(":");
        String signature = parts[1];
        return JwtTokenUtil.validateToken(signature, token -> {
            final Claims claims = JwtTokenUtil.getClaimsFromToken(token, userDetails.getVerificationCode());
            final String userName = (String) JwtTokenUtil.getSubjectFromClaims(claims);
            final Date created = JwtTokenUtil.getCreatedDateFromClaims(claims);

            return (
                    userDetails.getUsername().equals(userName)
                            && !JwtTokenUtil.isTokenExpired(claims)
                            && userDetails.getUsername().endsWith(JwtTokenUtil.getAudienceFromClaims(claims))
                            && !isCreatedBeforeLastPasswordReset(created, DateConvertUtility.asUtilDate(userDetails.getLastChangedPasswordOn()))
            );
        });
    }

    private static boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }
}
