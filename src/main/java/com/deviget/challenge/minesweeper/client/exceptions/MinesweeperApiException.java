package com.deviget.challenge.minesweeper.client.exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;

public class MinesweeperApiException extends Exception {
    private Object body;

    static private String getBody(ResponseBody body) {
        String txt = "";
        try {
            txt = body.string();
        } catch (IOException exception) {
            txt = exception.getMessage();
        }
        return txt;
    }

    public MinesweeperApiException(ResponseBody body, Class<?> bodyClass) {
        super(getBody(body));
        if (bodyClass != null) {
            try {
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
                this.body = gson.fromJson(getBody(body), bodyClass);
            } catch (Exception exception) {
                // nothing
            }
        }
    }

    public Object getBody() {
        return this.body;
    }

}
