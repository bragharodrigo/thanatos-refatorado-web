package frames;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import service.TitularService;
import service.UsuarioService;
import thanatosdb.Jazigo;

public class TelaCadastros extends JFrame {

    private UsuarioService usuarioService;
    private TitularService titularService;

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtNasc;
    private JTextField txtTelefone;
    private JTextField txtLogin;
    private JPasswordField txtSenha;

    private JComboBox<String> cmbTipo;
    private JComboBox<String> cmbNivel;
    private JComboBox<JazigoItem> cmbJazigo;

    private JLabel lblLogin;
    private JLabel lblSenha;
    private JLabel lblNivel;
    private JLabel lblJazigo;

    public TelaCadastros() {
        initComponents();
        configurarDesign();

        this.usuarioService = new UsuarioService();
        carregarJazigosDisponiveis();

        this.titularService = new TitularService();
    }

    private void configurarDesign() {
        this.setTitle("Cadastros");
        this.setSize(850, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
    }

    private void carregarJazigosDisponiveis() {
        cmbJazigo.removeAllItems();
        cmbJazigo.addItem(new JazigoItem(null, "- Nenhum -"));

        try {
            dao.JazigoDAO jDao = new dao.JazigoDAO();
            List<Jazigo> livres = jDao.listarJazigosLivres();
            for (Jazigo j : livres) {
                String label = "Nº: " + j.getNumero() + " (" + j.getTipo() + ")";
                cmbJazigo.addItem(new JazigoItem(j, label));
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar jazigos: " + e.getMessage());
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

        JLabel lblTitulo = new JLabel("CADASTRAR NOVO REGISTRO");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(60, 60, 60));
        lblTitulo.setBounds(30, 20, 400, 30);
        content.add(lblTitulo);

        JPanel panelForm = new JPanel();
        panelForm.setBounds(30, 70, 570, 460);
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createTitledBorder("Dados Cadastrais"));
        panelForm.setLayout(null);
        content.add(panelForm);

        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setBounds(20, 30, 120, 25);
        panelForm.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(150, 30, 380, 25);
        panelForm.add(txtNome);

        JLabel lblTipo = new JLabel("Tipo de Cadastro:");
        lblTipo.setBounds(20, 70, 120, 25);
        panelForm.add(lblTipo);

        cmbTipo = new JComboBox<>(new String[]{"- Selecione -", "Titular (Cliente)", "Funcionário"});
        cmbTipo.setBounds(150, 70, 180, 25);
        panelForm.add(cmbTipo);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(20, 110, 120, 25);
        panelForm.add(lblCpf);

        txtCpf = new JTextField();
        txtCpf.setBounds(150, 110, 180, 25);
        panelForm.add(txtCpf);

        JLabel lblNasc = new JLabel("Nascimento:");
        lblNasc.setBounds(20, 150, 120, 25);
        panelForm.add(lblNasc);

        txtNasc = new JTextField();
        txtNasc.setBounds(150, 150, 180, 25);
        panelForm.add(txtNasc);

        JLabel lblTel = new JLabel("Telefone:");
        lblTel.setBounds(20, 190, 120, 25);
        panelForm.add(lblTel);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(150, 190, 180, 25);
        panelForm.add(txtTelefone);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 235, 530, 10);
        panelForm.add(separator);

        // Componentes do bloco de Funcionário
        lblLogin = new JLabel("Login de Acesso:");
        lblLogin.setBounds(20, 250, 120, 25);
        panelForm.add(lblLogin);

        txtLogin = new JTextField();
        txtLogin.setBounds(150, 250, 180, 25);
        panelForm.add(txtLogin);

        lblSenha = new JLabel("Definir Senha:");
        lblSenha.setBounds(20, 290, 120, 25);
        panelForm.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(150, 290, 180, 25);
        panelForm.add(txtSenha);

        lblNivel = new JLabel("Cargo / Nível:");
        lblNivel.setBounds(20, 330, 120, 25);
        panelForm.add(lblNivel);

        cmbNivel = new JComboBox<>(new String[]{"- Escolha -", "ADMIN", "VENDEDOR", "COVEIRO"});
        cmbNivel.setBounds(150, 330, 180, 25);
        panelForm.add(cmbNivel);

        // Componentes do bloco de Titular
        lblJazigo = new JLabel("Compra de Jazigo:");
        lblJazigo.setBounds(20, 250, 120, 25);
        panelForm.add(lblJazigo);

        cmbJazigo = new JComboBox<>();
        cmbJazigo.setBounds(150, 250, 250, 25);
        panelForm.add(cmbJazigo);

        // Oculta os blocos condicionais inicialmente
        lblLogin.setVisible(false);
        txtLogin.setVisible(false);
        lblSenha.setVisible(false);
        txtSenha.setVisible(false);
        lblNivel.setVisible(false);
        cmbNivel.setVisible(false);
        lblJazigo.setVisible(false);
        cmbJazigo.setVisible(false);

        // Controle de visibilidade da tela
        cmbTipo.addActionListener(e -> {
            String selecionado = cmbTipo.getSelectedItem().toString();
            boolean isFunc = selecionado.equals("Funcionário");
            boolean isTitular = selecionado.equals("Titular (Cliente)");

            lblLogin.setVisible(isFunc);
            txtLogin.setVisible(isFunc);
            lblSenha.setVisible(isFunc);
            txtSenha.setVisible(isFunc);
            lblNivel.setVisible(isFunc);
            cmbNivel.setVisible(isFunc);

            lblJazigo.setVisible(isTitular);
            cmbJazigo.setVisible(isTitular);
            lblCpf.setVisible(isTitular);
            txtCpf.setVisible(isTitular);
            lblNasc.setVisible(isTitular);
            txtNasc.setVisible(isTitular);
            lblTel.setVisible(isTitular);
            txtTelefone.setVisible(isTitular);
        });

        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.setBounds(220, 390, 130, 35);
        btnSalvar.setBackground(new Color(98, 0, 238));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.addActionListener(e -> salvarCadastro());
        panelForm.add(btnSalvar);
    }

    // Método refatorado 
    private void salvarCadastro() {
        String tipo = cmbTipo.getSelectedItem().toString();
        String nome = txtNome.getText().trim();

        if (tipo.equals("Funcionário")) {
            try {
                String login = txtLogin.getText().trim();
                String senha = new String(txtSenha.getPassword()).trim();
                String cargo = cmbNivel.getSelectedItem().toString();

                usuarioService.cadastrarUsuario(nome, login, senha, cargo);

                JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");

                txtNome.setText("");
                txtLogin.setText("");
                txtSenha.setText("");
                cmbNivel.setSelectedIndex(0);
                cmbTipo.setSelectedIndex(0);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
            }
        } else if (tipo.equals("Titular (Cliente)")) {
            try {
                String cpf = txtCpf.getText().trim();
                String tel = txtTelefone.getText().trim();
                String nascStr = txtNasc.getText().trim();

                // Captura o ID do jazigo (se houver algum selecionado)
                JazigoItem itemJazigo = (JazigoItem) cmbJazigo.getSelectedItem();
                Integer idJazigo = null;
                if (itemJazigo != null && itemJazigo.jazigo != null) {
                    idJazigo = itemJazigo.jazigo.getIdJazigo();
                }

                // Consome a camada Service!
                titularService.cadastrarTitularComJazigo(nome, cpf, tel, nascStr, idJazigo);

                if (idJazigo != null) {
                    JOptionPane.showMessageDialog(this, "Titular cadastrado e Jazigo vinculado!");
                } else {
                    JOptionPane.showMessageDialog(this, "Titular cadastrado (sem jazigo)!");
                }

                txtNome.setText("");
                txtCpf.setText("");
                txtNasc.setText("");
                txtTelefone.setText("");
                cmbTipo.setSelectedIndex(0);
                carregarJazigosDisponiveis();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
            }
        }

    }

    private class JazigoItem {

        final Jazigo jazigo;
        final String label;

        public JazigoItem(Jazigo jazigo, String label) {
            this.jazigo = jazigo;
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }
}
