/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.account.service;

import com.hellokoding.account.model.Product;
import java.util.List;

/**
 *
 * @author cyprian
 */
public interface ProductService {
    void save(Product product);

    Product findByName(String name);
    Product findById(Long id);
    
    List<Product> findAll();
}
