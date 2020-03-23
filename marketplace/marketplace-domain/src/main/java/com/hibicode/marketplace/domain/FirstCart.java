package com.hibicode.marketplace.domain;

import java.util.Objects;

public class FirstCart {

    private String id;
    private String clientId;
    private AddedItem item;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public AddedItem getItem() {
        return item;
    }

    public void setItem(AddedItem item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstCart firstCart = (FirstCart) o;
        return Objects.equals(id, firstCart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
