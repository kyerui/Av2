package Q3;



public class OperacaoNormalState implements UsinaState {
    @Override
    public void monitorar(UsinaNuclear usina) {
        // Regra: OPERACAO_NORMAL → ALERTA_AMARELO: se temperatura > 300°C
        if (usina.getTemperatura() > 300) {
            usina.setState(new AlertaAmareloState());
        }
    }

    @Override
    public void aumentarPotencia(UsinaNuclear usina) {
        System.out.println("Aumentando potência... (Temp: " + usina.getTemperatura() + ")");
    }

    @Override
    public void desligar(UsinaNuclear usina) {
        System.out.println("Iniciando procedimento de desligamento...");
        usina.setState(new DesligadaState());
    }

    @Override
    public String getStatus() {
        return "OPERACAO_NORMAL";
    }
}