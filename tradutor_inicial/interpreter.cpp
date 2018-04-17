#include <cstdio>
#include <iostream>
#include <cctype>
#include <cstdlib>
#include "lex.cpp"

using namespace std;

void print_char(char);
void print_number(int);
void prog();
void loop();
void cmd();
void expr();
void rest();
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
	}
}

void cmd()
{
	if (lookahead.type == VAR)
	{
		match(VAR); match(EQ); expr(); match(EOL);
	}
	else if (lookahead.type == PRINT)
	{
		match(PRINT); expr();
	}
	else
	{
		// erro
	}
}

void expr()
{
	if (lookahead.type == VAR)
	{
		match(VAR); rest();
	}
	else if (lookahead.type == NUM)
	{
		match(NUM); rest();
	}
	else
	{
		// erro
	}
}

void rest()
{
	if (lookahead.type == PLUS)
	{
		match(PLUS); expr();
	}
	else if (lookahead.type == EOL)
	{
		match(EOL);
	}
	else
	{
		// erro
	}
}

void match(int type) {
	if (lookahead.type == type) {
		lookahead = next_token();
	}
	else {
		printf("Match error");
	}
}

int main() {
	input = "print 2+2;";
	lookahead = next_token();
	prog();
	return 0;
}
