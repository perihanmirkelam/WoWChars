package com.pmirkelam.wowchars.ui.filter;

import android.content.Context;
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
import com.pmirkelam.wowchars.R;

import java.util.List;

public class FilterViewModel extends ViewModel {

    private static MutableLiveData<List<Char>> charsMutableLiveData;
    private static CharRepository charRepository;

    public FilterViewModel() {
        charRepository = CharRepository.getInstance();
        charsMutableLiveData = charRepository.getFilteredCharsMutableLiveData("Druid");
    }

    public LiveData<List<Char>> filteredChars() {
        return charsMutableLiveData;
    }

    @BindingAdapter("onItemSelect")
    public static void onItemSelected(final Spinner spinner, int i){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("FilterViewModel", "Spinner onItemSelected : " + spinner.getSelectedItem().toString());
                charsMutableLiveData = charRepository.getFilteredCharsMutableLiveData(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("FilterViewModel", "onNothingSelected : ");
            }
        });
    }

    public void setSelectedChar(Char selectedChar){
        charRepository.setSelectedCharMutableLiveData(selectedChar);
    }
}