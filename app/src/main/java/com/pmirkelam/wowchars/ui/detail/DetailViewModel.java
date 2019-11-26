package com.pmirkelam.wowchars.ui.detail;

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

    public void onCloseDetailScreen(){
        isCloseDetailScreen.setValue(true);
    }

    public MutableLiveData<Boolean> isClosed(){
        return isCloseDetailScreen;
    }

    public MutableLiveData<Char> getSelectedChar(){
        return selectedChar;
    }
}
