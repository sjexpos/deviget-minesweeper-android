package com.deviget.challenge.minesweeper.client.entities;

import java.util.List;

public class MyGamesReponse {

	private List<GameResponse> games;

	public MyGamesReponse() {
		super();
	}

	public List<GameResponse> getGames() {
		return games;
	}

	public void setGames(List<GameResponse> games) {
		this.games = games;
	}
	
}
