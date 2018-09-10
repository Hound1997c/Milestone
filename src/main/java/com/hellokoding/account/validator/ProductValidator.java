/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.account.validator;

import com.hellokoding.account.model.Product;
import com.hellokoding.account.model.User;
import com.hellokoding.account.service.ProductService;
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
public class ProductValidator implements Validator{
      
    @Autowired
    private ProductService productService;
    
    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
       Product product = (Product) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (product.getName().length() < 6 || product.getName().length() > 32) {
            errors.rejectValue("name", "Size.productForm.name");
        }
        if (productService.findByName(product.getName()) != null) {
            errors.rejectValue("name", "Duplicate.productForm.name");
        }
    }
    
}
