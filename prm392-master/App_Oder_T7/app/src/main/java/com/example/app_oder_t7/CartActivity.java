package com.example.app_oder_t7;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app_oder_t7.DTO.Cart;
import com.example.app_oder_t7.DTO.MonAnDTO;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private TextView totalItemsTextView;
    private TextView totalPriceTextView;
    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cart_list);
        totalItemsTextView = findViewById(R.id.total_items);
        totalPriceTextView = findViewById(R.id.total_price);

        cart = (Cart) getIntent().getSerializableExtra("cart");

        if (cart != null) {
            ArrayList<String> cartItems = new ArrayList<>();
            for (MonAnDTO item : cart.getCartItems()) {
                cartItems.add(item.getTenMonAn() + " - $" + String.format("%.2f", item.getGiaTien()));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_layout_hienthimonan, cartItems);
            cartListView.setAdapter(adapter);

            totalItemsTextView.setText("Total Items: " + cart.getTotalItems());
            totalPriceTextView.setText("Total Price: $" + String.format("%.2f", cart.getTotalPrice()));
        } else {
            totalItemsTextView.setText("Total Items: 0");
            totalPriceTextView.setText("Total Price: $0.00");
        }
    }
}