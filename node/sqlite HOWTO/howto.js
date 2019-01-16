/**
 * Example of sqlite use with Node JS
 * 
 * Open/Create 
 * optionaly migrate a db
 * and 
 */

const db = require('sqlite');

Promise.resolve()
  // Open the Database, instead of the in-memory-db a file name is posibel
  .then(() => db.open(':memory:', { Promise }))

  // Update db schema to the latest version
  .then(() => db.migrate({ force: 'last' }))

  // Read all Data
  .then(() => db.all('select * from Todo'))
  .then((result) => console.log('Result:', result))

  // Insert a new one
  .then(() => db.run('INSERT INTO Todo (id, priority, text) VALUES (3, 1, "Todo 3")'))

  // Select Prio 1
  .then(() => db.all('select * from Todo where priority = ?', 1))
  .then((result) => console.log('Result:', result))

  .catch((err) => {
      console.error(err.stack);
      process.exit();
  })
  // Finally, work with db
  .finally(() => {
      db.close((err) => {
          if(err) console.log('Error');
          else console.log('closed db')
      });
  });
