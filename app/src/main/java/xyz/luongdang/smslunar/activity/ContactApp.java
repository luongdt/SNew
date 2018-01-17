package xyz.luongdang.smslunar.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;



import xyz.luongdang.smslunar.R;
import xyz.luongdang.smslunar.adapter.AdapterContact;
import xyz.luongdang.smslunar.object.ObjContact;

public class ContactApp extends AppCompatActivity {
	ListView lvDanhBa;
	ArrayList<ObjContact> listDanhBa = new ArrayList<ObjContact>();
	AdapterContact adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_danh_ba);

		lvDanhBa = (ListView) findViewById(R.id.lvDanhba);
		loadDanhBa();
		lvDanhBa.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub

				ObjContact contact;
				contact = (ObjContact) lvDanhBa.getItemAtPosition(pos);
				String phone = contact.getPhone();
				Intent iDanhBa = new Intent();
				iDanhBa.putExtra("PHONE", phone + "");
				setResult(1, iDanhBa);
				finish();

			}
		});
	}

	public void loadDanhBa() {

		Cursor contact = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
				ContactsContract.Contacts.HAS_PHONE_NUMBER + " = 1", null,
				"UPPER(" + ContactsContract.Contacts.DISPLAY_NAME + ") ASC");
		while (contact.moveToNext()) {
			String name = contact
					.getString(contact
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			String phone = contact
					.getString(contact
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			ObjContact con = new ObjContact(name, phone);
			listDanhBa.add(con);
		}
		contact.close();
		adapter = new AdapterContact(this, R.layout.contactapp, listDanhBa);
		lvDanhBa.setAdapter(adapter);

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent iDanhBa = new Intent();
		iDanhBa.putExtra("PHONE", "");
		setResult(1, iDanhBa);
		finish();
	}
}
