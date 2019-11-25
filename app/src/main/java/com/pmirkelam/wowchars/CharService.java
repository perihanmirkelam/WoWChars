package com.pmirkelam.wowchars;

// X-RapidAPI-Key:
// ee01bea652msh4f5fa619e7afc24p1e0ec0jsn3d9fc66a7d8a
//
// x-rapidapi-host:
// omgvamp-hearthstone-v1.p.rapidapi.com

// Search;
//https://omgvamp-hearthstone-v1.p.rapidapi.com/cards/search/{name}
//Filter;
//https://omgvamp-hearthstone-v1.p.rapidapi.com/cards/classes/{class}
//Detail;
//https://omgvamp-hearthstone-v1.p.rapidapi.com/cards/{name}

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
    Call<List<Char>> searchCharByName(@Path("name") String name);
}