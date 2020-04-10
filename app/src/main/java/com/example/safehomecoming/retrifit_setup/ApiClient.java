package com.example.safehomecoming.retrifit_setup;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    public static final String BASE_URL = "http://ec2-13-125-121-5.ap-northeast-2.compute.amazonaws.com/"; // 영주 누나 aws

    private static Retrofit retrofit;

    public static Retrofit getApiClient()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder().
                    baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        }
        return retrofit;
    }
}
