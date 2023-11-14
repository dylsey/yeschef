package com.yeschef.api.services;

import java.util.List;

import com.yeschef.api.daos.RecipeDao;
import com.yeschef.api.daos.RecipeDaoImpl;
import com.yeschef.api.models.Recipe;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;


public class RecipeService {
	
	private RecipeDao recipeDao = new RecipeDaoImpl();
	
	public List<Recipe> getRecipes(){		
		return recipeDao.getRecipes();
	}

	public Recipe getRecipeById(Integer id) {
		return recipeDao.getRecipeById(id);
	}
	
	public Recipe deleteRecipeById(Integer id) {
		return recipeDao.deleteRecipeById(id);
	}
	
	public List<Recipe> searchRecipesByName(String searchTerm){
		return recipeDao.searchRecipesByName(searchTerm);
	}
	
	public Recipe createRecipe(Recipe recipe) {		
		return recipeDao.createRecipe(recipe);
	}
	
	
	

}
