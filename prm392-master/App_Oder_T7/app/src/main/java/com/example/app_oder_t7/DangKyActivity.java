package com.example.app_oder_t7;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.app_oder_t7.DAO.NhanVienDAO;
import com.example.app_oder_t7.DTO.NhanVienDTO;
import com.example.app_oder_t7.FragmentAPP.DatePickerFragment;
import com.example.app_oder_t7.R;


public class DangKyActivity extends AppCompatActivity implements OnClickListener, OnFocusChangeListener {

    EditText edTenDangNhapDk, edMatKhau, edNgaySinh, edCMND;
    Button btnDongY, btnThoat;
    RadioGroup rgGioiTinh;
    RadioButton rdNam, rdNu;

    String sGioiTinh;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        edTenDangNhapDk = findViewById(R.id.edTenDangNhapDK);
        edMatKhau = findViewById(R.id.edMatKhauDK);
        edNgaySinh = findViewById(R.id.edNgaySinhDK);
        edCMND = findViewById(R.id.edCMNDDK);
        btnDongY = findViewById(R.id.btnDongYDK);
        btnThoat = findViewById(R.id.btnThoatDK);

        rgGioiTinh = findViewById(R.id.rgGioiTinh);
        rdNam = findViewById(R.id.rdNam);
        rdNu = findViewById(R.id.rdNu);

        btnDongY.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
        edNgaySinh.setOnFocusChangeListener(this);

        nhanVienDAO = new NhanVienDAO(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDongYDK) {
            DongYThemNhanVien();
        } else if (v.getId() == R.id.btnThoatDK) {
            finish();
        }
    }

    private void DongYThemNhanVien() {
        String sTenDangNhap = edTenDangNhapDk.getText().toString().trim();
        String sMatKhau = edMatKhau.getText().toString().trim();
        String sNgaySinh = edNgaySinh.getText().toString().trim();
        String sCMNDStr = edCMND.getText().toString().trim();

        if (sTenDangNhap.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
            return;
        }

        if (sMatKhau.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        if (sCMNDStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập CMND", Toast.LENGTH_SHORT).show();
            return;
        }

        int sCMND = Integer.parseInt(sCMNDStr);

        // Determine gender
        sGioiTinh = rdNam.isChecked() ? "Nam" : "Nữ";

        NhanVienDTO nhanVienDTO = new NhanVienDTO();
        nhanVienDTO.setTENDN(sTenDangNhap);
        nhanVienDTO.setMATKHAU(sMatKhau);
        nhanVienDTO.setGIOITINH(sGioiTinh);
        nhanVienDTO.setNGAYSINH(sNgaySinh);
        nhanVienDTO.setCMND(sCMND);

        long kiemtra = nhanVienDAO.ThemNhanVien(nhanVienDTO);
        if (kiemtra != 0) {
            Toast.makeText(this, "Thêm thành Công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.edNgaySinhDK && hasFocus) {
            // Show date picker
            DatePickerFragment datePickerFragment = new DatePickerFragment();
            datePickerFragment.show(getSupportFragmentManager(), "Ngày Sinh");
        }
    }
}
