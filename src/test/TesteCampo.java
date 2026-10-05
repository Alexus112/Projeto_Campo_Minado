package test;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Campo;

public class TesteCampo {

    private Campo campo;

    @BeforeEach 
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }

    @Test
    void testeVizinhoRealDistancia1() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);

        assertTrue(resultado);
    }

}
