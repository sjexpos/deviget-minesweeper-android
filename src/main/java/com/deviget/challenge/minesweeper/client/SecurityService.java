package com.deviget.challenge.minesweeper.client;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SecurityService extends IAuthenticationService {
	
    static public class SignOutResponse {
        @SerializedName("token")
        public String token;
    }

    static public class SessionInfoRespose {
        @SerializedName("token")
        public String token;
    }

    static public class SignUpResponse {
        @SerializedName("userId")
    	public String userId;
        @SerializedName("username")
        public String username;
    }
	
	@FormUrlEncoded
	@POST(Constants.SIGN_IN_ENDPOINT)
	Call<SignInResponse> signIn(@Field("username") String username, @Field("password") String password);

	@FormUrlEncoded
	@POST(Constants.SIGN_OUT_ENDPOINT)
	Call<SessionInfoRespose> signOut();

	@FormUrlEncoded
	@POST(Constants.SIGN_UP_ENDPOINT)
	Call<SignUpResponse> signUp(@Field("firstname") String firstname, @Field("lastname") String lastname, @Field("username") String username, @Field("password") String password);

}
