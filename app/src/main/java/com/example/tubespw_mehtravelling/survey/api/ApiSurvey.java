package com.example.tubespw_mehtravelling.survey.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSurvey {
    public static final String BASE_URL = "http://127.0.0.1:8000/api/";
    public static Retrofit retrofit = null;
    public static Retrofit getSurvey()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}