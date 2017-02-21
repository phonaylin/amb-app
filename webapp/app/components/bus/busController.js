busModule.controller('busController', ['$scope','$routeParams','$log','busServices','$location', function ($scope,$routeParams,$log,busServices,$location) {
    
    $scope.back = function () {
        window.history.back();
    };

	$scope.bus = 'This is bus';
	$scope.busRouteByCity = [];
	$scope.busRouteList = function(){
			busServices.getBusRoutes().then(function(response) { 
			    $scope.busPOIList = response.data._embedded.busroutes;
				var tempTitle = '';
				var num = 0;
				for(var i=0;i<$scope.busPOIList.length;i++){			
					var title = $scope.busPOIList[i]['originPOI']['title'];		
					if(tempTitle != title){
						num = i;
						$scope.busRouteByCity.push({RouteName:title,RouteList:[$scope.busPOIList[i]]});
						tempTitle = title;
					}else{
						$scope.busRouteByCity[num].RouteList.push($scope.busPOIList[i]);
					}
                    var link = $scope.busPOIList[i]._links.self.href;
					var n  = link.split('/');
                    var RouteId = n[n.length - 1];
                    $scope.busRouteByCity[num].RouteList[i]['RouteID'] = RouteId;
				}
			});
	};
	$scope.busRouteList();
	var RouteCode = $routeParams.Route;


    $scope.busRouteDetail = function (){
          
    };

$scope.quantity = [0,1,2,3,4,5,6,7,8,9,10];


$scope.hasQuantity = function(index) {
        var toy = $scope.quantity[index];
        if (toy <= 0) {
            return true;
        }
        return false;
}

$scope.currRoute = null;

$scope.getBusTicketByRoute = function (Route) {
        var FromTo = Route.split('-');
        $scope.currfromCity = Fromto[0]; 
        $scope.currtoCity = Fromto[1];
        $scope.currRoute  = Route;
    };

}]);



busModule.controller("bookTicketsController", function ($scope, $http, $location, $routeParams) {
    var RouteName = $routeParams.param;
    $scope.getBusTicketByRoute(RouteName);
    $scope.formData = {};
    $scope.formData.route_name = $scope.currRoute;
    $scope.formData.date = "Today";
    $scope.formData.originPOI = $scope.currfromCity;
    $scope.formData.destinPOI = $scope.currtoCity;


    $scope.processForm = function () {
        console.log($scope.formData);
        $http({
            method: 'POST',
            url: '/book',
            data: $.param($scope.formData), // pass in data as strings
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            } // set the headers so angular passing info as form data (not request payload)
        })
            .success(function (data) {
                $location.path("/bookings");
            });
    };
});





