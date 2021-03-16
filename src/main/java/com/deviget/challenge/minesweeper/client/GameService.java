package com.deviget.challenge.minesweeper.client;

import com.deviget.challenge.minesweeper.client.entities.CreatedGameResponse;
import com.deviget.challenge.minesweeper.client.entities.MyGamesReponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GameService {

	@GET(Constants.GET_GAMES)
	Call<MyGamesReponse> getAllMyGames();
	
	@FormUrlEncoded
	@POST(Constants.CREATE_GAME)
	Call<CreatedGameResponse> createGame(@Field("columns") Integer columns, @Field("rows") Integer rows, @Field("mines") Integer mines);
	
}
