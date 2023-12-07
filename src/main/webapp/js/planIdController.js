/**
 * Access the previously created module 'movieapp'
 */

(function() {
	var yeschefapp = angular.module('yeschefapp');

	yeschefapp.controller('planIdController', function($scope, $http, $routeParams, $location) {


		$scope.goToRecipeBrowse = function() {
			//console.log("recipe id: " + recipeId);
			$location.path('/search/');
		}

		$scope.goToRecipeView = function(recipeId) {
			console.log("recipe id: " + recipeId);
			$location.path('/recipes/' + recipeId);
		}

		$scope.updateMealPlan = function() {
			$http.put("/api/webapi/mealplans/", $scope.mealPlan)
				.then(function(response) {
					$scope.updateStatus = 'update successful';
					$scope.mealPlanNameCurrent = $scope.mealPlan.mealPlanName;
				}, function(response) {
					$scope.updateStatusBad = 'error trying to update meal plan';
					console.log('error http PUT meal plan' + response.status);
				});
		}

		$scope.deleteMealPlan = function() {
			var confirmDelete = window.confirm('Are you sure you want to delete this meal plan? ' + $scope.mealPlan.id);

			if (confirmDelete) {
				$http.delete("/api/webapi/mealplans/" + $scope.mealPlan.id)
					.then(function(response) {
						$scope.updateStatus = 'delete successful';
						window.alert('meal plan id: ' + $scope.mealPlan.id + ' successfully deleted!');
						$scope.disableUpdate = true;
					}, function(response) {
						$scope.updateStatusBad = 'error trying to update meal plan';
						console.log('error http PUT meal plan' + response.status);
					});
			}
		}

		$scope.getMealPlansById = function() {
			$http.get("/api/webapi/mealplans/" + $routeParams.mealPlanId)
				.then(function(response) {
					var mealPlans = response.data;
					if (mealPlans.length == 1) {
						$scope.mealPlan = mealPlans[0];
						$scope.mealPlanNameCurrent = $scope.mealPlan.mealPlanName;
						console.log('mealPlan id:' + $scope.mealPlan.id);
					}
				}, function(response) {
					console.log('error http GET mealPlan by id:' + response.status);
				});
		}

		$scope.getMealPlansById();

		$scope.getRecipesFromMealPlanById = function() {
			$http.get("/api/webapi/mealplans/" + $routeParams.mealPlanId + "/recipes/")
				.then(function(response) {
					$scope.recipes = response.data;
					console.log('meal plan id: ' + $routeParams.mealPlanId);
					console.log('number of recipes in plan: ' + $scope.recipes.length);
				}, function(response) {
					console.log('error http GET recipes in meal plan: ' + response.status);
				});
		}
	});
})();
