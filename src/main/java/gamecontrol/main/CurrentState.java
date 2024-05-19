package gamecontrol.main;

public class CurrentState {

    GamePanel gp;
    private int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;
    public final int chooseState = 4;
    public final int finalState = 5;
    public final int explanationState = 6;

    public CurrentState() {
        // Define o estado inicial do jogo aqui
        gameState = playState;
    }

    public void setPlayState() {
        // Define o estado do jogo como "Jogando"
        gameState = playState;
    }

    public void setPauseState() {
        // Define o estado do jogo como "Pausado"
        gameState = pauseState;
    }

    public void setGameOverState() {
        // Define o estado do jogo como "Fim de Jogo"
        gameState = gameOverState;
    }

    public void setChooseState() {
        // Define o estado do jogo como "Escolha"
        gameState = chooseState;
    }

    public void setFinalState() {
        // Define o estado do jogo como "Final"
        gameState = finalState;
    }

    public int getGameState() {
        // Obtém o estado atual do jogo
        return gameState;
    }

    public void setExplanationState() {
        // Define o estado do jogo como "Explicação"
        gameState = explanationState;
    }
}    