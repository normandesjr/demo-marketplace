package com.hibicode.marketplace.controller.dto.builder;

import com.hibicode.marketplace.controller.dto.ProductDTO;

import java.math.BigDecimal;

public final class ProductDTOBuilder {

    private ProductDTO productDTO;

    private ProductDTOBuilder() {
        productDTO = new ProductDTO();
    }

    public ProductDTOBuilder withName(String name) {
        productDTO.setName(name);
        return this;
    }

    public ProductDTOBuilder withSku(String sku) {
        productDTO.setSku(sku);
        return this;
    }

    public ProductDTOBuilder withPrice(BigDecimal price) {
        productDTO.setPrice(price);
        return this;
    }

    public ProductDTO build() {
        return productDTO;
    }

    public static ProductDTOBuilder create() {
        return new ProductDTOBuilder();
    }

}
