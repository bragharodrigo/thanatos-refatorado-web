package thanatosdb;

import frames.TelaLogin;

public class ThanatosDB {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });

    }
}
