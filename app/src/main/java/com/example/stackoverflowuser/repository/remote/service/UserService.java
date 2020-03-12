package com.example.stackoverflowuser.repository.remote.service;

import com.example.stackoverflowuser.repository.remote.model.StackOverflowUserResultGSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("/2.2/users?site=stackoverflow")
    Call<StackOverflowUserResultGSON> getStackOverflowUsers(
            @Query("page") int page,
            @Query("pagesize") int pageSize);
}
