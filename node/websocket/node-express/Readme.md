# Node JS Websocket Server

To compile

    npm run compile

To start the server

    npm run start

The Server is listening on `ws://localhost:8999/`

A broadcast message starts with `@alle` e.g. `@alle high folks` which send *high folks* to all connections but the
sender.