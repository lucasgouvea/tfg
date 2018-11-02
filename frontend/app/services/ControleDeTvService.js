'use strict';

angular.module('app').factory('ControleDeTvService',
  ['$http','$q','$localStorage','$state', 'config',
  function($http, $q, $localStorage, $state, config){

  var factory = {
    post: post
  };

  var header = {headers: {
    'authenticationToken' : localStorage.authenticationToken
  }}

  return factory;

  function post(item) {

    var deferred = $q.defer()
    $http.post(config.backend + '/broker-web/', item, header)
      .then(
          function(response){
            console.log(response)
            deferred.resolve(response)
          },
          function(err){
            console.log(err)
            deferred.reject(err)
          }
      )
    return deferred.promise
  }

}]);




