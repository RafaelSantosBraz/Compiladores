#include "Bib.hpp"

using namespace std;

struct TRANSICAO{
    int atual;
    char simbolo;
    int prox;
};

struct TABELA{
    TRANSICAO inicio;
    TRANSICAO proxima;
};

int maquina_estados_finita(int estado, char entrada, TABELA *delta){
    return -1;
}
