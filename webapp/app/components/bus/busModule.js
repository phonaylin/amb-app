var busModule = angular.module('busModule', ['ngCookies']);


busModule.filter('getRouteId',function(){
    return function(input){
        if(input.length>0){
            var link = input;
            var n  = link.split('/');
            var RouteId = n[n.length - 1];
            return RouteId;
        }
    }
});

