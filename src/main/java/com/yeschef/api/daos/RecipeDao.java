package com.yeschef.api.daos;

import java.util.List;
import com.yeschef.api.models.MealType;
import com.yeschef.api.models.Recipe;

public interface RecipeDao {
	
	public List<Recipe> getRecipes();
	public List<Recipe> getRecipesById(Integer id);
	public List<Recipe> getRecipesByMealType(MealType mealType);
	public List<Recipe> searchRecipesByName(String searchTerm);
	public Recipe deleteRecipeById(Integer id);	
	public Recipe createRecipe(Recipe newRecipe);
			
}
