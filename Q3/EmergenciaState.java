package Q3;

public class EmergenciaState implements UsinaState {

    public EmergenciaState() {
        System.out.println("!!! MELTDOWN IMINENTE !!! ACIONANDO EMERGÊNCIA TOTAL !!!");
    }

    @Override
    public void monitorar(UsinaNuclear usina) {
        // Estado final. Ações de recuperação são automáticas ou externas.
        // Nenhuma transição de saída definida aqui.
        System.out.println("EMERGENCIA: Contenção de núcleo acionada.");
    }

    @Override
    public void aumentarPotencia(UsinaNuclear usina) {
        System.out.println("BLOQUEADO. ESTADO DE EMERGÊNCIA.");
    }

    @Override
    public void desligar(UsinaNuclear usina) {
        System.out.println("BLOQUEADO. ESTADO DE EMERGÊNCIA. Reator em SCRAM automático.");
    }

    @Override
    public String getStatus() {
        return "EMERGENCIA";
    }
}