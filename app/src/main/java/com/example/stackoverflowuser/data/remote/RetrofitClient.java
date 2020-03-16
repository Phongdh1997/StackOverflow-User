package com.example.stackoverflowuser.data.remote;

import com.example.stackoverflowuser.data.remote.service.DetailUserInfoService;
import com.example.stackoverflowuser.data.remote.service.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://api.stackexchange.com";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static UserService getUserService() {
        return retrofit.create(UserService.class);
    }
    public static DetailUserInfoService getDetailUserInfoService() {
        return retrofit.create(DetailUserInfoService.class);
    }
}
