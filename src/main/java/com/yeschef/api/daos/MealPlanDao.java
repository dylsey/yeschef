package com.yeschef.api.daos;

import java.util.List;
import com.yeschef.api.models.MealPlan;
import com.yeschef.api.models.Recipe;


public interface MealPlanDao {

	public List<MealPlan> getMealPlans();
	public List<MealPlan> getMealPlansById(Integer id);	
	public List<Recipe> getRecipesFromMealPlanByMealPlanId(Integer id);	
	public MealPlan createMealPlan(MealPlan newMealPlan);	
	public MealPlan updateMealPlan(MealPlan updateMealPlan);
	public Recipe addRecipeToMealPlanByMealPlanId(Integer id, Integer recipeId);
	public Recipe removeRecipeFromMealPlanByMealPlanId(Integer id, Integer recipeId);	
	public MealPlan deleteMealPlanById(Integer id);
	List<Recipe> getRecipesById(Integer id);			
}
