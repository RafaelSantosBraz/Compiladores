
using namespace std;

struct TRANSICAO{
    int atual;
    char simbolo;
    int prox;
};

int main()
{
    TRANSICAO numero[2];
    numero[0].atual = 0;
    numero[0].prox = 1;
    numero[0].simbolo = '0';
    return 0;
}
