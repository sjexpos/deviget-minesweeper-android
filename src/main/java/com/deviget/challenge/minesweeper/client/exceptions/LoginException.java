package com.deviget.challenge.minesweeper.client.exceptions;

import okhttp3.ResponseBody;

public class LoginException extends GeneralSecurityException {
    public LoginException(ResponseBody body, Class<?> bodyClass) {
        super(body, bodyClass);
    }
}
