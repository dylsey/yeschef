package com.yeschef.api.models;

import java.time.LocalDateTime;

public class RecipeItem {
	
	private Integer id;
	private String recipeItemName;
	private Integer recipeId;//foreign key connecting both SQL tables. This needs to be the same as the recipe ID from the Recipe Class
	private Integer spoonacularRecipeId; // also links both data tables... Is 
	private String imageUrl;
	private String quantity;
	
	private LocalDateTime updateDateTime;
	private LocalDateTime createDateTime;
	
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
	public String getRecipeItemName() {
		return recipeItemName;
	}
	public void setRecipeItemName(String recipeItemName) {
		this.recipeItemName = recipeItemName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}
	public Integer getSpoonacularRecipeId() {
		return spoonacularRecipeId;
	}
	public void setSpoonacularRecipeId(Integer spoonacularIngredientId) {
		this.spoonacularRecipeId = spoonacularIngredientId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
