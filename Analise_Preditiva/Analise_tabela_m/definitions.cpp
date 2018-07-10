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
    int variable;
    vector<int> product;
};

// representação da tabela m como vetor de células
vector<element> m_table;

// pilha auxiliar do analisador
stack<int> symbols;

// representa o buffer de entrada
vector<token> input;
int pos_input;