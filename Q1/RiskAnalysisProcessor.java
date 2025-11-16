/**
 * DECISÃO DE DESIGN (Strategy Pattern - Context):
 * Esta classe é o "Contexto" do padrão Strategy. Ela mantém uma
 * referência à *interface* Strategy (RiskAlgorithm), não a uma
 * implementação concreta.
 *
 * JUSTIFICATIVA (Polimorfismo e SRP):
 * 1. (Polimorfismo): A classe opera sobre a interface RiskAlgorithm,
 * permitindo que qualquer implementação seja injetada.
 * 2. (SRP): A responsabilidade desta classe é *orquestrar* a análise,
 * não *realizar* o cálculo. Ela delega o cálculo para o objeto
 * de estratégia atual.
 *
 * A troca de algoritmo é feita pelo método setAlgorithm(),
 * permitindo a mudança em tempo de execução, como pedido no requisito.
 */
public class RiskAnalysisProcessor {
    
    private RiskAlgorithm currentAlgorithm;

    public RiskAnalysisProcessor(RiskAlgorithm initialAlgorithm) {
        this.currentAlgorithm = initialAlgorithm;
    }

    public void setAlgorithm(RiskAlgorithm algorithm) {
        System.out.println("--- Estratégia de Análise alterada para: " + algorithm.getClass().getSimpleName());
        this.currentAlgorithm = algorithm;
    }

    public void performAnalysis(FinancialDataContext context) {
        if (currentAlgorithm == null) {
            System.out.println("Nenhum algoritmo de análise definido.");
            return;
        }
        currentAlgorithm.calculate(context);
    }
}