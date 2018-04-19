#ifndef SYMBOL_TABLE_HPP_INCLUDED
#define SYMBOL_TABLE_HPP_INCLUDED
#include <cstdio>
#include <iostream>
#include <cctype>
#include <cstdlib>
#include <string>
#include <vector>

using namespace std;

// informações da tabela
struct info
{
    string lexeme;
    int value;
};

// referência à tabela de símbolos
typedef vector<info> table;

// a tabela de símbolos
table symbols;

bool is_there(string);
int key_insertion(string);
bool value_insertion(int, int);
int get_value(int);
int get_index(string);

#endif // SYMBOL_TABLE_HPP_INCLUDED
