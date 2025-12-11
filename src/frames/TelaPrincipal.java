package frames;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal() {
        initComponents();
        configurarDesign();
    }

    private void configurarDesign() {
        this.setTitle("Tela Principal");
        this.setSize(850, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
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

        criarBotaoMenu(sidebar, "CONSULTAS", 130);
        criarBotaoMenu(sidebar, "SEPULTAMENTOS", 180);
        criarBotaoMenu(sidebar, "JAZIGOS", 230);
        criarBotaoMenu(sidebar, "BLOCOS", 280);
        criarBotaoMenu(sidebar, "CADASTROS", 330);

        JButton btnSair = new JButton("SAIR");
        btnSair.setBounds(40, 500, 140, 35);
        btnSair.setBackground(new Color(200, 60, 60));
        btnSair.setForeground(Color.WHITE);
        btnSair.setFocusPainted(false);
        btnSair.addActionListener(e -> System.exit(0));
        sidebar.add(btnSair);

        JPanel content = new JPanel();
        content.setBackground(new Color(230, 230, 230));
        content.setBounds(220, 0, 630, 600);
        content.setLayout(null);
        add(content);

        JLabel lblBemVindo = new JLabel("Painel de Controle");
        lblBemVindo.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblBemVindo.setForeground(new Color(60, 60, 60));
        lblBemVindo.setBounds(40, 30, 300, 40);
        content.add(lblBemVindo);

        criarCard(content, "JAZIGOS LIVRES", "69", 40, 100);
        criarCard(content, "VENDIDOS", "111", 230, 100);
        criarCard(content, "SEPULTAMENTOS HOJE", "03", 420, 100);
    }

    private void criarBotaoMenu(JPanel panel, String texto, int y) {
        JButton btn = new JButton(texto);
        btn.setBounds(20, y, 180, 40);
        btn.setBackground(new Color(80, 80, 80));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));

        btn.addActionListener(e -> {
            if (texto.equals("JAZIGOS")) {
                new TelaJazigos().setVisible(true);
                this.dispose();
            } else if (texto.equals("CONSULTAS")) {
                new TelaConsulta().setVisible(true);
                this.dispose();
            } else if (texto.equals("BLOCOS")) {
                new TelaBlocos().setVisible(true);
                this.dispose();
            } else if (texto.equals("SEPULTAMENTOS")) {
                new TelaSepultamento().setVisible(true);
                this.dispose();
            } else if (texto.equals("CADASTROS")) {
                new TelaCadastros().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Você clicou em: " + texto);
            }
        }
        );

        panel.add(btn);
    }

    private void criarCard(JPanel panel, String titulo, String numero, int x, int y) {
        JPanel card = new JPanel();
        card.setBounds(x, y, 170, 120);
        card.setBackground(Color.WHITE);
        card.setLayout(null);

        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 10));
        lblTitulo.setForeground(Color.GRAY);
        lblTitulo.setBounds(15, 15, 140, 20);
        card.add(lblTitulo);

        JLabel lblNum = new JLabel(numero);
        lblNum.setFont(new Font("SansSerif", Font.BOLD, 40));
        lblNum.setForeground(new Color(98, 0, 238));
        lblNum.setBounds(15, 45, 140, 50);
        card.add(lblNum);

        panel.add(card);
    }
}
