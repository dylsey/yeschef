package com.yeschef.api.services;

import java.util.List;
import com.yeschef.api.models.RequestError;
import com.yeschef.api.daos.MealPlanDao;
import com.yeschef.api.daos.MealPlanDaoImpl;
import com.yeschef.api.daos.RecipeDaoImpl;
import com.yeschef.api.daos.RecipeDao;
import com.yeschef.api.models.MealPlan;
import com.yeschef.api.models.Recipe;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class MealPlanService {

	private MealPlanDao mealPlanDao = new MealPlanDaoImpl();
	private RecipeDao recipeDao = new RecipeDaoImpl();

	public List<MealPlan> getMealPlans() {
		return mealPlanDao.getMealPlans();
	}

	public List<MealPlan> getMealPlansById(Integer id) {
		validateMealPlanId(id);
		return mealPlanDao.getMealPlansById(id);
	}

	public List<Recipe> getRecipesFromMealPlanByMealPlanId(Integer id) {
		validateMealPlanId(id);
		return mealPlanDao.getRecipesFromMealPlanByMealPlanId(id);
	}

	public MealPlan createMealPlan(MealPlan newMealPlan) {
		return mealPlanDao.createMealPlan(newMealPlan);
	}

	public MealPlan updateMealPlan(MealPlan updateMealPlan) {

		List<MealPlan> myMealPlans = mealPlanDao.getMealPlansById(updateMealPlan.getId());

		if (myMealPlans.size() == 0) {
			mealPlanNotFound(updateMealPlan.getId());
			return null;
		} else {

			return mealPlanDao.updateMealPlan(updateMealPlan);
		}

	}

	public Recipe addRecipeToMealPlanByMealPlanId(Integer id, Integer recipeId) {
		validateMealPlanId(id);
		return mealPlanDao.addRecipeToMealPlanByMealPlanId(id, recipeId);
	}

	public Recipe removeRecipeFromMealPlanByMealPlanId(Integer id, Integer recipeId) {
		validateMealPlanId(id);
		validateRecipeId(id);
		return mealPlanDao.removeRecipeFromMealPlanByMealPlanId(id, recipeId);
	}

	public MealPlan deleteMealPlanByMealPlanId(Integer id) {
		validateMealPlanId(id);
		return mealPlanDao.deleteMealPlanById(id);
	}

	public List<Recipe> getRecipesById(Integer id) {
		validateRecipeId(id);
		return recipeDao.getRecipesById(id);
	}

	/*
	 * error handling methods
	 */
	private void makeError(RequestError error) {
		Response response = Response.status(400).entity(error).build();
		throw new WebApplicationException(response);

	}

	private void validateMealPlanId(Integer id) {
		if (id < 0) {
			RequestError error = new RequestError(1, "Invalid meal plan id " + id + ". Value must be > 0.");
			makeError(error);

		}
	}

	private void mealPlanNotFound(Integer id) {
		RequestError error = new RequestError(2, "Meal Plan not found, id '" + id + "'.");
		makeError(error);
	}

	private void validateRecipeId(Integer id) {
		if (id < 0) {
			RequestError error = new RequestError(3, "Invalid recipe id " + id + ". Value must be > 0.");
			makeError(error);

		}
	}

}
