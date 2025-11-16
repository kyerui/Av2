package Q3;


public class AlertaVermelhoState implements UsinaState {
    
    public AlertaVermelhoState() {
        System.out.println("PERIGO: Entrando em ALERTA VERMELHO. Evacuação Nível 1.");
    }

    @Override
    public void monitorar(UsinaNuclear usina) {
        // Regra: ALERTA_VERMELHO → EMERGENCIA: se sistema de resfriamento falhar
        if (!usina.isSistemaResfriamentoOk()) {
            // Regra de Negócio: A única forma de chegar em EMERGENCIA é por esta validação.
            usina.setState(new EmergenciaState());
        }
        // (Poderia ter lógica para voltar para Amarelo se o resfriamento for consertado e a temperatura baixar, mas vamos manter simples)
    }

    @Override
    public void aumentarPotencia(UsinaNuclear usina) {
        System.out.println("PERIGO: Potência bloqueada. ALERTA VERMELHO.");
    }

    @Override
    public void desligar(UsinaNuclear usina) {
        System.out.println("Iniciando procedimento de SCRAM (Desligamento Rápido)...");
        usina.setState(new DesligadaState());
    }

    @Override
    public String getStatus() {
        return "ALERTA_VERMELHO";
    }
}