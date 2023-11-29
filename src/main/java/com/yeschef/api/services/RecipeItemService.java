//package com.yeschef.api.services;
//
//import java.util.List;
//
//
//import com.yeschef.api.daos.RecipeItemDao;
//import com.yeschef.api.daos.RecipeItemDaoImpl;
//import com.yeschef.api.models.RecipeItem;
//import com.yeschef.api.models.RequestError;
//
//import jakarta.ws.rs.WebApplicationException;
//import jakarta.ws.rs.core.Response;
//
//public class RecipeItemService {
//
//	private RecipeItemDao recipeItemDao = new RecipeItemDaoImpl();	
//	
//	public List<RecipeItem> getRecipeItems(){
//		return recipeItemDao.getRecipeItems();
//	}
//	
//	public List<RecipeItem> getRecipeItemsByRecipeId(Integer recipeId){
//		validateRecipeId(recipeId);
//		return recipeItemDao.getRecipeItemsByRecipeId(recipeId);
//	}
//	
//	public RecipeItem deleteRecipeItemsByRecipeId(Integer recipeId){
//		return recipeItemDao.deleteRecipeItemsByRecipeId(recipeId);
//	}
//	
//	public RecipeItem createRecipeItem(RecipeItem recipeItem, Integer recipeId) {
//		validateRecipeId(recipeId);
//		return recipeItemDao.createRecipeItem(recipeItem, recipeId);
//	}
//	
////error handling methods 
//	
//	private void makeError(RequestError error) {
//		Response response = Response.status(400)
//				.entity(error)
//				.build();
//		throw new WebApplicationException(response);
//	
//	}
//	
//	private void validateRecipeId(Integer recipeId) {
//		if (recipeId == null || recipeId < 0 ) {
//			RequestError error = new RequestError(1, 
//					"Invalid recipe id " + recipeId + ". Value must be > 0.");
//			makeError(error);
//			
//		}
//	}
//}
