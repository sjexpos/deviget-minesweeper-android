package com.deviget.challenge.minesweeper.client;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IAuthenticationService {

    static public class SignInResponse {
        @SerializedName("token")
        public String token;
    }

    @FormUrlEncoded
    @POST(Constants.SIGN_IN_ENDPOINT)
    Call<SignInResponse> signIn(@Field("username") String username, @Field("password") String password);

}
