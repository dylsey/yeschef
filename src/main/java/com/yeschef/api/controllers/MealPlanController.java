package com.yeschef.api.controllers;

import java.util.List;
import com.yeschef.api.models.MealPlan;
import com.yeschef.api.models.Recipe;
import com.yeschef.api.services.MealPlanService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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
	@Path("/{idValue}")
	public List<MealPlan> getMealPlansById(@PathParam("idValue") Integer id) {
		return mealPlanService.getMealPlansById(id);
	}
	
	@GET
	@Path("/{idValue}/recipes")
	public List<Recipe> getRecipesFromMealPlanById(@PathParam("idValue") Integer id) {
		return mealPlanService.getRecipesFromMealPlanByMealPlanId(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public MealPlan createMealPlan(MealPlan newMealPlan) {
		return mealPlanService.createMealPlan(newMealPlan);
	}
	
	@PUT
	@Path("/{idValue}/addrecipes/{recipeId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Recipe addRecipeToMealPlanById(@PathParam("idValue") Integer id, @PathParam("recipeId") Integer recipeId) {
		return mealPlanService.addRecipeToMealPlanByMealPlanId(id, recipeId);
	}
	
	@PUT
	@Path("/{idValue}/removerecipes/{recipeId}")
//	@Consumes(MediaType.APPLICATION_JSON)
	public Recipe removeRecipeFromMealPlanById(@PathParam("idValue") Integer id, @PathParam("recipeId") Integer recipeId) {
		return mealPlanService.removeRecipeFromMealPlanByMealPlanId(id, recipeId);
	}
	
	@DELETE
	@Path("/{idValue}")
	public MealPlan deleteMealPlanById(@PathParam("idValue") Integer id) {
		return mealPlanService.deleteMealPlanByMealPlanId(id);
	}
	

	
}

