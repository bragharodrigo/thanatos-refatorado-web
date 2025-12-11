package dao;

import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FalecidoDAO {

    public boolean registrarSepultamento(String nome, String cpf, String dataNascimentoStr, String dataSepultamentoStr, int idJazigo) {

        String sql = "INSERT INTO falecido (nome, cpf, nascimento, data_sepultamento, id_jazigo) VALUES (?, ?, ?, ?, ?)";

        Connection con = ConexaoDB.conectar();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cpf);

            try {
                stmt.setDate(3, Date.valueOf(dataNascimentoStr));
                stmt.setDate(4, Date.valueOf(dataSepultamentoStr));
            } catch (IllegalArgumentException e) {
                System.out.println("Erro na data (DAO): " + e.getMessage());
                return false;
            }

            stmt.setInt(5, idJazigo);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                new JazigoDAO().ocuparJazigo(idJazigo);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar sepultamento: " + e.getMessage());
        } finally {
            ConexaoDB.desconectar(con);
        }
        return false;
    }

    public java.util.List<Object[]> pesquisarPorNome(String termoBusca) {
        java.util.List<Object[]> resultados = new java.util.ArrayList<>();
        String sql = "SELECT id_falecido, nome, cpf, data_sepultamento FROM falecido WHERE nome LIKE ?";

        java.sql.Connection con = conexao.ConexaoDB.conectar();
        java.sql.PreparedStatement stmt = null;
        java.sql.ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + termoBusca + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] linha = {
                    rs.getInt("id_falecido"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_sepultamento")
                };
                resultados.add(linha);
            }
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar falecido: " + e.getMessage());
        } finally {
            conexao.ConexaoDB.desconectar(con);
        }

        return resultados;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM falecido WHERE id_falecido = ?";
        try (java.sql.Connection con = conexao.ConexaoDB.conectar(); java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(null, "Registro de falecido excluído.");
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e.getMessage());
        }
    }

    public void atualizarNome(int id, String novoNome) {
        String sql = "UPDATE falecido SET nome = ? WHERE id_falecido = ?";
        try (java.sql.Connection con = conexao.ConexaoDB.conectar(); java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (java.sql.SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

}
