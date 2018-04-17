#include "cstdio"
#include "string"
#include "iostream"
#include "cstdlib"

using namespace std;

// linhas da tabela
struct info
{
	int type;
	string lexeme;
	int value;
};

// a própria tabela de símbolos
typedef pair<int, info> table;