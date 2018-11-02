angular.module('app').factory('LoginService',
  ['$http', '$q', '$localStorage', '$state', '$rootScope', 'config',
  function($http, $q, $localStorage, $state, $rootScope, config){

  var self = this;
  self.logged = false;
  self.authenticationToken = '';
  self.user = {
    name : '',
    password : ''
  };

  var header = {headers: {
    'authenticationToken' : localStorage.authenticationToken
  }}

  var factory = {

    login : function(user){
      var deferred = $q.defer();
      $http.post(config.backend + '/login/',user)
        .then(function(response){
          localStorage.setItem('authenticationToken', response.data.token);
          localStorage.setItem('logged', 'true');
          localStorage.setItem('usuario', user.name)
          deferred.resolve(true);
        },
        function(errResponse){
          console.log(errResponse.status); //400 BAD_REQUEST = wrong password
                                          //404 NOT_FOUND = user not found

          deferred.resolve(true);
        }
      );
    return deferred.promise;
    },

    logout : function(){
      var deferred = $q.defer();
      $http.get(config.backend + '/logout/',header)
        .then(function(response){
          localStorage.setItem('logged', 'false');
          localStorage.setItem('authenticationToken', 0);
          localStorage.setItem('usuario', '');
          deferred.resolve(true);
        },
        function(errResponse){
          console.log(errResponse.status); //400 BAD_REQUEST
          deferred.resolve(false);
        }
      );
    return deferred.promise;
    },

    isLogged : function(){
      return this;
    },

    setLogged : function(isLogged){
      self.logged = isLogged
    }
  };

  return factory;

}]);






