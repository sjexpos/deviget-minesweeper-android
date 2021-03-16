package com.deviget.challenge.minesweeper.client.okhttp3;

import com.deviget.challenge.minesweeper.client.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TimeoutInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        int connectTimeout = chain.connectTimeoutMillis();
        int readTimeout = chain.readTimeoutMillis();
        int writeTimeout = chain.writeTimeoutMillis();
        String connectNew = request.header(Constants.HTTP_HEADER_CONNECTION_TIMEOUT);
        String readNew = request.header(Constants.HTTP_HEADER_READ_TIMEOUT);
        String writeNew = request.header(Constants.HTTP_HEADER_WRITE_TIMEOUT);
        if (!isEmpty(connectNew)) {
            connectTimeout = Integer.valueOf(connectNew);
        }
        if (!isEmpty(readNew)) {
            readTimeout = Integer.valueOf(readNew);
        }
        if (!isEmpty(writeNew)) {
            writeTimeout = Integer.valueOf(writeNew);
        }
        Request.Builder builder = request.newBuilder();
        builder.removeHeader(Constants.HTTP_HEADER_CONNECTION_TIMEOUT);
        builder.removeHeader(Constants.HTTP_HEADER_READ_TIMEOUT);
        builder.removeHeader(Constants.HTTP_HEADER_WRITE_TIMEOUT);
        return chain
                .withConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .withReadTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .withWriteTimeout(writeTimeout, TimeUnit.MILLISECONDS)
                .proceed(builder.build());
    }

    private boolean isEmpty(String header) {
        return header == null || "".equals(header.trim());
    }

}
