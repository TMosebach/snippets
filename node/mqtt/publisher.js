const mqtt = require('mqtt');
const client  = mqtt.connect('mqtt://localhost');

client.on('connect', () => {
    setInterval(() => {
        client.publish('myTopic', 'Hello mqtt '+new Date().toTimeString());
        console.log('Message Sent');
    }, 5000);
});