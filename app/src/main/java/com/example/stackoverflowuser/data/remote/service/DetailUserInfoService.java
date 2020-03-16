package com.example.stackoverflowuser.data.remote.service;

import com.example.stackoverflowuser.data.remote.model.ReputationDetailResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DetailUserInfoService {
    @GET("/2.2/users/{user_id}/reputation-history?site=stackoverflow")
    Call<ReputationDetailResult> getDetailUserInfo(
            @Path("user_id") long userId,
            @Query("page") int page,
            @Query("pagesize") int pageSize);
}
