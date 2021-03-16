package com.deviget.challenge.minesweeper.client.okhttp3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    static private final Logger logger = LoggerFactory.getLogger(HttpLogger.class.getPackage().getName()+".Caller");

    @Override
    public void log(String message) {
        if (message != null && (message.contains("-->") || message.contains("<--"))) {
            logger.info(message);
        } else {
            logger.debug(message);
        }
    }

}
