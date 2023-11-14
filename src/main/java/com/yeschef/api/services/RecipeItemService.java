package com.yeschef.api.services;

import java.util.List;

import com.yeschef.api.daos.RecipeItemDao;
import com.yeschef.api.models.RecipeItem;

public class RecipeItemService {

	private RecipeItemDao recipeItemDao;
	
	public List<RecipeItem> getRecipeItems(){
		return recipeItemDao.getRecipeItems();
	}
	
	public List<RecipeItem> getRecipeItemsByRecipeId(Integer recipeId){
		return recipeItemDao.getRecipeItemsByRecipeId(recipeId);
	}
	
	public List<RecipeItem> deleteRecipeItemsByRecipeId(Integer recipeId){
		return recipeItemDao.deleteRecipeItemByRecipeId(recipeId);
	}
	
	public RecipeItem createRecipeItem(RecipeItem recipeItem) {
		return recipeItemDao.createRecipeItem(recipeItem);
	}
	
}
