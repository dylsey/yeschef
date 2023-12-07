/**
 * Access the previously created module.  
 */

(function() {
	var yeschefapp = angular.module('yeschefapp');

	yeschefapp.controller('mealPlanController', function($scope, $http, $routeParams, $location) {


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

		$scope.goToPlanIdView = function(mealPlanId) {
			console.log("meal plan id: " + mealPlanId);
			$location.path('/mealplans/' + mealPlanId);
		}
		
			$scope.createMealPlan = function() {
			$http.post("/api/webapi/mealplans", {} )
			.then(function(response) {				
				$scope.createStatus = 'create successful';
				window.alert('new meal plan created. please refresh meal plans to see new plan.')				
				console.log('new meal plan added');
			}, function(response) {
				$scope.createStatus = 'error trying to create recipe';	
				console.log('error http POST recipe: ' + response.status);
			});
		}
		
		
	})
	
})()