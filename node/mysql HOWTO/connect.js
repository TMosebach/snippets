
const mysqlx = require('@mysql/xdevapi');

(async () => {
    try {
        let session = await mysqlx.getSession('mysqlx://ossi:geheim@localhost:33060/');

        await session.sql('CREATE SCHEMA MYDB').execute();
        await session.sql('USE MYDB').execute();
        await session.sql('CREATE TABLE user (id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, name VARCHAR(30), password VARCHAR(30))')
            .execute();

        await session.startTransaction()
        let insertSql = await session.sql('INSERT INTO user (name, password) VALUES (?, ?)');
        let result = await insertSql.bind(['moi', 'geheim']).execute();
        console.log(result.getAutoIncrementValue(), result.getGeneratedIds(), result);
        await session.commit();

        console.log('Resultobjekt: ', result)
        console.log("last generated ID", result.getAutoIncrementValue());
 
        await session.sql('select * from user').execute( data => {
            console.log(data);
        });

        await session.sql('DROP SCHEMA MYDB').execute();
        await session.close();

        console.log('und wieder geschlossen');
    } catch(err) { console.log(err) }
})();