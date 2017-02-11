ambApp.directive('ambheader', [function () {
	return {
		restrict: 'A',
		transclude: false,
		templateUrl : "components/core/header/headerView.html",
		controller : "homeController"
	};
}])

ambApp.directive('ambfooter', [function () {
	return {
		restrict: 'A',
		transclude: false,
		templateUrl : "components/core/footer/footerView.html",
		controller : "homeController"
	};
}])