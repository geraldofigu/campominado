package model;

import exception.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampoTest {

    private Campo underTest;

    @BeforeEach
    void iniciarCampo() {
        underTest = new Campo(3, 3);
    }

    @Test
    void deveAdicionarVizinhoDaDireita() {
        Campo vizinho = new Campo(3, 4);
        boolean resultado = underTest.adicionarVizinho(vizinho);

        assertTrue(resultado);
    }

    @Test
    void deveAdicionarVizinhoDaEsquerda() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = underTest.adicionarVizinho(vizinho);

        assertTrue(resultado);
    }

    @Test
    void deveAdicionarVizinhoDeCima() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = underTest.adicionarVizinho(vizinho);

        assertTrue(resultado);
    }

    @Test
    void deveAdicionarVizinhoDeBaixo() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = underTest.adicionarVizinho(vizinho);

        assertTrue(resultado);
    }

    @Test
    void deveAdicionarVizinhoDaDiagonal() {
        Campo vizinho = new Campo(4, 4);
        boolean resultado = underTest.adicionarVizinho(vizinho);

        assertTrue(resultado);
    }

    @Test
    void naoDeveAdicionarVizinho() {
        Campo vizinho = new Campo(3, 5);
        boolean resultado = underTest.adicionarVizinho(vizinho);

        assertFalse(resultado);
    }

    @Test
    void deveTestarValorPadraoMarcacao() {
        assertFalse(underTest.isMarcado());
    }

    @Test
    void deveAlternarMarcacao() {
        underTest.alternarMarcacao();
        assertTrue(underTest.isMarcado());
    }

    @Test
    void deveAlternarMarcacaoDuasVezes() {
        underTest.alternarMarcacao();
        underTest.alternarMarcacao();
        assertFalse(underTest.isMarcado());
    }

    @Test
    void deveAbrirCampoNaoMinadoNaoMarcado() {
        boolean test = underTest.abrir();
        assertTrue(test);
    }

    @Test
    void naoDeveAbrirCampoNaoMinadoMarcado() {
        underTest.alternarMarcacao();
        assertFalse(underTest.abrir());
    }

    @Test
    void naoDeveAbrirCampoMinadoMarcado() {
        underTest.alternarMarcacao();
        underTest.minar();
        assertFalse(underTest.abrir());
    }

    @Test
    void deveAbrirMinadoNaoMarcado() {
        underTest.minar();
        assertThrows(ExplosaoException.class, () -> {
            underTest.abrir();
        });
    }

    @Test
    void deveAbrirComVizinhos() {
        Campo campo11 = new Campo(1, 1);

        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        underTest.adicionarVizinho(campo22);

        underTest.abrir();

        assertTrue(campo11.isAberto());
        assertTrue(campo22.isAberto());
    }

    @Test
    void deveAbrirComVizinhosNaoAbrindoMinado1() {
        Campo campo11 = new Campo(1, 1);
        campo11.minar();

        Campo campo22 = new Campo(2, 2);

        campo22.adicionarVizinho(campo11);
        underTest.adicionarVizinho(campo22);

        underTest.abrir();

        assertFalse(campo11.isAberto());
        assertTrue(campo22.isAberto());
    }

    @Test
    void deveAbrirComVizinhosNaoAbrindoMinado2() {
        Campo campo11 = new Campo(1, 1);

        Campo campo12 = new Campo(1, 2);
        campo12.minar();

        Campo campo22 = new Campo(2, 2);

        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);
        underTest.adicionarVizinho(campo22);

        underTest.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }

}