#include <cstdio>
#include <iostream>
#include <cctype>
#include <cstdlib>

#define NUM 256
#define SUB 257
#define SOM 258

using namespace std;

struct TOKEN
{
    int id;
    char valor;
};

void list();
void term();
void x();
void match(char);
void print(char);

TOKEN lookahead;
string input;
int pos = 0;

void print(char c)
{
    printf("%c", c);
}

TOKEN nextToken()
{
    TOKEN token;
    if (pos < input.length())
    {
        if (isdigit(input[pos]))
        {
            token.id = NUM;
            token.valor = input[pos++];
        }
        else if (input[pos] == '+')
        {
            token.id = SOM;
            token.valor = input[pos++];
        }
        else if (input[pos] == '-')
        {
            token.id = SUB;
            token.valor = input[pos++];
        }
        else
        {
            token.id = EOF;
        }
        return token;
    }
    else
    {
        token.id = EOF;
        return token;
    }
}

void list()
{
    printf("list\n");
    term();
    x();
}

void x()
{
    //printf("x\n");
    if (lookahead.valor == '+')
    {
        match('+');
        term();
        print('+');
        x();
    }
    else if (lookahead.valor == '-')
    {
        match('-');
        term();
        print('-');
        x();
    }
    else if (lookahead.id == EOF)
    {
        printf("\nSuccess.\n");
    }
    else
    {
        printf("Syntax error on x\n");
        exit(1);
    }
}

void term()
{
    //printf("term\n");
    // if (isalpha(lookahead)){ //var
    //     match(lookahead);
    // } else
    if (isdigit(lookahead.valor))
    {
        print(lookahead.valor);
        match(lookahead.valor);
    }
    else
    {
        printf("Syntax error on term\n");
        exit(1);
    }
}

void match(char s)
{
    //printf("match %c\n", s);
    if (lookahead.valor == s)
    {
        lookahead = nextToken();
    }
    else
    {
        printf("Match error");
    }
}

int main()
{
    input = "2+2-2";
    lookahead = nextToken();
    list();
}