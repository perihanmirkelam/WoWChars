package com.pmirkelam.wowchars;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.UnsupportedEncodingException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.pmirkelam.wowchars.Constants.BASE_URL;

public class CharRepository {

    public static final String TAG = CharRepository.class.getSimpleName();
    private static CharRepository INSTANCE;
    private MutableLiveData<List<Char>> charListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Char> selectedCharMutableLiveData = new MutableLiveData<>();

    private CharRepository(){
    }

    public static CharRepository getInstance() {
        if(INSTANCE == null){
            INSTANCE = new CharRepository();
        }
        return INSTANCE;
    }

    public MutableLiveData<List<Char>> getSearchedCharsMutableLiveData(String name) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CharService service = retrofit.create(CharService.class);
        Call<List<Char>> call = service.getSearchedCharsByName(name);

        call.enqueue(new Callback<List<Char>>() {
            @Override
            public void onResponse(Call<List<Char>> call, Response<List<Char>> response) {
                if (response.body() != null) {
                    for (Char wChar : response.body()) {
                        Log.i(TAG, "CHAR: " + wChar.getName());
                    }
                } else {
                    Log.e(TAG, "Response is null");
                }
                List<Char> chars = response.body();
                charListMutableLiveData.setValue(chars);
            }

            @Override
            public void onFailure(Call<List<Char>> call, Throwable t) {
                Log.e(TAG, (t.getMessage() != null ? t.getMessage() : " onFailure"));
            }
        });
        return charListMutableLiveData;
    }

    public MutableLiveData<Char> getSelectedCharMutableLiveData() {
        return selectedCharMutableLiveData;
    }

    public void setSelectedCharMutableLiveData(final Char targetChar) {

        String name = targetChar.getCardId();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CharService service = retrofit.create(CharService.class);
        Call<List<Char>> call = service.getSelectedCharsByName(name);

        call.enqueue(new Callback<List<Char>>() {
            @Override
            public void onResponse(Call<List<Char>> call, Response<List<Char>> response) {
                if (response.body() != null) {
                    for (Char wChar : response.body()) {
                        if(targetChar.getName().equals(wChar.getName())){
                            selectedCharMutableLiveData.setValue(wChar);
                            Log.i(TAG, "Selected Char: " + wChar.getCardId());
                        }
                        Log.i(TAG, "CHAR: " + wChar.getName());
                    }
                } else {
                    Log.e(TAG, "Response is null URL:" + call.request().url());
                }
            }

            @Override
            public void onFailure(Call<List<Char>> call, Throwable t) {
                Log.e(TAG, (t.getMessage() != null ? t.getMessage() : " onFailure"));
            }
        });
    }

//    public void setSelectedCharMutableLiveData(final Char targetChar){
//        selectedCharMutableLiveData.setValue(targetChar);
//    }

}

