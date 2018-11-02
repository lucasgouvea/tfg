'use strict'

angular.module('app').factory('ControleDeTv', function(){

  function ControleDeTv(canal){
    this.canal = canal;
  }

  ControleDeTv.prototype.setCanal = function(canal){
    this.canal = canal;
  }

  return ControleDeTv;
})