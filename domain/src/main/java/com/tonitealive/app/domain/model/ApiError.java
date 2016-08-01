package com.tonitealive.app.domain.model;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class ApiError {

    @SerializedName("timestamp") abstract Long timestamp();
    @SerializedName("status") abstract int status();
    @SerializedName("error") abstract String error();
    @SerializedName("exception") abstract String exception();
    @SerializedName("message") abstract String message();
    @SerializedName("path") abstract String path();
    @SerializedName("error_code") abstract int errorCode();


}