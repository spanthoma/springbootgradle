package span.thoma.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import span.thoma.security.dto.User;
import span.thoma.security.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by admin on 2017-09-11.
 */

@Controller
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;

    @GetMapping("/login/facebook")
    public String facebook() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/template/user", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(ModelMap modelMap, @Valid User user, BindingResult bindingResult, SessionStatus sessionStatus) {

        if(bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(e -> {
                modelMap.put(e.getField(), messageSource.getMessage(e.getDefaultMessage(), null, LocaleContextHolder.getLocale()));
            });

            return "register";
        } else {
            sessionStatus.setComplete();
            user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
            userService.createUser(user);
            return "redirect:/login";
        }
    }
}
