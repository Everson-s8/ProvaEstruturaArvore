package ArvoreBinaria;

public class No<T extends Comparable<T>> {

    private No<T> direita;
    private No<T> esquerda;
    private No<T> pai;
    private T dado;
    public No(T elemento){
        this.dado = elemento;
        this.direita = this.esquerda = this.pai = null;
    }

    public No<T> getDireita() {
        return direita;
    }

    public void setDireita(No<T> direita) {
        this.direita = direita;
    }

    public No<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No<T> esquerda) {
        this.esquerda = esquerda;
    }

    public No<T> getPai() {
        return pai;
    }

    public void setPai(No<T> pai) {
        this.pai = pai;
    }

    public T getDado() {
        return dado;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }
}
