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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author cyprian
 */
@Controller
@RequestMapping(value="/makenOrders")
public class MakenController {
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
    public String showMaken(Model model){
        
        List<Order> listOfOrders = orderService.findAll();
        List<Order> wholeList = filtr(listOfOrders,"accepted");
        wholeList.addAll(filtr(listOfOrders,"declined"));
        model.addAttribute("orderList",wholeList);
        return "makenOrders";
    }
}
