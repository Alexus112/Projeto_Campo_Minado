package application;
import modelo.Tabuleiro;
import visao.ConsoleTabuleiro;

public class Aplicacao {

    public static void main(String[] args) {
        
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
        new ConsoleTabuleiro(tabuleiro);
    }

}
