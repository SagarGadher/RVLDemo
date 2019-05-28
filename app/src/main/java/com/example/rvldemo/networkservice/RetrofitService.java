package com.example.rvldemo.networkservice;

import com.example.rvldemo.database.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("users")
    Call<List<User>> getAllUser();
}
