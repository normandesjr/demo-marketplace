package com.hibicode.marketplace.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.hibicode.marketplace.domain.Product;
import com.hibicode.marketplace.domain.repository.Products;
import com.hibicode.marketplace.repository.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
public class ProductRepository implements Products {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    public ProductRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void save(Product product) {
        var productEntity = new ProductEntity(product);
        dynamoDBMapper.save(productEntity);
    }

    @Override
    public Optional<Product> bySku(String sku) {
        return ofNullable(dynamoDBMapper.load(ProductEntity.class, sku, sku))
                .map(entity -> entity.toProduct());
    }
}
