package frames;

import dao.FalecidoDAO;
import dao.JazigoDAO;
import thanatosdb.Jazigo;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TelaSepultamento extends javax.swing.JFrame {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtNasc;
    private JTextField txtDataSep;
    private JTextField txtTitular;
    private JComboBox<Object> cmbJazigo;

    public TelaSepultamento() {
        initComponents();
        configurarDesign();
        carregarJazigos();
    }

    private void configurarDesign() {
        this.setTitle("Sepultamento");
        this.setSize(850, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
    }

    private void carregarJazigos() {
        JazigoDAO dao = new JazigoDAO();
        List<Jazigo> lista = dao.listarJazigosLivres();

        cmbJazigo.removeAllItems();
        cmbJazigo.addItem("- Selecione -");

        for (Jazigo j : lista) {
            cmbJazigo.addItem(j);
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

        JLabel lblTitulo = new JLabel("REGISTRO DE SEPULTAMENTO");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(60, 60, 60));
        lblTitulo.setBounds(30, 20, 400, 30);
        content.add(lblTitulo);

        JPanel panelForm = new JPanel();
        panelForm.setBounds(30, 70, 570, 420);
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createTitledBorder("Dados do Falecido"));
        panelForm.setLayout(null);
        content.add(panelForm);

        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setBounds(30, 40, 150, 20);
        panelForm.add(lblNome);
        txtNome = new JTextField();
        txtNome.setBounds(30, 65, 500, 30);
        panelForm.add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(30, 110, 100, 20);
        panelForm.add(lblCpf);
        txtCpf = new JTextField();
        txtCpf.setBounds(30, 135, 200, 30);
        panelForm.add(txtCpf);

        JLabel lblNasc = new JLabel("Data Nascimento (dd/mm/aaaa):");
        lblNasc.setBounds(280, 110, 200, 20);
        panelForm.add(lblNasc);
        txtNasc = new JTextField();
        txtNasc.setBounds(280, 135, 200, 30);
        panelForm.add(txtNasc);

        JLabel lblDataSep = new JLabel("Data Sepultamento (dd/mm/aaaa):");
        lblDataSep.setBounds(30, 180, 250, 20);
        panelForm.add(lblDataSep);
        txtDataSep = new JTextField();
        txtDataSep.setBounds(30, 205, 200, 30);
        panelForm.add(txtDataSep);

        JSeparator sep = new JSeparator();
        sep.setBounds(30, 260, 500, 10);
        panelForm.add(sep);

        JLabel lblJazigo = new JLabel("Seleção de Jazigo:");
        lblJazigo.setBounds(30, 280, 150, 20);
        panelForm.add(lblJazigo);

        cmbJazigo = new JComboBox<>();
        cmbJazigo.setBounds(150, 275, 200, 30);

        cmbJazigo.addActionListener(e -> {
            Object selecionado = cmbJazigo.getSelectedItem();
            if (selecionado instanceof Jazigo) {
                Jazigo j = (Jazigo) selecionado;
                txtTitular.setText(j.getNomeTitular());
            } else {
                txtTitular.setText("");
            }
        });
        panelForm.add(cmbJazigo);

        JLabel lblTitular = new JLabel("Titular Responsável:");
        lblTitular.setBounds(30, 320, 200, 20);
        panelForm.add(lblTitular);

        txtTitular = new JTextField();
        txtTitular.setBounds(180, 315, 350, 30);
        txtTitular.setEditable(false);
        panelForm.add(txtTitular);

        JButton btnSalvar = new JButton("REGISTRAR");
        btnSalvar.setBounds(150, 370, 150, 35);
        btnSalvar.setBackground(new Color(98, 0, 238));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.addActionListener(e -> realizarSepultamento());
        panelForm.add(btnSalvar);

        JButton btnCancelar = new JButton("Limpar");
        btnCancelar.setBounds(320, 370, 100, 35);
        btnCancelar.setBackground(Color.LIGHT_GRAY);
        btnCancelar.addActionListener(e -> limparCampos());
        panelForm.add(btnCancelar);
    }

    private void realizarSepultamento() {
        if (cmbJazigo.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Selecione um jazigo válido.");
            return;
        }

        Jazigo jazigoSelecionado = (Jazigo) cmbJazigo.getSelectedItem();

        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String dataNascTexto = txtNasc.getText();
        String dataSepTexto = txtDataSep.getText();

        if (nome.isEmpty() || cpf.isEmpty() || dataSepTexto.isEmpty() || dataNascTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.");
            return;
        }

        String dataNascSQL = "";
        String dataSepSQL = "";

        try {
            SimpleDateFormat sdfEntrada = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfSaida = new SimpleDateFormat("yyyy-MM-dd");

            dataNascSQL = sdfSaida.format(sdfEntrada.parse(dataNascTexto));
            dataSepSQL = sdfSaida.format(sdfEntrada.parse(dataSepTexto));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida! Use dd/mm/aaaa");
            return;
        }

        FalecidoDAO dao = new FalecidoDAO();
        boolean sucesso = dao.registrarSepultamento(nome, cpf, dataNascSQL, dataSepSQL, jazigoSelecionado.getIdJazigo());

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Sepultamento registrado com sucesso!");
            limparCampos();
            carregarJazigos();
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtNasc.setText("");
        txtDataSep.setText("");
        txtTitular.setText("");
        if (cmbJazigo.getItemCount() > 0) {
            cmbJazigo.setSelectedIndex(0);
        }
    }

}
