'use strict'

angular.module('app').controller('HomeController',
  ['$scope', '$localStorage', '$state',
  function($scope, $localStorage,$state){

    this.usuario = localStorage.getItem('usuario');
    this.logged = false;
    if(localStorage.getItem('logged') === 'true'){
      this.logged = true;
    }

  }]);