package com.pmirkelam.wowchars.ui.filter;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pmirkelam.wowchars.Char;
import com.pmirkelam.wowchars.CharRepository;

import java.util.List;

import static com.pmirkelam.wowchars.Constants.DEFAULT_NAME_TO_SEARCH;

public class FilterViewModel extends ViewModel {

    private MutableLiveData<List<Char>> charMutableLiveData;
    private MutableLiveData<String> filteredClass;
    private CharRepository charRepository;

    public FilterViewModel() {
        charRepository = CharRepository.getInstance();
        charMutableLiveData = charRepository.getFilteredCharsMutableLiveData(DEFAULT_NAME_TO_SEARCH);
        filteredClass = new MutableLiveData<>();
    }

    public LiveData<List<Char>> filteredChars() {
        return charMutableLiveData;
    }

    public MutableLiveData<String> getFilteredClass() {
        return filteredClass;
    }

    @BindingAdapter("onItemSelect")
    public void onItemSelected(Spinner spinner, int i){
        spinner.onClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("FilterViewModel", "onItemSelected : " + i);
                if(i!=0){
                    charMutableLiveData = charRepository.getSearchedCharsMutableLiveData(String.valueOf(i);
                }
            }
        });

    }

    public void setSelectedChar(Char selectedChar){
        charRepository.setSelectedCharMutableLiveData(selectedChar);
    }
}