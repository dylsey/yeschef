package com.yeschef.api.models;

import java.time.LocalDateTime;

public class RecipeItem {

	private Integer id;
	private String recipeItem;
	private Integer recipeId;// foreign key connecting both SQL tables. This needs to be the same as the
								// recipe ID from the Recipe Class
	private Integer spoonacularRecipeId; // also links both data tables... not Foreign key but should be the same in
											// each table
	private String imageUrl;
	private String quantity;
	private LocalDateTime updateDateTime;
	private LocalDateTime createDateTime;
	private Recipe recipe;

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

	public String getRecipeItem() {
		return recipeItem;
	}

	public void setRecipeItem(String recipeItem) {
		this.recipeItem = recipeItem;
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

//	this needs to be linked to the auto Id created in SQL
	public void setRecipeId(Integer recipeId) {
		if (this.recipe != null && this.spoonacularRecipeId.equals(this.recipe.getSpoonacularRecipeId())) {
			this.recipe.setId(recipeId);
			this.recipeId = recipeId;
		}
	}

	public Integer getSpoonacularRecipeId() {
		return spoonacularRecipeId;
	}

	// might need to make this method get the spoon id from the recipe item?
	public void setSpoonacularRecipeId(Integer spoonacularRecipeId) {
		this.spoonacularRecipeId = spoonacularRecipeId;
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
