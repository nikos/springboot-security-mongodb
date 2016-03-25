package de.nava.demo.controller;

import de.nava.demo.domain.UserCreateForm;
import de.nava.demo.domain.validator.UserCreateFormValidator;
import de.nava.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserCreateFormValidator formValidator;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator formValidator) {
        this.userService = userService;
        this.formValidator = formValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(formValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable String id) {
        log.debug("Getting user page for user={}", id);
        return new ModelAndView("user", "user", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        log.debug("Getting user create form");
        return new ModelAndView("user_create", "form", new UserCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form,
                                       BindingResult bindingResult) {
        log.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            log.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "user_create";
        }
        // everything fine redirect to list
        return "redirect:/users";
    }

}
