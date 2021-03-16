package com.deviget.challenge.minesweeper.client;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.deviget.challenge.minesweeper.client.IAuthenticationService.SignInResponse;
import com.deviget.challenge.minesweeper.client.SecurityService.SignUpResponse;
import com.deviget.challenge.minesweeper.client.entities.CreatedGameResponse;
import com.deviget.challenge.minesweeper.client.entities.MyGamesReponse;

import retrofit2.Response;

public class GamesTest {

	@Test
	public void test_games() throws Exception {
		String password = UUID.randomUUID().toString().substring(0, 8);
		String username = "test_"+password;
		Response<SignUpResponse> signup = Factory.getInstance().getSecurityService().signUp("test", "test", username, password).execute();
		Response<SignInResponse> signIn = Factory.getInstance().getSecurityService().signIn(username, password).execute();
		Factory.getInstance().getSessionManager().saveAuthToken(signIn.body().token);

		Response<CreatedGameResponse> game1 = Factory.getInstance().getGameService().createGame(10, 10, 5).execute();
		Response<CreatedGameResponse> game2 = Factory.getInstance().getGameService().createGame(10, 10, 5).execute();
		Response<CreatedGameResponse> game3 = Factory.getInstance().getGameService().createGame(10, 10, 5).execute();
		
		Response<MyGamesReponse> games = Factory.getInstance().getGameService().getAllMyGames().execute();
		
		Assert.assertEquals(3, games.body().getGames().size());
	}

}
