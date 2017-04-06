var ambApp = angular.module('ambApp', ['ngRoute','ngResource','ambApp.Route','busModule','ui.bootstrap','720kb.datepicker','moment-picker','ngCookies']);
ambApp.config(['$routeProvider','$cookiesProvider', '$locationProvider','$httpProvider', function ($routeProvider, $cookiesProvider, $locationProvider,$httpProvider) {

  // $httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN';
  // $httpProvider.defaults.xsrfHeaderName = 'X-XSRF-TOKEN';
  // $httpProvider.interceptors.push('sessionInjector');
	$locationProvider.hashPrefix('');
	$routeProvider.otherwise({redirectTo:'/'});
  //$httpProvider.defaults.useXDomain = true;
  //$httpProvider.defaults.headers.common['Access-Control-Allow-Origin'] = "*";
  //$httpProvider.defaults.headers.common['Access-Control-Allow-Headers'] = "Origin, X-Requested-With, Content-Type, Accept" ;
  
	//delete $httpProvider.defaults.headers.common['X-Requested-With'];
    // $httpProvider.interceptors.push(function() {
    //     return {
    //         response: function(response) {
    //             $httpProvider.defaults.headers.common['X-XSRF-TOKEN'] = response.headers('XSRF-TOKEN');
    //             return response;
    //         }
    //     }    
    // });  
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

// ambApp.factory('sessionInjector', ['SessionService', function(SessionService) {  
//     var sessionInjector = {
//         request: function(config) {
//             if (!SessionService.isAnonymus) {
//                 config.headers['x-session-token'] = SessionService.token;
//             }
//             return config;
//         }
//     };
//     return sessionInjector;
// }]);

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
ambApp.run(function($rootScope,busServices,$http,$location,$route,$cookies) {
   $rootScope.$on('scope.stored', function (event, data) {
        console.log("scope.stored", data);
    });
    $http.defaults.headers.post['X-XSRF-TOKEN'] =  $cookies.get('XSRF-TOKEN');
    console.log('XSRF-TOKEN:'+$cookies['XSRF-TOKEN']);
});



// ambApp.provider('myCSRF',[function(){
//   var headerName = 'X-CSRFToken';
//   var cookieName = 'csrftoken';
//   var allowedMethods = ['GET'];

//   this.setHeaderName = function(n) {
//     headerName = n;
//   }
//   this.setCookieName = function(n) {
//     cookieName = n;
//   }
//   this.setAllowedMethods = function(n) {
//     allowedMethods = n;
//   }
//   this.$get = ['$cookies', function($cookies){
//     return {
//       'request': function(config) {
//         if(allowedMethods.indexOf(config.method) === -1) {
//           // do something on success
//           config.headers[headerName] = $cookies[cookieName];
//         }
//         return config;
//       }
//     }
//   }];
// }]).config(function($httpProvider) {
//   $httpProvider.interceptors.push('myCSRF');
// });