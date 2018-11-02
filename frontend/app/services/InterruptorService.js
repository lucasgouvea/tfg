'use strict';

angular.module('app').factory('InterruptorService',
  ['$http','$q','$localStorage','$state', 'config',
  function($http, $q, $localStorage, $state, config){

  var factory = {
    post: post,
    getInitial : getInitial
  };

  var header = {headers: {
    'authenticationToken' : localStorage.authenticationToken
  }}

  return factory;

  function post(item) {
    var deferred = $q.defer()
    $http.post("http://localhost:1028/broker-web/", item, header)
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

    function getInitial() {
    var deferred = $q.defer()
    $http.get("http://localhost:1028/broker/", header)
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




