package gamecontrol.main;


public class DarwinControler {

    GamePanel gp;
    private int cruzamentos = 0; // variavel que vai segurar a quantidade de cruzamentos que o jogador fez
    public int getCruzamentos() {
        return cruzamentos;
    }
    public void setCruzamentos(int cruzamentos) {
        this.cruzamentos = cruzamentos;
    }

    public DarwinControler(GamePanel gp) {
        this.gp = gp; // Atribui o GamePanel recebido ao atributo gp
    }

    public void darwinInteference() {
        //Se o jogador cruzar 1 vez ele vai ver uma tela de explicação
        // if (cruzamentos == 1 || cruzamentos == 2 || cruzamentos == 3) {
        //     gp.currentState.setExplanationState();
        // }                                               //movi isso para o overlay de evolucao
        //Se o jogador cruzar 4 vezes, ele ganha o jogo
        if (cruzamentos == 4 ) {
            gp.currentState.setFinalState();
            cruzamentos=0;
        }
    }

} 
