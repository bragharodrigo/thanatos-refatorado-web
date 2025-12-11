/*
Optei pela implementação manual da interface, o GUI Builder estava distorcendo 
completamente o posicionamento dos elementos. 
Como eu fiz um Wireframe bem detalhado no Figma , eu tentei reproduzir o mais próximo possível o design desenvolvido.
 */
package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import thanatosdb.Usuario;

public class TelaLogin extends javax.swing.JFrame {

    public TelaLogin() {
        initComponents();
        configurarDesign();
    }

    private void configurarDesign() {

        this.setTitle("Login");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.getContentPane().setBackground(new Color(100, 100, 100));
        this.setLayout(null);
    }

    private void initComponents() {

        JLabel lblTitulo = new JLabel("THANATOS Lite");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(100, 30, 200, 30);
        add(lblTitulo);

        JLabel lblUser = new JLabel("LOGIN:");
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(50, 90, 80, 20);
        add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(120, 90, 200, 25);
        txtUser.setText("admin");
        txtUser.setToolTipText("Usuário padrão: admin");
        add(txtUser);

        JLabel lblPass = new JLabel("SENHA:");
        lblPass.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblPass.setForeground(Color.WHITE);
        lblPass.setBounds(50, 130, 80, 20);
        add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(120, 130, 200, 25);
        txtPass.setText("1234");
        txtPass.setToolTipText("Senha padrão: 1234");
        add(txtPass);

        JButton btnEntrar = new JButton("ENTRAR");
        btnEntrar.setBounds(140, 190, 120, 35);
        btnEntrar.setBackground(new Color(98, 0, 238)); // Roxo do design
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = txtUser.getText();
                String senha = new String(txtPass.getPassword());

                Usuario adminTeste = new Usuario("Teobaldo","admin", "1234", "ADMIN");

                if (adminTeste.verificarLogin(login, senha)) {
                    JOptionPane.showMessageDialog(null, "Bem-vindo, Administrador(a)!");

                    dispose();

                    new TelaPrincipal().setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Acesso Negado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(btnEntrar);
    }
}
