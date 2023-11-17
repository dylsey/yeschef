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
	
	
	@GET
	@Path("/search/{searchTerm}")
	public List<Recipe> searchRecipesByName(@PathParam("searchTerm") String searchTerm){
		return recipeService.searchRecipesByName(searchTerm);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Recipe createRecipe(Recipe recipe) {
		return recipeService.createRecipe(recipe);
	}
	
	@GET
	@Path("/{idValue}/items")
	public List<RecipeItem> getRecipeItemsByRecipeId(@PathParam("idValue") Integer id) {
		return recipeItemService.getRecipeItemsByRecipeId(id);
	}
	
	@POST
	@Path("/{idValue}/items")
	@Consumes(MediaType.APPLICATION_JSON) 
	public RecipeItem createRecipeItem(RecipeItem recipeItem, @PathParam("idValue") Integer recipeId) {
		recipeItem.setRecipeId(recipeId);
		return recipeItemService.createRecipeItem(recipeItem);
	}
	
}
