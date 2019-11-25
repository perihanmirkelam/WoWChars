package com.pmirkelam.wowchars;

import android.os.StrictMode;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.pmirkelam.wowchars.Constants.BASE_URL;

public class CharRepository {

    public static final String TAG = CharRepository.class.getSimpleName();
    private MutableLiveData<List<Char>> charListMutableLiveData = new MutableLiveData<>();

    public void getInceptorSearchedCharsMutableLiveData() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://omgvamp-hearthstone-v1.p.rapidapi.com/cards/search/Rexxar")
                .get()
                .addHeader("x-rapidapi-host", "omgvamp-hearthstone-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "ee01bea652msh4f5fa619e7afc24p1e0ec0jsn3d9fc66a7d8a")
                .build();

        try{
            okhttp3.Response response = client.newCall(request).execute();
            if (response.body() != null) {
                    Log.i(TAG, "CHAR: " + response.body().string());
            } else {
                Log.e(TAG, "Response is null");
            }
        }catch (IOException e){
            Log.e(TAG, e.getMessage());
        }

    }


    public CharRepository() {
    }

    public MutableLiveData<List<Char>> getSearchedCharsMutableLiveData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CharService service = retrofit.create(CharService.class);

        Call<List<Char>> call = service.searchCharByName("Rexxar");

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

