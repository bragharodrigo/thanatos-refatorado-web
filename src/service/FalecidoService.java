package service;

import dao.FalecidoDAO;
import java.text.SimpleDateFormat;

public class FalecidoService {
    
    private FalecidoDAO falecidoDAO;

    public FalecidoService() {
        this.falecidoDAO = new FalecidoDAO();
    }

    public void registrarSepultamento(String nome, String cpf, String dataNasc, String dataSep, int idJazigo) throws Exception {
        // Validação das regras
        if (nome == null || nome.trim().isEmpty() || dataSep == null || dataSep.trim().isEmpty() || idJazigo <= 0) {
            throw new Exception("Preencha todos os campos obrigatórios e selecione um jazigo válido.");
        }

        // Formatação de Datas
        SimpleDateFormat sdfEntrada = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfSaida = new SimpleDateFormat("yyyy-MM-dd");
        
        String dataNascSQL = null;
        String dataSepSQL;

        try {
            if (dataNasc != null && !dataNasc.trim().isEmpty()) {
                dataNascSQL = sdfSaida.format(sdfEntrada.parse(dataNasc));
            }
            dataSepSQL = sdfSaida.format(sdfEntrada.parse(dataSep));
        } catch (Exception e) {
            throw new Exception("Data inválida! Use o formato dd/mm/aaaa.");
        }

        // Transfere para o DAO
        boolean sucesso = falecidoDAO.registrarSepultamento(nome, cpf, dataNascSQL, dataSepSQL, idJazigo);
        if (!sucesso) {
            throw new Exception("Falha ao registrar o sepultamento no banco de dados.");
        }
    }
}

