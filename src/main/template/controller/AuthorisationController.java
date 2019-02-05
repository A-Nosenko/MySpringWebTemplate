package template.controller;

import template.model.User;
import template.service.contract.AccountService;
import template.service.contract.UserService;
import template.validation.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import template.service.contract.SecurityService;

@Controller
public class AuthorisationController {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationValidator registrationValidator;

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String makelogin(User user, ModelMap modelMap, Model model, String error) {
        modelMap.put("user", user);
        if (error != null) {
            model.addAttribute("error", "Wrong login or password.");
        }
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/registration")
    public String registration(User user, ModelMap modelMap) {
        modelMap.put("user", user);
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registration")
    public String makeRegistration(User user, BindingResult result) {

        registrationValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(user);
        accountService.addAccount(user.getId(), 100);

        securityService.autoLogin(user.getLogin(), user.getRepeatedPassword());
        return "registrationReady";
    }
}
