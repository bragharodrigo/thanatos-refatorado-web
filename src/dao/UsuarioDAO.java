package dao;

import conexao.ConexaoDB;
import java.sql.*;
import thanatosdb.Usuario;

public class UsuarioDAO {

    public void cadastrar(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuario (nome, login, senha, cargo) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexaoDB.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getLogin());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getCargo());
            stmt.executeUpdate();
        }
    }
}
