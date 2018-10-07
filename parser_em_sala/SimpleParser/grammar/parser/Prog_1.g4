grammar Prog_1;

@header{
package parser;
}

@members{
    
}

prog : (cmd)+
     ;

cmd  : atrib EOL    #cmdAtrib
     | write EOL    #cmdWrite
     | read EOL     #cmdRead
     | expr EOL     #cmdExpr
     | decl EOL     #cmdDecl
     | ifstm        #cmdIf     
     ;

atrib: VAR '=' expr           
     ; 

write: WRITE expr       #writeExpr      
     | WRITE STR        #writeStr
     ;

read : READ VAR               
     ;

expr returns [Number value]
     : expr '+' term    #exprPlus
     | expr '-' term    #exprMin
     | expr term        #exprHided
     | term             #exprTerm
     ;

term returns [Number value]
     : term '*' fact    #termMult
     | term '/' fact    #termDiv
     | fact             #termFact
     ;

fact returns [Number value]
     : NUM                    #factNum
     | VAR                    #factVar
     | '(' expr ')'           #factExpr
     ;

decl : type VAR            #declSimple
     | type VAR '=' expr   #declValue
     ;

type : INT              #typeInt
     | DOUBLE           #typeDouble
     ;

ifstm: IF cond THEN block                       #ifStm
     | IF cond THEN b1=block ELSE b2=block      #ifStmElse
     ;

cond : '(' cond OR cdand ')' #condOR
     | cdand                 #condAnd        
     ;

cdand: '(' cdand AND cndts ')'     #cdandAnd
     | cndts                       #cdandCndts
     ;

cndts: '(' expr ')'                         #cndtsExpr
     | '(' e1=expr RELOP e2=expr ')'        #cndtsRelop
     | '!' ('(' expr ')')                   #cndtsNotExpr
     | '!' ('(' e1=expr RELOP e2=expr ')')  #cndtsNotRelop
     | '(' cond ')'                         #cndtsCond
     ;

block: cmd                #blockSingle
     | BEGIN (cmd)+ END   #blockCompose
     ;

RELOP   : '>'|'<'|'>='|'<='|'=='|'!=';
OR      : '||';
AND     : '&&';
NOT     : '!';
IF      : [iI][fF];
THEN    : [tT][hH][eE][nN];
ELSE    : [eE][lL][sS][eE];
END     : [eE][nN][dD];
BEGIN   : [bB][eE][gG][iI][nN];
STR     : '"'(~["\\\r\n])*'"';
READ    : [rR][eE][aA][dD];
WRITE   : [wW][rR][iI][tT][eE];
INT     : [iI][nN][tT];
DOUBLE  : [dD][oO][uU][bB][lL][eE];
VAR     : [_a-zA-Z][_a-zA-Z0-9]*;
MULT    : '*';
SUM     : '+';
MIN     : '-';
DIV     : '/';
OPEN    : '(';
CLOSE   : ')';
EOL     : ';';
NUM     : '-'?[0-9]+ ('.'[0-9]+)?;
WS      : [ \t\r\n]+ -> skip;
COM     : '//'(~[\r\n])*'\r'?'\n' -> skip;