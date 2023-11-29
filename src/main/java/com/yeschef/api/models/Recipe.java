package com.yeschef.api.models;

import java.time.LocalDateTime;

public class Recipe {
	
	private Integer id;//id given by sql database. Primary Key.
	private Integer mealPlanId;
	private String name;
	private String recipeUrl;
	private String imageUrl;
	private MealType mealType;
	private LocalDateTime updateDateTime;
	private LocalDateTime createDateTime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMealPlanId() {
		return mealPlanId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getRecipeUrl() {
		return recipeUrl;
	}
	public void setRecipeUrl(String recipeUrl) {
		this.recipeUrl = recipeUrl;
	}
	public MealType getMealType() {
		return mealType;
	}
	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}

	public void setMealPlanId(Integer mealPlanId) {
		this.mealPlanId = mealPlanId;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}	

}
