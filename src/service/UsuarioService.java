package service;

import dao.UsuarioDAO;
import thanatosdb.Usuario;

public class UsuarioService {
    
    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Criar usuário
    public void cadastrarUsuario(String nome, String login, String senha, String cargo) throws Exception {
        // Validações
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("O nome não pode estar vazio.");
        }
        if (login == null || login.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
            throw new Exception("Login e senha são obrigatórios!");
        }
        if (cargo == null || cargo.trim().isEmpty() || cargo.equals("- Escolha -")) {
            throw new Exception("Selecione um cargo válido.");
        }

        // Cria o objeto
        Usuario novoUsuario = new Usuario(nome, login, senha, cargo);
        usuarioDAO.cadastrar(novoUsuario);
    }
}
