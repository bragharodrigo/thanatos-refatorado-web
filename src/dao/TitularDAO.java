package dao;

import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import thanatosdb.Titular;
import java.sql.Date;
import java.sql.Statement;

public class TitularDAO {

    public int cadastrar(Titular t) throws SQLException {
        String sql = "INSERT INTO titular (nome, cpf, telefone, nascimento) VALUES (?, ?, ?, ?)";
        int idGerado = -1;

        try (Connection con = ConexaoDB.conectar(); PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, t.getNome());
            stmt.setString(2, t.getCpf());
            stmt.setString(3, t.getTelefone());
            stmt.setDate(4, Date.valueOf(t.getDataNascimento()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
            }
        }
        return idGerado;
    }

    public java.util.List<Object[]> pesquisarPorNome(String termoBusca) {
        java.util.List<Object[]> resultados = new java.util.ArrayList<>();
        String sql = "SELECT id_titular, nome, cpf, telefone FROM titular WHERE nome LIKE ?";

        try (java.sql.Connection con = conexao.ConexaoDB.conectar(); java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, "%" + termoBusca + "%");
            java.sql.ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] linha = {
                    rs.getInt("id_titular"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone")
                };
                resultados.add(linha);
            }
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro pesquisa: " + e.getMessage());
        }
        return resultados;
    }

    public void excluir(int id) {

        new JazigoDAO().desvincularTitular(id);

        String sql = "DELETE FROM titular WHERE id_titular = ?";
        try (java.sql.Connection con = conexao.ConexaoDB.conectar(); java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(null, "Titular excluído com sucesso!");
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao excluir (verifique se ele tem jazigos): " + e.getMessage());
        }
    }

    public void atualizarNome(int id, String novoNome) {
        String sql = "UPDATE titular SET nome = ? WHERE id_titular = ?";
        try (java.sql.Connection con = conexao.ConexaoDB.conectar(); java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (java.sql.SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

}
