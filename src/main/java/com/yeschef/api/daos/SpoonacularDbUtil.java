package com.yeschef.api.daos;

import javax.net.ssl.HttpsURLConnection;

public class SpoonacularDbUtil {

	private static String connectionUrl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";
	private static String header1 = "x-rapidapi-key";
	private static String apiKey = "****user specific API key****";
	private static String header2 = "x-rapidapi-host";
	private static String hostKey = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";
	
	
	public static HttpsURLConnection getConnection() {
		HttpsURLConnection connection = null;
		
		try {
			Class.forName(connectionUrl);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		return connection;
	}
}
