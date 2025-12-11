package frames;

import dao.SetorDAO;
import thanatosdb.Setor;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaBlocos extends javax.swing.JFrame {

    private JPanel panelCards;

    public TelaBlocos() {
        initComponents();
        configurarDesign();
        carregarBlocosDoBanco();
    }

    private void configurarDesign() {
        this.setTitle("Gerenciamento de Blocos");
        this.setSize(850, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
    }

    private void carregarBlocosDoBanco() {
        SetorDAO dao = new SetorDAO();
        List<Setor> lista = dao.listar();

        if (panelCards != null) {
            panelCards.removeAll();

            for (Setor s : lista) {
                int porcentagem = dao.getPorcentagemOcupacao(s.getIdSetor());
                criarCardBloco(panelCards, "Setor " + s.getNomeSetor(), porcentagem, s.getDescricao());
            }

            panelCards.revalidate();
            panelCards.repaint();
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

        JLabel lblTitulo = new JLabel("GERENCIAMENTO DE BLOCOS");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(60, 60, 60));
        lblTitulo.setBounds(30, 20, 400, 30);
        content.add(lblTitulo);

        JPanel panelCadastro = new JPanel();
        panelCadastro.setBounds(30, 70, 570, 100);
        panelCadastro.setBackground(Color.WHITE);
        panelCadastro.setBorder(BorderFactory.createTitledBorder("Novo Setor"));
        panelCadastro.setLayout(null);
        content.add(panelCadastro);

        JLabel lblNome = new JLabel("Letra do Setor (ex: F):");
        lblNome.setBounds(20, 35, 150, 25);
        panelCadastro.add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(160, 35, 50, 25);
        panelCadastro.add(txtNome);

        JLabel lblDesc = new JLabel("Descrição:");
        lblDesc.setBounds(220, 35, 80, 25);
        panelCadastro.add(lblDesc);

        JTextField txtDesc = new JTextField();
        txtDesc.setBounds(290, 35, 120, 25);
        panelCadastro.add(txtDesc);

        JButton btnSalvar = new JButton("ADICIONAR");
        btnSalvar.setBounds(430, 35, 110, 25);
        btnSalvar.setBackground(new Color(98, 0, 238));
        btnSalvar.setForeground(Color.WHITE);

        btnSalvar.addActionListener(e -> {
            try {
                String nome = txtNome.getText().toUpperCase().trim();
                String desc = txtDesc.getText();

                if (nome.length() != 1) {
                    JOptionPane.showMessageDialog(this, "O setor deve ser apenas 1 letra (Ex: F, G, H).");
                    return;
                }

                Setor novo = new Setor(nome, desc);
                SetorDAO dao = new SetorDAO();
                dao.cadastrar(novo);

                JOptionPane.showMessageDialog(this, "Setor " + nome + " criado!");
                txtNome.setText("");
                txtDesc.setText("");

                carregarBlocosDoBanco();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });
        panelCadastro.add(btnSalvar);

        JLabel lblLista = new JLabel("Blocos Cadastrados / Ocupação:");
        lblLista.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblLista.setForeground(new Color(100, 100, 100));
        lblLista.setBounds(30, 190, 300, 20);
        content.add(lblLista);

        panelCards = new JPanel();

        panelCards.setLayout(new GridLayout(0, 1, 0, 10));
        panelCards.setBackground(new Color(230, 230, 230));

        JScrollPane scrollCards = new JScrollPane(panelCards);
        scrollCards.setBounds(30, 220, 570, 320);
        scrollCards.setBorder(null);

        scrollCards.getVerticalScrollBar().setUnitIncrement(16);
        content.add(scrollCards);
    }

    private void criarCardBloco(JPanel container, String nome, int porcentagem, String descricao) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(520, 100));
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        JLabel lblNome = new JLabel(nome);
        lblNome.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblNome.setBounds(15, 10, 200, 25);
        card.add(lblNome);

        JLabel lblDesc = new JLabel(descricao);
        lblDesc.setFont(new Font("SansSerif", Font.ITALIC, 11));
        lblDesc.setForeground(Color.GRAY);
        lblDesc.setBounds(15, 30, 300, 15);
        card.add(lblDesc);

        JLabel lblOcupacao = new JLabel("Ocupação: " + porcentagem + "%");
        lblOcupacao.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblOcupacao.setForeground(new Color(80, 80, 80));
        lblOcupacao.setBounds(400, 10, 100, 20);
        card.add(lblOcupacao);

        JProgressBar progress = new JProgressBar(0, 100);
        progress.setValue(porcentagem);
        progress.setBounds(15, 60, 490, 15);
        progress.setForeground(new Color(98, 0, 238));

        if (porcentagem > 80) {
            progress.setForeground(Color.RED);
        } else if (porcentagem > 50) {
            progress.setForeground(Color.ORANGE);
        }

        card.add(progress);
        container.add(card);
    }
}
