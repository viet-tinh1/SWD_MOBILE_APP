package com.example.app_oder_t7.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private List<MonAnDTO> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public void addItem(MonAnDTO item) {
        cartItems.add(item);
    }

    public List<MonAnDTO> getCartItems() {
        return cartItems;
    }

    public int getTotalItems() {
        return cartItems.size();
    }

    public String getTotalPrice() {
        String total = "";
        for (MonAnDTO item : cartItems) {
            total += item.getGiaTien();
        }
        return total;
    }
}
