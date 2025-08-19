package com.example.app_oder_t7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_oder_t7.DAO.NhanVienDAO;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDongYDN, btnDangKyDN;
    EditText edTenDangNhapDN, edMatKhauDN;

    NhanVienDAO nhanVienDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        btnDangKyDN = findViewById(R.id.btnDangKyDN);
        btnDongYDN = findViewById(R.id.btnDongYDN);
        edMatKhauDN = findViewById(R.id.edMatKhauDN);
        edTenDangNhapDN = findViewById(R.id.edTenDangNhapDN);

        nhanVienDAO = new NhanVienDAO(this);
        btnDongYDN.setOnClickListener(this);
        btnDangKyDN.setOnClickListener(this);
        //HienThiButtonDangKyVSDongY();
    }

    /*private void HienThiButtonDangKyVSDongY() {
        boolean kiemtra = nhanVienDAO.KiemTraNhanVien();
        btnDangKyDN.setVisibility(kiemtra ? View.GONE : View.VISIBLE);
        btnDongYDN.setVisibility(kiemtra ? View.VISIBLE : View.GONE);
    }*/

    private void btnDongY() {
        String stendangnhap = edTenDangNhapDN.getText().toString();
        String smatkhau = edMatKhauDN.getText().toString();
        boolean kiemtra = nhanVienDAO.KiemTraDangNhap(stendangnhap, smatkhau);
        if (kiemtra) {
            Intent iTrangChu = new Intent(DangNhapActivity.this, TrangChuActivity.class);
            iTrangChu.putExtra("tendn", stendangnhap);
            startActivity(iTrangChu);
        } else {
            Toast.makeText(this, "Đăng Nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void btnDangKy() {
        Intent iDangKy = new Intent(DangNhapActivity.this, DangKyActivity.class);
        startActivity(iDangKy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //HienThiButtonDangKyVSDongY();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDongYDN) {
            btnDongY();
        } else if (v.getId() == R.id.btnDangKyDN) {
            btnDangKy();
        }
    }
}
