grammar Domain;

model : (type)+;

type : datatype | entity;

datatype : 'datatype' NAME ;

entity : 'entity' NAME ('extends' type_name)? '{'
    attribut*

    feature*
 '}';

type_name : NAME;

feature : 'many' NAME ':' type_name;

attribut : NAME ':' type_name;

NAME : [a-zA-Z_][a-zA-Z_0-9]+;
WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;
