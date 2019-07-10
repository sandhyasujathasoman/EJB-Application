package com.javacodegeeks.enterprise.ejb;


import javax.ejb.Local;

import com.javacodegeeks.enterprise.product.Product;
 
@Local
public interface Cart {
 
      void addProductToCart(Product product);
 
      void checkOut();
 
}

