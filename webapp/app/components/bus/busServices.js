busModule.service('busServices',['$http', function($http){
	return {
       	getBusRoutes : function(){
			return $http.get("http://52.77.49.9:9000/busroutes");
 		},
		getPOIs : function(){
			return $http.get("http://52.77.49.9:9000/pOIs");
 		},
		orderBusTicket : function(data){
			return $http.post("http://52.77.49.9:9000/busorders",data,{headers:APIHEADER});
 		}
	}
}]);

/*
http://52.77.49.9:9000/busroutes
http://52.77.49.9:9000/pOIs
http://52.77.49.9:9000/busorders

http://52.77.49.9:9000/busroutes/search/findByOriginPOI?origin=http://52.77.49.9:9000/pOIs/1&projection=summary

http://52.77.49.9:9000/busroutes/search/findByOriginPOIAndDestPOI?origin=http://52.77.49.9:9000/pOIs/1,destin=http://52.77.49.9:9000/pOIs/2,projection=summary
http://52.77.49.9:9000/busroutes/search
*/