package com.codem.smstemplate.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codem.smstemplate.R;

/**
 * Created by Luongdt on 1/18/2018.
 */

public class PhongtucFragment extends Fragment{
    public PhongtucFragment() {
        // Required empty public constructor
    }

    public static PhongtucFragment newInstance() {
        PhongtucFragment fragment = new PhongtucFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phongtuc, container, false);
        return view;
    }
}
