package thanatosdb;

public class Jazigo {

    private int idJazigo;
    private String numero;
    private String tipo;
    private String status;
    private int idSetor;

    private String nomeTitular;

    public Jazigo() {
    }

    public Jazigo(int idJazigo, String numero, String tipo, String status, int idSetor) {
        this.idJazigo = idJazigo;
        this.numero = numero;
        this.tipo = tipo;
        this.status = status;
        this.idSetor = idSetor;
    }

    public int getIdJazigo() {
        return idJazigo;
    }

    public void setIdJazigo(int idJazigo) {
        this.idJazigo = idJazigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    @Override
    public String toString() {

        return numero + " (" + tipo + ")";
    }
    
    public String getDetalhes() {
    return "Número: " + numero + ", Tipo: " + tipo + ", Status: " + status;
}
    
}
