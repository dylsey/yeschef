/**
 * Access the previously created module.  
 */

(function(){
 	var yeschefapp = angular.module('yeschefapp');
 
	 yeschefapp.controller('updateController', function($scope, $http, $routeParams){
 		
 		
 		var recipeId = $routeParams.recipeId;
 		window.alert('id of movie passed from search: ' + recipeId)
 	
	 
 
 })
 })()