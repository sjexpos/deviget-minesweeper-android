package com.deviget.challenge.minesweeper.client.exceptions;

import okhttp3.ResponseBody;

public class GeneralSecurityException extends MinesweeperApiException {

    public GeneralSecurityException(ResponseBody body, Class<?> bodyClass) {
        super(body, bodyClass);
    }

}
