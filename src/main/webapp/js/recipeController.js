/**
 * Access the previously created recipe.  
 */
//change window alerts to console logs
(function() {
	var yeschefapp = angular.module('yeschefapp');

	yeschefapp.controller('recipeController', function($scope, $http, $routeParams, $window) {

		$scope.getRecipesById = function() {
			$http.get("/api/webapi/recipes/" + $routeParams.recipeId)
				.then(function(response) {
					var recipes = response.data;
					if (recipes.length == 1) {
						$scope.recipe = recipes[0];
						console.log('chosen recipe id: ' + $scope.recipe.id);
					}
				}, function(response) {
					console.log('error http GET recipe by Id: ' + response.status);
				});
		}

		$scope.getRecipesById();
		
		$scope.openRecipeLink = function() {
			$window.open($scope.recipe.recipeUrl, '_blank');
		};

		$scope.deleteRecipe = function() {
			var confirmDelete = window
				.confirm('Are you sure you want to delete this recipe? ' + $scope.recipe.name);
			
			if(confirmDelete){
				$http.delete("/api/webapi/recipes/" + $scope.recipe.id)
					.then(function(response) {
						$scope.updateStatus = 'delete successful';
						window.alert('delete recipe successful ' + $scope.recipe.name);
						$scope.disableUpdate = true;
					}, function(response) {
						$scope.updateStatus = 'error trying to delete recipe';
						console.log('error http DELETE recipe: ' + response.status);
					});
			}
		}	

		$scope.getAllMealPlans = function() {
			$http.get("/api/webapi/mealplans/")
				.then(function(response) {
					$scope.mealPlans = response.data;
					console.log('number of plans: ' + $scope.mealPlans.length);
				}, function(response) {
					console.log('error http GET all meal plans: ' + response.status);
				});
		}

		$scope.getAllMealPlans();

		$scope.addRecipeToMealPlanById = function(mealPlanId) {
			$http.put("/api/webapi/mealplans/" + mealPlanId + "/addrecipes/" + $scope.recipe.id)
				.then(function(response) {
					$scope.recipe = response.data;
					window.alert('recipe added to meal plan: ' + mealPlanId);
					$scope.mealPlan = response.data;
					console.log('recipe id: ' + $scope.recipe.id + ' added to meal plan id: ' + mealPlanId)
				}, function(response) {
					console.log('error http PUT add recipe to meal plan by id' + response.status);
				});
		}

		$scope.removeRecipeFromMealPlanById = function(mealPlanId) {
			$http.put("/api/webapi/mealplans/" + mealPlanId + "/removerecipes/" + $scope.recipe.id)
				.then(function(response) {
					$scope.recipe = response.data;
					window.alert('recipe removed from meal plan: ' + mealPlanId);
					$scope.mealPlan = response.data;
					console.log('recipe id: ' + $scope.recipe.id + ' removed from meal plan id: ' + mealPlanId)
				}, function(response) {
					console.log('error http PUT add recipe to meal plan by id' + response.status);
				});
		}

		

	})
})()

