busModule.controller('busController', ['$scope','$routeParams','$log','busServices','$location', function ($scope,$routeParams,$log,busServices,$location) {
	$scope.bus = 'This is bus';
	$scope.busRouteList = function(){
			busServices.getBusRouteList().then(function(response) { 
			    $scope.busRouteList = response.data;
			});
	};
	$scope.busRouteList();
	$scope.$log= $scope.busRouteList;
	var RouteCode = $routeParams.Route;

$scope.busRouteDetail = function(){
		if(RouteCode != ''){
			$scope.bookingUrl = SITEURL+'/';
			$scope.routeName = 'RouteCode';
			busServices.getBusRouteDetail().then(function(response) { 
			    $scope.busRouteDetail = response.data;
			});
		}
};

$scope.busRouteDetail();


//$scope.quantity = 10;
$scope.quantity = [0,1,2,3,4,5,6,7,8,9,10];


 $scope.hasQuantity = function(index) {
        var toy = $scope.quantity[index];
        if (toy <= 0) {
            return true;
        }
        return false;
}


$scope.processTicketForm = function() {
	$location.path('/booking/register/14');
};

}]);

