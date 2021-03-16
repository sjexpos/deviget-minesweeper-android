package com.deviget.challenge.minesweeper.client.exceptions;

import okhttp3.ResponseBody;

public class BackendException extends MinesweeperApiException {

    public BackendException(ResponseBody body, Class<?> bodyClass) {
        super(body, bodyClass);
    }

}
