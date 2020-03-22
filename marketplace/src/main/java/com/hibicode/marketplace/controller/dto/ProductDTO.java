package com.hibicode.marketplace.controller.dto;

import com.hibicode.marketplace.model.Product;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class ProductDTO {

    @NotNull
    @Pattern(regexp = "[A-Z]{3}[1-9]{3}")
    private String sku;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    @DecimalMin("0")
    private BigDecimal price;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.sku = product.getSku();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product toProduct() {
        Product product = new Product();
        product.setName(this.name);
        product.setSku(this.sku);
        product.setSkuRange(this.sku);
        product.setPrice(this.price);
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(sku, that.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }
}
