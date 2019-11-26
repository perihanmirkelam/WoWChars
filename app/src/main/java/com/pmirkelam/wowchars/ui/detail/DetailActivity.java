package com.pmirkelam.wowchars.ui.detail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pmirkelam.wowchars.R;
import com.pmirkelam.wowchars.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    private DetailViewModel detailViewModel;
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        binding = DataBindingUtil.setContentView(DetailActivity.this, R.layout.activity_detail);
        binding.setLifecycleOwner(this);
        binding.setDetailViewModel(detailViewModel);

        detailViewModel.isClosed().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isClosed) {
                if(isClosed){
                    finish();
                }
            }
        });

    }
}
