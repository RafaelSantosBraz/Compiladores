#include <cstdio>
#include <iostream>
#include <cctype>
#include <cstdlib>
#include "lex.cpp"
#include "symbol_table.cpp"

using namespace std;

void print_char(char);
void print_number(int);
void prog();
void loop();
void cmd();
int expr();
int rest();
void match(int);

token lookahead;

void print_char(char c)
{
	printf("%c ", c);
}

void print_number(int i)
{
	printf("%d ", i);
}

void prog()
{
	cmd();
	loop();
}

void loop()
{
	if (lookahead.type != EOF)
	{
		prog();
	}
	else
	{
		// terminou
		//printf("Complete!\n");
	}
}

void cmd()
{
	if (lookahead.type == VAR)
	{
		match(VAR); match(EQ); value_insertion(lookahead.value, expr()); /*match(EOL);*/
	}
	else if (lookahead.type == PRINT)
	{
		match(PRINT); cout << expr() << endl;
	}
	else
	{
		// erro
		printf("Syntax Error <cmd>!\n");
        exit(1);
	}
}

int expr()
{
	if (lookahead.type == VAR)
	{
		match(VAR); return get_value(lookahead.value) + rest();
	}
	else if (lookahead.type == NUM)
	{
		match(NUM); return lookahead.value + rest();
	}
	else
	{
		// erro
        printf("Syntax Error <expr>!\n");
        exit(1);
	}
}

int rest()
{
	if (lookahead.type == PLUS)
	{
		match(PLUS); return expr();
	}
	else if (lookahead.type == EOL)
	{
		match(EOL); return 0;
	}
	else
	{
		// erro
		printf("Syntax Error <rest>!\n");
        exit(1);
	}
}

void match(int type) {
    //cout << token_name(lookahead.type) << endl;
	if (lookahead.type == type) {
		lookahead = next_token();
	}
	else {
		printf("Match error");
	}
}

int main() {
	input = "x=2;";
	lookahead = next_token();
	prog();
	return 0;
}
