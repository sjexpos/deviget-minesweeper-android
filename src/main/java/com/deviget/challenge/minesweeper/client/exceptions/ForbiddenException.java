package com.deviget.challenge.minesweeper.client.exceptions;

import okhttp3.ResponseBody;

public class ForbiddenException extends GeneralSecurityException {

    public ForbiddenException(ResponseBody body, Class<?> bodyClass) {
        super(body, bodyClass);
    }

}
