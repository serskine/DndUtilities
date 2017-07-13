package com.soupthatisthick.util.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 7/6/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class StubFragment extends AppFragment {
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_stub, container);
        textView = (TextView) view.findViewById(R.id.theMessage);
        return view;
    }
}
