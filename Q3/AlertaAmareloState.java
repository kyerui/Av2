package Q3;

public class AlertaAmareloState implements UsinaState {

    private final long entryTime;
    private static final long TIME_LIMIT_MS = 30000;

    public AlertaAmareloState() {
        this.entryTime = System.currentTimeMillis();
        System.out.println("ATENÇÃO: Entrando em ALERTA AMARELO.");
    }

    @Override
    public void monitorar(UsinaNuclear usina) {
        double temp = usina.getTemperatura();

        // Regra: ALERTA_AMARELO → ALERTA_VERMELHO: se temp > 400°C por > 30s
        if (temp > 400) {
            long duration = System.currentTimeMillis() - entryTime;
            if (duration > TIME_LIMIT_MS) {
                usina.setState(new AlertaVermelhoState());
            } else {
                System.out.printf("ALERTA AMARELO: Temp > 400 por %d segundos...\n", duration / 1000);
            }
        }
        else if (temp <= 300) {
            usina.setState(new OperacaoNormalState());
        }
    }

    @Override
    public void aumentarPotencia(UsinaNuclear usina) {
        System.out.println("AVISO: Potência bloqueada. ALERTA AMARELO.");
    }
    
    @Override
    public void desligar(UsinaNuclear usina) {
        System.out.println("Iniciando procedimento de desligamento de emergência (Amarelo)...");
        usina.setState(new DesligadaState());
    }

    @Override
    public String getStatus() {
        return "ALERTA_AMARELO";
    }
}