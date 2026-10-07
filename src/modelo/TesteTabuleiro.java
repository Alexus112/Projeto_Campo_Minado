package modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class TesteTabuleiro {

    private Tabuleiro tabuleiro1;

    @BeforeEach
    void iniciarTabuleiro(){
        tabuleiro1 = new Tabuleiro(2, 2, 0);
    }

    //Teste_Abrir

    @Test
    void testeAbrirCampoTablueiro(){
        tabuleiro1.abrirCampo(1, 1);
        assertTrue(tabuleiro1.isCampoAberto(1, 1));
    }

    //Teste_Marcar

    @Test 
    void testeMarcarCampoTabuleiro(){
        tabuleiro1.marcarCampo(0, 0);
        assertTrue(tabuleiro1.isCampoMarcado(0, 0));
    }

    //Teste_Objetivo

    @Test
    void testeObjetivoAlcancado(){
        tabuleiro1.abrirCampo(0, 0);
        tabuleiro1.abrirCampo(0, 1);
        tabuleiro1.abrirCampo(1, 0);
        tabuleiro1.abrirCampo(1, 1);

        assertTrue(tabuleiro1.objetivoAlcancado());
    }

    //Teste_Reiniciar

    @Test
    void testeReiniciar(){
        tabuleiro1.abrirCampo(0, 0);
        tabuleiro1.reiniciar();
        assertFalse(tabuleiro1.isCampoAberto(0, 0));

    }

    //Teste_Tabuleiro_Tela

    @Test
    void testeTabuleiroTela(){
        String resultadoEsperado = " ?  ? \n" +
                                   " ?  ? \n";

        String resultadoObtido = tabuleiro1.toString();

        assertEquals(resultadoEsperado, resultadoObtido);
        
    }

}
