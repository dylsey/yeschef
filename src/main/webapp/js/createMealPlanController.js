/**
 * Access the previously created module 'movieapp'
 */

(function() {
	var yeschefapp = angular.module('yeschefapp');

	yeschefapp.controller('createMealPlanController', function($scope, $http ) {			
		
		
		
		$scope.createMealPlan = function() {
			$http.post("/api/webapi/mealplans", $scope.mealPlan)
			.then(function(response) {				
				$scope.createStatus = 'create successful';
				$scope.disableCreate = true;
				window.alert('clear form to enter another recipe');
			}, function(response) {
				$scope.createStatus = 'error trying to create recipe';	
				console.log('error http POST recipe: ' + response.status);
			});
		}
		
		$scope.clear = function() {
			$scope.createStatus = '';
			$scope.recipe.name = '';

						
			$scope.createForm.$setUntouched();
			$scope.createForm.$setPristine();
								
			$scope.disableCreate = false;
		}
		
	});
	
})()
