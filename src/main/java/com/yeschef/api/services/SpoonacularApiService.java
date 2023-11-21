package com.yeschef.api.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SpoonacularApiService {
	// base url
	private final String spoonBaseUrl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";
	// these are key value pairs for the http header
	private final String header1 = "x-rapidapi-key";
	private final String apiKey = "e033f0fec8msh8822ae38ed8d4c7p1b7c6cjsn6d220a7560f2";

	private final String header2 = "x-rapidapi-host";
	private final String hostKey = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";

	private String name;
	private Integer id; // need to get this from spoonacular api call
	private String imageUrl; // need to get this from spoonacular api call

	public void getRandomRecipes() throws IOException {
		Integer numberOfRecipes = 1; // how to pass input into this variable?

		// establish connection don't know how to do this w/o casting
		try {
			URL url = new URL(spoonBaseUrl + "/recipes/random?number=" + numberOfRecipes);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			// header values
			connection.setRequestProperty(header1, apiKey);
			connection.setRequestProperty(header2, hostKey);
			//response header 
			connection.setRequestProperty("Content-Type", "application/json");
			
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			connection.connect();

			System.out.println(url);
			
			// getting response code
			Integer responseCode = connection.getResponseCode();
			System.out.println(responseCode);
			
			 System.out.println("Response Message: " + connection.getResponseMessage());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		SpoonacularApiService apiService = new SpoonacularApiService();
		apiService.getRandomRecipes();
	}
}



