package com.pmirkelam.wowchars.ui.detail;

import androidx.lifecycle.ViewModel;

import com.pmirkelam.wowchars.Char;

public class DetailViewModel extends ViewModel {

    private Char wChar;
    private static DetailViewModel detailViewModel;

    public static DetailViewModel getInstance(){
        if(detailViewModel == null){
            detailViewModel = new DetailViewModel();
        }
        return detailViewModel;
    }

    public void setChar(Char wChar){
       this.wChar = wChar;
    }

    public void onCloseDetailScreen(){

    }


}
