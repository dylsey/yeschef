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
				//window.alert("recipe id: " + recipeId);
			$location.path('/recipes/' + recipeId);
		}


	})
})()