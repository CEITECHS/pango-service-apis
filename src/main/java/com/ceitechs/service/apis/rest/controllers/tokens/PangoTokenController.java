package com.ceitechs.service.apis.rest.controllers.tokens;

import com.ceitechs.domain.service.util.Hex;
import com.ceitechs.service.apis.handler.ExceptionHandlerUtil;
import com.ceitechs.service.apis.rest.resources.LoginResource;
import com.ceitechs.service.apis.rest.resources.MessageResource;
import com.ceitechs.service.apis.rest.resources.TokenResource;
import com.ceitechs.service.apis.security.PangoUserDetails;
import com.ceitechs.service.apis.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author iddymagohe on 10/30/16.
 * @since 1.0
 */
@RestController
public class PangoTokenController {

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * This endpoint will authenticate a pango user with supplied emailAddress and password, an expiring access token is
     * returned along side the firstname, lastname as well the userReferenceId
     *
     * @param loginResource
     * @return
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginResource loginResource, BindingResult result) {

        if (result.hasErrors()) {
            return ExceptionHandlerUtil.handleException(HttpStatus.BAD_REQUEST, result, null);
        }

        ResponseEntity<?> response = null;
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginResource.getEmailAddress(), loginResource.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

			/*
             * Reload user as password of authentication principal will be null
			 * after authorization and password is needed for token generation
			 *
			 */
            PangoUserDetails userDetails = (PangoUserDetails) userDetailsService.loadUserByUsername(loginResource.getEmailAddress());
            String userPart = String.valueOf(Hex.encode(loginResource.getEmailAddress().getBytes()));
            String token = TokenUtils.createToken(userDetails);
            response = ResponseEntity.ok(new TokenResource(userDetails, userPart + ":" + token));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResource("Wrong userId or Password", e.getMessage()));
        }

        return response;
    }

    @RequestMapping(value = "/authenticate/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(@RequestHeader(value = "user-token") String userToken) {
        ResponseEntity<?> response = null;
        try {
            String userName = TokenUtils.getUserNameFromToken(userToken);
            PangoUserDetails userDetails = (PangoUserDetails) userDetailsService.loadUserByUsername(userName);
            Optional<String> refreshedToken = TokenUtils.resfreshedToken(userToken, userDetails);

            response = ResponseEntity.ok(new TokenResource(userDetails, refreshedToken.get()));
        } catch (Exception ex) {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResource("You can not refresh this token, please login to get a new token", ex instanceof NoSuchElementException ? "Un-Authorized token" : ex.getMessage()));
        }

        return response;
    }


}
