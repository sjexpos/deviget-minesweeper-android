package com.deviget.challenge.minesweeper.client;

import com.deviget.challenge.minesweeper.client.session.ICredentialsProvider;

public class DefaultCredentialsProvider implements ICredentialsProvider {
	private String username;
	private String password;

	public DefaultCredentialsProvider(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
