var app = angular.module('app', [
  'ui.router',
  'ngStorage',
  'ui.bootstrap',
  'ngMQTT'
]);

app.constant('config',{
  backend: 'http://localhost:1028'
});

app.config(['MQTTProvider',function(MQTTProvider){
    MQTTProvider.setHref('192.168.1.75:8883');
}]);

app.config(['$stateProvider', '$urlRouterProvider', '$locationProvider',
  function($stateProvider, $urlRouterProvider, $locationProvider) {

    var hello = function(){
      console.log('hi');
    }
    var checkAuthentication = function($localStorage, $location){
      if(localStorage.getItem('logged') === 'false'){
        alert("Realizar autenticação!")
        if($location.path() === '/login'){
          $location.reload()
        }
        else{
          $location.path('/login')
        }
      }
      return true
    }

   $urlRouterProvider.otherwise('/');
    $stateProvider
      /********** Home e Login **********/
      .state('home',{
        url : '/home',
        params : { afterLogin: null },
        templateUrl : 'static/html/home.html',
        controller : 'HomeController',
        controllerAs : 'ctrl'
      })
      .state('login',{
        url : '/login',
        params : {
          afterLogout: null
        },
        templateUrl : 'static/html/login.html',
        controller: 'LoginController',
        controllerAs: 'ctrl'
      })
      .state('register',{
        url : '/register',
        templateUrl : 'static/html/register.html',
        controller: 'RegisterController',
        controllerAs: 'ctrl'
      })
      /********** Services **********/
      .state('services',{
        url : '/services',
        templateUrl : 'static/html/services.html',
        resolve: {
          hello : hello
        }
      })
        .state('services.controle-de-tv',{
          url : '/controle-de-tv',
          templateUrl : 'static/html/controle-de-tv.html',
          controller: 'ControleDeTvController',
          controllerAs: 'ctrl',
        })
        .state('services.interruptor',{
          url : '/interruptor',
          templateUrl : 'static/html/interruptor.html',
          controller: 'InterruptorController',
          controllerAs: 'ctrl',
        })
        .state('services.sensor-de-temperatura',{
          url : '/sensor-de-temperatura',
          templateUrl : 'static/html/sensor-de-temperatura.html',
          controller: 'SensorDeTemperaturaController',
          controllerAs: 'ctrl'
        })
}]);
