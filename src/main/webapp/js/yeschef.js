/**
 * yes chef model for javascript
 */


(function(){
 var yeschefapp = angular.module('yeschefapp', ['ngRoute']);
 
	 yeschefapp.config(function($routeProvider) {
	  $routeProvider
	  .when("/search", {
	    templateUrl : "search.html",
	    controller : "searchController"
	  })
	  .when("/create", {
	    templateUrl : "create.html",
	    controller : "createController"
	  })
	  .when("/update/:recipeId", {
	    templateUrl : "update.html",
	    controller: "updateController"
	  })
	  .when("/stack", {
	    templateUrl : "stack.html"
	  })
	  .when("/resume", {
	    templateUrl : "resume.html"
	  })
	  .otherwise({
	    templateUrl : "main.html"
	  });
	});
 
})()