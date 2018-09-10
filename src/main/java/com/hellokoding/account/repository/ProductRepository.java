/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.account.repository;

import com.hellokoding.account.model.Product;
import com.hellokoding.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author cyprian
 */
public interface ProductRepository extends JpaRepository<Product, Long>{
    Product findByName(String name);
    Product findById(Long id);
}
