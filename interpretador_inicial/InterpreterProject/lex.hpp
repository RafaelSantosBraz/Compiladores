#ifndef LEX_HPP_INCLUDED
#define LEX_HPP_INCLUDED
#include <cstdio>
#include <iostream>
#include <cctype>
#include <cstdlib>
#include <string>
#include <vector>
#include "symbol_table.hpp"

// constantes para types dos tokens
#define NUM 256
#define PLUS 257
#define VAR 258
#define EQ 259
#define PRINT 260
#define EOL 261
#define ERR 8000

using namespace std;

// estrutura de representação dos Tokens
struct token
{
	int type;
	int value;
};

// entrada para o Lex
string input;
// posição atual (char) em input
int pos = 0;
// char auxiliar para "manter" o último símbolo
char c = EOF;
// contagem da linha atual
int current_line = 0;

string token_name(int);
char get_char();
token next_token();

#endif // LEX_HPP_INCLUDED
