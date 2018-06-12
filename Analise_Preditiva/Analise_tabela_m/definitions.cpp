#include <iostream>
#include <vector>
#include <string>
#include <stack>

using namespace std;

// cada célula da tabela m
struct element
{
    char terminal;
    char variable;
    string product;
};

// representação da tabela m como vetor de células
vector<element> m_table;

// pilha auxiliar do analisador
stack<char> symbols;

// representa o buffer de entrada
vector<char> input;
int pos_input;