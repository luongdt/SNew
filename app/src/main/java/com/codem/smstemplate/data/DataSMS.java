package com.codem.smstemplate.data;

/**
 * Created by luongdt on 1/18/18.
 */

public class DataSMS {
    //	public void loadDuLieuSuKien(String sukien) {
//		String pName = this.getClass().getPackage().getName();
//		String folder = "/data/data/" + pName + "/databases/";
//		String dbPath = folder + "database.db";
//		Log.v("duong dan:", dbPath);
//		db = SQLiteDatabase.openDatabase(dbPath, null,
//				SQLiteDatabase.OPEN_READWRITE);
//		String sql = "SELECT * FROM TINNHAN WHERE LOAITINNHAN=" + "'" + sukien
//				+ "'" + " ORDER BY ID DESC";
//		Cursor cs = db.rawQuery(sql, null);
//		while (cs.moveToNext()) {
//			int id = cs.getInt(cs.getColumnIndexOrThrow("ID"));
//			String noidung = cs.getString(cs.getColumnIndexOrThrow("NOIDUNG"));
//			String loaitinnhan = cs.getString(cs
//					.getColumnIndexOrThrow("LOAITINNHAN"));
//			ObjSms tn = new ObjSms(id, noidung, loaitinnhan);
//			listTinNhan.add(tn);
//		}
//		cs.close();
//		db.close();
//		lvTinNhan.setAdapter(new AdapterSms(this, R.layout.layout_list_tinnhan,
//				listTinNhan));
//
//	}
}
