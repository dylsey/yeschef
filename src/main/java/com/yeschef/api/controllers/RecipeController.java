package com.yeschef.api.controllers;

import java.util.List;
import com.yeschef.api.models.Recipe;
import com.yeschef.api.models.RecipeItem;
import com.yeschef.api.services.RecipeItemService;
import com.yeschef.api.services.RecipeService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Path("/recipes")
public class RecipeController {
	
	private RecipeService recipeService = new RecipeService();
	private RecipeItemService recipeItemService = new RecipeItemService();
	
	//gets all recipes in the home database
	@GET
	public List<Recipe> getRecipes(){		
		return recipeService.getRecipes();
	}
	//pulls recipe in home database based on SQL's given id number
	@GET
	@Path("/{idValue}")
	public List<Recipe> getRecipeById(@PathParam("idValue") Integer id) {
		return recipeService.getRecipesById(id);
	}
	
	@DELETE // this will delete both the recipe and the items in the recipe. 
	@Path("/{idValue}")
	public Recipe deleteRecipeById(@PathParam("idValue") Integer id) {
		recipeItemService.deleteRecipeItemsByRecipeId(id);
		return recipeService.deleteRecipeById(id);
	}
	
	//will I need to feed this method input from a search box on the home page? 
	@GET
	@Path("/search/{searchTerm}")
	public List<Recipe> searchRecipesByName(@PathParam("searchTerm") String searchTerm){
		return recipeService.searchRecipesByName(searchTerm);
	}
	
	//returns all items in the recipe items table. will i need to make these available to choose to add to the grocery cart? how? 
	//is this method necessary? 
	@GET
	@Path("/items")
	public List<RecipeItem> getRecipeItems(){
		return recipeItemService.getRecipeItems();
	}
	
	
	@GET
	@Path("/items/{idValue}") //@Path("/{idValue}/items") this was the original path and I cannot remember why. it does'nt make sense to me this way. 
	public List<RecipeItem> getRecipeItemsByRecipeId(@PathParam("idValue") Integer recipeId) {
		return recipeItemService.getRecipeItemsByRecipeId(recipeId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Recipe createRecipe(Recipe newRecipe) {
		return recipeService.createRecipe(newRecipe);
	}
	
//	does this method work correctly? 
	@POST
	@Path("/items/{idValue}")
	@Consumes(MediaType.APPLICATION_JSON) 
	public RecipeItem createRecipeItem(RecipeItem recipeItem, @PathParam("idValue") Integer recipeId) {
		recipeItem.setRecipeId(recipeId);
		return recipeItemService.createRecipeItem(recipeItem, recipeId);
	}
	
}
