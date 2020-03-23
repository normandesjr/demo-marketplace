package com.hibicode.marketplace.domain;

import java.math.BigDecimal;

public class AddedItem {

    private String sku;
    private Integer quantity;
    private BigDecimal total;

    public AddedItem(String sku, Integer quantity) {
        this.sku = sku;
        this.quantity = quantity;
    }

    public AddedItem(String sku, Integer quantity, BigDecimal total) {
        this(sku, quantity);
        this.total = total;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
