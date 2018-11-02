'use strict';

angular.module('app').controller('SensorDeTemperaturaController',
  ['$scope','$state', '$q', 'SensorDeTemperaturaService',
  function($scope,$state, $q, SensorDeTemperaturaService){

  var self = this

  $scope.float = /^[0-9]+[.]?[0-9]+$/;
  $scope.time = /^[0-9][0-9][:][0-9][0-9]$/;

  self.sensores = [
    {
        id: 1,
        local: "Sala",
        temperatura: 25.4
    },
    {
        id: 2,
        local: "Cozinha",
        temperatura: 24.2
    },
    {
        id: 3,
        local: "Quarto",
        temperatura: 23.3
    },
    {
        id: 4,
        local: "Banheiro",
        temperatura: 23.3
    },
    {
        id: 5,
        local: "Quarto 2",
        temperatura: 23.9
    },
    {
        id: 6,
        local: "Quarto 3",
        temperatura: 20.9
    }
  ]

  setInterval(function(){
    var sensor = Math.floor((Math.random()*6));
    self.sensores[sensor].temperatura = parseFloat((self.sensores[sensor]
        .temperatura + 0.1).toFixed(1));
    $scope.$apply()
  }, 1000);

  $scope.update = function(){
    SensorDeTemperaturaService.get()
      .then(function(response){
        self.sensores[0].temperatura = response.data
      }, function(err){
        console.log(err)
      })
  }


}]);




