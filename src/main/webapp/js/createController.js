/**
 * Access the previously created module 'movieapp'
 */

(function() {
	var yeschefapp = angular.module('yeschefapp');

	yeschefapp.controller('createController', function($scope, $http) {			
		
		//$scope.genres = ['Horror','Action','Comedy','SciFi'];
		
		$scope.createRecipe = function() {
			$http.post("/api/webapi/recipes", $scope.recipe)
			.then(function(response) {				
				$scope.createStatus = 'create successful';
				$scope.disableCreate = true;
			}, function(response) {
				$scope.createStatus = 'error trying to create recipe';	
				console.log('error http POST recipe: ' + response.status);
			});
		}
		
//		$scope.clear = function() {
//			$scope.recipe.name = '';
//			$scope.recipe.spoonid = '';
//			$scope.recipe.recipeurl = '';
//			$scope.disableCreate = false;
//		}
		
	});
	
})()
