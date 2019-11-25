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
    private MutableLiveData<List<Char>> charListMutableLiveData = new MutableLiveData<>();

    public CharRepository() {
    }

    public MutableLiveData<List<Char>> getSearchedCharsMutableLiveData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CharService service = retrofit.create(CharService.class);

        Call<List<Char>> call = service.searchCharByName("Saron");

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
                List<Char> orders = response.body();
                charListMutableLiveData.setValue(orders);
            }

            @Override
            public void onFailure(Call<List<Char>> call, Throwable t) {
                Log.e(TAG, (t.getMessage() != null ? t.getMessage() : " onFailure"));
            }
        });
        return charListMutableLiveData;
    }
}

