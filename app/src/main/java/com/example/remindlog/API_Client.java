package com.example.remindlog;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Client {
    public static final String BASE_URL="http://10.0.2.2:81/";
    public static Retrofit retrofit=null;

    public static Retrofit getApiClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
