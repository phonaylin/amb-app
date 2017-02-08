var ambApp = angular.module('ambApp', ['ngRoute','ngResource','ambApp.Route','busModule','ui.bootstrap','moment-picker']);
ambApp.config(['$routeProvider', '$locationProvider','$httpProvider', function ($routeProvider, $locationProvider,$httpProvider) {
	// $httpProvider.defaults.useXDomain = true;
	$locationProvider.hashPrefix('');
	$routeProvider.otherwise({redirectTo:'/'});
	// delete $httpProvider.defaults.headers.common['X-Requested-With'];
}]);


// ambApp.run(function ($rootScope,busService,$http,$location,$route) {


// });
