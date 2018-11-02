'use strict';

angular.module('app').controller('RegisterController',
  ['$scope','RegisterService', function($scope,RegisterService){


  var self = this;
  self.intro = 'registro de usuario';
  self.user = {
    name : '',
    password : ''
  };

  $scope.register = function(){
    console.log("Registrando");
    self.user.name = $scope.name;
    self.user.password = $scope.password;
    RegisterService.register(self.user);
  }


}]);




