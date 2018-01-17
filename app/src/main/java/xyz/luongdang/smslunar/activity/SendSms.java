package xyz.luongdang.smslunar.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import xyz.luongdang.smslunar.R;

public class SendSms extends AppCompatActivity {
	EditText edNoiDung, edSDT;
	Button btnDanhBa, btnGui;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gui_tin_nhan);
		edNoiDung = (EditText) findViewById(R.id.edNoiDung);
		edSDT = (EditText) findViewById(R.id.edSDT);
		btnDanhBa = (Button) findViewById(R.id.btnDanhBa);
		btnGui = (Button) findViewById(R.id.btnGui);
		kiemTraRong();
		edNoiDung.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				kiemTraRong();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		edSDT.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				kiemTraRong();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Intent i = getIntent();
		String noidung = i.getStringExtra("NOIDUNG");
		edNoiDung.setText(noidung);

	}

	public void btnDanhBa(View v) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			Toast.makeText(getApplication(),
					"Phiên bản Android của bạn thấp không sử dụng được",
					Toast.LENGTH_LONG).show();
			btnDanhBa.setEnabled(false);
		} else {
			Intent iDanhBa = new Intent(SendSms.this, ContactApp.class);
			startActivityForResult(iDanhBa, 1);
		}

	}

	public void kiemTraRong() {
		if (edNoiDung.getText().toString().equals("")) {
			btnGui.setEnabled(false);
		} else {
			btnGui.setEnabled(true);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		String phone = data.getStringExtra("PHONE");
		if (phone == null) {

		} else {
			edSDT.setText(phone.toString());
		}
	}

	public void btnGui(View v) {

		String phone = edSDT.getText().toString();
		String noidung = edNoiDung.getText().toString();
		Uri uri = Uri.parse("smsto:" + phone.toString());
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);
		it.putExtra("sms_body", noidung);
		startActivity(it);
		Toast.makeText(getApplicationContext(), "Send gửi tin nhắn",
				Toast.LENGTH_LONG).show();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	public void btnCopy(View v) {
		int sdk = Build.VERSION.SDK_INT;
		if (sdk < Build.VERSION_CODES.HONEYCOMB) {
			android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			clipboard.setText(edNoiDung.getText().toString());
			Toast.makeText(getApplication(), "Nội dung đã được copy",
					Toast.LENGTH_LONG).show();
		} else {
			android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			android.content.ClipData clip = android.content.ClipData
					.newPlainText("text label", edNoiDung.getText().toString());
			clipboard.setPrimaryClip(clip);
			Toast.makeText(getApplication(), "Nội dung đã được copy",
					Toast.LENGTH_LONG).show();
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();

	}

}
