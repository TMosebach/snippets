const express = require('express');
const router = express.Router();



const jwt = require('jsonwebtoken');

const USERS = [
  { 'id': 1, 'username': 'jemma' },
  { 'id': 2, 'username': 'paul' },
  { 'id': 3, 'username': 'sebastian' },
];

router
  .route('/')
    .get( (req, res) => res.send('Birds home page'))
     /*
      * Login, a successfull login gives the token for further authentification
      * curl -X POST -d '{ "username": "paul", "password": "geheim"}' localhost:3000/api/1.0/auth
      */
    .post( (req, res) => {

      const body = req.body;

      console.log(body);

      const user = USERS.find(user => user.username == body.username);
      if(!user || body.password != 'todo') return res.sendStatus(401);
      
      const token = jwt.sign({userID: user.id}, 'geheim', {expiresIn: '2h'});
      res.send({token});
    });

module.exports = router;