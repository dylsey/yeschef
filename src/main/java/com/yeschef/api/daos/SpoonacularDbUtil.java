package com.yeschef.api.daos;

import javax.net.ssl.HttpsURLConnection;

public class SpoonacularDbUtil {

	private static String connectionUrl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";
	private static String header1 = "x-rapidapi-key";
	private static String apiKey = "e033f0fec8msh8822ae38ed8d4c7p1b7c6cjsn6d220a7560f2";
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
