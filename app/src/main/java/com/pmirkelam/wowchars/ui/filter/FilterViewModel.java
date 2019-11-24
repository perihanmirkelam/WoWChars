package com.pmirkelam.wowchars.ui.filter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FilterViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FilterViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}