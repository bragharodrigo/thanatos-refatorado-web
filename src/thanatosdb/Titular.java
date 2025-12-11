package thanatosdb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Titular {

    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private List<Jazigo> jazigos;

    public Titular(String nome, String cpf, String telefone, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.jazigos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Jazigo> getJazigos() {
        return jazigos;
    }

    public void setJazigos(List<Jazigo> jazigos) {
        this.jazigos = jazigos;
    }

    
    
    public void comprarJazigo(Jazigo jazigo) {
        this.jazigos.add(jazigo);
        jazigo.setStatus("Ocupado"); // Simulação simples de compra
        System.out.println(this.nome + " comprou o jazigo " + jazigo.getDetalhes());
    }
}
