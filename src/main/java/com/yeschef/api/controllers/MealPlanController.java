package com.yeschef.api.controllers;

import java.util.List;
import com.yeschef.api.models.MealPlan;
import com.yeschef.api.models.Recipe;
import com.yeschef.api.services.MealPlanService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Path("/mealplans")
public class MealPlanController {


	private MealPlanService mealPlanService = new MealPlanService();

	@GET
	public List<MealPlan> getMealPlans() {
		return mealPlanService.getMealPlans();
	}
	
	@GET
	@Path("/mealplans/{idValue}")
	public List<Recipe> getMealPlansById(@PathParam("idValue") Integer id) {
		return mealPlanService.getRecipesFromMealPlanById(id);
	}
	
	@GET
	@Path("/mealplans/{}")
	public List<Recipe> getRecipesFromMealPlanById(@PathParam("") Integer id) {
		return mealPlanService.getRecipesFromMealPlanById(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public MealPlan createMealPlan(MealPlan newMealPlan) {
		return mealPlanService.createMealPlan(newMealPlan);
	}
	
	@DELETE
	@Path("/mealplans/{idValue}")
	public MealPlan deleteMealPlanById(@PathParam("idValue") Integer id) {
		return mealPlanService.deleteMealPlanById(id);
	}
	
	@DELETE
	@Path("/{idValue}")
	public Recipe deleteRecipeFromMealPlanById(@PathParam("idValue/idValue") Integer id, Integer recipeId) {
		return mealPlanService.deleteRecipeFromMealPlanById(id, recipeId);
	}
	
	
}

