package com.deviget.challenge.minesweeper.client.session;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deviget.challenge.minesweeper.client.Constants;

import okhttp3.Interceptor;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	private ISessionManager sessionManager;

	public AuthInterceptor(ISessionManager sessionManager) {
		super();
		this.sessionManager = sessionManager;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		logger.trace("AuthInterceptor.intercept - begin");
		Builder requestBuilder = chain.request().newBuilder();
		String token = sessionManager.fetchAuthToken();
		if (token != null && !"".equals(token.trim())) {
			requestBuilder.addHeader(Constants.AUTHORIZATION_HEADER_NAME, token);
		}
		Response response = chain.proceed(requestBuilder.build());
		logger.trace("AuthInterceptor.intercept - end");
		return response;
	}

}
