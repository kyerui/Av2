package Q3;

/**
 * DECISÃO DE DESIGN (State Pattern - State Interface):
 * Define o contrato comum para todos os estados e para o Decorator.
 * O Contexto (UsinaNuclear) só conhece esta interface.
 */
public interface UsinaState {
    /**
     * O método principal onde a lógica de transição é verificada.
     */
    void monitorar(UsinaNuclear usina);

    void aumentarPotencia(UsinaNuclear usina);
    void desligar(UsinaNuclear usina);
    String getStatus();
}