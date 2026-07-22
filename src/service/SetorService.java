package service;

import dao.SetorDAO;
import thanatosdb.Setor;
import java.util.List;

public class SetorService {

    private SetorDAO setorDAO;

    public SetorService() {
        this.setorDAO = new SetorDAO();
    }

    public void cadastrarSetor(String nome, String descricao) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("A letra do setor não pode ser vazia.");
        }

        // Padronizar e validar o tamanho
        nome = nome.toUpperCase().trim();
        if (nome.length() != 1) {
            throw new Exception("O setor deve ser apenas 1 letra (Ex: F, G, H).");
        }

        Setor novoSetor = new Setor(nome, descricao);
        setorDAO.cadastrar(novoSetor);
    }

    public List<Setor> listarSetores() {
        return setorDAO.listar();
    }

    public int calcularOcupacao(int idSetor) {
        return setorDAO.getPorcentagemOcupacao(idSetor);
    }

    // Método de cálculo puro (sem acesso ao banco de dados) 
    public double calcularPorcentagemOcupacao(int vagasOcupadas, int vagasTotais) throws Exception {
        if (vagasTotais <= 0) {
            throw new Exception("A capacidade total do setor deve ser maior que zero.");
        }
        if (vagasOcupadas < 0) {
            throw new Exception("O número de vagas ocupadas não pode ser negativo.");
        }
        if (vagasOcupadas > vagasTotais) {
            throw new Exception("O número de vagas ocupadas não pode exceder a capacidade total.");
        }

        return ((double) vagasOcupadas / vagasTotais) * 100.0;
    }
}
