package com.pmirkelam.wowchars.ui.filter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pmirkelam.wowchars.R;

public class FilterFragment extends Fragment {

    private FilterViewModel filterViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        filterViewModel =
                ViewModelProviders.of(this).get(FilterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_filter, container, false);
       // final TextView textView = root.findViewById(R.id.text_filter);
        filterViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
           //     textView.setText(s);
            }
        });
        return root;
    }
}