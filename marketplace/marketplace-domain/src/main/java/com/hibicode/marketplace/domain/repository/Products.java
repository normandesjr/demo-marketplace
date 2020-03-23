package com.hibicode.marketplace.domain.repository;

import com.hibicode.marketplace.domain.Product;

import java.util.Optional;

public interface Products {

    public void save(Product product);

    public Optional<Product> bySku(String sku);

}
