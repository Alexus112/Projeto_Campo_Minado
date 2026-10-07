package modelo;

import java.util.ArrayList;
import java.util.List;

import excecao.ExplosaoException;

public class Campo {

    private final int linha;
    private final int coluna;

    private boolean isAberto;
    private boolean isMinado;
    private boolean isMarcado;
  
    private List<Campo> vizinhos = new ArrayList<>();

    Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(this.linha - vizinho.linha);
        int deltaColuna = Math.abs(this.coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        if (deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else {
            return false;
        }
    }

    void alternarMarcacao() {
        if (!isAberto) {
            isMarcado = !isMarcado;
        }
    }

    boolean abrir() {
        if (!isAberto && !isMarcado) {
            isAberto = true;

            if (isMinado) {
                throw new ExplosaoException();
            }

            if (vizinhancaSegura()) {
                vizinhos.forEach(v -> v.abrir());
            }

            return true;
        } else {
            return false;
        }
    }

    boolean vizinhancaSegura() {
        return vizinhos.stream()
                .noneMatch(v -> v.isMinado);
    }
    
    public boolean isMinado(){
        return isMinado;
    }

    public boolean isMarcado(){
        return isMarcado;
    }

    public boolean isAberto(){
        return isAberto;
    }

    public boolean isFechado(){
        return !isAberto;
    }
    
    void minar(){
        if(!isMinado){
            isMinado = true;
        }
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    boolean objetivoAlcancado(){
        boolean desvendado = !isMinado && isAberto;
        boolean protegido = isMinado && isMarcado;
        return desvendado || protegido;
    }

    long minasNaVizinhanca(){
        return vizinhos.stream()
                       .filter(v -> v.isMinado)
                       .count();
    }

    void reiniciar(){
        isAberto = false;
        isMinado = false;
        isMarcado = false;
    }

    public String toString(){
        if(isMarcado){
            return "x";
        } else if(isAberto && isMinado){
            return "*";
        } else if(isAberto && minasNaVizinhanca() > 0){
            return Long.toString(minasNaVizinhanca());
        } else if(isAberto){
            return " ";
        } else{
            return "?";
        }
    }

}
