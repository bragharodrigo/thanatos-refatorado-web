package frames;

import dao.JazigoDAO;
import dao.TitularDAO;
import dao.UsuarioDAO;
import thanatosdb.Jazigo;
import thanatosdb.Titular;
import thanatosdb.Usuario;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TelaCadastros extends javax.swing.JFrame {

    private JComboBox<JazigoItem> cmbJazigo;
    private JComboBox<String> cmbNivel;
    private JPasswordField txtSenha;
    private JTextField txtLogin;
    private JTextField txtNome, txtCpf, txtNasc, txtTelefone;
    private JLabel lblSenha, lblLogin;
    private JComboBox<String> cmbTipo;

    public TelaCadastros() {
        initComponents();
        configurarDesign();
        carregarJazigosDisponiveis();
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
        JazigoDAO dao = new JazigoDAO();
        List<Jazigo> livres = dao.listarJazigosLivres();

        cmbJazigo.removeAllItems();
        // Item "vazio"
        cmbJazigo.addItem(new JazigoItem(null, "- Nenhum -"));

        for (Jazigo j : livres) {
            cmbJazigo.addItem(new JazigoItem(j, j.getNumero() + " (" + j.getTipo() + ")"));
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
        panelForm.setBounds(30, 70, 570, 450); // Aumentei um pouco a altura
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createTitledBorder("Dados Cadastrais"));
        panelForm.setLayout(null);
        content.add(panelForm);

        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setBounds(30, 30, 150, 20);
        panelForm.add(lblNome);
        txtNome = new JTextField();
        txtNome.setBounds(30, 50, 500, 30);
        panelForm.add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(30, 90, 100, 20);
        panelForm.add(lblCpf);
        txtCpf = new JTextField();
        txtCpf.setBounds(30, 110, 150, 30);
        panelForm.add(txtCpf);

        JLabel lblNasc = new JLabel("Nascimento (DD-MM-AAAA):");
        lblNasc.setBounds(200, 90, 180, 20);
        panelForm.add(lblNasc);
        txtNasc = new JTextField();
        txtNasc.setBounds(200, 110, 150, 30);
        panelForm.add(txtNasc);

        JLabel lblTel = new JLabel("Telefone:");
        lblTel.setBounds(370, 90, 100, 20);
        panelForm.add(lblTel);
        txtTelefone = new JTextField();
        txtTelefone.setBounds(370, 110, 160, 30);
        panelForm.add(txtTelefone);

        JLabel lblTipo = new JLabel("Tipo de Cadastro:");
        lblTipo.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblTipo.setBounds(30, 160, 150, 20);
        panelForm.add(lblTipo);

        String[] tipos = {"- Selecione -", "Titular (Cliente)", "Usuário (Funcionário)"};
        cmbTipo = new JComboBox<>(tipos);
        cmbTipo.setBounds(30, 180, 200, 30);
        panelForm.add(cmbTipo);

        lblLogin = new JLabel("Login de Acesso:");
        lblLogin.setBounds(280, 160, 150, 20);
        lblLogin.setVisible(false);
        panelForm.add(lblLogin);
        txtLogin = new JTextField();
        txtLogin.setBounds(280, 180, 200, 30);
        txtLogin.setVisible(false);
        panelForm.add(txtLogin);

        JSeparator sep = new JSeparator();
        sep.setBounds(30, 230, 500, 10);
        panelForm.add(sep);

        JLabel lblJazigo = new JLabel("Compra de Jazigo:");
        lblJazigo.setBounds(30, 250, 150, 20);
        panelForm.add(lblJazigo);

        cmbJazigo = new JComboBox<>();
        cmbJazigo.setBounds(30, 270, 200, 30);
        cmbJazigo.setEnabled(false);
        panelForm.add(cmbJazigo);

        JLabel lblNivel = new JLabel("Cargo / Nível:");
        lblNivel.setBounds(280, 250, 150, 20);
        panelForm.add(lblNivel);
        cmbNivel = new JComboBox<>(new String[]{"- Escolha -", "ADMIN", "VENDEDOR", "COVEIRO"});
        cmbNivel.setBounds(280, 270, 200, 30);
        cmbNivel.setEnabled(false);
        panelForm.add(cmbNivel);

        lblSenha = new JLabel("Definir Senha:");
        lblSenha.setBounds(280, 310, 100, 20);
        lblSenha.setVisible(false);
        panelForm.add(lblSenha);
        txtSenha = new JPasswordField();
        txtSenha.setBounds(280, 330, 200, 30);
        txtSenha.setVisible(false);
        panelForm.add(txtSenha);

        cmbTipo.addActionListener(e -> {
            String selecionado = (String) cmbTipo.getSelectedItem();

            if ("Titular (Cliente)".equals(selecionado)) {
                cmbJazigo.setEnabled(true);
                cmbNivel.setEnabled(false);
                lblSenha.setVisible(false);
                txtSenha.setVisible(false);
                lblLogin.setVisible(false);
                txtLogin.setVisible(false);
            } else if ("Usuário (Funcionário)".equals(selecionado)) {
                cmbJazigo.setEnabled(false);
                cmbNivel.setEnabled(true);
                lblSenha.setVisible(true);
                txtSenha.setVisible(true);
                lblLogin.setVisible(true);
                txtLogin.setVisible(true);
            } else {
                cmbJazigo.setEnabled(false);
                cmbNivel.setEnabled(false);
            }
        });

        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.setBounds(30, 390, 150, 35);
        btnSalvar.setBackground(new Color(98, 0, 238));
        btnSalvar.setForeground(Color.WHITE);

        btnSalvar.addActionListener(e -> salvarCadastro());
        panelForm.add(btnSalvar);
    }

    private void salvarCadastro() {
        try {
            String tipo = (String) cmbTipo.getSelectedItem();
            String nome = txtNome.getText().trim();
            String cpf = txtCpf.getText().trim();
            String nascStr = txtNasc.getText().trim();
            String telefone = txtTelefone.getText().trim();

            if (nome.isEmpty() || tipo.equals("- Selecione -")) {
                JOptionPane.showMessageDialog(this, "Preencha nome e selecione o tipo!");
                return;
            }

            if ("Usuário (Funcionário)".equals(tipo)) {
                // Lógica de Salvar Usuário (Mantida igual)
                String login = txtLogin.getText().trim();
                String senha = new String(txtSenha.getPassword());
                String cargo = (String) cmbNivel.getSelectedItem();

                if (login.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha login e senha!");
                    return;
                }

                Usuario u = new Usuario(nome, login, senha, cargo);
                UsuarioDAO uDao = new UsuarioDAO();
                uDao.cadastrar(u);
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado!");

            } else {

                LocalDate dataNascimento;
                try {

                    if (nascStr.contains("/")) {
                        DateTimeFormatter brFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        dataNascimento = LocalDate.parse(nascStr, brFormat);
                    } else {

                        dataNascimento = LocalDate.parse(nascStr);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Data inválida! Use o formato DD/MM/AAAA (ex: 25/12/1990) ou AAAA-MM-DD.");
                    return;
                }

                Titular t = new Titular(nome, cpf, telefone, dataNascimento);
                TitularDAO tDao = new TitularDAO();

                int idTitular = tDao.cadastrar(t);

                JazigoItem itemJazigo = (JazigoItem) cmbJazigo.getSelectedItem();
                if (itemJazigo != null && itemJazigo.jazigo != null && idTitular > 0) {
                    JazigoDAO jDao = new JazigoDAO();
                    jDao.comprar(itemJazigo.jazigo.getIdJazigo(), idTitular);
                    JOptionPane.showMessageDialog(this, "Titular cadastrado e Jazigo vinculado!");
                } else {
                    JOptionPane.showMessageDialog(this, "Titular cadastrado (sem jazigo)!");
                }
            }

            txtNome.setText("");
            txtCpf.setText("");
            txtNasc.setText("");
            carregarJazigosDisponiveis();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    class JazigoItem {

        Jazigo jazigo;
        String label;

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
