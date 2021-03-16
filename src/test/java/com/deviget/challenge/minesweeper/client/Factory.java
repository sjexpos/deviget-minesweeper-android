package com.deviget.challenge.minesweeper.client;

import java.io.File;

import com.deviget.challenge.minesweeper.client.session.ISessionManager;

import retrofit2.Retrofit;

public class Factory {
	static private Factory factory = null;
	public static final String API_URL = "http://localhost:8080/";

	static public Factory getInstance() {
		if (factory == null) {
			factory = new Factory();
		}
		return factory;
	}
	
	private ISessionManager sessionManager;
	private SecurityService securityService;
	private GameService gameService;
	
	private File getCacheDir() {
		File tmpDir = new File(System.getProperty("java.io.tmpdir"));
		File cacheDir = new File(tmpDir, "OkHttp.cache");
		return cacheDir;
	}

	private Factory() {
		this.sessionManager = new InMemorySessionManager();
		Retrofit retrofit = RetrofitBuilder.create()
				.setCacheDirectory( getCacheDir() )
				.setCredentialsProvider( new DefaultCredentialsProvider("", "") )
				.setSessionManager( this.sessionManager )
//				.setHostResolver(new IHostResolver() {
//					@Override
//					public String getHost() {
//						return "localhost:8080";
//					}
//				})
				.build();
		this.securityService = retrofit.create(SecurityService.class);
		this.gameService = retrofit.create(GameService.class);
	}

	public ISessionManager getSessionManager() {
		return this.sessionManager;
	}
	
	public SecurityService getSecurityService() {
		return securityService;
	}

	public GameService getGameService() {
		return this.gameService;
	}
	
}
