const express = require('express');
const app = express();
const expressJwt = require('express-jwt');
const jwt = require('jsonwebtoken');

/* Logging aktivieren */
const morgan = require('morgan');
app.use(morgan('dev'));

const bodyParser = require('body-parser');
app.use(bodyParser.json());

const SECRET = 'jwtSecret';

app.use(expressJwt({secret: SECRET}).unless({path: ['/api/1.0/auth', '/api/1.0/register']}));

/* Enabling CORS */
app.use((req, res, next) => {
    //Enabling CORS
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Methods', 'GET,HEAD,OPTIONS,POST,PUT');
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept, x-client-key, x-client-token, x-client-secret, Authorization');
    next();
});

app.get('/api/1.0/auth', (req, res) => res.send('Birds home page'));

let USERS = [
    { id: 1, kennung: '047110', password: 'geheim' }
];

let PROFILES = [
    { id: 1, user_id: 1, vorname: 'James', nachname: 'Blond'}
];

/*
* Login, a successfull login gives the token for further authentification
* curl -i -X POST -H "Content-Type: application/json" -d '{"user":"paul", "password":"todo"}' localhost:3000/api/1.0/auth
*/
app.post('/api/1.0/auth', (req, res) => {

    const body = req.body;

    const user = USERS.find(user => user.kennung == body.user);
    if(!user || body.password != user.password) return res.sendStatus(401);
    
    var token = jwt.sign({userID: user.id}, SECRET, {expiresIn: '2h'});
    res.send({token});
});

/*
* Login, a successfull login gives the token for further authentification
* curl -i -X POST -H "Content-Type: application/json" -d '{"kennung":"052582", "password":"secret"}' localhost:3000/api/1.0/register
*/
app.post('/api/1.0/register', (req, res) => {

    const body = req.body;

    USERS.push({ id: USERS.length +1, kennung: body.kennung, password: body.password });
    PROFILES.push({id: PROFILES.length +1, user_id: USERS.length, vorname: body.vorname, nachname: body.nachname});

    console.log(USERS, PROFILES);

    res.status(201).end();
});

app.get('/api/1.0/profile/:id', (req, res) => {
    let userId = req.params.id;
    const profile = PROFILES.find(p => p.user_id == userId);
    res.status(200).type('application/json').send(profile);
});

app.listen(3000, () => console.log('User skeleton Backend is listening port 3000!'));
