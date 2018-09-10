/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.account.web;

import com.hellokoding.account.model.Order;
import com.hellokoding.account.model.Product;
import com.hellokoding.account.model.Role;
import com.hellokoding.account.model.User;
import com.hellokoding.account.service.OrderService;
import com.hellokoding.account.service.ProductService;
import com.hellokoding.account.service.UserService;
import com.hellokoding.account.validator.OrderValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import org.springframework.security.authentication.AnonymousAuthenticationToken;

/**
 *
 * @author cyprian
 */
@Controller
@RequestMapping(value = "/orderProduct")
public class OrderController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderValidator orderValidator;
    
    
    @Autowired
    private UserService userService;
    
    
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String orderProduct(Model model) {
        //User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        //model.addAttribute("surname", user);
        //@Inject
        List<Product> listOfProducts = productService.findAll();
        System.out.println("\n\n\n\ndlugosc listy to: "+listOfProducts.size()+" "+listOfProducts.get(0).getName());
        
        //System.out.println("ten element: "+listOfProducts.get(1).getName());
        //System.out.println("ten element: "+listOfProducts.get(1).getId());
        //System.out.println("ten element: "+listOfProducts.get(1).getUser().getId());
        model.addAttribute("textProductList",listOfProducts);
        /*List<Order> listOfOrders = new ArrayList<Order>();
        for(int n = listOfProducts.size();n>0;n--){
            listOfOrders.add(new Order());
            
        } */
        //model.addAttribute("orderForm", listOfOrders);  //
        model.addAttribute("orderForm", new Order());
        System.out.println("xd");
      
        if(SecurityContextHolder.getContext().getAuthentication() != null &&
           SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
           !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){   
            Set<Role> roles =userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles();
            System.out.println("xdd: " + roles.size());
            for (Role role : roles){
                System.out.println("dx");
                System.out.println("rola: " + role.getName());
                if(role.getName().equalsIgnoreCase("ROLE_USER")){
                    System.out.println("jest rola");
                    return "orderProduct";
                }
                System.out.println("nie ma takiej roli");
            }
            return "login";
        }
        else{
            return "login";
        }
    }
    
    @RequestMapping(value = "/{prodId}/{prodName}", method = RequestMethod.POST)
    public String orderProduct(@PathVariable("prodId") Long prodId, @PathVariable("prodName") String prodName,@ModelAttribute("orderForm") Order orderForm,
                                BindingResult bindingResult, Model model) { //
        System.out.println("POST");
        System.out.println("probojemy: " + prodId + " " + prodName);
        orderValidator.validate(orderForm,bindingResult);
        
        if (bindingResult.hasErrors()) {
            List<Product> listOfProducts = productService.findAll();
            System.out.println("\n\n\n\ndlugosc listy to: "+listOfProducts.size()+" "+listOfProducts.get(1).getName());
            model.addAttribute("textProductList",listOfProducts);
            model.addAttribute("orderForm", orderForm);
            return "orderProduct";
        }
        
        orderForm.setUser(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        orderForm.setProduct(productService.findById(prodId));
        orderForm.setStatus("waiting");
        //System.out.println("probojemy: " +);
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //LocalDateTime now = LocalDateTime.now();
        orderForm.setDate(new Date());
        orderService.save(orderForm);
        return "redirect:/";
    }
}
