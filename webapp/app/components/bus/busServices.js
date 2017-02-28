busModule.service('busServices',['$http', function($http){
	return {
       	getBusRoutes : function(){
			return $http.get(APIURL+"busroutes");
 		},
		getPOIs : function(){
			return $http.get(APIURL+"pOIs");
 		},
		orderBusTicket : function(data){
			return $http.post(APIURL+"busorders",data,{headers:APIHEADER});
 		},
		searchRouteFromTo: function(data){
			var fromCityID = data.fromCity;//integer
			var toCityID = data.toCity;//integer
			// if(angular.isNumber(fromCityID) && angular.isNumber(toCityID)){
			// 	var fromCityUrl = APIURL+"pOIs/"+fromCityID;
			// 	var toCityUrl = APIURL+"pOIs/"+toCityID;
				return $http.get(APIURL+"busroutes/search/findByOriginPOIAndDestPOI?origin="+data.fromCity+"&dest="+data.toCity+"&projection=summary");
			// }
		}

	}
}]);



/*
http://52.77.49.9:9000/busroutes
http://52.77.49.9:9000/pOIs
http://52.77.49.9:9000/busorders

http://52.77.49.9:9000/busroutes/search/findByOriginPOI?origin=http://52.77.49.9:9000/pOIs/1&projection=summary

http://52.77.49.9:9000/busroutes/search/findByOriginPOIAndDestPOI?origin=http://52.77.49.9:9000/pOIs/1&dest=http://52.77.49.9:9000/pOIs/2&projection=summary
http://52.77.49.9:9000/busroutes/search

http://52.77.49.9:9000/busroutes/2

*/