/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.account.web;

import com.hellokoding.account.model.Product;
import com.hellokoding.account.model.Role;
import com.hellokoding.account.model.User;
import com.hellokoding.account.service.ProductService;
import com.hellokoding.account.service.UserService;
import com.hellokoding.account.validator.ProductValidator;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author cyprian
 */
@Controller
@RequestMapping(value = "/addingProduct")
public class ProductController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private  ProductService productService;
    
    @Autowired
    private ProductValidator productValidator;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String addingProduct(Model model) {
        model.addAttribute("productForm", new Product());
        if(SecurityContextHolder.getContext().getAuthentication() != null &&
           SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
           !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){   
            Set<Role> roles =userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles();
            for (Role role : roles){
                if(role.getName().equalsIgnoreCase("ROLE_USER")){
                    return "addingProduct";
                }
            }
            return "login";
        }
        else{
            return "login";
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addingProduct(@ModelAttribute("productForm") Product productForm, BindingResult bindingResult) {   //, BindingResult bindingResult, Model model //@RequestParam("name") String name
        productValidator.validate(productForm,bindingResult);
        
        if (bindingResult.hasErrors()) {
            return "addingProduct";
        }
        
        System.out.println("Takie imie" + SecurityContextHolder.getContext().getAuthentication().getName());
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("Takie imie ma on" + user.getUsername());
        productForm.setUser(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        productService.save(productForm);
        return "redirect:/"; //welcome
    }
}
