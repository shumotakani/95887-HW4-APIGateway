'use strict'

angular.module('app.services', ['ngResource'])

.factory('QuoteService', function($resource) {
	return $resource('/api/quote/:id', {id:'@_id'}, {
		random: {
			method: 'GET',
			url: '/api/quote/random'
		}
	});
})

.factory('ListQuote', function($resource) {
	return $resource('/api/quote/:authorName', {authorName:'@_authorName'}, {
		list: { //a customized method to call the Java method in the backend.
			method: 'GET', 
			url: '/api/quote/list',
			isArray: true
		}
	});
});
