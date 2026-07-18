package service;

import dao.JazigoDAO;
import thanatosdb.Jazigo;

public class JazigoService {
    
    private JazigoDAO jazigoDAO;

    public JazigoService() {
        this.jazigoDAO = new JazigoDAO();
    }

    public void cadastrarJazigo(String numero, String tipo, String status, int idSetor) throws Exception {
        // 1. Regras de Validação (que antes ficavam na tela)
        if (numero == null || numero.trim().isEmpty()) {
            throw new Exception("Por favor, preencha o número do jazigo.");
        }
        
        if (idSetor <= 0) {
            throw new Exception("Por favor, selecione um Setor válido para o jazigo.");
        }

       
        Jazigo novoJazigo = new Jazigo();
        novoJazigo.setNumero(numero.trim().toUpperCase()); // Padronizando o número em maiúsculo
        novoJazigo.setTipo(tipo);
        novoJazigo.setStatus(status);
        novoJazigo.setIdSetor(idSetor);

        
        jazigoDAO.cadastrar(novoJazigo);
    }
}
