var express = require('express');
var app = express();
app.use(express.static(__dirname + '/app'));
console.log("Subindo servidor em localhost:3000");
app.listen(process.env.PORT || 3000);
