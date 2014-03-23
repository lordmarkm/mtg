var cardlist = angular.module('cardlistApp', []);

cardlist.controller('CardListCtrl', function($scope, $http) {
	
	$http.get('/cardlist/user/' + vars.username).success(function(data){
		$scope.bundles = data;
	});
	
});