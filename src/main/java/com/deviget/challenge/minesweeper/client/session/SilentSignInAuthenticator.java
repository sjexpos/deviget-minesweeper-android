package com.deviget.challenge.minesweeper.client.session;

import java.io.IOException;

import com.deviget.challenge.minesweeper.client.Constants;
import com.deviget.challenge.minesweeper.client.IAuthenticationService;
import com.deviget.challenge.minesweeper.client.IAuthenticationService.SignInResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class SilentSignInAuthenticator implements Authenticator {
	private static final Logger logger = LoggerFactory.getLogger(SilentSignInAuthenticator.class);
	private final ICredentialsProvider credentialsProvider;
	private final ISessionManager sessionManager;
	private IAuthenticationService AuthenticationService;
	
	public SilentSignInAuthenticator(ICredentialsProvider credentialsProvider, ISessionManager sessionManager) {
		super();
		this.credentialsProvider = credentialsProvider;
		this.sessionManager = sessionManager;
	}

	public void setAuthenticationService(IAuthenticationService authenticationService) {
		AuthenticationService = authenticationService;
	}

	@Override
	public Request authenticate(Route route, Response response) throws IOException {
		logger.trace("SilentSignInAuthenticator.intercept - begin");
    	String path = response.request().url().encodedPath();
		if (!path.contains(Constants.SIGN_IN_ENDPOINT)) {
			SignInResponse signIn = this.AuthenticationService.signIn(credentialsProvider.getUsername(), credentialsProvider.getPassword()).execute().body();
			if (signIn != null && signIn.token != null && !"".equals(signIn.token.trim())) {
				sessionManager.saveAuthToken(signIn.token);
		    	Request newRequest = response.request().newBuilder()
						.header(Constants.AUTHORIZATION_HEADER_NAME, signIn.token)
						.build();
				logger.trace("SilentSignInAuthenticator.intercept - end");
				return newRequest;
			}
		}
		logger.trace("SilentSignInAuthenticator.intercept - end");
		return null;
	}

}
