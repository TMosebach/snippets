const WebSocket = require('ws');
 
const wss = new WebSocket.Server({ port: 3000 });
 
wss.on('connection', function connection(ws) {

    console.log('user connected');

    ws.on('message', function incoming(message) {
        console.log('received: %s', message);
        wss.clients.forEach(client => {
            if (client !== ws) {
                console.log('Leite Message weiter', message);
                client.send(message);
            }
        });
    });
});