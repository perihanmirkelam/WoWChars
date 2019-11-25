package com.pmirkelam.wowchars.ui.search;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pmirkelam.wowchars.Char;
import com.pmirkelam.wowchars.CharRepository;

import java.util.List;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<List<Char>> charMutableLiveData;
    private CharRepository charRepository;

    public SearchViewModel() {
        charRepository = new CharRepository();
        charMutableLiveData = charRepository.getSearchedCharsMutableLiveData("Saron");
    }

    public LiveData<List<Char>> searchChar() {
        return charMutableLiveData;
    }

    public void onTextChanged(CharSequence s, int start, int before, int count){
        if(!TextUtils.isEmpty(s) && count > 2){
            charMutableLiveData = charRepository.getSearchedCharsMutableLiveData(s.toString());
        }
    }


}