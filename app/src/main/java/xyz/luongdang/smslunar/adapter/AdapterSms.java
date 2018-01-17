package xyz.luongdang.smslunar.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xyz.luongdang.smslunar.R;
import xyz.luongdang.smslunar.object.ObjSms;


public class AdapterSms extends
		com.nhaarman.listviewanimations.ArrayAdapter<ObjSms> {
	public Activity context = null;
	public int layout;
	public ArrayList<ObjSms> myList = null;

	public AdapterSms(Activity context, int layout,
			ArrayList<ObjSms> myList) {
		super(myList);
		this.context = context;
		this.layout = layout;
		this.myList = myList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(layout, null);
		TextView tvChiTietTinNhan = (TextView) convertView
				.findViewById(R.id.tvChiTietTinNhan);
		tvChiTietTinNhan.setText(myList.get(position).getNoidung());
		return convertView;
	}

}
