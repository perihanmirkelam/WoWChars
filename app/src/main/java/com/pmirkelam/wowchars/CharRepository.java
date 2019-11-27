package com.pmirkelam.wowchars;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

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
    private MutableLiveData<Char> selectedCharMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Char>> currentListMutableLiveData = new MutableLiveData<>();

    private CharRepository() {
    }

    public static CharRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CharRepository();
        }
        return INSTANCE;
    }

    public MutableLiveData<List<Char>> getCharList() {
        return currentListMutableLiveData;
    }

    public MutableLiveData<Char> getChar() {
        return selectedCharMutableLiveData;
    }

    public void searchCharByName(String name) {

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
                    currentListMutableLiveData.setValue(response.body());
                } else {
                    Log.e(TAG, "Response is null");
                    currentListMutableLiveData.setValue(null);
                }

            }

            @Override
            public void onFailure(Call<List<Char>> call, Throwable t) {
                Log.e(TAG, (t.getMessage() != null ? t.getMessage() : " onFailure"));
            }
        });
    }

    public void searchCharsByClass(String playerClass) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CharService service = retrofit.create(CharService.class);
        Call<List<Char>> call = service.getFilteredCharsByClass(playerClass);

        call.enqueue(new Callback<List<Char>>() {
            @Override
            public void onResponse(Call<List<Char>> call, Response<List<Char>> response) {
                if (response.body() != null) {
                    for (Char wChar : response.body()) {
                        Log.i(TAG, "CHAR: " + wChar.getName());
                        currentListMutableLiveData.setValue(response.body());
                    }
                } else {
                    Log.e(TAG, "Response of searchCharsByClass is null");
                    currentListMutableLiveData.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<Char>> call, Throwable t) {
                Log.e(TAG, (t.getMessage() != null ? t.getMessage() : " onFailure"));
                currentListMutableLiveData.setValue(null);
            }
        });
    }

    /**
     * No needed this method actually, but asked to use the Detail API Call
     * @param targetChar already has char detail info.
     */
    public void setClickedCharMutableLiveData(final Char targetChar) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CharService service = retrofit.create(CharService.class);
        Call<List<Char>> call = service.getClickedCharByID(targetChar.getCardId());

        call.enqueue(new Callback<List<Char>>() {
            @Override
            public void onResponse(Call<List<Char>> call, Response<List<Char>> response) {
                if (response.body() != null) {
                    for (Char wChar : response.body()) {
                        if (targetChar.getName().equals(wChar.getName())) {
                            selectedCharMutableLiveData.setValue(wChar);
                        }
                    }
                } else {
                    Log.e(TAG, "Response of  is null URL:" + call.request().url());
                }
            }

            @Override
            public void onFailure(Call<List<Char>> call, Throwable t) {
                Log.e(TAG, (t.getMessage() != null ? t.getMessage() : " onFailure"));
            }
        });
    }


}

