/**
 * yes chef module for javascript
 */


(function() {
	var yeschefapp = angular.module('yeschefapp', ['ngRoute']);

	yeschefapp.config(function($routeProvider) {
		$routeProvider
			//search view
			.when("/search", {
				templateUrl: "search.html",
				controller: "searchController"
			})
			//recipe view
			.when("/recipes/:recipeId", {
				templateUrl: "recipe.html",
				controller: "recipeController"
			})
			//create recipe view
			.when("/createrecipe/", {
				templateUrl: "createrecipe.html",
				controller: "createRecipeController"
			})
			//meal plan  view
			.when("/mealplans", {
				templateUrl: "mealplan.html",
				controller: "mealPlanController"
			})
			//meal plan id view
			.when("/mealplans/:mealPlanId", {
				templateUrl: "planid.html",
				controller: "planIdController"
			})

			//update meal plan view
			//.when("/list/", {
			//	templateUrl: "list.html",
			//	controller: "listController"
		//	})

			.when("/stack", {
				templateUrl: "stack.html"
			})
			.when("/resume", {
				templateUrl: "resume.html"
			})
			.otherwise({
				templateUrl: "main.html"
			});
	});

})()