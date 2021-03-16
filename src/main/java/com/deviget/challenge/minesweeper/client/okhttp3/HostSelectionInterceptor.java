package com.deviget.challenge.minesweeper.client.okhttp3;

import com.deviget.challenge.minesweeper.client.IHostResolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HostSelectionInterceptor implements Interceptor {
	private static final Logger logger = LoggerFactory.getLogger(HostSelectionInterceptor.class);

	private IHostResolver hostResolver = null;

	public HostSelectionInterceptor(IHostResolver hostResolver) {
		this.hostResolver = hostResolver;
	}

	@Override
    public Response intercept(Chain chain) throws IOException {
		logger.trace("HostSelectionInterceptor.intercept - begin");
    	Request request = chain.request();
    	String host = this.hostResolver.getHost();
    	if (host != null) {
    		HttpUrl newUrl = request.url().newBuilder().host(host).build();
    		request = request.newBuilder().url(newUrl).build();
    	}
    	Response response = chain.proceed(request);
		logger.trace("HostSelectionInterceptor.intercept - end");
    	return response;
    }

}
