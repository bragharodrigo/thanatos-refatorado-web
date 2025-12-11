package frames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import dao.TitularDAO;
import dao.FalecidoDAO;
import dao.JazigoDAO;
import java.util.List;

public class TelaConsulta extends javax.swing.JFrame {

    private JTable tabelaResultados;
    private DefaultTableModel modeloTabela;
    private JTextField txtBusca;
    private JComboBox<String> cmbTipoConsulta;

    public TelaConsulta() {
        initComponents();
        configurarDesign();
    }

    private void configurarDesign() {
        this.setTitle("Consultas e Gerenciamento");
        this.setSize(850, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
    }

    private void initComponents() {

        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(50, 50, 50));
        sidebar.setBounds(0, 0, 220, 650);
        sidebar.setLayout(null);
        add(sidebar);

        JLabel lblLogo = new JLabel("THANATOS");
        lblLogo.setFont(new Font("Serif", Font.BOLD, 22));
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setBounds(40, 30, 150, 30);
        sidebar.add(lblLogo);

        JButton btnVoltar = new JButton("VOLTAR / MENU");
        btnVoltar.setBounds(20, 550, 180, 35);
        btnVoltar.setBackground(new Color(100, 100, 100));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.addActionListener(e -> {
            new TelaPrincipal().setVisible(true);
            this.dispose();
        });
        sidebar.add(btnVoltar);

        JPanel content = new JPanel();
        content.setBackground(new Color(230, 230, 230));
        content.setBounds(220, 0, 630, 650);
        content.setLayout(null);
        add(content);

        JPanel panelFiltros = new JPanel();
        panelFiltros.setBounds(40, 40, 550, 100);
        panelFiltros.setBackground(Color.WHITE);
        panelFiltros.setLayout(null);
        content.add(panelFiltros);

        JLabel lblTipo = new JLabel("TIPO:");
        lblTipo.setBounds(20, 20, 50, 25);
        panelFiltros.add(lblTipo);

        cmbTipoConsulta = new JComboBox<>(new String[]{"TITULAR", "FALECIDO", "JAZIGO"});
        cmbTipoConsulta.setBounds(70, 20, 150, 25);
        panelFiltros.add(cmbTipoConsulta);

        JLabel lblBusca = new JLabel("BUSCA:");
        lblBusca.setBounds(230, 20, 60, 25);
        panelFiltros.add(lblBusca);

        txtBusca = new JTextField();
        txtBusca.setBounds(290, 20, 140, 25);
        panelFiltros.add(txtBusca);

        JButton btnPesquisar = new JButton("BUSCAR");
        btnPesquisar.setBounds(440, 20, 90, 25);
        btnPesquisar.setBackground(new Color(98, 0, 238));
        btnPesquisar.setForeground(Color.WHITE);
        btnPesquisar.addActionListener(e -> realizarPesquisa());
        panelFiltros.add(btnPesquisar);

        String[] colunas = {"ID", "NOME / NUMERO", "CPF / TIPO", "INFO EXTRA"};
        modeloTabela = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaResultados = new JTable(modeloTabela);

        tabelaResultados.getColumnModel().getColumn(0).setPreferredWidth(40);

        JScrollPane scroll = new JScrollPane(tabelaResultados);
        scroll.setBounds(40, 160, 550, 300);
        content.add(scroll);

        JButton btnEditar = new JButton("EDITAR NOME");
        btnEditar.setBounds(40, 480, 150, 40);
        btnEditar.setBackground(new Color(255, 193, 7));
        btnEditar.setForeground(Color.BLACK);
        btnEditar.addActionListener(e -> acaoEditar());
        content.add(btnEditar);

        JButton btnExcluir = new JButton("EXCLUIR SELEÇÃO");
        btnExcluir.setBounds(200, 480, 150, 40);
        btnExcluir.setBackground(new Color(220, 53, 69));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.addActionListener(e -> acaoExcluir());
        content.add(btnExcluir);
    }

    private void realizarPesquisa() {
        String tipo = (String) cmbTipoConsulta.getSelectedItem();
        String termo = txtBusca.getText();
        modeloTabela.setRowCount(0);

        List<Object[]> resultados = null;

        if ("TITULAR".equals(tipo)) {
            resultados = new TitularDAO().pesquisarPorNome(termo);
        } else if ("FALECIDO".equals(tipo)) {
            resultados = new FalecidoDAO().pesquisarPorNome(termo);
        } else if ("JAZIGO".equals(tipo)) {
            resultados = new JazigoDAO().pesquisarPorNumero(termo);
        }

        if (resultados != null) {
            for (Object[] linha : resultados) {
                modeloTabela.addRow(linha);
            }
        }
    }

    private void acaoExcluir() {
        int linha = tabelaResultados.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item na tabela para excluir.");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        int id = (int) tabelaResultados.getValueAt(linha, 0);
        String tipo = (String) cmbTipoConsulta.getSelectedItem();

        if ("TITULAR".equals(tipo)) {
            new TitularDAO().excluir(id);
        } else if ("FALECIDO".equals(tipo)) {
            new FalecidoDAO().excluir(id);
        } else if ("JAZIGO".equals(tipo)) {
            new JazigoDAO().excluir(id);
        }

        realizarPesquisa();
    }

    private void acaoEditar() {
        int linha = tabelaResultados.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item para editar.");
            return;
        }

        int id = (int) tabelaResultados.getValueAt(linha, 0);
        String atual = (String) tabelaResultados.getValueAt(linha, 1);
        String tipo = (String) cmbTipoConsulta.getSelectedItem();

        String novoValor = JOptionPane.showInputDialog(this, "Editar Nome/Número:", atual);

        if (novoValor != null && !novoValor.isEmpty()) {

            if ("TITULAR".equals(tipo)) {
                new TitularDAO().atualizarNome(id, novoValor);
            } else if ("FALECIDO".equals(tipo)) {
                new FalecidoDAO().atualizarNome(id, novoValor);
            } else if ("JAZIGO".equals(tipo)) {
                new JazigoDAO().atualizarNumero(id, novoValor);
            }

            realizarPesquisa();
        }
    }
}
