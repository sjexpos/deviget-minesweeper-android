package com.deviget.challenge.minesweeper.client.session;

public interface ISessionManager {

	void saveAuthToken(String token);
	
	String fetchAuthToken();

	boolean hasAuthToken();

}
