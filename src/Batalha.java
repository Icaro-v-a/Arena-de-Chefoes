/**
 * MODELO INICIAL - É AQUI QUE A EQUIPE VAI PROGRAMAR AS REGRAS DO JOGO.
 * A comunicação HTTP já está pronta em ServidorArena.java.
 */
public class Batalha {
    private int vidaJogador = 100;
    private int vidaChefe = 150;
    private int pocoes = 2;
    private int turno = 1;
    private boolean encerrado = false;
    private String resultado = "em_andamento";
    private String mensagem = "A batalha começou! Escolha sua primeira ação.";

    public synchronized String agir(String acao) {
        // TODO 1: implementar as ações atacar (18 a 25), defender e curar (26 a 34).
        //          Cura inválida não gasta poção nem turno.
        // TODO 2: se o chefe estiver vivo, fazê-lo contra-atacar:
        //          - turno múltiplo de 3: ESPECIAL, 32 a 42 de dano;
        //          - demais turnos: COMUM, 11 a 17 de dano;
        //          - defender reduz o dano recebido a 1/4 (mínimo 1).
        // TODO 3: limitar os PV, verificar vitória/derrota e incrementar
        //          o turno SOMENTE depois de uma ação válida.
        // DICA: use java.util.Random, Math.min e Math.max.
        mensagem = "TODO em Batalha.java: implementar a ação '" + acao + "'.";
        return estadoJson();
    }

    public synchronized String reiniciar() {
        vidaJogador = 100;
        vidaChefe = 150;
        pocoes = 2;
        turno = 1;
        encerrado = false;
        resultado = "em_andamento";
        mensagem = "Nova batalha! Boa sorte.";
        return estadoJson();
    }

    // Este aviso do PRÓXIMO contra-ataque já está implementado para vocês.
    // As regras em agir(...) devem corresponder a ele.
    private String avisoProximoTurno() {
        if (encerrado) return "Batalha encerrada.";
        if (turno % 3 == 0) {
            return "⚠️ ATENÇÃO! O próximo contra-ataque será ESPECIAL!";
        }
        return "Próximo contra-ataque: COMUM.";
    }

    /** Contrato da API: não altere os nomes das propriedades sem atualizar o JS. */
    public synchronized String estadoJson() {
        return "{" +
            "\"jogadorVida\":" + vidaJogador + "," +
            "\"jogadorMax\":100," +
            "\"chefeVida\":" + vidaChefe + "," +
            "\"chefeMax\":150," +
            "\"pocoes\":" + pocoes + "," +
            "\"turno\":" + turno + "," +
            "\"encerrado\":" + encerrado + "," +
            "\"resultado\":\"" + escapar(resultado) + "\"," +
            "\"mensagem\":\"" + escapar(mensagem) + "\"," +
            "\"aviso\":\"" + escapar(avisoProximoTurno()) + "\"" +
            "}";
    }

    private String escapar(String texto) {
        return texto.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r");
    }
}
