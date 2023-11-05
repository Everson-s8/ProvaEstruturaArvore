package ArvoreBinaria;

import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria <T extends Comparable>{

    private No raiz;

    public ArvoreBinaria(){
        raiz = null;
    }

    // Chamada para inserir recursivamente
    // permitindo ao usuario passar o elemento que deseja inserir
    // e já inserir em um no e chama para inserir na arvore
    public void inserirNo(T elemento){
        raiz = inserirRecursivo(raiz, new No(elemento));
    }

    // onde faz para inserir o No na arvore em uma chamada recursiva
    // verificando onde o novo No se encaixa na arvore e inserindo
    private No inserirRecursivo(No atual, No novoNo){
        if (atual == null){
            atual = novoNo;
            return atual;
        }
        if (novoNo.getDado().compareTo(atual.getDado()) < 0 ){
            if (atual.getEsquerda() == null){
                atual.setEsquerda(novoNo);
                atual.getEsquerda().setPai(atual);
            }else {
                inserirRecursivo(atual.getEsquerda(), novoNo);
            }
        }else {
            if (atual.getDireita() == null){
                atual.setDireita(novoNo);
                atual.getDireita().setPai(atual);
            }else {
                inserirRecursivo(atual.getDireita(), novoNo);
            }
        }
        return atual;
    }
    public No remover(No no) throws Exception{
        if (raiz == null){
            throw new Exception("Arvore Vazia");
        }
        raiz = removerRecursivo(raiz, no);
        return no;
    }

    private No removerRecursivo(No atual, No removerNo) throws Exception {
        if (atual == null | removerNo == null){
            throw new Exception("Elemento não existe");
        }
        if (removerNo.getDado().compareTo(atual.getDado()) < 0){
            atual.setEsquerda(removerRecursivo(atual.getEsquerda(), removerNo));
        } else if (removerNo.getDado().compareTo(atual.getDado()) > 0) {
            atual.setDireita(removerRecursivo(atual.getDireita(), removerNo));
        }else {
            if (atual.getEsquerda() == null){
                return atual.getDireita();
            }else if (atual.getDireita() == null){
                return atual.getEsquerda();
            }
            atual.setDado(valorMaximo(atual.getEsquerda()));
            atual.setEsquerda(removerRecursivo(atual.getEsquerda(), atual));
        }
        return atual;
    }

    private T valorMaximo(No no){
        T max = (T) no.getDado();
        if (no.getDireita() != null){
            max = (T) no.getDireita().getDado();
            no = no.getDireita();
        }
        return max;
    }
    // Percorrer por profundidade

    public void imprimir(String tipo) throws Exception {
        if (tipo.equalsIgnoreCase("emOrdem")){
            emOrdem(getRaiz());
        } else if (tipo.equalsIgnoreCase("preOrdem")) {
            preOrdem(getRaiz());
        } else if (tipo.equalsIgnoreCase("posOrdem")) {
            posOrdem(getRaiz());
        } else if (tipo.equalsIgnoreCase("largura")) {
            pecorrerLargura(raiz);
        }
    }
    private void emOrdem(No no){
        if (no != null){
            emOrdem(no.getEsquerda());
            System.out.print(no.getDado() + " ");
            emOrdem(no.getDireita());
        }
    }

    private void preOrdem(No no){
        if (no != null){
            System.out.print(no.getDado() + " ");
            preOrdem(no.getEsquerda());
            preOrdem(no.getDireita());
        }
    }

    private void posOrdem(No no){
        if (no != null){
            posOrdem(no.getEsquerda());
            posOrdem(no.getDireita());
            System.out.print(no.getDado() + " ");
        }
    }


    // Pecorrer por largura

    private void pecorrerLargura(No no) throws Exception {
        if (no == null){
            throw new Exception("Elemento não existe");
        }

        Queue<No> fila = new LinkedList<>();      // Criando uma fila de No
        fila.offer(no);                           // começa inserindo na fila o no de partida

        while (!fila.isEmpty()){                   // Repetir até a fila for diferente de vazia
            No atual = fila.poll();                // desfilerar o elemento da fila, e atribui a atual
            System.out.print(atual.getDado() + " ");

            if (atual.getEsquerda() != null){
                fila.offer(atual.getEsquerda());
            }

            if (atual.getDireita() != null){
                fila.offer(atual.getDireita());
            }
        }
    }

    public T buscar(No no) throws Exception {
        if (estaVazia()){
            throw new Exception("Arvore Vazia");
        }
        No atual = raiz;

        while (true){
            if (atual == null){
                throw new Exception("Elemento não existe");
            }
            if (no.getDado() == atual.getDado()){
                return (T) atual.getDado();
            }else {
                if (no.getDado().compareTo(atual.getDado()) < 0){
                    atual = atual.getEsquerda();
                }else {
                    atual = atual.getDireita();
                }
            }
        }
    }

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    public boolean estaVazia(){
        if (raiz == null){
            return true;
        }
        return false;
    }
}
