package com.yeschef.api.services;

import java.util.List;

import com.yeschef.api.daos.RecipeItemDao;
import com.yeschef.api.daos.RecipeItemDaoImpl;
import com.yeschef.api.models.RecipeItem;

public class RecipeItemService {

	private RecipeItemDao recipeItemDao = new RecipeItemDaoImpl();
	
	public List<RecipeItem> getRecipeItems(){
		return recipeItemDao.getRecipeItems();
	}
	
	public List<RecipeItem> getRecipeItemsByRecipeId(Integer recipeId){
		return recipeItemDao.getRecipeItemsByRecipeId(recipeId);
	}
	
	public RecipeItem deleteRecipeItemsByRecipeId(Integer recipeId){
		return recipeItemDao.deleteRecipeItemsByRecipeId(recipeId);
	}
	
	public RecipeItem createRecipeItem(RecipeItem recipeItem) {
		return recipeItemDao.createRecipeItem(recipeItem);
	}
	
}
