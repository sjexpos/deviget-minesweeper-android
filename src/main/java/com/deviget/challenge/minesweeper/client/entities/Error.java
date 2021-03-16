package com.deviget.challenge.minesweeper.client.entities;

import com.google.gson.annotations.SerializedName;

public class Error {
    @SerializedName("timestamp")
    private long timestamp;
    @SerializedName("status")
    private int status;
    @SerializedName("error")
    private String error;
    @SerializedName("message")
	public Object message;
    @SerializedName("path")
	public String path;


}
