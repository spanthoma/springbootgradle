package span.thoma.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import span.thoma.security.dto.User;
import span.thoma.security.service.UserService;

import javax.validation.Valid;

/**
 * Created by admin on 2017-09-11.
 */

@Controller
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;


    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
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
