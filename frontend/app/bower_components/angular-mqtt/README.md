
```
<script src="bower_components/angular/angular.js"></script>
<script src="src/browserMqtt.js"></script>
<script src="src/angular-MQTT.js"></script>

```

---


```
    var app = angular.module('app', [
        'ngMQTT'
    ]);

    app.config(['MQTTProvider',function(MQTTProvider){
        MQTTProvider.setHref('ws://cv.endaosi.com:18585');
    }]);

    app.controller('indexCtrl', ['$scope', 'MQTTService', function ($scope, MQTTService) {
        MQTTService.on('hello', function(data){
            alert(data)
        });


        MQTTService.send('hello','word');
        MQTTService.send('hello','word1');
        MQTTService.send('hello','word2');
    }]);

```

MQTT server install mothod see: http://blog.csdn.net/qhdcsj/article/details/45042515
