package com.codem.smstemplate.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codem.smstemplate.R;
import com.codem.smstemplate.activity.MainPageActivity;
import com.codem.smstemplate.object.ObjSms;

import java.util.ArrayList;

/**
 * Created by Luongdt on 1/18/2018.
 */

public class HomeFragment extends Fragment{
    private ArrayList<ObjSms> listSMS = new ArrayList<>();
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listSMS = ((MainPageActivity)getActivity()).db.loadDuLieuSuKien("NAMMOI");
        return view;
    }


}
