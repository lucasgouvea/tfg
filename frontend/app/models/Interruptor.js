'use strict'

angular.module('app').factory('Interruptor', function(){

    function Interruptor(id, ligado){
        this.id = id;
        this.ligado = ligado;
    }

    return Interruptor;
})