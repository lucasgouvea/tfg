'use strict';

angular.module('app').controller('IndexController',
  ['$scope', '$localStorage', 'LoginService', 'MQTTService',
  function($scope, $localStorage, LoginService, MQTTService){

  MQTTService.on('hello', function(data){
    alert(data)
  });

  MQTTService.send('hello','word');
  console.log(MQTTService);
  this.logged = localStorage.getItem('logged');
  if(this.logged=='true'){
    $scope.logged = true;
    this.usuario = localStorage.getItem('usuario')
  }
  else{
    $scope.logged = false;
  }

}]);




