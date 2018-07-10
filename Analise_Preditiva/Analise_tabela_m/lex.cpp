#include "cstdio"
#include "string"
#include "iostream"
#include "cstdlib"
#include "tokens.cpp"

using namespace std;

// estrutura de representação dos Tokens
struct token
{
    int type;
    string value;
};

// entrada para o Lex
string input_lex;
// posição atual (char) em input_lex
int pos = 0;
// char auxiliar para "manter" o último símbolo
char c = EOF;

// faz a leitura de um char em input_lex
char get_char()
{
    if (pos < input_lex.length())
    {
        return input_lex[pos++];
    }
    else
    {
        return EOF;
    }
}

// faz a identificação do próximo Token na input_lex e o retorna
token next_token()
{
    token t;
    char peek;
    // manter o último símbolo ou requisitar um novo
    if (c == EOF)
    {
        peek = get_char();
    }
    else
    {
        peek = c;
        c = EOF;
    }
    //ignorar espaços e tabulações
    do
    {
        if (peek == ' ' || peek == '\t' || peek == '\n')
            continue;
        else
            break;
    } while (peek = get_char());
    if (peek == '+')
    {
        t.type = ADD;
    }
    else if (peek == '*')
    {
        t.type = MUL;
    }
    else if (peek == '(')
    {
        t.type = OPP;
    }
    else if (peek == ')')
    {
        t.type = CLP;
    }
    else if (peek == EOF)
    {
        t.type = EOF;
    }
    else
    {
        string aux = "";
        do
        {
            aux += peek;
            peek = get_char();
        } while (peek != '+' && peek != '*' && peek != '(' && peek != ')' && peek != EOF);
        t.type = ID;
        t.value = aux;
        c = peek;
    }
    return t;
}
