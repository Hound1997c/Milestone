/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.account.web;

import com.hellokoding.account.model.User;
import com.hellokoding.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author cyprian
 */
@Controller
@RequestMapping(value="changePassword")
public class ChangePWController {
     
    @Autowired
    UserService userService;
    
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String changePW(Model model) {
        System.out.println("takie imie");
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("takie imie" + user.getName());
        System.out.println("takie imie");
        model.addAttribute("userForm", user);
        return "changePassword";
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public String changePW(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "redirect:/";
    }
}
