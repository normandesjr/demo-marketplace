package com.hibicode.marketplace.api.dto;

import com.hibicode.marketplace.domain.Product;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class ProductWeb {

    @NotNull
    @Pattern(regexp = "[A-Z]{3}[1-9]{3}")
    private String sku;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    @DecimalMin("0")
    private BigDecimal price;

    public ProductWeb() {
    }

    public ProductWeb(Product product) {
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
        return new Product(sku, name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductWeb that = (ProductWeb) o;
        return Objects.equals(sku, that.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }
}
