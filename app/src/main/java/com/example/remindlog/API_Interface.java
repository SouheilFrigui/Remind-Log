package com.example.remindlog;

import android.widget.TextView;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_Interface {

    @GET(value = "register.php")
    Call<User> performRegistration(@Query("name") String Name, @Query("nick_name") String NickName, @Query("user_password") String UserPassword);

    @GET(value = "login.php")
    Call<User> performUserLogin(@Query("nick_name") String NickName, @Query("user_password") String UserPassword);

    @GET(value = "customerRegistration.php")
    Call<Customer> performCustomerRegistration(@Query("name")String Nom, @Query("phone") String Tel, @Query("function") String Fonction, @Query("residence") String
                                               Residence, @Query("appartement") String Appartement, @Query("visite") String Date_visite, @Query("budget")
                                               String Budget, @Query("description") String Description, @Query("sys") String Date_sys, @Query("commercial")
                                               String Commercial);




}
