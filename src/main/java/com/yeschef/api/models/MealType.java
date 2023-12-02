package com.yeschef.api.models;

public enum MealType {
	Beef, Pork, Bird, Seafood, Vegetarian, Vegan;
	
	public static MealType convertStringtoMealType(String value) {
		MealType myMealType = null;
		
		for (MealType mealType : MealType.values()) {
			if (mealType.toString().equalsIgnoreCase(value)) {
				myMealType = mealType;
				break;
			}
		}
		return myMealType;
	}

}
