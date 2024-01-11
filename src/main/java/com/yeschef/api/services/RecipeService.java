package com.yeschef.api.services;

import java.util.List;
//import java.util.Random;

import com.yeschef.api.models.RequestError;
import com.yeschef.api.daos.RecipeDao;
import com.yeschef.api.daos.RecipeDaoImpl;
import com.yeschef.api.models.MealType;
import com.yeschef.api.models.Recipe;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;


public class RecipeService {
	
	private RecipeDao recipeDao = new RecipeDaoImpl();
	
	public List<Recipe> getRecipes(){		
		return recipeDao.getRecipes();
	}

	public List<Recipe> getRecipesById(Integer id) {
		validateRecipeId(id);
		return recipeDao.getRecipesById(id);
	}
	
	public List<Recipe> getRandomRecipe() {
		return recipeDao.getRandomRecipe();
	}
	
	public List<Recipe> getRecipesByMealType(MealType mealType){
		return recipeDao.getRecipesByMealType(mealType);
	}
	
	public Recipe deleteRecipeById(Integer id) {
		validateRecipeId(id);
		return recipeDao.deleteRecipeById(id);
	}
	
	public List<Recipe> searchRecipesByName(String searchTerm){
		validateRecipeName(searchTerm);
		return recipeDao.searchRecipesByName(searchTerm);
	}	

	public Recipe createRecipe(Recipe newRecipe) {		
		return recipeDao.createRecipe(newRecipe);
	}
	
	/*
	 * Error handling methods
	 */
	
	private void makeError(RequestError error) {
		Response response = Response.status(400)
				.entity(error)
				.build();
		throw new WebApplicationException(response);
	
	}
	
	private void validateRecipeId(Integer id ) {
		if (id < 0 ) {
			RequestError error = new RequestError(1, 
					"Invalid recipe id " + id + ". Value must be > 0.");
			makeError(error);
			
		}
	}
	
	private void validateRecipeName(String searchTerm) {
		if (searchTerm == null) {
			RequestError error = new RequestError(2, 
					"invalid recipe name '" + searchTerm + "'. Value must have length > 0");
			makeError(error);		
		}
		
	}

}
