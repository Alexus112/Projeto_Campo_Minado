package MODEL;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private final int linha;
    private final int coluna;

    private boolean isAberto;
    private boolean isMinado;
    private boolean isMarcado;
    private boolean isVizinho;

    private List<Campo> vizinhos = new ArrayList<>();

    Campo(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }
    
}
