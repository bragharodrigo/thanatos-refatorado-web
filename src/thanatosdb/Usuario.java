package thanatosdb;

public class Usuario {

    private String nome;
    private String login;
    private String senha;
    private String cargo; // ADMIN, VENDEDOR, COVEIRO

    public Usuario(String nome, String login, String senha, String cargo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean verificarLogin(String loginInput, String senhaInput) {
        return this.login.equals(loginInput) && this.senha.equals(senhaInput);
    }

}
