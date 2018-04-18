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

// auxiliar do cálculo de <expr>
int calculus = 0;
int value_ant = 0;

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
		match(VAR); match(EQ); expr(); /*match(EOL);*/ /*cout << lookahead.value << " " << calculus << endl;*/ cout << "aaa" << calculus << endl; value_insertion(lookahead.value, calculus); calculus = 0;
		//printf("Var %d %d", lookahead.value, get_value(lookahead.value));
	}
	else if (lookahead.type == PRINT)
	{
		match(PRINT); expr(); cout << calculus << endl; calculus = 0;
	}
	else
	{
		// erro
		printf("Syntax Error <cmd>!\n");
        exit(1);
	}
}

void expr()
{
	if (lookahead.type == VAR)
	{
	    //int aux = (get_value(lookahead.value) + rest());
	    //cout << aux << endl;
	    value_ant = get_value(lookahead.value);
		match(VAR); rest();
	}
	else if (lookahead.type == NUM)
	{
	    //int aux = (lookahead.value + rest());
	    //cout << aux << endl;
	    value_ant = lookahead.value;
		match(NUM); rest();
	}
	else
	{
		// erro
        printf("Syntax Error <expr>!\n");
        exit(1);
	}
}

void rest()
{
	if (lookahead.type == PLUS)
	{
		calculus += value_ant; match(PLUS); expr();
	}
	else if (lookahead.type == EOL)
	{
		calculus += value_ant; match(EOL);
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
	if (lookahead.type == type)
	{
		lookahead = next_token();
	}
	else
	{
		printf("Match error");
	}
}

int main()
{
	input = "x = 66+2+1; print x;";
	lookahead = next_token();
	prog();
	value_insertion(get_index("x"), 69);
	cout << get_value(get_index("x")) << endl;
	return 0;
}
