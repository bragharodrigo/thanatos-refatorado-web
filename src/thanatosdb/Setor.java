package thanatosdb;

public class Setor {

    private int idSetor;
    private String nomeSetor;
    private String descricao;

    // Construtores
    public Setor() {
    }

    public Setor(String nomeSetor, String descricao) {
        this.nomeSetor = nomeSetor;
        this.descricao = descricao;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
