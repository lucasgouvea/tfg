
var app = angular.module('webscreen',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/webscreen',
    ALBUM_SERVICE_API : 'http://localhost:8080/webscreen/api/album/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'AlbumController',
                controllerAs:'ctrl',
                resolve: {
                    albums: function ($q, AlbumService) {
                        console.log('Load all albums');
                        var deferred = $q.defer();
                        AlbumService.loadAllAlbums().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);