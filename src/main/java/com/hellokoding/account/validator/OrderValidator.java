/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.account.validator;

import com.hellokoding.account.model.Order;
import com.hellokoding.account.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author cyprian
 */
@Component
public class OrderValidator implements Validator{
    
    //@Autowired
        //private OrderService orderService;
    @Override
    public boolean supports(Class<?> aClass) {
        return Order.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Order order = (Order) o;
        //Product product = (Product) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productAmount", "NotEmpty");
        if (order.getProductAmount()<=0) {
            errors.rejectValue("productAmount", "NotZero");
        } 
       
    }
    
}
