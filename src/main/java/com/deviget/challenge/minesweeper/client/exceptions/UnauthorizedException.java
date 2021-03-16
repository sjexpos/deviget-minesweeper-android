package com.deviget.challenge.minesweeper.client.exceptions;

import okhttp3.ResponseBody;

public class UnauthorizedException extends GeneralSecurityException {

    public UnauthorizedException(ResponseBody body, Class<?> bodyClass) {
        super(body, bodyClass);
    }

}
