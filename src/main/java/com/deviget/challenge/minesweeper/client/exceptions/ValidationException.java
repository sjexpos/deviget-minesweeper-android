package com.deviget.challenge.minesweeper.client.exceptions;

import okhttp3.ResponseBody;

public class ValidationException extends MinesweeperApiException {

    public ValidationException(ResponseBody body, Class<?> bodyClass) {
        super(body, bodyClass);
    }

}
