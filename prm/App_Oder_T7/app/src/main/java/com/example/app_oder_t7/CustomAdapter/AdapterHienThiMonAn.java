package com.example.app_oder_t7.CustomAdapter;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.app_oder_t7.DTO.BanAnDTO;
import com.example.app_oder_t7.DTO.MonAnDTO;
import com.example.app_oder_t7.R;
import com.example.app_oder_t7.ThemLoaiThucDonActivity;
import com.example.app_oder_t7.TrangChuActivity;

import java.util.List;

public class AdapterHienThiMonAn extends BaseAdapter implements View.OnClickListener {

    Context context;
    int layout;
    List<MonAnDTO> monAnDTOList;
    public AdapterHienThiMonAn(Context context, int layout, List<MonAnDTO> monAnDTOList) {
        this.context = context;
        this.layout = layout;
        this.monAnDTOList = monAnDTOList;
    }
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
    public class ViewHolderMonAn {
        ImageView imMonAn;
        Button AddButton;
        TextView txtTenMonAn;
        TextView txtGiaTien;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        AdapterHienThiMonAn.ViewHolderMonAn viewHolderMonAn;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderMonAn = new AdapterHienThiMonAn.ViewHolderMonAn();
            view = inflater.inflate(R.layout.custom_layout_hienthimonan, parent, false);
            //viewHolderMonAn.AddButton = view.findViewById(R.id.add_to_cart_button);
            viewHolderMonAn.imMonAn = view.findViewById(R.id.imMonan);
            viewHolderMonAn.txtTenMonAn = view.findViewById(R.id.txtTenMonAn);
            viewHolderMonAn.txtGiaTien = view.findViewById(R.id.txtGiaTien);

            view.setTag(viewHolderMonAn);
        } else {
            viewHolderMonAn = (AdapterHienThiMonAn.ViewHolderMonAn) view.getTag();
        }

        MonAnDTO monAnDTO = monAnDTOList.get(position);
        viewHolderMonAn.txtTenMonAn.setText(monAnDTO.getTenMonAn());
        viewHolderMonAn.txtGiaTien.setText(monAnDTO.getGiaTien());

       /* if (monAnDTO.isDuocChon()) {
            HienThiButton(viewHolderMonAn);
        } else {
            AnButton(viewHolderMonAn);
        }*/

        viewHolderMonAn.imMonAn.setTag(position);
        viewHolderMonAn.imMonAn.setOnClickListener(this);
        return view;
    }

   /* private void HienThiButton(AdapterHienThiMonAn.ViewHolderMonAn viewHolderMonAn) {

        viewHolderMonAn.AddButton.setVisibility(View.VISIBLE);

    }

    private void AnButton(AdapterHienThiMonAn.ViewHolderMonAn viewHolderMonAn) {

        viewHolderMonAn.AddButton.setVisibility(View.INVISIBLE);
    }*/

    @Override
    public void onClick(View v) {
        AdapterHienThiMonAn.ViewHolderMonAn viewHolderMonAn = (AdapterHienThiMonAn.ViewHolderMonAn) ((View) v.getParent()).getTag();
        if (v.getId() == R.id.imMonan) {
            String tenmon = viewHolderMonAn.txtTenMonAn.getText().toString();
            String giatien = viewHolderMonAn.txtGiaTien.getText().toString();
            int vitri = (int) v.getTag();
            monAnDTOList.get(vitri).setDuocChon(true);
           //HienThiButton(viewHolderMonAn);
        }

    }
}
