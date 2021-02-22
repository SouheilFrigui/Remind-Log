package com.example.remindlog;

import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("result")
    private String Result;

    public String getResult() {
        return Result;
    }
}
