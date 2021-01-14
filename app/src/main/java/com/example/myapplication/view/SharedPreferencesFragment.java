package com.example.myapplication.view;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.myapplication.R;


public class SharedPreferencesFragment extends PreferenceFragment {

    public SharedPreferencesFragment() {
        // Required empty public constructor
    }

    public static SharedPreferencesFragment newInstance() {
        SharedPreferencesFragment fragment = new SharedPreferencesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.shared_pref);
    }

}
