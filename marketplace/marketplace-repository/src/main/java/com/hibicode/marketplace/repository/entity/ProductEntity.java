package com.hibicode.marketplace.repository.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.hibicode.marketplace.domain.Product;
import io.github.normandesjr.annotation.DynamoDBPrefix;

import java.math.BigDecimal;
import java.util.Objects;

@DynamoDBTable(tableName = "marketplace")
public class ProductEntity {

    private String sku;
    private String skuRange;
    private String name;
    private BigDecimal price;

    public ProductEntity() {
    }

    public ProductEntity(Product product) {
        this.sku = product.getSku();
        this.skuRange = product.getSku();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    @DynamoDBPrefix("PROD_")
    @DynamoDBHashKey(attributeName = "pk")
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku ;
    }

    @DynamoDBPrefix("PROD_")
    @DynamoDBRangeKey(attributeName = "sk")
    public String getSkuRange() {
        return skuRange;
    }

    public void setSkuRange(String skuRange) {
        this.skuRange = skuRange;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "data")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product toProduct() {
        return new Product(sku, name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity productEntity = (ProductEntity) o;
        return Objects.equals(sku, productEntity.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "sku='" + sku + '\'' +
                ", skuRange='" + skuRange + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}