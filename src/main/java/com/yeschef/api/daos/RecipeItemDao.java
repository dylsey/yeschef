package com.yeschef.api.daos;

import java.util.List;
import com.yeschef.api.models.RecipeItem;

public interface RecipeItemDao {
	
	public List<RecipeItem> getRecipeItems();//do i need this method? 
	public List<RecipeItem> getRecipeItemsByRecipeId(Integer recipeId);
	public RecipeItem deleteRecipeItemsByRecipeId(Integer recipeId);
	public RecipeItem createRecipeItem(RecipeItem newRecipeItem, Integer recipeId);
		
}
