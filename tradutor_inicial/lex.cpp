#include "cstdio"
#include "string"
#include "iostream"
#include "cstdlib"

// constantes para types dos tokens
#define NUM 256
#define PLUS 257
#define VAR 258
#define EQ 259
#define PRINT 260
#define EOL 261
#define ERR 8000

using namespace std;

// estrutura de representação dos Tokens
struct token
{
	int type;
	int value;
};

// entrada para o Lex
string input;
// posição atual (char) em input
int pos = 0;
// char auxiliar para "manter" o último símbolo
char c = EOF;
// contagem da linha atual
int current_line = 0;

// faz a leitura de um char em input
char get_char()
{
	if (pos < input.length())
	{
		return input[pos++];
	}
	else
	{
		return EOF;
	}
}

// faz a identificação do próximo Token na input e o retorna
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
		if (peek == ' ' || peek == '\t')
			continue;
		else if (peek == '\n')
			current_line++;
		else
			break;
	} while (peek = get_char());
	// identificação de inteiros
	if (isdigit(peek))
	{
		int v = 0;
		double r = 0.0;
		do
		{
			v = v * 10 + atoi(&peek);
			peek = get_char();
		} while (isdigit(peek));
		t.type = NUM;
		t.value = v;
		c = peek;
	}
	// identificação de palavras
	else if (isalpha(peek))
	{
		string aux = "";
		do
		{
			aux += peek;
			peek = get_char();
		} while (isalpha(peek));
		if (aux == "print")
		{
			t.type = PRINT;
		}
		else
		{
			t.type = VAR;
			t.value = 0;// tabela de símbolos
		}
		c = peek;
	}
	else if (peek == '+')
	{
		t.type = PLUS;
	}
	else if (peek == '=')
	{
		t.type = EQ;
	}
	else if (peek == EOF)
	{
		t.type = EOF;
	}
	else if (peek == ';')
	{
		t.type = EOL;
	}
	else
	{
		t.type = ERR;
	}
	return t;
}
