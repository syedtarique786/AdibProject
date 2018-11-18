package com.an.paginglibrary.sample.rest;


import com.an.paginglibrary.sample.model.Results;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {

    @POST(ServiceUrls.NEWS_FEEDS)
    Call<Results> getNewsFeed(@Body JsonObject jsonObject);


}
