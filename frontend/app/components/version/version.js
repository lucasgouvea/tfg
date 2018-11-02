'use strict';

angular.module('home-automation.version', [
  'home-automation.version.interpolate-filter',
  'home-automation.version.version-directive'
])

.value('version', '0.1');
