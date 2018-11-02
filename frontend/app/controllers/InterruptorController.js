'use strict';

angular.module('app').controller('InterruptorController',
  ['$scope','$state', '$q', 'InterruptorService', 'Interruptor',
  function($scope,$state, $q, InterruptorService, Interruptor){

  var self = this

  $scope.float = /^[0-9]+[.]?[0-9]+$/;
  $scope.time = /^[0-9][0-9][:][0-9][0-9]$/;

  self.interruptor = new Interruptor(1, true)
  self.interruptores = [self.interruptor]


  InterruptorService.getInitial().then(function(response){
    self.interruptor.ligado = response.data
  })


  $scope.change = function (){
      self.interruptor.ligado = !self.interruptor.ligado
      InterruptorService.post(self.interruptor)    
    } 

/*
  self.i1 = new Interruptor(1, 'Sala', true);
  self.i2 = new Interruptor(2, 'Cozinha', false);
  self.i3 = new Interruptor(3, 'Banheiro', true);


  self.interruptores = [self.i1, self.i2, self.i3]
*/



}]);




