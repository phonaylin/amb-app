busModule.controller('busController',function ($scope,$routeParams,$log,busServices,$location,Scopes,$cookies) {
    
    $scope.back = function () {
        window.history.back();
    };
    $scope.cityIdmapping = [];	
	$scope.busRouteByCity = [];
	$scope.busRouteList = function(){
			busServices.getBusRoutes().then(function(response) { 
			    $scope.busPOIList = response.data._embedded.busroutes;
				var tempTitle = '';
                var destinTitle = '';
				var num = 0;
				for(var i=0;i<$scope.busPOIList.length;i++){			
					var title = $scope.busPOIList[i]['originPOI']['title'];
                    var destin = $scope.busPOIList[i]['destPOI']['title'];		
					if(tempTitle != title){
						num = i;
						$scope.busRouteByCity.push({RouteName:title,RouteList:[$scope.busPOIList[i]]});
						tempTitle = title;
                        destinTitle = destin;
					}else{
                        var routeName = tempTitle+destin;
                        var diffrouteName = title+destinTitle;
                        if(routeName != diffrouteName ){
                            $scope.busRouteByCity[num].RouteList.push($scope.busPOIList[i]);    
                        }
					}
				}
			});            
	};
    $scope.busRouteList();
});

busModule.controller('busBookTicketController',function ($cookies,$scope,$routeParams,$log,busServices,$location,Scopes) {

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
                                //$scope.busRouteListbyCity[i].Qty = 10;
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
    $scope.formData.order_quantity = [];
    $scope.formData.tickets = [];

    //BOOK BUS TICKET
    $scope.formData.totalTickets = 0;
    $scope.processForm = function () {
          var checkQty = Object.keys($scope.formData.order_quantity);
            var arrayQty = [];
                angular.forEach($scope.busRouteListbyCity,function(value,key){
                    angular.forEach($scope.formData.order_quantity, function(value1, key1){
                    if(value1 > 0){
                        if(key1 == value.RouteID){
                            $scope.formData.totalTickets += value1;
                            value.order_qty = value1;
                            value.fare = value.fare.substring(3, value.fare.length);
                            arrayQty.push(value);
                        }
                    }
                    });
                });
             $scope.formData.tickets = arrayQty;
             console.log($scope.formData.tickets);
             if($scope.formData.tickets.length <= 0){
                $scope.messages = 'You haven\'t select any ticket yet';     
             }else{ 
                Scopes.store('busBookTicketController', $scope.formData);
                $cookies.putObject('busTicketDetail', $scope.formData);
                $location.path("/booking/bus-ticket-payment-details/");
             }
    };
});

busModule.controller('busTicketPaymentController',function ($cookies,$scope,$routeParams,busServices,$location,Scopes) {
    $scope.minDate = new Date()+1;
    $scope.busTicketDetail ={};
    $scope.getNumber = function(num) {
        return new Array(num);   
    }
    $scope.totalTickets = 0;
//     $scope.ticketForm = {
//         "buyerEmailAddress": "phonaylin@gmail.com",
//         "buyerMobileNumber": "82011660",
//         "travelDate": "2017-03-20",
//         "comment": "Whatever man",
//         "orderItems": [
//             {
//                 "passengerFirstName": "Nay Lin",
//                 "passengerLastName": "Aung",
//                 "passengerEmailAddress": "phonaylin@gmail.com",
//                 "passengerContactNumber": "+6582011660",
//                 "travelDate": "2017-01-01",
//                 "ticketNumber" : "aaa"
//             },
//             {
//                 "passengerFirstName": "Nay Lin",
//                 "passengerLastName": "Aung",
//                 "passengerEmailAddress": "phonaylin@gmail.com",
//                 "passengerContactNumber": "+6582011660",
//                 "travelDate": "2017-01-01",
//                 "ticketNumber" : "aaa"
//             }
//         ],
//         "busRoute": "http://localhost:9000/busroutes/2"
//   };
    $scope.ticketForm = {};
    $scope.busTicketDetail = Scopes.get('busBookTicketController');
    $scope.subTotal = 0;  
    $scope.grandTotal = 0;
    if($cookies.getObject('busTicketDetail')){
        $scope.busTicketDetail = $cookies.getObject('busTicketDetail');
        $scope.calculate = function() {
            angular.forEach($scope.busTicketDetail.tickets,function(value,key){
                $scope.subTotal += (value.fare*value.order_qty);
            })
            $scope.grandTotal = $scope.subTotal;
        }
        $scope.calculate();
    }else{
        $location.path("/not-found");
    }
    $scope.registerBooking = function(){
        if($scope.ticketForm){
            $cookies.remove('busTicketDetail');
            console.log($scope.ticketForm);    
            if($scope.ticketForm.paymentMethod=='PAYPAL'){

            }
        }
        console.log('This is booking payment page');
    }
});


