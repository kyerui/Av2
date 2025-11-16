package Q3;

public class Main {
    
    // Helper para simular a passagem do tempo
    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UsinaNuclear usina = new UsinaNuclear();
        System.out.println("Status atual: " + usina.getStatus());

        // 1. Ligando
        usina.aumentarPotencia();
        System.out.println("Status atual: " + usina.getStatus());

        // 2. Simulando aquecimento (NORMAL -> AMARELO)
        System.out.println("\n--- Simulando aquecimento (310°C) ---");
        usina.setTemperatura(310);
        usina.monitorar(); // Deve transitar para AMARELO

        // 3. Simulando aquecimento rápido (AMARELO -> VERMELHO)
        System.out.println("\n--- Simulando aquecimento rápido (410°C) por 35s ---");
        usina.setTemperatura(410);
        System.out.println("Esperando 10s..."); sleep(10); usina.monitorar();
        System.out.println("Esperando 10s..."); sleep(10); usina.monitorar();
        System.out.println("Esperando 15s (total 35s)..."); sleep(15);
        usina.monitorar(); // Deve transitar para VERMELHO

        // 4. Modo Manutenção (Testando o Decorator)
        System.out.println("\n--- Entrando em MANUTENÇÃO sobre o ALERTA VERMELHO ---");
        usina.entrarManutencao();
        System.out.println("Status atual: " + usina.getStatus());
        // Regras de monitoramento devem ser suspensas
        usina.monitorar();
        usina.setSistemaResfriamentoOk(false); // Isso normalmente causaria EMERGENCIA
        usina.monitorar(); // ...mas não causa, pois estamos em manutenção
        System.out.println("Status (ainda em manutenção): " + usina.getStatus());
        
        System.out.println("\n--- Saindo da MANUTENÇÃO ---");
        usina.sairManutencao();
        System.out.println("Status atual: " + usina.getStatus()); // De volta a ALERTA_VERMELHO

        // 5. Simulando falha (VERMELHO -> EMERGENCIA)
        System.out.println("\n--- O monitoramento agora (pós-manutenção) detecta a falha ---");
        // A falha de resfriamento (setada acima) é detectada
        usina.monitorar(); // Deve transitar para EMERGENCIA
        
        // 6. Teste de bloqueio
        usina.aumentarPotencia(); // Deve ser bloqueado
        usina.entrarManutencao(); // Deve ser bloqueado
    }
}