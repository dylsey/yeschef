package com.yeschef.api.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class MealPlan {

	private Integer id;//can make more than one meal plan. do i need to be able to do that? Hold off on this for now. 
	private String mealPlanName; //can name the meal plan
	private List<Recipe> recipes = new ArrayList<>();//adding a list of recipes to the meal plan object		
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMealPlanName() {
		return mealPlanName;
	}
	public void setMealPlanName(String mealPlanName) {
		this.mealPlanName = mealPlanName;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

}
