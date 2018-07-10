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
    getline(cin, input_lex);
    token end = next_token();
    do 
    {
        input.push_back(end);   
    } while ((end = next_token()).type != EOF);
    end.type = MON;
    input.push_back(end);
    pos_input = 0;
}

// inicializa a pilha para utilização
void prepare_stack()
{
    symbols.push('$');
    symbols.push(m_table[0].variable);
}

// identifica se o char é um não terminal
bool is_variable(char symbol)
{
    for (int c = 0; c < m_table.size(); c++)
    {
        if (m_table.at(c).variable == symbol)
        {
            return true;
        }
    }
    return false;
}

// casa o símbolo da pilha com a da entrada
bool match(token symbol)
{
    if (symbol == input.at(pos_input))
    {        
        pos_input++;
        return true;
    }
    return false;
}

// aplicação do algoritmo de análise
bool derivation()
{
    char actual = symbols.top();
    symbols.pop();
    if (is_variable(actual))
    {
        for (int c = 0; c < m_table.size(); c++)
        {
            if (m_table.at(c).variable == actual && m_table.at(c).terminal == input.at(pos_input))
            {
                if ((m_table.at(c).product.size() > 1))
                {
                    for (int i = m_table.at(c).product.size() - 1; i > -1; i--)
                    {
                        symbols.push(m_table.at(c).product.at(i));
                    }
                }
                else if (m_table.at(c).product.at(0) != '#')
                {
                    symbols.push(m_table.at(c).product.at(0));                    
                }
                return true;
            }
        }
        return false;
    }
    else
    {
        return match(actual);
    }
}

// permanece derivando a entreda
void derive_up()
{
    while (derivation() && pos_input < input.size())
    {            
    }
    if (symbols.empty())
    {
        cout << "Sucess!\n";
    }
    else
    {
        cout << "ERROR!\n";
    }
}

int main(int argc, char const *argv[])
{
    load_m_table();
    load_input();
    prepare_stack();
    derive_up();
    return 0;
}
