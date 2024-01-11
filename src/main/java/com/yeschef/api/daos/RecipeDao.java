package com.yeschef.api.daos;

import java.util.List;
//import java.util.Random;

import com.yeschef.api.models.MealType;
import com.yeschef.api.models.Recipe;

public interface RecipeDao {
	
	public List<Recipe> getRecipes();
	public List<Recipe> getRecipesById(Integer id);
	public List<Recipe> getRecipesByMealType(MealType mealType);
	public List<Recipe> searchRecipesByName(String searchTerm);
	public List<Recipe> getRandomRecipe();
	public Recipe deleteRecipeById(Integer id);	
	public Recipe createRecipe(Recipe newRecipe);
			
}
