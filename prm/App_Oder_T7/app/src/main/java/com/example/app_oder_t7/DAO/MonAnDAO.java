package com.example.app_oder_t7.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_oder_t7.DTO.BanAnDTO;
import com.example.app_oder_t7.DTO.MonAnDTO;
import com.example.app_oder_t7.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spaba on 6/15/2017.
 */

public class MonAnDAO {

  SQLiteDatabase database;

  public MonAnDAO(Context context) {
    CreateDatabase createDatabase = new CreateDatabase(context);
    database = createDatabase.open();

  }

  public boolean ThemMonAn(MonAnDTO monAnDTO) {
    ContentValues contentValues = new ContentValues();
    contentValues.put(CreateDatabase.TB_MONAN_TENMONAN, monAnDTO.getTenMonAn());
    contentValues.put(CreateDatabase.TB_MONAN_GIATIEN, monAnDTO.getGiaTien());
    contentValues.put(CreateDatabase.TB_MONAN_MALOAI, monAnDTO.getMaLoai());
    contentValues.put(CreateDatabase.TB_MONAN_HINHANH, monAnDTO.getHinhAnh());

    long kiemtra = database.insert(CreateDatabase.TB_MONAN, null, contentValues);
    if (kiemtra != 0) {
      return true;
    } else {
      return false;
    }
  }
  public List<MonAnDTO> LayTatCaMonAn(){
    List<MonAnDTO> monAnDTOList = new ArrayList<MonAnDTO>();
    String truyvan = "SELECT * FROM " + CreateDatabase.TB_MONAN;
    Cursor cursor = database.rawQuery(truyvan,null);
    cursor.moveToFirst();
    while(!cursor.isAfterLast()){
      MonAnDTO monAnDTO = new MonAnDTO();
      monAnDTO.setMaMonAn(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MAMON)));
      monAnDTO.setTenMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN)));

      monAnDTO.setGiaTien(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIATIEN)));

      monAnDTOList.add(monAnDTO);
      cursor.moveToNext();
    }
    return monAnDTOList;
}}
