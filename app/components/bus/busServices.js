busModule.service('busServices',['$http', function($http){
	return {
		getBusRouteList: function(data){
		  return $http.get(BASEURL+"/data/routes.json").then(function(response) {
		  	 console.log(response.data);
	         return response.data;	
    	});
       },
       getBusRouteDetail : function(data){
       		return $http.get(BASEURL+"/data/footer.json").then(function(response){
 				return response.data;
 			});
 		}
	}
}]);
