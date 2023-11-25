/**
 * Access the previously created recipe.  
 */
//change window alerts to console logs
(function() {
    var yeschefapp = angular.module('yeschefapp');

    yeschefapp.controller('recipeController', function($scope, $http, $routeParams) {           

        $scope.getRecipesById = function() {
			var recipeId = $routeParams.recipeId;
            $http.get("/api/webapi/recipes/" + recipeId)
                .then(function(response) {
					var recipes = response.data;
					if (recipes.length == 1) {
						$scope.recipe = recipes[0];
						window.alert('recipe id: ' + $scope.recipe.id);	
					} else {
						//error message
					}
              }, function(response) {
                    console.log('error http GET recipe by Id: ' + response.status);
                });
        }
        $scope.getRecipesById();
        
    })
})()












