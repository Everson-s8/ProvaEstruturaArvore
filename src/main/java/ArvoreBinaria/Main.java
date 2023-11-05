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
        arvore.remover(new No(60));
        System.out.println(arvore.buscar(new No(60)));

        // faltou fazer exceção se o número não existir
        // faltou fazer exceção se for tentar remover com a arvore vazia retornar um erro.
        // fazer um pecorrer por nv de acordo com outros no além da raiz

//        arvore.imprimir("preOrdem");

    }
}
