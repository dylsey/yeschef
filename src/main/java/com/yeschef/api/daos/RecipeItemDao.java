package com.yeschef.api.daos;

import java.util.List;

import com.yeschef.api.models.RecipeItem;

public interface RecipeItemDao {
	
	public List<RecipeItem> getRecipeItems();
	public List<RecipeItem> getRecipeItemsByRecipeId(Integer recipeId);
	public List<RecipeItem> deleteRecipeItemsByRecipeId(Integer recipeId);
	public RecipeItem createRecipeItem(RecipeItem recipeItem);
	
	
	

}
