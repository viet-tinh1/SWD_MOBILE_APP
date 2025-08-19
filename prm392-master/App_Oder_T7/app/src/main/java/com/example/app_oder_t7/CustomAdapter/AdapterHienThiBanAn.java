package com.example.app_oder_t7.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.app_oder_t7.DTO.BanAnDTO;
import com.example.app_oder_t7.OrderActivity;
import com.example.app_oder_t7.R;
import com.example.app_oder_t7.ThemLoaiThucDonActivity;

import java.util.List;

/**
 * Created by spaba on 6/13/2017.
 */

public class AdapterHienThiBanAn extends BaseAdapter implements OnClickListener {

  Context context;
  int layout;
  List<BanAnDTO> banAnDTOList;

  public AdapterHienThiBanAn(Context context, int layout, List<BanAnDTO> banAnDTOList) {
    this.context = context;
    this.layout = layout;
    this.banAnDTOList = banAnDTOList;
  }

  @Override
  public int getCount() {
    return banAnDTOList.size();
  }

  @Override
  public Object getItem(int position) {
    return banAnDTOList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return banAnDTOList.get(position).getMaBan();
  }

  public class ViewHolderBanAn {
    ImageView imBanAn, imGoiMon, imThanhToan, imAnButton;
    TextView txtTenBanAn;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = convertView;
    ViewHolderBanAn viewHolderBanAn;
    if (view == null) {
      LayoutInflater inflater = (LayoutInflater) context
              .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      viewHolderBanAn = new ViewHolderBanAn();
      view = inflater.inflate(R.layout.custom_layout_hienthibanan, parent, false);
      viewHolderBanAn.imBanAn = view.findViewById(R.id.imBanAn);
      viewHolderBanAn.imGoiMon = view.findViewById(R.id.imGoiMon);
      viewHolderBanAn.imThanhToan = view.findViewById(R.id.imThanhToan);
      viewHolderBanAn.imAnButton = view.findViewById(R.id.imAnButton);
      viewHolderBanAn.txtTenBanAn = view.findViewById(R.id.txtTenBanAn);
      view.setTag(viewHolderBanAn);
    } else {
      viewHolderBanAn = (ViewHolderBanAn) view.getTag();
    }

    BanAnDTO banAnDTO = banAnDTOList.get(position);
    viewHolderBanAn.txtTenBanAn.setText(banAnDTO.getTenBan());

    if (banAnDTO.isDuocChon()) {
      HienThiButton(viewHolderBanAn);
    } else {
      AnButton(viewHolderBanAn);
    }
    viewHolderBanAn.imGoiMon.setOnClickListener(v -> {
      Intent intent = new Intent(context, OrderActivity.class);
      context.startActivity(intent);
    });
    viewHolderBanAn.imBanAn.setTag(position);
    viewHolderBanAn.imBanAn.setOnClickListener(this);
    return view;
  }

  private void HienThiButton(ViewHolderBanAn viewHolderBanAn) {
    viewHolderBanAn.imGoiMon.setVisibility(View.VISIBLE);
    viewHolderBanAn.imThanhToan.setVisibility(View.VISIBLE);
    viewHolderBanAn.imAnButton.setVisibility(View.VISIBLE);
  }

  private void AnButton(ViewHolderBanAn viewHolderBanAn) {
    viewHolderBanAn.imGoiMon.setVisibility(View.INVISIBLE);
    viewHolderBanAn.imThanhToan.setVisibility(View.INVISIBLE);
    viewHolderBanAn.imAnButton.setVisibility(View.INVISIBLE);
  }

  @Override
  public void onClick(View v) {
    ViewHolderBanAn viewHolderBanAn = (ViewHolderBanAn) ((View) v.getParent()).getTag();
    if (v.getId() == R.id.imBanAn) {
      String tenban = viewHolderBanAn.txtTenBanAn.getText().toString();
      int vitri = (int) v.getTag();
      banAnDTOList.get(vitri).setDuocChon(true);
      HienThiButton(viewHolderBanAn);
    }
  }
}
