'use strict';

angular.module('app').factory('RegisterService',
  ['$http','$q','$localStorage','config', function($http,$q,$localStorage,config){

  var factory = {
    register: register
  };

  return factory;

  var self = this;
  self.user = {
    name : '',
    password : ''
  };

  function register(user){
    console.log('Registrando');
    $http.post(config.backend + '/register/',user)
      .then(
        function(response){
          console.log('Registrado');
        },
        function(errResponse){
          console.log('Erro' + errResponse.status);
        }
      );
  }


}]);




