package com.deviget.challenge.minesweeper.client.exceptions;

import okhttp3.ResponseBody;

public class NotFoundException extends ValidationException {

    public NotFoundException(ResponseBody body, Class<?> bodyClass) {
        super(body, bodyClass);
    }

}
