const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');

// Connection URL
const url = 'mongodb://localhost:27017';

// Database Name
const dbName = 'myproject';

// Create a new MongoClient
const client = new MongoClient(url, { useNewUrlParser: true });

client.connect()
  // Use connect method to connect to the Server
  .then( () => {
    console.log("Connected successfully to server");
    
    const db = client.db(dbName);
    const collection = db.collection('documents');

    collection.insertMany([
      {a : 1}, {a : 2}, {a : 3}
    ])
    .then(result => {
      assert.equal(3, result.result.n);
      assert.equal(3, result.ops.length);
      console.log("Inserted 3 documents into the collection");
    })

    // alle lesen
    collection.find({}).toArray(function(err, docs) {
      assert.equal(err, null);
      console.log("Found the following records");
      console.log(docs);
    });

    // lesen mit Filter
    collection.find({'a': 3}).toArray(function(err, docs) {
      assert.equal(err, null);
      console.log("Found the following records");
      console.log(docs);
    });

    collection.updateOne({ a : 2 }
      , { $set: { b : 1 } }, function(err, result) {
      assert.equal(err, null);
      assert.equal(1, result.result.n);
      console.log("Updated the document with the field a equal to 2");
    }); 

    collection.deleteMany({ a : 3 }, function(err, result) {
      assert.equal(err, null);
      assert.equal(1, result.result.n);
      console.log("Removed the document with the field a equal to 3");
    }); 
  })

  // and close 
  .then( () => client.close())
  .catch(err => {
    throw err;
  });


