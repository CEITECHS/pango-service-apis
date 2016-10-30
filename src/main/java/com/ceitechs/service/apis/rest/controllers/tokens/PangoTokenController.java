package com.ceitechs.service.apis.rest.controllers.tokens;

import com.ceitechs.domain.service.util.PangoUtility;
import com.ceitechs.service.apis.handler.ExceptionHandlerUtil;
import com.ceitechs.service.apis.rest.resources.LoginResource;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginResource.getEmailAddress(),loginResource.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

			/*
			 * Reload user as password of authentication principal will be null
			 * after authorization and password is needed for token generation
			 *
			 */
            PangoUserDetails userDetails = (PangoUserDetails) userDetailsService.loadUserByUsername(loginResource.getEmailAddress());
            String token = TokenUtils.createToken(userDetails);
            response = ResponseEntity.ok(new TokenResource(userDetails, token));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return response;
    }
}
