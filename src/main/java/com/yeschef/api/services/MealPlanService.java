package com.yeschef.api.services;

import java.util.List;
import com.yeschef.api.models.RequestError;
import com.yeschef.api.daos.MealPlanDao;
import com.yeschef.api.daos.MealPlanDaoImpl;
import com.yeschef.api.models.MealPlan;
import com.yeschef.api.models.Recipe;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class MealPlanService {

	
	private MealPlanDao mealPlanDao = new MealPlanDaoImpl();

	
	public List<MealPlan> getMealPlans() {
		return mealPlanDao.getMealPlans();
	}
	
	public List<MealPlan> getMealPlansById(Integer id) {
		validateMealPlanId(id);
		return mealPlanDao.getMealsPlanById(id);
	}
	
	public List<Recipe> getRecipesFromMealPlanById(Integer id) {
		validateMealPlanId(id);
		return mealPlanDao.getRecipesFromMealPlanById(id);
	}
	
	public Recipe addRecipeToMealPlanById(Integer id, Recipe recipe) {
		validateMealPlanId(id);
		return mealPlanDao.addRecipeToMealPlanById(id, recipe);
	}
	
	public MealPlan createMealPlan(MealPlan newMealPlan) {
		return mealPlanDao.createMealPlan(newMealPlan);
	}

	public MealPlan deleteMealPlanById(Integer id) {
		validateMealPlanId(id);
		return mealPlanDao.deleteMealPlanById(id);
	}
	
	public Recipe deleteRecipeFromMealPlanById(Integer id, Integer recipeId) {
		validateMealPlanId(id);
		validateRecipeId(id);
		return mealPlanDao.deleteRecipeFromMealPlanById(id, recipeId);
	}
/*
 * error handling methods	
 */
	private void makeError(RequestError error) {
		Response response = Response.status(400)
				.entity(error)
				.build();
		throw new WebApplicationException(response);
	
	}
	
	private void validateMealPlanId(Integer id ) {
		if (id < 0 ) {
			RequestError error = new RequestError(1, 
					"Invalid meal plan id " + id + ". Value must be > 0.");
			makeError(error);
			
		}
	}
		
	private void validateRecipeId(Integer id ) {
		if (id < 0 ) {
			RequestError error = new RequestError(1, 
					"Invalid recipe id " + id + ". Value must be > 0.");
			makeError(error);
			
		}
	}
	
}

