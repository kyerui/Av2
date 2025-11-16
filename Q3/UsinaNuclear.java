package Q3;


/**
 * DECISÃO DE DESIGN (State Pattern - Context):
 * Esta é a classe Contexto. Ela não contém nenhuma lógica de
 * negócio sobre estados.
 *
 * RESPONSABILIDADES (SRP):
 * 1. Manter os dados brutos (sensores) da usina.
 * 2. Manter a referência ao objeto de estado atual (`currentState`).
 * 3. Delegar todas as ações (como monitorar) ao estado atual.
 * 4. Fornecer um método (`setState`) para que os estados possam
 * executar transições.
 */
public class UsinaNuclear {

    private UsinaState currentState;


    private double temperatura;
    private double pressao;
    private boolean sistemaResfriamentoOk;

    public UsinaNuclear() {
        this.currentState = new DesligadaState();
        this.sistemaResfriamentoOk = true;
    }

    public void monitorar() {
        this.currentState.monitorar(this);
    }
    
    public void aumentarPotencia() {
        this.currentState.aumentarPotencia(this);
    }

    public void desligarReator() {
        this.currentState.desligar(this);
    }
    
    public String getStatus() {
        return this.currentState.getStatus();
    }
    
    /**
     * DECISÃO DE DESIGN (Decorator Pattern):
     * Para entrar em manutenção, "decoramos" o estado atual com o ManutencaoStateDecorator. O Decorator interceptará todas as chamadas, sobreescrevendo o comportamento.
     */
    public void entrarManutencao() {
        if (currentState instanceof ManutencaoStateDecorator) {
            System.out.println("Já está em manutenção.");
            return;
        }
        // Regra de negócio: Não pode entrar em manutenção em emergência
        if (currentState.getClass().getSimpleName().equals("EmergenciaState")) {
            System.out.println("ERRO: Impossível entrar em manutenção durante EMERGÊNCIA.");
            return;
        }
        
        System.out.println("A Usina está entrando em modo de MANUTENÇÃO.");
        this.currentState = new ManutencaoStateDecorator(this.currentState);
    }

    /**
     * DECISÃO DE DESIGN (Decorator Pattern):
     * Para sair da manutenção, "desembrulhamos" o estado, recuperando o estado original que estava dentro do Decorator.
     */
    public void sairManutencao() {
        if (currentState instanceof ManutencaoStateDecorator) {
            System.out.println("A Usina está saindo do modo de MANUTENÇÃO.");
            this.currentState = ((ManutencaoStateDecorator) currentState).getWrappedState();
        } else {
            System.out.println("A Usina não está em manutenção.");
        }
    }

    public void setState(UsinaState newState) {
        System.out.println("[TRANSIÇÃO DE ESTADO]: " + 
                           currentState.getStatus() + " -> " + newState.getStatus());
        this.currentState = newState;
    }

    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }
    public double getPressao() { return pressao; }
    public void setPressao(double pressao) { this.pressao = pressao; }
    public boolean isSistemaResfriamentoOk() { return sistemaResfriamentoOk; }
    public void setSistemaResfriamentoOk(boolean ok) { this.sistemaResfriamentoOk = ok; }
}