/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.account.service;

import com.hellokoding.account.model.Order;
import com.hellokoding.account.repository.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cyprian
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    
    @Override
    public void save(Order order) {
        orderRepository.save(order);
        //orderRepository.saveAndFlush(this)
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id);
    }
    
    @Override
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    @Override
    public void saveAndFlush(Order order) {
        orderRepository.saveAndFlush(order);
    }
    
}
