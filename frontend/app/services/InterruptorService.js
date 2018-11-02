'use strict';

angular.module('app').factory('InterruptorService',
  ['$http','$q','$localStorage','$state', 'config',
  function($http, $q, $localStorage, $state, config){

  var factory = {
    createItem: createItem,
  };

  var header = {headers: {
    'authenticationToken' : localStorage.authenticationToken
  }}

  return factory;

  function createItem(item, model) {

    var deferred = $q.defer()
    $http.post(config.backend + '/' + model + '/', item, header)
      .then(
          function(response){
            deferred.resolve(response)
          },
          function(err){
            deferred.reject(err)
          }
      )
    return deferred.promise
  }

}]);




