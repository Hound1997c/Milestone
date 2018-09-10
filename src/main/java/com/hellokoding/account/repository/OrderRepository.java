/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.account.repository;


import com.hellokoding.account.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author cyprian
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findById(Long id);
    List<Order> findAll();
}
