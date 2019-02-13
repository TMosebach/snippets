"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express = require("express");
const http = require("http");
const WebSocket = require("ws");
const app = express();
//initialize a simple http server
const server = http.createServer(app);
//initialize the WebSocket server instance
const wss = new WebSocket.Server({ server });
wss.on('connection', (ws) => {
    /* Test der Verbindung geht auch anders
    ws.isAlive = true;

    ws.on('pong', () => {
        ws.isAlive = true;
    });*/
    //connection is up, let's add a simple simple event
    ws.on('message', (message) => {
        //log the received message and send it back to the client
        console.log('received: %s', message);
        const broadcastRegex = /^@alle/;
        if (broadcastRegex.test(message)) {
            message = message.replace(broadcastRegex, '');
            //send back the message to the other clients
            wss.clients
                .forEach(client => {
                if (client != ws) {
                    client.send(`Hello, broadcast message -> ${message}`);
                }
            });
        }
        else {
            ws.send(`Hello, you sent -> ${message}`);
        }
    });
    //send immediatly a feedback to the incoming connection    
    ws.send('Hi there, I am a WebSocket server');
});
/* Initiieren des Verbindungstest
setInterval(() => {
    wss.clients.forEach((ws: ExtWebSocket) => {
        
        if (!ws.isAlive) return ws.terminate();
        
        ws.isAlive = false;
        ws.ping(null, false, true);
    });
}, 10000);*/
//start our server
server.listen(process.env.PORT || 8999, () => {
    console.log(`Server started on port 8999 :)`);
});
//# sourceMappingURL=server.js.map