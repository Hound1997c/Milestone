/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.account.service;

import com.hellokoding.account.model.Order;
import java.util.List;

/**
 *
 * @author cyprian
 */
public interface OrderService {
    void save(Order order);
    
    Order findById(Long id);
    
    void saveAndFlush(Order order);
    
    List <Order> findAll();
    
}
