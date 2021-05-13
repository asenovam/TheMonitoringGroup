/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monitoringgroup.controllers;

import com.monitoringgroup.entities.UserEntity;
import com.monitoringgroup.converters.RegisterConverter;
import com.monitoringgroup.dao.UserDao;
import com.monitoringgroup.user.LoginBean;
import com.monitoringgroup.user.RegisterBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mar_9
 */
@Controller
public class AccountEntryController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RegisterConverter registerConverter;

    @GetMapping("/home")
    public String greeting(Model model) {
        model.addAttribute("login", new LoginBean());

        return "home";
    }

    @PostMapping("/home")
    public String greetingSubmit(@ModelAttribute LoginBean login, Model model, RedirectAttributes attr) {
        // shte imame logika koqto vima potrebitel ot DB and ako passwod e OK logva dashoard, ako li ne error page 

        UserEntity user = userDao.findByEmail(login.getEmail());

        if (user == null) {
            login.setErrorMessage("No user with nickname: " + login.getEmail());
            model.addAttribute("login", login);
            return "home";
        }
        // TODO's : ADD A CHECK FOR PASSOWRD MATCH - IF DOES NOT MATCH THE PASSWORD SHOW ERROR MESSGAE ON LOGIN PAGHE 
        attr.addAttribute("email", login.getEmail());

        return "redirect:/userdashboard";
    }

    @GetMapping("/register")
    public String loadRegister(Model model) {
        model.addAttribute("register", new RegisterBean());

        return "register";
    }


    @PostMapping("/register")
    public String executeRegister(@ModelAttribute RegisterBean register, Model model, RedirectAttributes attr) {
        if (register.getEmail() == null || register.getEmail().isEmpty()
                || register.getPassword() == null || register.getPassword().isEmpty()
                || register.getConfirmPassword() == null || register.getConfirmPassword().isEmpty()) {
            register.setErrorMessage("Fill all mandatory fields!");

            model.addAttribute("register", register);
            return "register";
        }

        if (!register.getPassword().equals(register.getConfirmPassword())) {
            register.setErrorMessage("Password and confirm password fields should be equals!");

            model.addAttribute("register", register);
            return "register";
        }

        UserEntity user = userDao.findByEmail(register.getEmail());
        if (user != null) {
            register.setErrorMessage("There is already an existing user with that email. "
            );

            model.addAttribute("register", register);
            return "register";
        }

        user = new UserEntity();
        registerConverter.convert(register, user);

        userDao.save(user);

        LoginBean login = new LoginBean();
        login.setEmail(register.getEmail());
        model.addAttribute("login", login);

        attr.addAttribute("email", login.getEmail());

        return "redirect:userdashboard";
    }

}
