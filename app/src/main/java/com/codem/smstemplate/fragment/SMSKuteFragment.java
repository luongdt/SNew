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

public class SMSKuteFragment extends Fragment{
    public SMSKuteFragment() {
        // Required empty public constructor
    }

    public static SMSKuteFragment newInstance() {
        SMSKuteFragment fragment = new SMSKuteFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smskute, container, false);
        return view;
    }
}
