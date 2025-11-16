package Q3;

/**
 * DECISÃO DE DESIGN (Decorator Pattern - Concrete Decorator):
 * Esta classe "embrulha" (wraps) um UsinaState existente.
 *
 * JUSTIFICATIVA: Ela implementa a mesma interface `UsinaState`,
 * permitindo que a `UsinaNuclear` a trate como qualquer outro estado.
 * No entanto, ela *intercepta* as chamadas de método para
 * "sobreescrever" o comportamento normal, cumprindo o requisito
 * de modo de manutenção.
 */
public class ManutencaoStateDecorator implements UsinaState {

    // Referência ao estado que foi "embrulhado"
    private final UsinaState wrappedState;

    public ManutencaoStateDecorator(UsinaState stateToWrap) {
        this.wrappedState = stateToWrap;
    }

    /**
     * Comportamento sobrescrito: A monitoração de regras é desativada.
     */
    @Override
    public void monitorar(UsinaNuclear usina) {
        System.out.println("MANUTENÇÃO: Regras de monitoramento suspensas.");
    }

    /**
     * Comportamento sobrescrito: Ações perigosas são bloqueadas.
     */
    @Override
    public void aumentarPotencia(UsinaNuclear usina) {
        System.out.println("MANUTENÇÃO: Ação 'aumentarPotencia' bloqueada.");
    }

    /**
     * Comportamento sobrescrito: Ações perigosas são bloqueadas.
     */
    @Override
    public void desligar(UsinaNuclear usina) {
        System.out.println("MANUTENÇÃO: Ação 'desligar' bloqueada. Use os controles manuais.");
    }

    @Override
    public String getStatus() {
        // Informa que está em manutenção E qual era o estado anterior.
        return "MANUTENÇÃO (Estado anterior: " + wrappedState.getStatus() + ")";
    }

    /**
     * Método auxiliar para permitir que o Contexto (UsinaNuclear)
     * "desembrulhe" o estado original.
     */
    public UsinaState getWrappedState() {
        return this.wrappedState;
    }
}