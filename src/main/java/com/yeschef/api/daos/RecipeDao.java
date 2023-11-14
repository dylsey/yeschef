package com.yeschef.api.daos;

import java.util.List;

import com.yeschef.api.models.Recipe;

public interface RecipeDao {
	
	public List<Recipe> getRecipes();
	public Recipe getRecipeById(Integer id);
	public Recipe deleteRecipeById(Integer id);
	public List<Recipe> searchRecipesByName(String searchTerm);
	public Recipe createRecipe(Recipe recipe);
	
	
	

}
