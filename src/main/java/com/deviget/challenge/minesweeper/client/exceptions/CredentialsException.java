package com.deviget.challenge.minesweeper.client.exceptions;

import okhttp3.ResponseBody;

public class CredentialsException extends LoginException {
    public CredentialsException(ResponseBody body, Class<?> bodyClass) {
        super(body, bodyClass);
    }
}
