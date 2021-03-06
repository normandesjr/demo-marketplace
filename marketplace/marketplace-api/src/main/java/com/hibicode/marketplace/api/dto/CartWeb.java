package com.hibicode.marketplace.api.dto;

public class CartWeb {

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

//    public CartCreateRequest toCart() {
//        return new CartCreateRequest(clientId, item.toItemCart());
//    }

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

//        public AddedItem toItemCart() {
//            return new AddedItem(sku, quantity);
//        }
    }

}
