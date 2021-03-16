package com.deviget.challenge.minesweeper.client;

import com.deviget.challenge.minesweeper.client.session.ISessionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemorySessionManager implements ISessionManager {
	private static final Logger logger = LoggerFactory.getLogger(InMemorySessionManager.class);
	private String token = null;

	@Override
	public void saveAuthToken(String token) {
		logger.info("InMemorySessionManager.saveAuthToken " + token);
		this.token = token;
	}

	@Override
	public String fetchAuthToken() {
		return this.token;
	}

	@Override
	public boolean hasAuthToken() {
		String token = fetchAuthToken();
		return (token != null && !"".equals(token.trim()));
	}

}
