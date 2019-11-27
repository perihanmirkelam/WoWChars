package com.pmirkelam.wowchars.ui.search;

import android.text.TextUtils;
import android.widget.SearchView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pmirkelam.wowchars.Char;
import com.pmirkelam.wowchars.CharRepository;

import java.util.List;

import static com.pmirkelam.wowchars.Constants.DEFAULT_NAME_TO_SEARCH;

public class SearchViewModel extends ViewModel {

    private static MutableLiveData<List<Char>> charMutableLiveData;
    private static CharRepository charRepository;

    public SearchViewModel() {
        charRepository = CharRepository.getInstance();
        charMutableLiveData = charRepository.getCharList();
        // Only search with "saron" name when the application is first opened
        if(charMutableLiveData.getValue() == null){
            charRepository.searchCharByName(DEFAULT_NAME_TO_SEARCH);
        }
    }

    public LiveData<List<Char>> getSearchedChars() {
        return charMutableLiveData;
    }

    @BindingAdapter("onTextChanged")
    public static void onTextChanged(SearchView sw, int i){
        sw.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!TextUtils.isEmpty(query) && query.length() > 2){
                    charRepository.searchCharByName(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText) && newText.length() > 2){
                    charRepository.searchCharByName(newText);
                }
                return false;
            }
        });
    }

    public void setClickedChar(Char clickedChar){
        charRepository.setClickedCharMutableLiveData(clickedChar);
    }

}