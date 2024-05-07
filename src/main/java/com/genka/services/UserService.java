package com.genka.services;

import com.genka.security.UserAuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public static UserAuthDetails getAuthenticatedUser() {
        try {
            return (UserAuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception ex) {
            return null;
        }
    }
}
