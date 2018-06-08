#include <iostream>
#include <fstream>
#include <cstdio>
#include <string>
#include "definitions.cpp"
#include "split.cpp"

using namespace std;

// deve preencher a tabela m, já definida
void load_m_table()
{
    ifstream patch;
    patch.open("table_definiton.txt");
    while (!patch.eof())
    {
        string text;        
        getline(patch, text);
        vector<string> aux = split(text, ' ');
        element elem;
        elem.variable = aux[0][0];
        elem.terminal = aux[1][0];
        elem.product = aux[2];
        m_table.push_back(elem);
    }
    patch.close();
}

// faz a leitura da entrada para preencher o buffer
void load_input()
{
    string aux;
    cin >> aux;
    for (int c = 0; c < aux.length(); c++)
    {
        input.push_back(aux[c]);
    }
}

// inicializa a pilha para utilização
void prepare_stack()
{
    symbols.push('$');
    symbols.push(m_table[0].variable);
}

// aplicação do algoritmo de análise
bool derivation()
{
    
}

int main(int argc, char const *argv[])
{
    load_m_table();
    load_input();
    prepare_stack();
    if (!derivation())
    {
        cout << "ERROR!\n";
    }
    return 0;
}
