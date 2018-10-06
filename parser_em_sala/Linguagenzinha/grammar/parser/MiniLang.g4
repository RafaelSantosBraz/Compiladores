grammar MiniLang;

@header {
package parser;
}


//----- PARSER -------------------------------------
prog    : stmt+
        ;

stmt    : ( atr | print | read ) EOL
        | ifstmt
        ;

print   : PRINT expr        #printExpr
        | PRINT STRING      #printStr
        ;

read    : READ '('VAR')'
        ;

atr     : VAR '=' expr
        ;

expr    returns [Number result]
        : expr '+' term    #exprPlus
        | expr '-' term    #exprMin
        | term             #exprTerm
        ;

term    returns [Number result]
        : term '*' fact    #termMult
        | term '/' fact    #termDiv
        | fact             #termFact
        ;

fact    returns [Number result]
        : NUM                    #factNum
        | VAR                    #factVar
        | '(' expr ')'           #factExpr
        ;

ifstmt  : IF '(' cond ')' block                #ifStmt
        | IF '(' cond ')' block ELSE block     #ifElseStmt
        ;

cond    : expr
        ;           //expressões relativas > < >= <= != lógicas && || !
                    //((x && y) || (z && a))
block   : stmt
        | '{' stmt+ '}'
        ;

//----- LEXER -------------------------------------
//dúvidas: https://regexr.com/
NUM     : [0-9]+ ;
IF      : 'if';
ELSE    : 'else';
PRINT   : 'print';
READ    : 'read';
STRING  : '"'(~["\\\r\n])*'"';
VAR     : [a-zA-Z]+;
SUM     : '+';
EQUALS  : '=';
B_EXPR  : '(';
E_EXPR  : ')';
B_BLOCK : '{';
E_BLOCK : '}';
EOL     : ';';
WS      : [ \t\n] -> skip;