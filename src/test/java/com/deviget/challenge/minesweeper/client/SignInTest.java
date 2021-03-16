package com.deviget.challenge.minesweeper.client;

import java.io.IOException;
import java.util.UUID;

import org.junit.Test;

import com.deviget.challenge.minesweeper.client.Factory;
import com.deviget.challenge.minesweeper.client.IAuthenticationService.SignInResponse;
import com.deviget.challenge.minesweeper.client.SecurityService.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInTest {

	@Test
	public void test_singin() {
		try {
			String password = UUID.randomUUID().toString().substring(0, 8);
			String username = "test_"+password;
			Response<SignUpResponse> signup = Factory.getInstance().getSecurityService().signUp("test", "test", username, password).execute();
			Response<SignInResponse> signIn = Factory.getInstance().getSecurityService().signIn(username, password).execute();
			System.out.println("OK - "+signIn.body().token);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_singin2()  throws Exception {
		String password = UUID.randomUUID().toString().substring(0, 8);
		String username = "test_"+password;
		Response<SignUpResponse> signup = Factory.getInstance().getSecurityService().signUp("test", "test", username, password).execute();

		Factory.getInstance().getSecurityService().signIn(username, password)
			.enqueue(new Callback<SignInResponse>() {
				@Override
				public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
					System.out.println("################ onResponse");
				}
				@Override
				public void onFailure(Call<SignInResponse> call, Throwable t) {
					System.out.println("################ onFailure");
				}
			});
	}
	
}
