package de.nava.demo.controller;

import de.nava.demo.domain.CurrentUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class BaseControllerAdvice {

    @Value("${info.app.version}")
    private String applicationVersion;

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
    }

    @ModelAttribute("applicationVersion")
    public String getApplicationVersion() {
        return applicationVersion;
    }

}
