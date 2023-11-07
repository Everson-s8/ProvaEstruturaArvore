package ArvoreBinaria;

public class Main {

    public static void main(String[] args) throws Exception {
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<Integer>();

        arvore.inserirNo(6);
        arvore.inserirNo(1);
        arvore.inserirNo(38);
        arvore.inserirNo(4);
        arvore.inserirNo(7);
        arvore.inserirNo(60);
        arvore.inserirNo(3);
        arvore.inserirNo(5);
        arvore.remover(new No(4));
        arvore.inserirNo(4);
        arvore.remover(new No(4));
        arvore.inserirNo(4);
        arvore.remover(new No(1));
        arvore.remover(new No(38));
        arvore.remover(new No(7));
        arvore.inserirNo(58);
        arvore.remover(new No(60));


        arvore.imprimir("preOrdem");

    }
}
