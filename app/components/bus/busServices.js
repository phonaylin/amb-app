busModule.service('busServices',['$http', function($http){
	return {
		getBusRouteList : function(data){
	   		return $http.get(BASEURL+"/data/routes.json");	
       },
       getBusRouteDetail : function(){
 			return $http.get(BASEURL+"/data/busroutesdetails.json");
 		}
	}
}]);
