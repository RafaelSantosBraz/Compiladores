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

// auxiliar do c�lculo de <expr>
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
	//cout << token_name(lookahead.type) << endl;
	if (lookahead.type == VAR)
	{	
		int var_reference = lookahead.value;	
		match(VAR); match(EQ); expr(); /*match(EOL);*/ /*cout << var_reference << " " << calculus << endl;*/ value_insertion(var_reference, calculus); calculus = 0;
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
		//cout << "endereço de get: " << lookahead.value << endl;
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

void match(int type) 
{
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
	getline(cin, input);
	while (input != "")
	{	
		pos = 0;
		lookahead = next_token();
		prog();	
		getline(cin, input);
	}	
	return 0;
}
