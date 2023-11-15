package com.yeschef.api.models;

public class RecipeItem {
	
	private Integer id;
	private Integer recipeId;//foreign key connecting both SQL tables. This needs to be the same as the 		 									recipe ID from the Recipe Class
	private Integer spoonacularIngredientId;
	private String imageUrl;
	private String quantity;
	private String recipeItemName;
	
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
	public Integer getSpoonacularIngredientId() {
		return spoonacularIngredientId;
	}
	public void setSpoonacularIngredientId(Integer spoonacularIngredientId) {
		this.spoonacularIngredientId = spoonacularIngredientId;
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
