package xyz.luongdang.smslunar.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
//
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import xyz.luongdang.smslunar.R;
import xyz.luongdang.smslunar.adapter.AdapterMenu;
import xyz.luongdang.smslunar.adapter.AdapterSms;
import xyz.luongdang.smslunar.object.ObjSms;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends AppCompatActivity {
	public String[] arrTenDanhMuc_Left = { "Tất cả", "Năm mới", "Tình yêu",
			"Chúc 8/3", "20-11", "Noel", "Chúc ngủ ngon", "Chúc Sinh nhật" };

	ListView lvDanhMuc, lvTinNhan;
	public SQLiteDatabase db;
	public ArrayList<ObjSms> listTinNhan = new ArrayList<ObjSms>();
	TextView btnMenu;
	public AdapterSms adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lvTinNhan = (ListView) findViewById(R.id.lvTinNhan);
		lvDanhMuc = (ListView) findViewById(R.id.lvDanhMuc_Left);
		btnMenu = (TextView) findViewById(R.id.btnMenu);
		AdapterMenu adapterDanhMuc = new AdapterMenu(this,
				R.layout.layout_list_danhmuc, arrTenDanhMuc_Left);
		lvDanhMuc.setAdapter(adapterDanhMuc);
		checkAndCreateDatabase();
		// loadDuLieu();


		lvDanhMuc.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				String ten = arrTenDanhMuc_Left[pos];
				switch (pos) {
				case 0:
					listTinNhan.removeAll(listTinNhan);
					loadDuLieu();
					break;
				case 1:
					listTinNhan.removeAll(listTinNhan);
					loadDuLieuSuKien("NAMMOI");
					break;
				case 4:
					listTinNhan.removeAll(listTinNhan);
					loadDuLieuSuKien("NHAGIAO");
					break;
				case 5:
					listTinNhan.removeAll(listTinNhan);
					loadDuLieuSuKien("NOEL");
					break;
				case 2:
					listTinNhan.removeAll(listTinNhan);
					loadDuLieuSuKien("TINHYEU");
					break;
				case 6:
					listTinNhan.removeAll(listTinNhan);
					loadDuLieuSuKien("NGUNGON");
					break;
				case 7:
					listTinNhan.removeAll(listTinNhan);
					loadDuLieuSuKien("SINHNHAT");
					break;
				case 3:
					listTinNhan.removeAll(listTinNhan);
					loadDuLieuSuKien("PHUNU");
					break;
				}

			}
		});
		lvTinNhan.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				ObjSms tn;
				tn = (ObjSms) lvTinNhan.getItemAtPosition(pos);
				String noidung = tn.getNoidung();
				Intent i = new Intent(MainActivity.this, SendSms.class);
				i.putExtra("NOIDUNG", noidung);
				startActivity(i);

			}
		});
		lvTinNhan.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				// TODO Auto-generated method stub
				int sdk = Build.VERSION.SDK_INT;
				if (sdk < Build.VERSION_CODES.HONEYCOMB) {
					android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					ObjSms tn;
					tn = (ObjSms) lvTinNhan.getItemAtPosition(pos);
					String noidung = tn.getNoidung();
					clipboard.setText(noidung.toString());
					Toast.makeText(getApplication(), "Đã copy tin nhắn",
							Toast.LENGTH_LONG).show();
				} else {
					android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					ObjSms tn;
					tn = (ObjSms) lvTinNhan.getItemAtPosition(pos);
					String noidung = tn.getNoidung();
					android.content.ClipData clip = android.content.ClipData
							.newPlainText("text label", noidung.toString());
					clipboard.setPrimaryClip(clip);
					Toast.makeText(getApplication(), "Đa copy tin nhắn",
							Toast.LENGTH_LONG).show();
				}
				return false;
			}
		});

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

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
		final ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Loading....");
		progressDialog.show();
		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				progressDialog.cancel();
				loadDuLieu();
			}
		};
		new Thread() {
			public void run() {
				try {
					copyDatabase(getApplicationContext());

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0);
			}
		}.start();

	}

	public void loadDuLieu() {
		String pName = this.getClass().getPackage().getName();
		String folder = "/data/data/" + pName + "/databases/";
		String dbPath = folder + "database.db";
		db = SQLiteDatabase.openDatabase(dbPath, null,
				SQLiteDatabase.OPEN_READWRITE);
		String sql = "SELECT * FROM TINNHAN ORDER BY ID DESC";
		Cursor cs = db.rawQuery(sql, null);
		while (cs.moveToNext()) {
			int id = cs.getInt(cs.getColumnIndexOrThrow("ID"));
			String noidung = cs.getString(cs.getColumnIndexOrThrow("NOIDUNG"));
			String loaitinnhan = cs.getString(cs
					.getColumnIndexOrThrow("LOAITINNHAN"));
			ObjSms tn = new ObjSms(id, noidung, loaitinnhan);
			listTinNhan.add(tn);
		}
		cs.close();
		db.close();
		adapter = new AdapterSms(this, R.layout.layout_list_tinnhan,
				listTinNhan);

		lvTinNhan.setAdapter(adapter);

	}

	public void loadDuLieuSuKien(String sukien) {
		String pName = this.getClass().getPackage().getName();
		String folder = "/data/data/" + pName + "/databases/";
		String dbPath = folder + "database.db";
		Log.v("duong dan:", dbPath);
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
			listTinNhan.add(tn);
		}
		cs.close();
		db.close();
		lvTinNhan.setAdapter(new AdapterSms(this, R.layout.layout_list_tinnhan,
				listTinNhan));

	}

	// Kiem tra v√† copy database
	public void checkAndCreateDatabase() {
		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		boolean firstUse = sharedPreferences.getBoolean("firstUse", true);
		if (firstUse) {
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putBoolean("firstUse", true);
			editor.commit();
			saveDataBase();

		} else {
			loadDuLieu();
		}
	}

	@Override
	public void onBackPressed() {
		displayInterstitial();
		finish();

		super.onBackPressed();

	}

	@Override
	public void onStart() {
		super.onStart();

	}

	@Override
	public void onStop() {

		super.onStop();
	}

	// Invoke displayInterstitial() when you are ready to display an
	// interstitial.
	public void displayInterstitial() {
	}

}
