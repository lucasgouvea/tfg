'use strict';

angular.module('app').factory('SensorDeTemperaturaService',
  ['$http','$q','$localStorage','$state', 'config',
  function($http, $q, $localStorage, $state, config){

  var factory = {
    get: get
  };

  var header = {headers: {
    'authenticationToken' : localStorage.authenticationToken
  }}

  return factory;


   function get() {

    var deferred = $q.defer()
    $http.get(config.backend + '/broker/', header)
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




