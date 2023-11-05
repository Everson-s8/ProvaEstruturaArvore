import ArvoreBinaria.No;
import ArvoreBinaria.ArvoreBinaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArvoreBinariaTeste {

    private ArvoreBinaria<Integer> arvoreBinaria;

    @BeforeEach
    public void setUp(){
        arvoreBinaria = new ArvoreBinaria();
    }

    @Test
    public void testInsercao() throws Exception {
        // Testar antes de inserir um valor se a arvore está nula
        assertThat(arvoreBinaria.estaVazia()).isEqualTo(true);

        // Testar após inserir a raiz
        arvoreBinaria.inserirNo(5);
        assertEquals(arvoreBinaria.getRaiz().getDado(), 5);
        assertThat(arvoreBinaria.estaVazia()).isEqualTo(false);

        // Inserindo uma esquerda e uma direita
        arvoreBinaria.inserirNo(15);
        arvoreBinaria.inserirNo(3);
        assertEquals(3, arvoreBinaria.getRaiz().getEsquerda().getDado());
        assertEquals(15, arvoreBinaria.getRaiz().getDireita().getDado() );

        // Inserindo varios outros valores e mostra ordenados em ordem
        arvoreBinaria.inserirNo(18);
        arvoreBinaria.inserirNo(9);
        arvoreBinaria.inserirNo(17);
        arvoreBinaria.inserirNo(30);
        arvoreBinaria.inserirNo(150);
        arvoreBinaria.inserirNo(31);
        arvoreBinaria.imprimir("emOrdem");
    }

    @Test
    public void insercaoComBusca() throws Exception {
        // Buscar elemento quanto a arvore está vazia

        Exception exception = assertThrows(Exception.class, () ->{
            arvoreBinaria.buscar(new No(6));
        });

        assertThat(exception.getMessage()).isEqualTo("Arvore Vazia");


        // Inserindo valores na arvore
        arvoreBinaria.inserirNo(6);
        arvoreBinaria.inserirNo(1);
        arvoreBinaria.inserirNo(38);
        arvoreBinaria.inserirNo(4);
        arvoreBinaria.inserirNo(7);
        arvoreBinaria.inserirNo(60);
        arvoreBinaria.inserirNo(3);

        // Buscar para ver se o elemento existe na arvore
        assertThat(arvoreBinaria.buscar(new No(7))).isEqualTo(7);
        assertThat(arvoreBinaria.buscar(new No(38))).isEqualTo(38);

        // Buscar após remover o valor, espera gerar erro de elemento não existe
        arvoreBinaria.remover(new No(60));

        Exception excecao = assertThrows(Exception.class, () -> {
            arvoreBinaria.buscar(new No(60));
        });

        assertThat(excecao.getMessage()).isEqualTo("Elemento não existe");

    }


    // Metodo para conseguir pegar os valores mostrados no console e salvar em uma string
    private String pegarValoresConsole(String tipo) throws Exception {
        // Definindo objetos para salvar o texto
        ByteArrayOutputStream saidaSalva = new ByteArrayOutputStream();
        PrintStream saidaExibida = new PrintStream(saidaSalva);

        // Redireciona a saída do console para o PrintStream
        PrintStream saidaDoConsole = System.out;
        System.setOut(saidaExibida);

        // Chama o método imprimir() com a String tipo como parametro, para assim escolher qual tipo de
        // percurso de profundidade vou fazer, assim vai imprimindo na ordem escolhida e mostrando no console
        arvoreBinaria.imprimir(tipo);

        // Restaura a saída do console
        System.setOut(saidaDoConsole);

        // Converte o conteúdo exibido no console em uma String e retorna
        return saidaSalva.toString();
    }

    @Test
    public void percorrerProfundidade() throws Exception {
        // Inserindo elementos na arvore
        arvoreBinaria.inserirNo(6);
        arvoreBinaria.inserirNo(1);
        arvoreBinaria.inserirNo(38);
        arvoreBinaria.inserirNo(4);
        arvoreBinaria.inserirNo(7);
        arvoreBinaria.inserirNo(60);
        arvoreBinaria.inserirNo(3);

        // Criando uma String com os valores esperados na operação na sua ordem
        // Pre Ordem:
        String valoresEsperadoPreOrdem ="6 1 4 3 38 7 60 ";
        String valoresPreOrdem = pegarValoresConsole("preOrdem");       // Pegando os valores enquanto a operação for preOrdem
        assertThat(valoresEsperadoPreOrdem).isEqualTo(valoresPreOrdem);     // comparando para ver se o valor é igual o esperado

        // Em Ordem:
        String valoresEsperadoEmOrdem = "1 3 4 6 7 38 60 ";
        String valoresEmOrdem = pegarValoresConsole("emOrdem");
        assertThat(valoresEsperadoEmOrdem).isEqualTo(valoresEmOrdem);

        //Pos Ordem:
        String valoresEsperadoemPosOrdem = "3 4 1 7 60 38 6 ";
        String valoresPosOrdem = pegarValoresConsole("posOrdem");
        assertThat(valoresEsperadoemPosOrdem).isEqualTo(valoresPosOrdem);
    }


    @Test
    public void percorrerLargura() throws Exception {
        // Inserindo elementos na arvore
        arvoreBinaria.inserirNo(6);
        arvoreBinaria.inserirNo(1);
        arvoreBinaria.inserirNo(38);
        arvoreBinaria.inserirNo(4);
        arvoreBinaria.inserirNo(7);
        arvoreBinaria.inserirNo(60);
        arvoreBinaria.inserirNo(3);

        // Criando uma String com os valores esperados na operação de pecorrer pela Largura
        String valoresEsperados = "6 1 38 4 7 60 3 ";
        String valoresLargura = pegarValoresConsole("largura");
        assertThat(valoresEsperados).isEqualTo(valoresLargura);
    }


    @Test
    public void removerInexistente() throws Exception {

        Exception excecao = assertThrows(Exception.class, () -> {
            arvoreBinaria.remover(new No(20));
        });

        assertThat(excecao.getMessage()).isEqualTo("Arvore Vazia");

        arvoreBinaria.inserirNo(6);
        arvoreBinaria.inserirNo(1);
        arvoreBinaria.inserirNo(38);
        arvoreBinaria.inserirNo(4);
        arvoreBinaria.inserirNo(7);
        arvoreBinaria.inserirNo(60);
        arvoreBinaria.inserirNo(3);

        Exception excecao2 = assertThrows(Exception.class, () -> {
            arvoreBinaria.remover(new No(20));
        });

        assertThat(excecao2.getMessage()).isEqualTo("Elemento não existe");
    }

    @Test
    public void removerPelaRaiz() throws Exception {
        assertThat(arvoreBinaria.estaVazia()).isEqualTo(true);

        // Inserindo valores na Arvore
        arvoreBinaria.inserirNo(6);
        arvoreBinaria.inserirNo(1);
        arvoreBinaria.inserirNo(38);
        arvoreBinaria.inserirNo(4);
        arvoreBinaria.inserirNo(7);
        arvoreBinaria.inserirNo(60);
        arvoreBinaria.inserirNo(3);

        // Remover todos valores da arvore até deixar ela vazia pela Raiz
        arvoreBinaria.remover(new No(6));
        arvoreBinaria.remover(new No(4));
        arvoreBinaria.remover(new No(3));
        arvoreBinaria.remover(new No(1));
        arvoreBinaria.remover(new No(38));
        arvoreBinaria.remover(new No(7));
        arvoreBinaria.remover(new No(60));

        assertThat(arvoreBinaria.estaVazia()).isEqualTo(true);

    }


}
