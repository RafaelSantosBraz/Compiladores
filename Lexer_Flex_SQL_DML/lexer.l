
%%

SELECT                    printf("<SEL>\n");
INSERT                    printf("<INS>\n");
UPDATE                    printf("<UPD>\n");
DELETE                    printf("<DEL>\n");
INTO                      printf("<INT>\n");
FROM                      printf("<FRM>\n");
VALUES                    printf("<VAL>\n");
SET                       printf("<SET>\n");
WHERE                     printf("<WHE>\n");
NOT                       printf("<NOT>\n");
AND                       printf("<AND>\n");
OR                        printf("<ORR>\n");
"ORDER BY"                printf("<ORD>\n");
;                         printf("<EOL>\n\n");
"*"                       printf("<STA>\n");
"("                       printf("<OPP>\n");
")"                       printf("<CPP>\n");
","                       printf("<COM>\n");
">"                       printf("<MOR>\n");
"<"                       printf("<LES>\n");
">="                      printf("<MEQ>\n");
"<="                      printf("<LEQ>\n");
=                         printf("<EQU>\n");
"/"                       printf("<DIV>\n");
"-"                       printf("<SUB>\n");
"+"                       printf("<PLU>\n");
" "|"\n"                  printf("");
[0-9]+(\.[0-9]+)?	      printf("<NUM, %s>\n", yytext);
\"[^\"]*\"                  printf("<STR, %s>\n", yytext);
[a-zA-Z][\._a-zA-Z0-9]*	  printf("<ID, %s>\n", yytext);

%%

int main(int argc, char *argv[]) {
    yyin = fopen(argv[1], "r");
    yylex();
    fclose(yyin);
    printf("\n");
    return 0;
}