package com.yeschef.api.daos;

import java.util.List;
import com.yeschef.api.models.MealPlan;
import com.yeschef.api.models.Recipe;


public interface MealPlanDao {

	public List<MealPlan> getMealPlans();
	public List<MealPlan> getMealsPlanById(Integer id);	
	public List<Recipe> getRecipesFromMealPlanById(Integer id);	
	public MealPlan createMealPlan(MealPlan newMealPlan);	
	public MealPlan deleteMealPlanById(Integer id);			
	public Recipe deleteRecipeFromMealPlanById(Integer id, Integer recipeId);
	public Recipe addRecipeToMealPlanById(Integer id, Recipe recipe);	
}
