package com.example.app_oder_t7;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_oder_t7.FragmentAPP.HienThiBanAnFragment;
import com.example.app_oder_t7.FragmentAPP.HienThiThucDonFragment;
import com.google.android.material.navigation.NavigationView;

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  DrawerLayout drawerLayout;
  NavigationView navigationView;
  androidx.appcompat.widget.Toolbar toolbar;
  TextView txtTenNhanVien_Navigation;
  FragmentManager fragmentManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_trangchu);
    drawerLayout = findViewById(R.id.drawerLayout);
    navigationView = findViewById(R.id.navigationview_trangchu);
    toolbar = findViewById(R.id.toolbar);

    View view = navigationView.inflateHeaderView(R.layout.layout_header_navigation_trangchu);
    txtTenNhanVien_Navigation = view.findViewById(R.id.txtTenNhanVien_Navigation);

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.mo, R.string.dong) {
      @Override
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
      }

      @Override
      public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
      }
    };
    drawerLayout.addDrawerListener(drawerToggle);
    drawerToggle.syncState();

    navigationView.setItemIconTintList(null);
    navigationView.setNavigationItemSelectedListener(this);

    Intent intent = getIntent();
    String tendn = intent.getStringExtra("tendn");
    Log.d("dulieu", tendn);
    txtTenNhanVien_Navigation.setText(tendn);
    fragmentManager = getSupportFragmentManager();
    FragmentTransaction tranHienThiBanAn = fragmentManager.beginTransaction();
    HienThiBanAnFragment hienThiBanAnFragment = new HienThiBanAnFragment();
    tranHienThiBanAn.replace(R.id.content, hienThiBanAnFragment);
    tranHienThiBanAn.commit();
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.itTrangChu) {
      FragmentTransaction tranHienThiBanAn = fragmentManager.beginTransaction();
      HienThiBanAnFragment hienThiBanAnFragment = new HienThiBanAnFragment();
      tranHienThiBanAn.replace(R.id.content, hienThiBanAnFragment);
      tranHienThiBanAn.commit();
      item.setChecked(true);
      drawerLayout.closeDrawers();
    } else if (id == R.id.itThucDon) {
      FragmentTransaction tranHienThiThucDon = fragmentManager.beginTransaction();
      HienThiThucDonFragment hienThiThucDonFragment = new HienThiThucDonFragment();
      tranHienThiThucDon.replace(R.id.content, hienThiThucDonFragment);
      tranHienThiThucDon.commit();
      item.setChecked(true);
      drawerLayout.closeDrawers();
    }

    return false;
  }
}
