package com.example.app_oder_t7.FragmentAPP;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.widget.DatePicker;
import android.widget.EditText;
import com.example.app_oder_t7.R;
import java.util.Calendar;

/**
 * Created by spaba on 6/12/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Calendar calendar = Calendar.getInstance();
    int iNam = calendar.get(Calendar.YEAR);
    int iThang = calendar.get(Calendar.MONTH);
    int iNgay = calendar.get(Calendar.DAY_OF_MONTH);

    return new DatePickerDialog(getActivity(),this,iNgay,iThang,iNam);
  }

  @Override
  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
    EditText edNgaySinh = (EditText) getActivity().findViewById(R.id.edNgaySinhDK);
    String sNgaySinh = dayOfMonth + "/" + (month+1) + "/" + year;
    edNgaySinh.setText(sNgaySinh);

  }
}
