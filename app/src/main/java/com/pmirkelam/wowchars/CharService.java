package com.pmirkelam.wowchars;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface CharService {
    @Headers({
            "x-rapidapi-host:omgvamp-hearthstone-v1.p.rapidapi.com",
            "x-rapidapi-key:ee01bea652msh4f5fa619e7afc24p1e0ec0jsn3d9fc66a7d8a"
            })
    @GET("search/{name}")
    Call<List<Char>> getSearchedCharsByName(@Path("name") String name);

    @Headers({
            "x-rapidapi-host:omgvamp-hearthstone-v1.p.rapidapi.com",
            "x-rapidapi-key:ee01bea652msh4f5fa619e7afc24p1e0ec0jsn3d9fc66a7d8a"
    })
    @GET("{id}")
    Call<List<Char>> getClickedCharByID(@Path("id") String id);

    @Headers({
            "x-rapidapi-host:omgvamp-hearthstone-v1.p.rapidapi.com",
            "x-rapidapi-key:ee01bea652msh4f5fa619e7afc24p1e0ec0jsn3d9fc66a7d8a"
    })
    @GET("classes/{class}")
    Call<List<Char>> getFilteredCharsByClass(@Path("class") String className);
}