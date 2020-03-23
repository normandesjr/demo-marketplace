package com.hibicode.marketplace.service;

import com.hibicode.marketplace.domain.Product;
import com.hibicode.marketplace.domain.repository.Products;
import com.hibicode.marketplace.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private Products products;

    @Autowired
    public ProductServiceImpl(Products products) {
        this.products = products;
    }

    @Override
    public void save(Product product) {
        products.save(product);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return products.bySku(sku);
    }
}
