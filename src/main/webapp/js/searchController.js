/**
 * Access the previously created recipe.  
 */

(function() {
	var yeschefapp = angular.module('yeschefapp');

	yeschefapp.controller('searchController', function($scope, $http, $location) {


		$scope.getAllRecipes = function() {
			$http.get("/api/webapi/recipes")
				.then(function(response) {
					$scope.recipes = response.data;
					console.log('number of recipes: ' + $scope.recipes.length);
				}, function(response) {
					console.log('error http GET recipes: ' + response.status);
				});
		}

		$scope.goToRecipeView = function(recipeId) {
			console.log("recipe id: " + recipeId);
			$location.path('/recipes/' + recipeId);
		}

		$scope.getRandomRecipe = function() {
			$http.get("/api/webapi/recipes/random")
				.then(function(response) {
					$scope.recipes = response.data;
					console.log('random recipe selected');
				}, function(response) {
					console.log('error http GET random recipe' + response.status);
				});
		}



		$scope.goToCreateRecipeView = function() {
			//window.alert("going to create recipe");
			$location.path('/createrecipe/');
		}



	})
})()