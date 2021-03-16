package com.deviget.challenge.minesweeper.client;

public class Constants {

	static public interface Headers {
		static public final String ACCEPT = "Accept";
	}

	static public interface MimeTypes {
		static public final String APPLICATION_JSON = "application/json";
	}

	static public final String HTTP_HEADER_CONNECTION_TIMEOUT = "X-Connection-Timeout";
	static public final String HTTP_HEADER_READ_TIMEOUT = "X-Read-Timeout";
	static public final String HTTP_HEADER_WRITE_TIMEOUT = "X-Write-Timeout";

	static public final String AUTHORIZATION_HEADER_NAME = "X-Auth-Token";
	static public final String SIGN_IN_ENDPOINT = "/api/security/signin";
	static public final String SIGN_OUT_ENDPOINT = "/api/security/signout";
	static public final String SIGN_UP_ENDPOINT = "/api/security/signup";

	static public final String GET_GAMES = "/api/game";
	static public final String CREATE_GAME = "/api/game";
	
}
