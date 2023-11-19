package com.yeschef.api.services;

import org.json.JSONObject;
import jakarta.ws.rs.core.Response;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class SpoonacularApiService {
	
    private String spoonBaseUrl = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";
    private final String apiKey = "e033f0fec8msh8822ae38ed8d4c7p1b7c6cjsn6d220a7560f2";

    private String name;
	private Integer id;//need to get this from spoonacular api call
	private String imageUrl;//need to get this from spoonacular api call



 
	public HttpResponse<JsonNode> getRecipeData() {
    	
    	HttpResponse<JsonNode> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/complexSearch?" + 										"query=pasta&type=main%20course&instructionsRequired=true&fillIngredients=true&addRecipeInformation="+
    									"true&ignorePantry=false&sortDirection=desc&limitLicense=false&ranking=2")
    			.header("X-RapidAPI-Key", apiKey)
    			.header("X-RapidAPI-Host", spoonBaseUrl)
    			.asJson();
    	
    	return response;
    }
	

    private JsonNode handleApiResponse(HttpResponse<JsonNode> response) {
    	
        if (response.isSuccess()) {
            JsonNode apiResponse = response.getBody();
            parseApiResponse(apiResponse);  // Pass the String to the parsing method
        } else {
            throw new RuntimeException("Failed to fetch data from Spoonacular API. Response code: " + response.getStatus());
        }
		return apiResponse;
    }

    public SpoonacularApiService parseApiResponse(JsonNode apiResponse) {
        JSONObject jsonObject = new JSONObject(apiResponse);
        name = jsonObject.getString("title");
        id = jsonObject.getInt("spoonacularRecipeId");
        imageUrl = jsonObject.getString("imageUrl");
        
        return new SpoonacularApiService();
    }
}

