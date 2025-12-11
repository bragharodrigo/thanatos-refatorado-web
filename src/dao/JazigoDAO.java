package dao;

import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import thanatosdb.Jazigo;

public class JazigoDAO {

    public List<Jazigo> listarJazigosLivres() {
        List<Jazigo> lista = new ArrayList<>();

        String sql = "SELECT j.id_jazigo, j.numero, j.tipo, j.status_jazigo, j.id_setor, t.nome AS nome_titular "
                + "FROM jazigo j "
                + "LEFT JOIN titular t ON j.id_titular = t.id_titular "
                + "WHERE j.status_jazigo IN ('Livre', 'Disponível')";

        try (Connection con = ConexaoDB.conectar(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Jazigo j = new Jazigo();
                j.setIdJazigo(rs.getInt("id_jazigo"));
                j.setNumero(rs.getString("numero"));
                j.setTipo(rs.getString("tipo"));
                j.setStatus(rs.getString("status_jazigo"));
                j.setIdSetor(rs.getInt("id_setor"));

                String titular = rs.getString("nome_titular");
                j.setNomeTitular(titular != null ? titular : "--- Sem Titular ---");

                lista.add(j);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar jazigos: " + e.getMessage());
        }
        return lista;
    }

    public void ocuparJazigo(int idJazigo) {
        String sql = "UPDATE jazigo SET status_jazigo = 'Ocupado' WHERE id_jazigo = ?";
        try (Connection con = ConexaoDB.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idJazigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar jazigo: " + e.getMessage());
        }
    }

    public void comprar(int idJazigo, int idTitular) throws SQLException {
        String sql = "UPDATE jazigo SET status_jazigo = 'Ocupado', id_titular = ? WHERE id_jazigo = ?";
        try (Connection con = ConexaoDB.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idTitular);
            stmt.setInt(2, idJazigo);
            stmt.executeUpdate();
        }
    }

    public void cadastrar(Jazigo jazigo) throws SQLException {
        String sql = "INSERT INTO jazigo (numero, tipo, status_jazigo, id_setor) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexaoDB.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, jazigo.getNumero());
            stmt.setString(2, jazigo.getTipo());
            stmt.setString(3, jazigo.getStatus());
            stmt.setInt(4, jazigo.getIdSetor());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar jazigo: " + e.getMessage());
        }
    }

    public List<Jazigo> pesquisar(String setorFiltro) {
        List<Jazigo> lista = new ArrayList<>();
        String sql = "SELECT * FROM jazigo";

        if (!setorFiltro.equals("Todos")) {
            sql += " WHERE id_setor = ?";
        }

        try (Connection con = ConexaoDB.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

            if (!setorFiltro.equals("Todos")) {

                char letra = setorFiltro.charAt(setorFiltro.length() - 1);
                int idSetor = 0;

                switch (letra) {
                    case 'A':
                        idSetor = 1;
                        break;
                    case 'B':
                        idSetor = 2;
                        break;
                    case 'C':
                        idSetor = 3;
                        break;
                    case 'D':
                        idSetor = 4;
                        break;
                    case 'E':
                        idSetor = 5;
                        break;
                    default:
                        idSetor = 1;
                }

                stmt.setInt(1, idSetor);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Jazigo j = new Jazigo();
                j.setIdJazigo(rs.getInt("id_jazigo"));
                j.setNumero(rs.getString("numero"));
                j.setTipo(rs.getString("tipo"));
                j.setStatus(rs.getString("status_jazigo"));
                j.setIdSetor(rs.getInt("id_setor"));
                lista.add(j);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar: " + e.getMessage());
        }
        return lista;
    }

    public java.util.List<Object[]> pesquisarPorNumero(String termoBusca) {
        java.util.List<Object[]> resultados = new java.util.ArrayList<>();

        String sql = "SELECT id_jazigo, numero, tipo, status_jazigo FROM jazigo WHERE numero LIKE ?";

        java.sql.Connection con = conexao.ConexaoDB.conectar();
        java.sql.PreparedStatement stmt = null;
        java.sql.ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + termoBusca + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] linha = {
                    rs.getInt("id_jazigo"),
                    rs.getString("numero"),
                    rs.getString("tipo"),
                    rs.getString("status_jazigo")
                };
                resultados.add(linha);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Erro ao pesquisar jazigo: " + e.getMessage());
        } finally {
            conexao.ConexaoDB.desconectar(con);
        }

        return resultados;
    }

    public void atualizarNumero(int id, String novoNumero) {
        String sql = "UPDATE jazigo SET numero = ? WHERE id_jazigo = ?";
        try (java.sql.Connection con = conexao.ConexaoDB.conectar(); java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, novoNumero);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao atualizar jazigo: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM jazigo WHERE id_jazigo = ?";
        try (java.sql.Connection con = conexao.ConexaoDB.conectar(); java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(null, "Jazigo removido do sistema.");
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro: Jazigo ocupado ou vinculado?");
        }
    }

    public void desvincularTitular(int idTitular) {

        String sql = "UPDATE jazigo SET id_titular = NULL, status_jazigo = 'Livre' WHERE id_titular = ?";

        try (java.sql.Connection con = conexao.ConexaoDB.conectar(); java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idTitular);
            stmt.executeUpdate();

        } catch (java.sql.SQLException e) {
            System.out.println("Erro ao desvincular jazigos: " + e.getMessage());
        }
    }

}
