const app = require('express')();
const http = require('http').Server(app);
let io = require('socket.io')(http);

app.get('/', function(req, res) {
    res.sendFile(__dirname + '/index.html');
});

io.on('connection', function(socket) {
    console.log('Neue WEbSocket connetion geöffnet.');
});

setInterval(function() {
    var stockprice = Math.floor(Math.random() * 1000);
    io.emit('stock price update', stockprice);
  }, 50);

http.listen(8000, () => {
    console.log('Listenint on *:8000');
});
