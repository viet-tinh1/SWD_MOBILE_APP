package com.example.app_oder_t7.FragmentAPP;

import static com.example.app_oder_t7.R.id.itThemThucDon;

import android.app.Activity;
import android.content.Context;
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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.app_oder_t7.CustomAdapter.AdapterHienThiMonAn;
import com.example.app_oder_t7.DAO.BanAnDAO;
import com.example.app_oder_t7.DAO.MonAnDAO;
import com.example.app_oder_t7.DTO.BanAnDTO;
import com.example.app_oder_t7.DTO.MonAnDTO;
import com.example.app_oder_t7.R;
import com.example.app_oder_t7.ThemThucDonActivity;
import com.example.app_oder_t7.TrangChuActivity;

import java.util.List;

/**
 * Created by spaba on 6/13/2017.
 */

public class HienThiThucDonFragment extends Fragment {

  public static int  REQUEST_CODE_THEM_THUCDON = 111;
  List<MonAnDTO> monAnDTOList;
  MonAnDAO monAnDAO;
  GridView gvHienThiThucDon;
  AdapterHienThiMonAn adapterHienThiMonAn;
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View  view = inflater.inflate(R.layout.layout_hienthithucdon,container,false);
    setHasOptionsMenu(true);
    ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.fragthucdon);
    gvHienThiThucDon = view.findViewById(R.id.gvHienThiThucDon);

    monAnDAO = new MonAnDAO(requireActivity());
    monAnDTOList = monAnDAO.LayTatCaMonAn();

    HienThiDanhSachMonAn();
    return view;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    MenuItem itThemThucDon = menu.add(1, R.id.itThemThucDon, 1, R.string.themthucdon);
    itThemThucDon.setIcon(R.drawable.logodangnhap);
//    itThemThucDon.setTitle("THỰC ĐƠN");
    itThemThucDon.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.itThemThucDon) {
      Intent iThemThucDon = new Intent(getActivity(), ThemThucDonActivity.class);
      startActivity(iThemThucDon);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void HienThiDanhSachMonAn() {
    monAnDTOList = monAnDAO.LayTatCaMonAn();
    adapterHienThiMonAn = new AdapterHienThiMonAn(requireActivity(),
            R.layout.custom_layout_hienthimonan, monAnDTOList);
    gvHienThiThucDon.setAdapter(adapterHienThiMonAn);
    adapterHienThiMonAn.notifyDataSetChanged();
  }


}

