package com.pmirkelam.wowchars.ui.detail;

import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pmirkelam.wowchars.Char;
import com.pmirkelam.wowchars.CharRepository;

public class DetailViewModel extends ViewModel {

    private static MutableLiveData<Boolean> isCloseDetailScreen;
    private CharRepository charRepository;
    public MutableLiveData<Char> selectedChar;

    public DetailViewModel(){
       isCloseDetailScreen= new MutableLiveData<>(false);
       charRepository = CharRepository.getInstance();
       selectedChar = charRepository.getSelectedCharMutableLiveData();
    }

    public MutableLiveData<Boolean> isClosed(){
        return isCloseDetailScreen;
    }

    public MutableLiveData<Char> getSelectedChar(){
        return selectedChar;
    }

    @BindingAdapter("onNavigationBackClick")
    public static void onNavigationClicked(Toolbar toolbar, int b){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DetailViewModel", "DetailViewModel onClick");
                isCloseDetailScreen.setValue(true);
            }
        });
    }

}

