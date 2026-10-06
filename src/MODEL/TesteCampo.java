package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excecao.ExplosaoException;

public class TesteCampo {

    private Campo campo;

    @BeforeEach 
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }

    //Testes_Vizinhos

    @Test
    void testeVizinhoRealDistancia1() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia2() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia3() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia4() {
        Campo vizinho = new Campo(2, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia5() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    //Testes_Marcar

    @Test
    void testePadrãoMarcacao(){
        assertFalse(campo.isMarcado());
    }

    @Test 
    void testeMarcacao(){
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeMarcacaoDuasVezes(){
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    //Testes_Abrir

    @Test
    void testeAbrirNaoMinadoNaoMarcado() {
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMarcado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado() {
        campo.minar();
        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinho1() {
        Campo campo22 = new Campo(2, 2);
        Campo campo11 = new Campo(1, 1);

        campo22.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo22);

        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
     void testeAbrirComVizinho2() {
        Campo campo22 = new Campo(2, 2);
        Campo campo12 = new Campo(1, 2);
        campo12.minar();
        Campo campo11 = new Campo(1, 1);

        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);

        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }

    //Teste_Objetivo

    @Test 
    void testeObjetivoDesvendado(){
        campo.abrir();
        assertTrue(campo.objetivoAlcancado());
    }

    
    @Test 
    void testeObjetivoMarcado(){
        Campo campo22 = new Campo(2, 2);
        campo22.minar();
        campo22.alternarMarcacao();
        campo.adicionarVizinho(campo22);
        campo.abrir();
        assertTrue(campo22.objetivoAlcancado());
    }
    
    @Test 
    void testeObjetivoDesvendadoMarcado(){
        Campo campo22 = new Campo(2, 2);
        campo22.minar();
        campo22.alternarMarcacao();
        campo.adicionarVizinho(campo22);
        campo.abrir();
        assertTrue(campo.objetivoAlcancado() && campo22.objetivoAlcancado());
    }

    //Teste_Minhas_Na_Vizinhanca

    @Test
    void minasVizininhanca(){
        Campo campo22 = new Campo(2, 2);
        Campo campo23 = new Campo(2, 3);
        Campo campo32 = new Campo(3, 2);
        Campo campo34 = new Campo(3, 4);

        campo22.minar();
        campo23.minar();
        campo32.minar();
        campo34.minar();

        campo.adicionarVizinho(campo22);
        campo.adicionarVizinho(campo23);
        campo.adicionarVizinho(campo32);
        campo.adicionarVizinho(campo34);

        assertEquals(4, campo.minasNaVizinhanca());
    }

    //Teste_Reiniciar

    @Test
    void reiniciarAberto(){
        campo.abrir();
        campo.reiniciar();
        assertTrue(campo.isFechado());
    }
    
    @Test
    void reiniciarMarcadoMinado(){
        campo.minar();
        campo.alternarMarcacao();
        campo.reiniciar();
        assertTrue(!campo.isMarcado() && !campo.isMinado());
    }
}