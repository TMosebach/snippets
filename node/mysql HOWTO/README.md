# Using Mysql

short examples for working with mysql. The examples use sql although there is an abstracter way. See documentation for the driver.

## Setup the database

*IMPORTANT* Before using mysql, the user need a password.

After setting up MySQL use
    
    mysql uroot

to connect to the newly created database. And at least chance the
root password e.g.

    ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'geheim'

It is better to create a new user for working e.g.

    CREATE USER 'ossi'@'%' IDENTIFIED BY 'geheim';
    GRANT ALL PRIVILEGES ON * . * TO 'ossi'@'%';

Now use the new user for working with the database.

You may directly created a schema

    CREATE SCHEMA mySchema;