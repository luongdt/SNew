package com.codem.smstemplate.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.codem.smstemplate.R;
import com.codem.smstemplate.data.Database;
import com.codem.smstemplate.fragment.CaudoiFragment;
import com.codem.smstemplate.fragment.HomeFragment;
import com.codem.smstemplate.fragment.PhongtucFragment;
import com.codem.smstemplate.fragment.SMSKuteFragment;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.lang.reflect.Field;

public class MainPageActivity extends BaseActivity {
    public Database db;

    private FirebaseAnalytics mFirebaseAnalytics;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    makeFragmentTransaction(R.id.content, HomeFragment.newInstance());
                    return true;
                case R.id.navigation_dashboard:
                    makeFragmentTransaction(R.id.content, CaudoiFragment.newInstance());
                    return true;
                case R.id.navigation_notifications:
                    makeFragmentTransaction(R.id.content, SMSKuteFragment.newInstance());
                    return true;
                case R.id.navigation_phongtuc:
                    makeFragmentTransaction(R.id.content, PhongtucFragment.newInstance());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//        Bundle bundle = new Bundle();
//        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
//        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
//        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
//        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        makeFragmentTransaction(R.id.content, new HomeFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
       // checkAndCreateDatabase();

         db = new Database(this);
    }

    // Method for disabling ShiftMode of BottomNavigationView
    @SuppressLint("RestrictedApi")
    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    //	// Kiem tra v√† copy database
//    public void checkAndCreateDatabase() {
//        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
//        boolean firstUse = sharedPreferences.getBoolean("firstUse", true);
//        if (firstUse) {
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean("firstUse", true);
//            editor.commit();
//            saveDataBase();
//
//        } else {
//       //     loadDuLieu();
//        }
//    }

    @Override
    public void onBackPressed() {
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

//    private void copyDatabase(Context context) throws IOException {
//        String pName = this.getClass().getPackage().getName();
//        String folder = "/data/data/" + pName + "/databases/";
//        File CheckDirectory;
//        CheckDirectory = new File(folder);
//        if (!CheckDirectory.exists()) {
//            CheckDirectory.mkdir();
//        }
//        File file = new File(folder, "database.db");
//        if (!file.exists()) {
//            Log.w("dsads", " not exist");
//            try {
//                file.createNewFile();
//            } catch (IOException e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//            }
//        } else {
//            Log.w("dsads", "exist");
//            file.delete();
//            file.createNewFile();
//        }
//        OutputStream databaseOutputStream = new FileOutputStream(folder
//                + "database.db");
//        InputStream databaseInputStream;
//
//        byte[] buffer = new byte[1024];
//        int length;
//
//        databaseInputStream = context.getResources().openRawResource(
//                R.raw.database);
//        while ((length = databaseInputStream.read(buffer)) > 0) {
//            databaseOutputStream.write(buffer);
//        }
//        databaseInputStream.close();
//
//        databaseInputStream.close();
//        databaseOutputStream.flush();
//        databaseOutputStream.close();
//    }
//
//    public void saveDataBase() {
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading....");
//        progressDialog.show();
//        final Handler handler = new Handler() {
//
//            @Override
//            public void handleMessage(Message msg) {
//                // TODO Auto-generated method stub
//                super.handleMessage(msg);
//                progressDialog.cancel();
//              //  loadDuLieu();
//            }
//        };
//        new Thread() {
//            public void run() {
//                try {
//                    copyDatabase(getApplicationContext());
//
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                handler.sendEmptyMessage(0);
//            }
//        }.start();
//
//    }

//    	public void loadDuLieu() {
//		String pName = this.getClass().getPackage().getName();
//		String folder = "/data/data/" + pName + "/databases/";
//		String dbPath = folder + "database.db";
//		db = SQLiteDatabase.openDatabase(dbPath, null,
//				SQLiteDatabase.OPEN_READWRITE);
//		String sql = "SELECT * FROM TINNHAN ORDER BY ID DESC";
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
//		adapter = new AdapterSms(this, R.layout.layout_list_tinnhan,
//				listTinNhan);
//
//		lvTinNhan.setAdapter(adapter);
//
//	}

}
