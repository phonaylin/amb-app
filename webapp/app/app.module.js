var ambApp = angular.module('ambApp', ['ngRoute','ngResource','ambApp.Route','busModule','ui.bootstrap','moment-picker']);
ambApp.config(['$routeProvider', '$locationProvider','$httpProvider', function ($routeProvider, $locationProvider,$httpProvider) {
	// $httpProvider.defaults.useXDomain = true;
	$locationProvider.hashPrefix('');
	$routeProvider.otherwise({redirectTo:'/'});
	// delete $httpProvider.defaults.headers.common['X-Requested-With'];
}]);



ambApp.filter('customCurrency', function() { 

  // Create the return function and set the required parameter name to **input**
  // setup optional parameters for the currency symbol and location (left or right of the amount)
  return function(input, symbol, place) {

    // Ensure that we are working with a number
    if(isNaN(input)) {
      return input;
    } else {

      // Check if optional parameters are passed, if not, use the defaults
      var symbol = symbol || '$';
      var place = place === undefined ? true : place;

      // Perform the operation to set the symbol in the right location
      if( place === true) {
        return symbol + input;
      } else {
        return input + symbol;
      }

    }
  }

});


ambApp.filter('range', function() {
  return function(input, min, max) {
    min = parseInt(min); //Make string input int
    max = parseInt(max);
    for (var i=min; i<max; i++)
      input.push(i);
    return input;
  };
});


ambApp.factory('Scopes', function ($rootScope) {
    var mem = {};
 
    return {
        store: function (key, value) {
            mem[key] = value;
        },
        get: function (key) {
            return mem[key];
        }
    };
});
ambApp.run(function($rootScope,busServices,$http,$location,$route) {
   $rootScope.$on('scope.stored', function (event, data) {
        console.log("scope.stored", data);
    });
});
