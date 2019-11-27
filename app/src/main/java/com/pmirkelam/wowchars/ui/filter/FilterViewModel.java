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

import static com.pmirkelam.wowchars.Constants.DEFAULT_NAME_TO_SEARCH;

public class FilterViewModel extends ViewModel {

    private MutableLiveData<List<Char>> charMutableLiveData;
    private MutableLiveData<Integer> classIndex;
    private CharRepository charRepository;

    public FilterViewModel() {
        charRepository = CharRepository.getInstance();
        charMutableLiveData = charRepository.getFilteredCharsMutableLiveData(DEFAULT_NAME_TO_SEARCH);
        classIndex = new MutableLiveData<>();
    }

    public LiveData<List<Char>> filteredChars() {
        return charMutableLiveData;
    }

    public MutableLiveData<Integer> getClassIndex() {
        return classIndex;
    }

    @BindingAdapter("onItemSelect")
    public void onItemSelected(Spinner spinner, int i){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("FilterViewModel", "onItemSelected : " + id);
                classIndex.setValue((int) id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("FilterViewModel", "onNothingSelected : ");
            }
        });

    }
            //

    public void setSelectedChar(Char selectedChar){
        charRepository.setSelectedCharMutableLiveData(selectedChar);
    }
}