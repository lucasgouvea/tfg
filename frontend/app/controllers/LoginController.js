'use strict';

angular.module('app').controller('LoginController',
  ['$scope','$state','$stateParams','$sessionStorage','LoginService',
    function($scope,$state,$stateParams,$sessionStorage,LoginService){

  var self = this
  self.intro = 'realize sua autenticacao'
  self.user = {
    name : '',
    password : ''
  }

  //document.location.reload(true);
  //Verifica se está recebendo parâmetro para deslogar
  checkLogout($stateParams.afterLogout)

  $scope.login = function(){
    console.log('LoginController: Logando');
    self.user.name = $scope.name;
    self.user.password = $scope.password;
    LoginService.login(self.user)
      .then(function(){
          redirect();
    });
  }

  function checkLogout(logout){
    if(logout){
      console.log('LoginController: Deslogando');
      localStorage.setItem('logged','false');
      LoginService.logout()
      .then(function(deferred){
        console.log(deferred)
        if(deferred){
          document.location.reload(true)
        } else {
          document.location.reload(true)
        }
        redirect()
      });
    }
    else{
      redirect()
    }
}

  $scope.register = function(){
    console.log('LoginController : Mudando para register');
    $state.go('register');
  }

  function redirect(){
    var logged = localStorage.getItem('logged');
    if(logged == 'true'){
      console.log('LoginController : Logado! Redirecionando');
      $state.go('home',{afterLogin: true});
      //
    }
    else{
      $state.go('login')
    }
  }

}]);




