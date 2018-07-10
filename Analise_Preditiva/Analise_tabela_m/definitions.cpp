#include <iostream>
#include <vector>
#include <string>
#include <stack>
#include "lex.cpp"

using namespace std;

// cada célula da tabela m
struct element
{
    int terminal;
    char variable;
    vector<token> product;
};

// representação da tabela m como vetor de células
vector<element> m_table;

// pilha auxiliar do analisador
stack<token> symbols;

// representa o buffer de entrada
vector<token> input;
int pos_input;