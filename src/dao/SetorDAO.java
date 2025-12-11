package dao;

import conexao.ConexaoDB;
import thanatosdb.Setor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SetorDAO {

    public void cadastrar(Setor setor) throws SQLException {
        String sql = "INSERT INTO setor (nome_setor, descricao) VALUES (?, ?)";
        try (Connection con = ConexaoDB.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, setor.getNomeSetor());
            stmt.setString(2, setor.getDescricao());
            stmt.executeUpdate();
        }
    }

    public List<Setor> listar() {
        List<Setor> lista = new ArrayList<>();
        String sql = "SELECT * FROM setor";
        try (Connection con = ConexaoDB.conectar(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Setor s = new Setor();
                s.setIdSetor(rs.getInt("id_setor"));
                s.setNomeSetor(rs.getString("nome_setor"));
                s.setDescricao(rs.getString("descricao"));
                lista.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar setores: " + e.getMessage());
        }
        return lista;
    }

    public int getPorcentagemOcupacao(int idSetor) {
        String sqlTotal = "SELECT COUNT(*) as total FROM jazigo WHERE id_setor = ?";
        String sqlOcupados = "SELECT COUNT(*) as ocupados FROM jazigo WHERE id_setor = ? AND status_jazigo = 'Ocupado'";

        int total = 0;
        int ocupados = 0;

        try (Connection con = ConexaoDB.conectar()) {

            PreparedStatement stmt1 = con.prepareStatement(sqlTotal);
            stmt1.setInt(1, idSetor);
            ResultSet rs1 = stmt1.executeQuery();
            if (rs1.next()) {
                total = rs1.getInt("total");
            }

            PreparedStatement stmt2 = con.prepareStatement(sqlOcupados);
            stmt2.setInt(1, idSetor);
            ResultSet rs2 = stmt2.executeQuery();
            if (rs2.next()) {
                ocupados = rs2.getInt("ocupados");
            }

        } catch (SQLException e) {
            return 0;
        }

        if (total == 0) {
            return 0;
        }

        return (ocupados * 100) / total;
    }

}
