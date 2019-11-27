package com.pmirkelam.wowchars.ui.filter;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pmirkelam.wowchars.Char;
import com.pmirkelam.wowchars.CharRepository;

import java.util.List;

public class FilterViewModel extends ViewModel {

    private static MutableLiveData<List<Char>> charListMutableLiveData;
    private static CharRepository charRepository;

    public FilterViewModel() {
        charRepository = CharRepository.getInstance();
        charListMutableLiveData = charRepository.getCharList();
    }

    public LiveData<List<Char>> filteredChars() {
        return charListMutableLiveData;
    }

    @BindingAdapter("onItemSelect")
    public static void onItemSelected(final Spinner spinner, int i){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                charRepository.searchCharsByClass(spinner.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void setSelectedChar(Char selectedChar){
        charRepository.setSelectedCharMutableLiveData(selectedChar);
    }
}