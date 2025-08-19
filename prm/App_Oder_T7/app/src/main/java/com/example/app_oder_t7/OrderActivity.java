package com.example.app_oder_t7;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_oder_t7.CustomAdapter.AdapterHienThiMonAn;
import com.example.app_oder_t7.DAO.MonAnDAO;
import com.example.app_oder_t7.DTO.Cart;
import com.example.app_oder_t7.DTO.MonAnDTO;
import com.example.app_oder_t7.FragmentAPP.HienThiBanAnFragment;
import com.example.app_oder_t7.FragmentAPP.HienThiThucDonFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private ListView produceListView;
    MonAnDAO monAnDAO;
    FragmentManager fragmentManager;
    private Button viewCartButton;
   private Cart cart;
   List<MonAnDTO> monAnDTOList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_order);
        produceListView = findViewById(R.id.produce_list);
        ImageView back = findViewById(R.id.back);
        viewCartButton = findViewById(R.id.view_cart_button);

        monAnDTOList = new ArrayList<>();
        monAnDAO = new MonAnDAO(this);
        monAnDTOList = monAnDAO.LayTatCaMonAn();

        // Set up adapter and ListView
        AdapterHienThiGoiMon adapter = new AdapterHienThiGoiMon(this, R.layout.custom_layout_order, monAnDTOList, cart);
        produceListView.setAdapter(adapter);
        fragmentManager = getSupportFragmentManager();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, HienThiBanAnFragment.class);

                startActivity(intent);
            }
        });
        viewCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(OrderActivity.this, CartActivity.class);
            intent.putExtra("cart", cart);
            startActivity(intent);
        });
    }
        public class AdapterHienThiGoiMon extends BaseAdapter implements View.OnClickListener  {

        Context context;
        int layout;
        List<MonAnDTO> monAnDTOList;
        public AdapterHienThiGoiMon(Context context, int layout, List<MonAnDTO> monAnDTOList, Cart cart) {
            this.context = context;
            this.layout = layout;
            this.monAnDTOList = monAnDTOList;
            this.cart = cart;
        }
            private Cart cart;
        @Override
        public int getCount() {
            return monAnDTOList.size();
        }

        @Override
        public Object getItem(int position) {
            return monAnDTOList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return monAnDTOList.get(position).getMaMonAn();
        }
        public class ViewHolderGoiMon {
            ImageView a;
            Button AddButton;
            TextView txtTenMonAn;
            TextView txtGiaTien;


        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
           AdapterHienThiGoiMon.ViewHolderGoiMon viewHolderGoiMon;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                viewHolderGoiMon = new AdapterHienThiGoiMon.ViewHolderGoiMon();
                view = inflater.inflate(R.layout.custom_layout_order, parent, false);
                viewHolderGoiMon.AddButton = view.findViewById(R.id.add_to_cart_button);
                viewHolderGoiMon.a = view.findViewById(R.id.a);
                viewHolderGoiMon.txtTenMonAn = view.findViewById(R.id.txtTenMonAn);
                viewHolderGoiMon.txtGiaTien = view.findViewById(R.id.txtGiaTien);

                view.setTag(viewHolderGoiMon);
            } else {
                viewHolderGoiMon = (AdapterHienThiGoiMon.ViewHolderGoiMon) view.getTag();
            }
            cart = new Cart();
            MonAnDTO monAnDTO = monAnDTOList.get(position);
            viewHolderGoiMon.txtTenMonAn.setText(monAnDTO.getTenMonAn());
            viewHolderGoiMon.txtGiaTien.setText(monAnDTO.getGiaTien());

            if (monAnDTO.isDuocChon()) {
                HienThiButton(viewHolderGoiMon);
            } else {
                AnButton(viewHolderGoiMon);
            }

            viewHolderGoiMon.AddButton.setOnClickListener(v -> {
                cart.addItem(monAnDTO); // Add item to Cart
                notifyDataSetChanged(); // Notify adapter about data change
            });
            viewHolderGoiMon.a.setTag(position);
            viewHolderGoiMon.a.setOnClickListener(this);
            return view;
        }

        private void HienThiButton(AdapterHienThiGoiMon.ViewHolderGoiMon viewHolderGoiMon) {

            viewHolderGoiMon.AddButton.setVisibility(View.VISIBLE);

        }

        private void AnButton(AdapterHienThiGoiMon.ViewHolderGoiMon viewHolderGoiMon) {

            viewHolderGoiMon.AddButton.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onClick(View v) {
            AdapterHienThiGoiMon.ViewHolderGoiMon viewHolderGoiMon = (AdapterHienThiGoiMon.ViewHolderGoiMon) ((View) v.getParent()).getTag();
            if (v.getId() == R.id.a) {
                String tenmon = viewHolderGoiMon.txtTenMonAn.getText().toString();
                String giatien = viewHolderGoiMon.txtGiaTien.getText().toString();
                int vitri = (int) v.getTag();
                monAnDTOList.get(vitri).setDuocChon(true);
                HienThiButton(viewHolderGoiMon);
            }

        }
    }

}