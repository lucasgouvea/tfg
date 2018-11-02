'use strict'

angular.module('app').factory('Interruptor', function(){

    function Interruptor(id, local, ligado){
        this.id = id;
        this.local = local;
        this.ligado = ligado;
    }

    return Interruptor;
})