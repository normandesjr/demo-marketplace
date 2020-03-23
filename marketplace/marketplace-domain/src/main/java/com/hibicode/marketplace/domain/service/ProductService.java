package com.hibicode.marketplace.domain.service;

import com.hibicode.marketplace.domain.Product;

import java.util.Optional;

public interface ProductService {

    void save(Product product);

    Optional<Product> findBySku(String sku);
}
