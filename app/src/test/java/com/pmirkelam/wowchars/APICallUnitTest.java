package com.pmirkelam.wowchars;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.pmirkelam.wowchars.Constants.BASE_URL;
import static org.junit.Assert.assertNotNull;

public class APICallUnitTest {

    @Test
    public void test() throws IOException {

        MockWebServer mockWebServer = new MockWebServer();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url(BASE_URL).toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mockWebServer.enqueue(new MockResponse().setBody("Saronite Chain Gang"));

        CharService service = retrofit.create(CharService.class);
        Call<List<Char>> call = service.getSearchedCharsByName("Saron");
        assertNotNull(call.execute());
        mockWebServer.shutdown();
    }

}
