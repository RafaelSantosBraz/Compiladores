#include <iostream>
#include <vector>
#include <string>

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
