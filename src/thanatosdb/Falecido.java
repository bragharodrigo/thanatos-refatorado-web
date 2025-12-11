package thanatosdb;

import java.time.LocalDateTime;

public class Falecido {

    private String nome;
    private LocalDateTime dataSepultamento;
    private Jazigo jazigo;

    public Falecido(String nome, LocalDateTime dataSepultamento, Jazigo jazigo) {
        this.nome = nome;
        this.dataSepultamento = dataSepultamento;
        this.jazigo = jazigo;
    }

    public String getDados() {
        return "Falecido: " + nome + " | Sepultamento: " + dataSepultamento;
    }

}
