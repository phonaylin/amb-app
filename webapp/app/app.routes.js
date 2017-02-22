angular.module('ambApp.Route', ['ngRoute']).config(config);

function config ($routeProvider,$httpProvider) {
	$routeProvider
	.when('/',{
		templateUrl :'components/home/homeView.html',
		controller: 'homeController'
	})
	.when('/contactus',{
		templateUrl :'components/bus/busstationsView.html',
		controller: 'homeController'
	})
	.when('/about',{
		templateUrl :'components/bus/busstationsView.html',
		controller: 'homeController'
	})
	.when('/blog',{
		templateUrl :'components/bus/busstationsView.html',
		controller: 'homeController'
	})
	.when('/privacy-policy',{
		templateUrl :'components/bus/busstationsView.html',
		controller: 'homeController'
	})
	.when('/terms',{
		templateUrl :'components/bus/busstationsView.html',
		controller: 'homeController'
	})
	/* Bus Routes */
	.when('/book-bus-ticket-mm',{
		templateUrl :'components/bus/busTicketHome.html',
		controller: 'busController'
	})
	.when('/booking/bus-ticket/:param',{
		templateUrl :'components/bus/busTicketDetailView.html',
		controller: 'busBookTicketController'
	})
	.when('/booking/register/:param',{
		templateUrl :'components/bus/busTicketPayment.html',
		controller: 'busController'
	})
	.when('/bus-operators',{
		templateUrl :'components/bus/busOperatorsView.html',
		controller: 'busController'
	})
	.when('/bus-stations',{
		templateUrl :'components/bus/busStationsView.html',
		controller: 'busController'
	})
	.otherwise({
			redirectTo: '/'
		});
	


}



