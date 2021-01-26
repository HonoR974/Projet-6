package com.projet.escalade.controller;

import com.projet.escalade.entity.User;
import com.projet.escalade.service.SecurityService;
import com.projet.escalade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller pour le login et l'inscription
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;



    /**
     * La page pour s'inscrire
     * @param model model
     * @return security/registration
     */
    @GetMapping("/security/registration")
    public String registration(Model model) {


        model.addAttribute("userForm", new User());

        return "security/registration";
    }

    /**
     * Inscription valider
     * @param userForm identifiant de l'utilisateur
     * @param bindingResult bindingResult
     * @param model model
     * @return redirect:/intro/index
     */
    @PostMapping("/security/registration")
    public String registration(@ModelAttribute("userForm") @Validated User userForm,
                               BindingResult bindingResult,
                               Model model)
    {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {

            return "security/registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        model.addAttribute("user", userService.findByUsername(userForm.getUsername()));




        return "redirect:/intro/index";
    }

    /**
     * La page pour se connecter
     * @param model model
     * @param error error
     * @param logout logout
     * @return security/login
     */
    @GetMapping("/security/login")
    public String login(Model model, String error, String logout) {


        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "security/login";
    }


}