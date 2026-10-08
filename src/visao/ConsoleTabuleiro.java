package visao;

import excecao.ExplosaoException;
import excecao.SairException;
import modelo.Tabuleiro;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Iterator;

public class ConsoleTabuleiro {

    private Tabuleiro tabuleiro;
    private Scanner ler = new Scanner(System.in);
    
    public ConsoleTabuleiro(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo(){
        try {
            boolean continuar = true;

            while(continuar){
                cicloDoJogo();
                System.out.println("Outra partida? (S/n)");
                String resposta = ler.nextLine();
                if("n".equalsIgnoreCase(resposta)){
                    continuar = false;
                } else{
                    tabuleiro.reiniciar();
                }

            }

        } catch (SairException e) {
            System.out.println("Saindo...");
        } finally {
            ler.close();
        }
    }

    private void cicloDoJogo(){
        try {
            while(!tabuleiro.objetivoAlcancado()){
                System.out.println(tabuleiro);
                String digitado = capturarValorDigitado("Digite (x,y): ");

                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                                             .map(e -> Integer.parseInt(e.trim()))
                                             .iterator();

                digitado = capturarValorDigitado("1 - Abrir | 2 - Marcar");
                if("1".equals(digitado)){
                    tabuleiro.abrirCampo(xy.next(), xy.next());
                } else if ("2".equals(digitado)){
                    tabuleiro.marcarCampo(xy.next(), xy.next());
                }
                
            }
            System.out.println(tabuleiro);
            System.out.println("Voce ganhou!");
        } catch (ExplosaoException e) {
            System.out.println(tabuleiro);
            System.out.println("Voce perdeu!");
        }
    }

    private String capturarValorDigitado(String texto){
        System.out.println(texto);
        String digitado = ler.nextLine();

        if("sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }

        return digitado;
    }

}
