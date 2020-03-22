package com.hibicode.marketplace.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import io.github.normandesjr.annotation.DynamoDBPrefix;

import java.math.BigDecimal;
import java.util.Objects;

@DynamoDBTable(tableName = "marketplace")
public class Product {

    private String sku;
    private String skuRange;
    private String name;
    private BigDecimal price;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(sku, product.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return "Product{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
