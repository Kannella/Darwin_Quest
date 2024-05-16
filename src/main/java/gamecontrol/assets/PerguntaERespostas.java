package gamecontrol.assets;

public class PerguntaERespostas {
    private String pergunta;
    private String resposta1;
    private String resposta2;
    private String resposta3;

    public PerguntaERespostas(String pergunta, String resposta1, String resposta2, String resposta3) {
        this.pergunta = pergunta;
        this.resposta1 = resposta1;
        this.resposta2 = resposta2;
        this.resposta3 = resposta3;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getResposta1() {
        return resposta1;
    }

    public String getResposta2() {
        return resposta2;
    }

    public String getResposta3() {
        return resposta3;
    }
}
