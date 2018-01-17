package xyz.luongdang.smslunar.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xyz.luongdang.smslunar.R;
import xyz.luongdang.smslunar.object.ObjContact;


public class AdapterContact extends
		com.nhaarman.listviewanimations.ArrayAdapter<ObjContact> {
	public Activity context = null;
	public int layout;
	ArrayList<ObjContact> myList = null;

	public AdapterContact(Activity context, int layout,
			ArrayList<ObjContact> myList) {
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
		TextView tvTenDanhBa = (TextView) convertView
				.findViewById(R.id.tvTenDanhBa);
		TextView tvSDT = (TextView) convertView.findViewById(R.id.tvSDT);
		tvTenDanhBa.setText(myList.get(position).getName());
		tvSDT.setText(myList.get(position).getPhone());
		return convertView;
	}

}
