package com.grammar.trocket.grammar.com.grammar.trocket.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grammar.trocket.grammar.R;


public class FragmentTabDictionary extends Fragment {

    /**
     * Inflate fragment tab 3
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_dictionary, container, false);
    }

}
