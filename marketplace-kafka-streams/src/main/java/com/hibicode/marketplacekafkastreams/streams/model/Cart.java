package com.hibicode.marketplacekafkastreams.streams.model;

import java.util.Objects;

public class Cart {

    private String customerId;
    private Item item;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(customerId, cart.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    public static class Item {
        private String sku;
        private Integer quantity;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return Objects.equals(sku, item.sku);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sku);
        }
    }
}
