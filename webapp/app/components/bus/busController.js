busModule.controller('busController',function ($scope,$routeParams,$log,busServices,$location) {
    
    $scope.back = function () {
        window.history.back();
    };
    $scope.cityIdmapping = [];
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
                    // var link = $scope.busPOIList[i]._links.self.href;
					// var n  = link.split('/');
                    // var RouteId = n[n.length - 1];
                    // $scope.busRouteByCity[num].RouteList[i]['RouteID'] = RouteId;
				}
			});            
	};
    $scope.busRouteList();
});

busModule.controller('busBookTicketController',function ($scope,$routeParams,$log,busServices,$location) {

$scope.quantity = [0,1,2,3,4,5,6,7,8,9,10];

$scope.hasQuantity = function(index) {
        var toy = $scope.quantity[index];
        if (toy <= 0) {
            return true;
        }
        return false;
}

    $scope.currRoute = null;
    $scope.currfromCity = "";
    $scope.currtoCity = "";
    $scope.currfromCityID = "";
    $scope.currtoCityID = "";
    $scope.formData = {};

    $scope.busRouteDetail = function(data){
        if(data){
        var FromTo = data.split('-');
            $scope.currfromCity = FromTo[0]; 
            $scope.currtoCity = FromTo[1];
            $scope.currRoute = data;
            busServices.getPOIs().then(function(response) { 
                var POIs = response.data._embedded.pOIs;
                for(var i=0;i<POIs.length;i++){
                    if(POIs[i].title == $scope.currfromCity){
                        $scope.currfromCityID = POIs[i]._links.pOI.href;
                    }
                    if(POIs[i].title == $scope.currtoCity){
                        $scope.currtoCityID = POIs[i]._links.pOI.href;
                    }
                }
                if($scope.currfromCityID !='' && $scope.currtoCityID != ''){
                    busServices.searchRouteFromTo({fromCity:$scope.currfromCityID,toCity:$scope.currtoCityID}).then(function(response){
                            $scope.busRouteListbyCity = response.data._embedded.busroutes;
                            for(var i =0;i<$scope.busRouteListbyCity.length;i++){
                                var link = $scope.busRouteListbyCity[i]._links.self.href;
                                var n  = link.split('/');
                                var RouteId = n[n.length - 1];
                                $scope.busRouteListbyCity[i].RouteID = RouteId;
                                $scope.busRouteListbyCity[i].Qty = 10;
                            }
                            $scope.busRouteListbyCity['fromCity'] = $scope.currfromCity;
                            $scope.busRouteListbyCity['toCity'] = $scope.currtoCity;
                            console.log($scope.formData);
                    });
                }
            });
        }
    };

    $scope.busRouteDetail($routeParams.param);
    $scope.formData.route_name = $scope.currRoute;
    $scope.formData.date = "Today";
    $scope.formData.fromCity = $scope.currfromCity;     
    $scope.formData.toCity = $scope.currtoCity;
    $scope.formData.route_data = [];
    $scope.formData.order_quantity = {};

    //BOOK BUS TICKET
    $scope.processForm = function () {
          var checkQty = Object.keys($scope.formData.order_quantity);
            var arrayQty = [];
            angular.forEach($scope.formData.order_quantity, function(value, key){
                if(value > 0){
                    arrayQty.push({RouteID:key,Qty:value});
                }
            });
             $scope.formData.tickets = arrayQty;
             console.log($scope.formData.tickets);
             console.log($scope.busRouteListbyCity);
             if($scope.formData.tickets.length <= 0){
                $scope.messages = 'You haven\'t select any ticket yet';     
             }else{
                 delete $scope.formData.order_quantity; 
                 angular.forEach($scope.busRouteListbyCity,function(value,key){
                     angular.forEach($scope.formData.tickets,function(value1,key1){
                         if(value1.RouteID == value.RouteID){
                            // $scope.formData.route_data.push({RouteID:value.RouteID,
                            //                                 order_qty:key1,
                            //                                 fare:value.fare,
                            //                                 originPOI:value.originPOI,
                            //                                 bus:value.bus,
                            //                                 departureTime:value.departureTime,
                            //                                 _links:value._links
                            //                                 });
                         }
                     });
                 });
                var formdata = JSON.stringify($scope.formData);
                console.log(formdata);
                $location.path("/booking/bus-ticket-payment-details/"+formdata);
             }
    };
});

busModule.controller('busTicketPaymentController',function ($scope,$routeParams,busServices,$location) {
    $scope.busTicketDetail ={};
    if($routeParams.param){
        var ticket= JSON.parse($routeParams.param);
        if(ticket.route_name){
            $scope.busTicketDetail = ticket;
            console.log($scope.busTicketDetail);
        }else{
            $location.path("/not-found");
        }
    }else{
            $location.path("/not-found");
    }
});


