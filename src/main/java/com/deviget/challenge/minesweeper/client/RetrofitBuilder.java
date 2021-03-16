package com.deviget.challenge.minesweeper.client;

import com.deviget.challenge.minesweeper.client.okhttp3.HttpLogger;
import com.deviget.challenge.minesweeper.client.okhttp3.HttpLoggingInterceptor;
import com.deviget.challenge.minesweeper.client.okhttp3.HostSelectionInterceptor;
import com.deviget.challenge.minesweeper.client.okhttp3.TimeoutInterceptor;
import com.deviget.challenge.minesweeper.client.retrofit2.AnnotatedConverterFactory;
import com.deviget.challenge.minesweeper.client.session.AuthInterceptor;
import com.deviget.challenge.minesweeper.client.session.ErrorHandlerInterceptor;
import com.deviget.challenge.minesweeper.client.session.ICredentialsProvider;
import com.deviget.challenge.minesweeper.client.session.ISessionManager;
import com.deviget.challenge.minesweeper.client.session.SilentSignInAuthenticator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    public static final String API_URL = "http://localhost:8080/";
    public static final long DEFAULT_READ_TIMEOUT_SECONDS = 2;
    public static final long DEFAULT_WRITE_TIMEOUT_SECONDS = 2;
    public static final long DEFAULT_CONNECTION_TIMEOUT_SECONDS = 2;

    static public RetrofitBuilder create() {
        return new RetrofitBuilder();
    }

    private ISessionManager sessionManager = null;
    private ICredentialsProvider credentialsProvider = null;
    private IHostResolver hostResolver = null;
    private File cacheDirectory = null;
    private int cacheSizeInMegas = 10;
    private long readTimeout = TimeUnit.SECONDS.toMillis(DEFAULT_READ_TIMEOUT_SECONDS);
    private long writeTimeout = TimeUnit.SECONDS.toMillis(DEFAULT_WRITE_TIMEOUT_SECONDS);
    private long connectionTimeout = TimeUnit.SECONDS.toMillis(DEFAULT_CONNECTION_TIMEOUT_SECONDS);

    private RetrofitBuilder() {
    }

    public RetrofitBuilder setSessionManager(ISessionManager sessionManager) {
        this.sessionManager = sessionManager;
        return this;
    }

    public RetrofitBuilder setCredentialsProvider(ICredentialsProvider credentialsProvider) {
        this.credentialsProvider = credentialsProvider;
        return this;
    }

    public RetrofitBuilder setHostResolver(IHostResolver hostResolver) {
        this.hostResolver = hostResolver;
        return this;
    }

    public RetrofitBuilder setCacheDirectory(File cacheDirectory) {
        this.cacheDirectory = cacheDirectory;
        return this;
    }

    public RetrofitBuilder setCacheSizeInMegas(int cacheSizeInMegas) {
        this.cacheSizeInMegas = cacheSizeInMegas;
        return this;
    }
    
    public RetrofitBuilder setReadTimeoutSeconds(long duration, TimeUnit unit) {
    	this.readTimeout = unit.toMillis(duration);
    	return this;
    }

    public RetrofitBuilder setWriteTimeoutSeconds(long duration, TimeUnit unit) {
    	this.writeTimeout = unit.toMillis(duration);
    	return this;
    }

    public RetrofitBuilder setConnectionTimeoutSeconds(long duration, TimeUnit unit) {
    	this.connectionTimeout = unit.toMillis(duration);
    	return this;
    }

    public Retrofit build() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLogger());
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        
        TimeoutInterceptor timeoutInterceptor = new TimeoutInterceptor();
        ErrorHandlerInterceptor errorHandlerInterceptor = new ErrorHandlerInterceptor();
        AuthInterceptor authInterceptor = new AuthInterceptor(this.sessionManager);
        SilentSignInAuthenticator silentSignInAuthenticator = new SilentSignInAuthenticator(this.credentialsProvider, this.sessionManager);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(timeoutInterceptor)
                .addInterceptor(logging)
                .addInterceptor(errorHandlerInterceptor);
        if (this.hostResolver != null) {
            HostSelectionInterceptor hostSelectionInterceptor = new HostSelectionInterceptor(this.hostResolver);
            builder.addInterceptor(hostSelectionInterceptor);
        }
        builder.addInterceptor(authInterceptor);
        builder.authenticator(silentSignInAuthenticator);
        if (this.cacheDirectory != null) {
            int cacheSize = this.cacheSizeInMegas * 1024 * 1024;
            Cache cache = new Cache(this.cacheDirectory, cacheSize);
            builder.cache(cache);
        }
        OkHttpClient okHttpClient = builder
        		.readTimeout(readTimeout, TimeUnit.MILLISECONDS)
        		.writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
        		.connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
        		.build();
        Converter.Factory converterFactory = new AnnotatedConverterFactory.Builder()
                .add(AnnotatedConverterFactory.Json.class, GsonConverterFactory.create(gson))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .callFactory(okHttpClient)
                .addConverterFactory(converterFactory)
                .build();
        IAuthenticationService authenticationService = retrofit.create(IAuthenticationService.class);
        silentSignInAuthenticator.setAuthenticationService(authenticationService);
        return retrofit;
    }

}
