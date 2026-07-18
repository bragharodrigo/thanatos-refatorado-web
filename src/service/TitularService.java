package service;

import dao.JazigoDAO;
import dao.TitularDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import thanatosdb.Titular;

public class TitularService {
    
    private TitularDAO titularDAO;
    private JazigoDAO jazigoDAO;

    public TitularService() {
        this.titularDAO = new TitularDAO();
        this.jazigoDAO = new JazigoDAO();
    }

    public void cadastrarTitularComJazigo(String nome, String cpf, String telefone, String nascStr, Integer idJazigo) throws Exception {
        // Validação básica
        if (nome == null || nome.trim().isEmpty() || cpf == null || cpf.trim().isEmpty()) {
            throw new Exception("Nome e CPF são campos obrigatórios para o Titular.");
        }

        // Tratamento e Conversão da Data 
        LocalDate dataNascimento;
        try {
            if (nascStr.contains("-")) {
                dataNascimento = LocalDate.parse(nascStr);
            } else {
                DateTimeFormatter brFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dataNascimento = LocalDate.parse(nascStr, brFormat);
            }
        } catch (Exception e) {
            throw new Exception("Data inválida! Use o formato DD/MM/AAAA (ex: 25/12/1990) ou AAAA-MM-DD.");
        }

        // Cria a entidade e salva
        Titular titular = new Titular(nome, cpf, telefone, dataNascimento);
        int idTitular = titularDAO.cadastrar(titular);

        // Vinculação do Jazigo (Compra)
        if (idJazigo != null && idJazigo > 0) {
            jazigoDAO.comprar(idTitular, idJazigo);
        }
    }
}