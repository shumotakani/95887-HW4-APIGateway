'use strict'

angular.module('app.controllers', [])

.controller('RandomQuoteCtrl', function($scope, QuoteService) {
	QuoteService.random()
		.$promise.then(function(quote) {
			$scope.quote = quote;
		});
})
.controller('ListQuoteCtrl', function($scope, ListQuote, $stateParams) {
	console.log($stateParams.authorName);
	ListQuote.list({authorName: $stateParams.authorName})
		.$promise.then(function(quote) {
			$scope.quotes = quote;
		});
	//list quotes. Issues a GET to /api/quote/:authorName
})
.controller('SaveQuoteCtrl', function($scope, $state, QuoteService) {
    $scope.saveQuote = function() {
        QuoteService.save(
            $scope.quote,
            function(response) {
                $state.go("quote", {});
            },
            function(err) {
                console.log(err);
            }
        );
    };
});