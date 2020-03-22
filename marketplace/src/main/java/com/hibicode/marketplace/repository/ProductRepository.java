package com.hibicode.marketplace.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.hibicode.marketplace.exception.NotFoundException;
import com.hibicode.marketplace.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static java.util.Optional.ofNullable;

@Repository
public class ProductRepository {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    public ProductRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void save(Product product) {
        dynamoDBMapper.save(product);
    }

    public Product findBySku(String sku) {
        return ofNullable(dynamoDBMapper.load(Product.class, sku, sku))
                .orElseThrow(NotFoundException::new);
    }
}
