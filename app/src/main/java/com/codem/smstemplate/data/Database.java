package com.codem.smstemplate.data;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.codem.smstemplate.R;
import com.codem.smstemplate.object.ObjSms;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Database extends SQLiteOpenHelper{

	private Context context;

	public Database(Context context) {
		super(context, "data/data/truongtvd.tinnhancute/databases/database.db",null, 1);
		Log.d("DBManager", "DBManager: ");
		this.context = context;
	}

	public Database(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "data/data/truongtvd.tinnhancute/databases/database.db", factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		checkAndCreateDatabase();
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<ObjSms> loadDuLieuSuKien(String sukien) {
		SQLiteDatabase db = this.getWritableDatabase();
		String pName = this.getClass().getPackage().getName();
		String folder = "/data/data/" + pName + "/databases/";
		String dbPath = folder + "database.db";
		Log.v("duong dan:", dbPath);
		ArrayList<ObjSms> listTemp = new ArrayList<>();
		db = SQLiteDatabase.openDatabase(dbPath, null,
				SQLiteDatabase.OPEN_READWRITE);
		String sql = "SELECT * FROM TINNHAN WHERE LOAITINNHAN=" + "'" + sukien
				+ "'" + " ORDER BY ID DESC";
		Cursor cs = db.rawQuery(sql, null);
		while (cs.moveToNext()) {
			int id = cs.getInt(cs.getColumnIndexOrThrow("ID"));
			String noidung = cs.getString(cs.getColumnIndexOrThrow("NOIDUNG"));
			String loaitinnhan = cs.getString(cs
					.getColumnIndexOrThrow("LOAITINNHAN"));
			ObjSms tn = new ObjSms(id, noidung, loaitinnhan);
			listTemp.add(tn);
			//	listTinNhan.add(tn);
		}
		cs.close();
		db.close();
		return listTemp;

	}

	public void checkAndCreateDatabase() {
		SharedPreferences sharedPreferences = ((Activity)context).getPreferences(MODE_PRIVATE);
		boolean firstUse = sharedPreferences.getBoolean("firstUse", true);
		if (firstUse) {
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putBoolean("firstUse", true);
			editor.commit();
			saveDataBase();

		} else {
			//     loadDuLieu();
		}
	}
	private void copyDatabase(Context context) throws IOException {
		String pName = this.getClass().getPackage().getName();
		String folder = "/data/data/" + pName + "/databases/";
		File CheckDirectory;
		CheckDirectory = new File(folder);
		if (!CheckDirectory.exists()) {
			CheckDirectory.mkdir();
		}
		File file = new File(folder, "database.db");
		if (!file.exists()) {
			Log.w("dsads", " not exist");
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			Log.w("dsads", "exist");
			file.delete();
			file.createNewFile();
		}
		OutputStream databaseOutputStream = new FileOutputStream(folder
				+ "database.db");
		InputStream databaseInputStream;

		byte[] buffer = new byte[1024];
		int length;

		databaseInputStream = context.getResources().openRawResource(
				R.raw.database);
		while ((length = databaseInputStream.read(buffer)) > 0) {
			databaseOutputStream.write(buffer);
		}
		databaseInputStream.close();

		databaseInputStream.close();
		databaseOutputStream.flush();
		databaseOutputStream.close();
	}

	public void saveDataBase() {
		final ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setMessage("Loading....");
		progressDialog.show();
		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				progressDialog.cancel();
				//  loadDuLieu();
			}
		};
		new Thread() {
			public void run() {
				try {
					copyDatabase(context);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0);
			}
		}.start();

	}



}
