package com.pmirkelam.wowchars.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pmirkelam.wowchars.Char;
import com.pmirkelam.wowchars.CharRepository;

import java.util.List;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private MutableLiveData<List<Char>> charMutableLiveData;
    private CharRepository charRepository;

    public SearchViewModel() {
        charRepository = new CharRepository();
        charMutableLiveData = charRepository.getSearchedCharsMutableLiveData();
       // charRepository.getInceptorSearchedCharsMutableLiveData();

        mText = new MutableLiveData<>();
        mText.setValue("This is search fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Char>> searchChar() {
        return charMutableLiveData;
    }
}