#include "cstdio"
#include "string"
#include "iostream"
#include "cstdlib"
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

// verifica se a variável existe na tabela
bool is_there(string lexeme)
{
    for (int i = 0; i < symbols.size(); i++)
    {
        info aux = symbols[i];
        if (aux.lexeme == lexeme)
        {
            return true;
        }
    }
    return false;
}

// insere uma nova variável na tabela
int key_insertion(string lexeme)
{
    if (!is_there(lexeme))
    {
        info aux;
        aux.lexeme = lexeme;
        symbols.push_back(aux);
        return symbols.size() - 1;
    }
    else
    {
        return -1;
    }
}

// insere/altera o valor de uma variável
bool value_insertion(int index, int value)
{
    if (index >= 0 && index < symbols.size())
    {
        info aux = symbols[index];
        aux.value = value;
        symbols[index] = aux;
        return true;
    }
    else
    {
        return false;
    }
}
