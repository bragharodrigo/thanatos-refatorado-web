package frames;

import dao.JazigoDAO;
import thanatosdb.Jazigo;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaJazigos extends javax.swing.JFrame {

    private service.JazigoService jazigoService;

    private JTable tabela;
    private DefaultTableModel tableModel;

    public TelaJazigos() {
        initComponents();
        configurarDesign();

        this.jazigoService = new service.JazigoService();
    }

    private void configurarDesign() {
        this.setTitle("Jazigos");
        this.setSize(850, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
    }

    private void preencherTabela(String filtroSetor) {
        JazigoDAO dao = new JazigoDAO();

        List<Jazigo> lista = dao.pesquisar(filtroSetor);

        tableModel.setRowCount(0);

        for (Jazigo j : lista) {

            String letraSetor;
            switch (j.getIdSetor()) {
                case 1:
                    letraSetor = "A";
                    break;
                case 2:
                    letraSetor = "B";
                    break;
                case 3:
                    letraSetor = "C";
                    break;
                case 4:
                    letraSetor = "D";
                    break;
                case 5:
                    letraSetor = "E";
                    break;
                default:
                    letraSetor = String.valueOf(j.getIdSetor());
            }

            tableModel.addRow(new Object[]{
                j.getIdJazigo(),
                letraSetor,
                j.getTipo(),
                j.getStatus()
            });
        }
    }

    private void initComponents() {

        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(50, 50, 50));
        sidebar.setBounds(0, 0, 220, 600);
        sidebar.setLayout(null);
        add(sidebar);

        JLabel lblLogo = new JLabel("THANATOS");
        lblLogo.setFont(new Font("Serif", Font.BOLD, 22));
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setBounds(40, 30, 150, 30);
        sidebar.add(lblLogo);

        JLabel lblLite = new JLabel("Lite");
        lblLite.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblLite.setForeground(Color.LIGHT_GRAY);
        lblLite.setBounds(100, 55, 50, 20);
        sidebar.add(lblLite);

        JButton btnVoltar = new JButton("VOLTAR / MENU");
        btnVoltar.setBounds(20, 500, 180, 35);
        btnVoltar.setBackground(new Color(100, 100, 100));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        btnVoltar.addActionListener(e -> {
            new TelaPrincipal().setVisible(true);
            this.dispose();
        });
        sidebar.add(btnVoltar);

        JPanel content = new JPanel();
        content.setBackground(new Color(230, 230, 230));
        content.setBounds(220, 0, 630, 600);
        content.setLayout(null);
        add(content);

        JLabel lblTitulo = new JLabel("GERENCIAMENTO DE JAZIGOS");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(60, 60, 60));
        lblTitulo.setBounds(30, 20, 400, 30);
        content.add(lblTitulo);

        JPanel panelPesquisa = new JPanel();
        panelPesquisa.setBounds(30, 70, 570, 200);
        panelPesquisa.setBackground(Color.WHITE);
        panelPesquisa.setBorder(BorderFactory.createTitledBorder("Pesquisa / Listagem"));
        panelPesquisa.setLayout(null);
        content.add(panelPesquisa);

        JLabel lblFiltro = new JLabel("Filtrar por Setor:");
        lblFiltro.setBounds(20, 30, 100, 25);
        panelPesquisa.add(lblFiltro);

        JComboBox<String> cmbFiltroSetor = new JComboBox<>(new String[]{"Todos", "Setor A", "Setor B", "Setor C", "Setor D", "Setor E"});
        cmbFiltroSetor.setBounds(120, 30, 150, 25);
        panelPesquisa.add(cmbFiltroSetor);

        String[] colunas = {"ID", "Setor", "Tipo", "Status"};

        tableModel = new DefaultTableModel(null, colunas);
        tabela = new JTable(tableModel);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 70, 530, 110);
        panelPesquisa.add(scroll);

        preencherTabela("Todos");

        JButton btnPesquisar = new JButton("PESQUISAR");
        btnPesquisar.setBounds(290, 30, 120, 25);
        btnPesquisar.setBackground(new Color(98, 0, 238));
        btnPesquisar.setForeground(Color.WHITE);

        btnPesquisar.addActionListener(e -> {
            String filtro = (String) cmbFiltroSetor.getSelectedItem();
            preencherTabela(filtro);
        });
        panelPesquisa.add(btnPesquisar);

        JPanel panelCadastro = new JPanel();
        panelCadastro.setBounds(30, 290, 570, 200);
        panelCadastro.setBackground(Color.WHITE);
        panelCadastro.setBorder(BorderFactory.createTitledBorder("Novo Cadastro / Edição"));
        panelCadastro.setLayout(null);
        content.add(panelCadastro);

        JLabel lblNum = new JLabel("Número:");
        lblNum.setBounds(20, 40, 60, 25);
        panelCadastro.add(lblNum);
        JTextField txtNum = new JTextField();
        txtNum.setBounds(80, 40, 80, 25);
        panelCadastro.add(txtNum);

        JLabel lblSetor = new JLabel("Setor:");
        lblSetor.setBounds(180, 40, 50, 25);
        panelCadastro.add(lblSetor);
        JComboBox<String> cmbSetor = new JComboBox<>(new String[]{"Setor A", "Setor B", "Setor C", "Setor D", "Setor E"});
        cmbSetor.setBounds(230, 40, 100, 25);
        panelCadastro.add(cmbSetor);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(350, 40, 40, 25);
        panelCadastro.add(lblTipo);
        JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Túmulo", "Gaveta"});
        cmbTipo.setBounds(390, 40, 100, 25);
        panelCadastro.add(cmbTipo);

        JLabel lblStatus = new JLabel("Status Inicial:");
        lblStatus.setBounds(20, 90, 100, 25);
        panelCadastro.add(lblStatus);
        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"Disponível", "Ocupado"});
        cmbStatus.setBounds(110, 90, 150, 25);
        panelCadastro.add(cmbStatus);

        JButton btnSalvar = new JButton("SALVAR JAZIGO");
        btnSalvar.setBounds(20, 140, 150, 35);
        btnSalvar.setBackground(new Color(0, 150, 0)); // Verde
        btnSalvar.setForeground(Color.WHITE);

        btnSalvar.addActionListener(e -> {
            try {
                // 1. Captura os dados 
                String numero = txtNum.getText();
                String tipo = cmbTipo.getSelectedItem().toString();
                String status = cmbStatus.getSelectedItem().toString();

                int idSetor = cmbSetor.getSelectedIndex();

                // 2. TRansferindo responsabilidade para a camada Serviços
                jazigoService.cadastrarJazigo(numero, tipo, status, idSetor);

                // 3. Noticação de salvamento 
                javax.swing.JOptionPane.showMessageDialog(this, "Jazigo Salvo com Sucesso!");

                // Limpa os campos da tela
                txtNum.setText("");
                cmbSetor.setSelectedIndex(0);

                // preencherTabela();
            } catch (Exception ex) {
                // 4. Captura de regras não atendidas
                javax.swing.JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
            }
        });
        panelCadastro.add(btnSalvar);

    }
}
