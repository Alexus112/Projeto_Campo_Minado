package application;
import modelo.Tabuleiro;

public class Aplicacao {

    public static void main(String[] args) {
        
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
        tabuleiro.abrirCampo(1, 2);
        tabuleiro.marcarCampo(2, 4);
        System.out.println(tabuleiro);
    }

}
