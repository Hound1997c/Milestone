/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.account.web;

import com.hellokoding.account.model.Order;
import com.hellokoding.account.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author cyprian
 */
@Controller
@RequestMapping(value="expectantOrders")
public class SeeWaitingController {
    
    
    @Autowired
    private OrderService orderService; 
    
    
    private List<Order> filtr (List<Order> listOfOrders,String status){
        List<Order> filtredList = new ArrayList<>();
        for(Order order : listOfOrders){
            if(order.getStatus().equalsIgnoreCase(status)){
                filtredList.add(order);
            }
        }
        return filtredList;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String expectant(Model model){
        
        List<Order> listOfOrders = orderService.findAll();
        
        model.addAttribute("orderList",filtr(listOfOrders,"waiting"));
        
        return "expectantOrders";
    }
    
    @RequestMapping(value = "/accept/{ordId}",  method = RequestMethod.GET)
    public String accept(Model model, @PathVariable("ordId") Long ordId){
        Order order = orderService.findById(ordId);
        order.setStatus("accepted");
        orderService.saveAndFlush(order);
        List<Order> listOfOrders = orderService.findAll();
        model.addAttribute("orderList",filtr(listOfOrders,"waiting"));
        //System.out.println("akceptujemy " + order.getStatus());
        
        return "expectantOrders";
    }
    
    @RequestMapping(value = "/decline/{ordId}", method = RequestMethod.GET)
    public String decline(Model model, @PathVariable("ordId") Long ordId){
        
        //System.out.println("odrzucamy" + ordId);
        Order order = orderService.findById(ordId);
        order.setStatus("declined");
        orderService.saveAndFlush(order);
        List<Order> listOfOrders = orderService.findAll();
         model.addAttribute("orderList",filtr(listOfOrders,"waiting"));
        return "expectantOrders";
    }
    
}
