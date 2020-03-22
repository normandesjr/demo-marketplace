package com.hibicode.marketplace.controller.cart;

import java.util.Objects;

public class CartRequest {

    private String clientId;
    private ItemRequest item;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public ItemRequest getItem() {
        return item;
    }

    public void setItem(ItemRequest item) {
        this.item = item;
    }

    public static class ItemRequest {

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
            ItemRequest that = (ItemRequest) o;
            return Objects.equals(sku, that.sku) &&
                    Objects.equals(quantity, that.quantity);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sku, quantity);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartRequest that = (CartRequest) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, item);
    }
}
