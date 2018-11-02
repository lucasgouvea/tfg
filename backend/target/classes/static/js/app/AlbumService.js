'use strict';

angular.module('webscreen').factory('AlbumService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllAlbums: loadAllAlbums,
                getAllAlbums: getAllAlbums,
                getAlbum: getAlbum,
                createAlbum: createAlbum,
                updateAlbum: updateAlbum,
                removeAlbum: removeAlbum
            };

            return factory;

            function loadAllAlbums() {
                console.log('Fetching all albums');
                var deferred = $q.defer();
                $http.get(urls.ALBUM_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all albums');
                            $localStorage.albums = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading albums');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllAlbums(){
                return $localStorage.albums;
            }

            function getAlbum(id) {
                console.log('Fetching Album with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.ALBUM_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Album with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading album with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createAlbum(album) {
                console.log('Creating Album');
                console.log(album.name);
                var deferred = $q.defer();
                $http.post(urls.ALBUM_SERVICE_API, album)
                    .then(
                        function (response) {
                            loadAllAlbums();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Album : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateAlbum(album, id) {
                console.log('Updating Album with id '+id);
                var deferred = $q.defer();
                $http.put(urls.ALBUM_SERVICE_API + id, album)
                    .then(
                        function (response) {
                            loadAllAlbums();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Album with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeAlbum(id) {
                console.log('Removing Album with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.ALBUM_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllAlbums();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Album with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);