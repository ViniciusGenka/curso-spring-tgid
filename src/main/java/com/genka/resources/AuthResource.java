package com.genka.resources;


import com.genka.resources.exceptions.AuthorizationException;
import com.genka.security.JWTUtil;
import com.genka.security.UserAuthDetails;
import com.genka.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserAuthDetails authenticatedUser = UserService.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new AuthorizationException("Acesso negado");
        }
        String token = jwtUtil.generateToken(authenticatedUser.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }
}