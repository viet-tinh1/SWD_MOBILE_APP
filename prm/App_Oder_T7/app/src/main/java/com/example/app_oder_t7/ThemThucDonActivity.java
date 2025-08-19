package com.example.app_oder_t7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_oder_t7.CustomAdapter.AdapterHienThiLoaiMonAn;
import com.example.app_oder_t7.DAO.LoaiMonAnDAO;
import com.example.app_oder_t7.DAO.MonAnDAO;
import com.example.app_oder_t7.DTO.LoaiMonAnDTO;
import com.example.app_oder_t7.DTO.MonAnDTO;

import java.util.List;

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener {

  ImageButton imThemLoaiThucDon;
  Spinner spinLoaiThucDon;
  public static final int REQUEST_CODE_THEMLOAITHUCDON = 112;
  public static final int REQUEST_CODE_MOHINH = 122;
  LoaiMonAnDAO loaiMonAnDAO;
  List<LoaiMonAnDTO> loaiMonAnDTOs;
  AdapterHienThiLoaiMonAn adapterHienThiLoaiMonAn;
  ImageView imHinhThucDon;
  Button btnDongYThemMonAn, btnThoatThemMonAn;
  String sDuongDanHinh;
  EditText edTenMonAn, edGiaTien;
  MonAnDAO monAnDAO;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_themthucdon);

    loaiMonAnDAO = new LoaiMonAnDAO(this);
    monAnDAO = new MonAnDAO(this);
    imThemLoaiThucDon = findViewById(R.id.imThemLoaiThucDon);
    spinLoaiThucDon = findViewById(R.id.spinLoaiMonAn);
    imHinhThucDon = findViewById(R.id.imHinhThucDon);
    btnDongYThemMonAn = findViewById(R.id.btnDongYThemMonAn);
    btnThoatThemMonAn = findViewById(R.id.btnThoatThemMonAn);
    edTenMonAn = findViewById(R.id.edThemTenMonAn);
    edGiaTien = findViewById(R.id.edThemGiaTien);

    HienThiSpinnerLoaiMonAn();
    imThemLoaiThucDon.setOnClickListener(this);
    imHinhThucDon.setOnClickListener(this);
    btnDongYThemMonAn.setOnClickListener(this);
    btnThoatThemMonAn.setOnClickListener(this);
  }

  private void HienThiSpinnerLoaiMonAn() {
    loaiMonAnDTOs = loaiMonAnDAO.LayDanhSachLoaiMonAn();
    adapterHienThiLoaiMonAn = new AdapterHienThiLoaiMonAn(this,
            R.layout.custom_layout_spinloaithucdon, loaiMonAnDTOs);
    spinLoaiThucDon.setAdapter(adapterHienThiLoaiMonAn);
    adapterHienThiLoaiMonAn.notifyDataSetChanged();
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.imThemLoaiThucDon) {
      Intent iThemLoaiMonAn = new Intent(getApplicationContext(), ThemLoaiThucDonActivity.class);
      startActivityForResult(iThemLoaiMonAn, REQUEST_CODE_THEMLOAITHUCDON);
    } else if (v.getId() == R.id.imHinhThucDon) {
      Intent iMohinh = new Intent();
      iMohinh.setType("image/*");
      iMohinh.setAction(Intent.ACTION_GET_CONTENT);
      startActivityForResult(Intent.createChooser(iMohinh, "Chọn hình thực đơn "),
              REQUEST_CODE_MOHINH);
    } else if (v.getId() == R.id.btnDongYThemMonAn) {
      ThemMonAn();
    } else if (v.getId() == R.id.btnThoatThemMonAn) {
      finish();
    }
  }

  private void ThemMonAn() {
    int vitri = spinLoaiThucDon.getSelectedItemPosition();
    int maloai = loaiMonAnDTOs.get(vitri).getMaLoai();
    String tenmonan = edTenMonAn.getText().toString().trim();
    String giatien = edGiaTien.getText().toString().trim();
    if (!tenmonan.isEmpty() && !giatien.isEmpty()) {
      MonAnDTO monAnDTO = new MonAnDTO();
      monAnDTO.setMaLoai(maloai);
      monAnDTO.setGiaTien(giatien);
      monAnDTO.setHinhAnh(sDuongDanHinh);
      monAnDTO.setTenMonAn(tenmonan);
      boolean kiemtra = monAnDAO.ThemMonAn(monAnDTO);
      if (kiemtra) {
        Toast.makeText(this, getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT)
                .show();
      } else {
        Toast.makeText(this, getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT)
                .show();
      }
    } else {
      Toast.makeText(this, getResources().getString(R.string.loithemmonan), Toast.LENGTH_SHORT)
              .show();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == REQUEST_CODE_THEMLOAITHUCDON) {
      if (resultCode == Activity.RESULT_OK) {
        boolean kiemtra = data.getBooleanExtra("kiemtraloaithucdon", false);
        if (kiemtra) {
          HienThiSpinnerLoaiMonAn();
          Toast.makeText(this, getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT)
                  .show();
        } else {
          Toast.makeText(this, getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT)
                  .show();
        }
      }
    } else if (requestCode == REQUEST_CODE_MOHINH && resultCode == Activity.RESULT_OK && data != null) {
      sDuongDanHinh = data.getData().toString();
      imHinhThucDon.setImageURI(data.getData());
    }
  }
}
