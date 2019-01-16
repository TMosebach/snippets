'use strict';

const express = require('express');
const app = express();
const bodyParser = require('body-parser');

app.use(bodyParser.json());

/* Logging aktivieren */
const morgan = require('morgan');
app.use(morgan('dev'));

/**
 * Simple REST
 * 
 * curl -i localhost:3000/hello/moi
 */
app.get('/hello/:name', (req, res) => {
    res.status(200).json({'hello': req.params.name});
})

/**
 * Deliver static resources from public directory
 */
app.use(express.static('public'));

app.listen(3000, () => console.log('Ready.'));