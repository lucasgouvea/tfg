'use strict';

angular.module('app').controller('ControleDeTvController',
  ['$scope','$state', '$q', 'ControleDeTvService', 'ControleDeTv',
  function($scope,$state, $q, ControleDeTvService, ControleDeTv){

  var self = this

  $scope.canal = /^[0-9][0-9][0-9]$/;

  self.canal = 10;
  self.num1 = null;
  self.num2 = null;
  self.num3 = null;
  self.controleDeTv = new ControleDeTv(self.canal);

  $scope.addNum = function(num){
    if(self.num1 === null){
      self.num1 = num.toString();
    } else if (self.num2 === null){
      self.num2 = num.toString();
    } else if (self.num3 === null){
      self.num3 = num.toString();
    }
    setCanal();
  }

  $scope.resetar = function(){
    self.num1 = null;
    self.num2 = null;
    self.num3 = null;
    setCanal();
  }

  $scope.enviar = function(){
    setCanal();
    self.controleDeTv.setCanal(self.canal);
    ControleDeTvService.post(self.canal)
  }

  function setCanal(){
    var n1Text = '';
    var n2Text = '';
    var n3Text = '';
    if(!(self.num1 === null)){
      n1Text = self.num1;
    }
    if(!(self.num2 === null)){
      n2Text = self.num2;
    }
    if(!(self.num3 === null)){
      n3Text = self.num3;
    }
    self.canal = n1Text + n2Text + n3Text
  }

}]);




