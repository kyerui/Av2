package Q3;


public class DesligadaState implements UsinaState {
    @Override
    public void monitorar(UsinaNuclear usina) {
        // Desligada. Não faz monitoramento ativo de temperatura.
    }

    @Override
    public void aumentarPotencia(UsinaNuclear usina) {
        System.out.println("Ligando reator e iniciando OPERAÇÃO NORMAL.");
        usina.setState(new OperacaoNormalState());
    }
    
    @Override
    public void desligar(UsinaNuclear usina) {
        System.out.println("Usina já está DESLIGADA.");
    }

    @Override
    public String getStatus() {
        return "DESLIGADA";
    }
}