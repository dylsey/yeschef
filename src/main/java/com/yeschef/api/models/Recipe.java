package com.yeschef.api.models;

import java.time.LocalDateTime;

public class Recipe {
	
	private Integer id;//id given by sql database. Primary Key. 
	private String name;
	private Integer spoonacularRecipeId;//need to get this from spoonacular api call
	private String imageUrl;//need to get this from spoonacular api call
	private String recipeUrl;
	
	
	public String getRecipeUrl() {
		return recipeUrl;
	}

	//need to set this url via spoonacular api data
	public void setRecipeUrl(String recipeUrl) {
		this.recipeUrl = recipeUrl;
	}

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
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSpoonacularRecipeId() {
		return spoonacularRecipeId;
	}
	// need to set this via spoonacular
	public void setSpoonacularRecipeId(Integer spoonacularRecipeId) {
		this.spoonacularRecipeId = spoonacularRecipeId;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	//need to set this via spoonacular
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}			
	
}
