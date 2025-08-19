package com.example.app_oder_t7.FragmentAPP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_oder_t7.CustomAdapter.AdapterHienThiBanAn;
import com.example.app_oder_t7.DAO.BanAnDAO;
import com.example.app_oder_t7.DTO.BanAnDTO;
import com.example.app_oder_t7.R;
import com.example.app_oder_t7.ThemBanAnActivity;
import com.example.app_oder_t7.TrangChuActivity;

import java.util.List;

public class HienThiBanAnFragment extends Fragment {

  public static final int REQUEST_CODE_THEM = 111;
  GridView gvHienThiBanAn;
  List<BanAnDTO> banAnDTOList;
  BanAnDAO banAnDAO;
  AdapterHienThiBanAn adapterHienThiBanAn;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.layout_hienthibanan, container, false);
    setHasOptionsMenu(true);
    ((TrangChuActivity) requireActivity()).getSupportActionBar().setTitle(R.string.banan);
    gvHienThiBanAn = view.findViewById(R.id.gvHienBanAn);

    banAnDAO = new BanAnDAO(requireActivity());
    banAnDTOList = banAnDAO.LayTatCaBanAn();

    HienThiDanhSachBanAn();
    return view;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    MenuItem itThemBanAn = menu.add(1, R.id.itThemBanAn, 1, R.string.thembanan);
    itThemBanAn.setIcon(R.drawable.thembanan);
    itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.itThemBanAn) {
      Intent iThemBanAn = new Intent(requireActivity(), ThemBanAnActivity.class);
      startActivityForResult(iThemBanAn, REQUEST_CODE_THEM);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_CODE_THEM && resultCode == Activity.RESULT_OK && data != null) {
      boolean kiemtra = data.getBooleanExtra("ketquathem", false);
      if (kiemtra) {
        HienThiDanhSachBanAn();
        Toast.makeText(requireActivity(), getResources().getString(R.string.themthanhcong),
                Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(requireActivity(), getResources().getString(R.string.themthatbai),
                Toast.LENGTH_SHORT).show();
      }
    }
  }

  private void HienThiDanhSachBanAn() {
    banAnDTOList = banAnDAO.LayTatCaBanAn();
    adapterHienThiBanAn = new AdapterHienThiBanAn(requireActivity(),
            R.layout.custom_layout_hienthibanan, banAnDTOList);
    gvHienThiBanAn.setAdapter(adapterHienThiBanAn);
    adapterHienThiBanAn.notifyDataSetChanged();
  }
}
