package com.example.stackoverflowuser.data.remote.service;

import com.example.stackoverflowuser.data.remote.model.UserResultGSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("/2.2/users?site=stackoverflow")
    Call<UserResultGSON> getStackOverflowUsers(
            @Query("page") int page,
            @Query("pagesize") int pageSize);
}
