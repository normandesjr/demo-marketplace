package com.hibicode.marketplace.api.dto.builder;

import com.hibicode.marketplace.api.dto.ProductWeb;

import java.math.BigDecimal;

public final class ProductWebBuilder {

    private ProductWeb productWeb;

    private ProductWebBuilder() {
        productWeb = new ProductWeb();
    }

    public ProductWebBuilder withName(String name) {
        productWeb.setName(name);
        return this;
    }

    public ProductWebBuilder withSku(String sku) {
        productWeb.setSku(sku);
        return this;
    }

    public ProductWebBuilder withPrice(BigDecimal price) {
        productWeb.setPrice(price);
        return this;
    }

    public ProductWeb build() {
        return productWeb;
    }

    public static ProductWebBuilder create() {
        return new ProductWebBuilder();
    }

}
