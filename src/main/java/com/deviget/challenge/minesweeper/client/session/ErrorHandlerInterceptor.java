package com.deviget.challenge.minesweeper.client.session;

import java.io.IOException;

import com.deviget.challenge.minesweeper.client.Constants;
import com.deviget.challenge.minesweeper.client.entities.Error;
import com.deviget.challenge.minesweeper.client.exceptions.BackendException;
import com.deviget.challenge.minesweeper.client.exceptions.CredentialsException;
import com.deviget.challenge.minesweeper.client.exceptions.ForbiddenException;
import com.deviget.challenge.minesweeper.client.exceptions.NotFoundException;
import com.deviget.challenge.minesweeper.client.exceptions.UnauthorizedException;
import com.deviget.challenge.minesweeper.client.exceptions.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ErrorHandlerInterceptor implements Interceptor {
	private static final Logger logger = LoggerFactory.getLogger(ErrorHandlerInterceptor.class);

	@Override
	public Response intercept(Chain chain) throws IOException {
		logger.trace("ErrorHandlerInterceptor.intercept - begin");
		Request request = chain.request();
        okhttp3.Response response = chain.proceed(request);
        String path = response.request().url().encodedPath();
		if (response.code() == 400) {
			throw new IOException(new ValidationException(response.body(), Error.class));
		}
        if (response.code() == 401 && path.contains(Constants.SIGN_IN_ENDPOINT)) {
        	throw new IOException(new CredentialsException(response.body(), Error.class));
        }
        if (response.code() == 401) {
			throw new IOException(new UnauthorizedException(response.body(), Error.class));
		}
		if (response.code() == 403) {
			throw new IOException(new ForbiddenException(response.body(), Error.class));
		}
		if (response.code() == 404) {
			throw new IOException(new NotFoundException(response.body(), Error.class));
		}
        if (response.code() == 500) {
        	throw new IOException(new BackendException(response.body(), Error.class));
        }
		logger.trace("ErrorHandlerInterceptor.intercept - end");
		return response;
	}
	
}
