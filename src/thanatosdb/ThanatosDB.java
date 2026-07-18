package thanatosdb;

import service.JazigoService;
import service.SetorService;
import service.UsuarioService;

public class ThanatosDB {

    public static void main(String[] args) {
        
        System.out.println("=========================================================");
        System.out.println(" INICIANDO TESTES DE REGRA DE NEGÓCIO (CONSOLE) ");
        System.out.println("=========================================================\n");

        try {
            // 1. Testando o Serviço de Usuários
            System.out.println("[TESTE 1] - Tentando cadastrar usuário via Service...");
            UsuarioService usuarioService = new UsuarioService();
            // Passando dados diretos (como se tivessem vindo da tela)
            usuarioService.cadastrarUsuario("Teste Console", "teste_console", "123", "VENDEDOR");
            System.out.println("✅ Sucesso: Usuário cadastrado no banco sem usar interface gráfica!\n");

            // 2. Testando o Serviço de Setores
            System.out.println("[TESTE 2] - Tentando cadastrar setor via Service...");
            SetorService setorService = new SetorService();
            // Vamos usar a letra 'Z' para não dar conflito com A,B,C,D,E do seu script SQL original
            setorService.cadastrarSetor("Z", "Setor de Testes do Console");
            System.out.println("✅ Sucesso: Setor 'Z' cadastrado corretamente!\n");

            // 3. Testando o Serviço de Jazigos
            System.out.println("[TESTE 3] - Tentando cadastrar um jazigo via Service...");
            JazigoService jazigoService = new JazigoService();
            // Vamos atrelar o jazigo ao Setor 1 (Setor A que já existe no seu BD)
            jazigoService.cadastrarJazigo("Z-99", "Túmulo", "Disponível", 1);
            System.out.println("✅ Sucesso: Jazigo Z-99 cadastrado no Setor 1!\n");

            System.out.println(">>>>> TODOS OS TESTES FORAM EXECUTADOS COM SUCESSO! A arquitetura está perfeitamente desacoplada. <<<<<");

        } catch (Exception e) {
            System.err.println("❌ ERRO DURANTE O TESTE DE NEGÓCIO: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=========================================================");
        System.out.println(" INICIANDO INTERFACE GRÁFICA (JAVA SWING) ");
        System.out.println("=========================================================");
        
        // Mantém a chamada original que abre a tela de inicial (Login)
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frames.TelaLogin().setVisible(true);
            }
        });
    }
}