

/**
 * DECISÃO DE DESIGN (Strategy Pattern - Interface Strategy):
 * Esta é a interface Strategy. Ela define o contrato comum que todas as
 * famílias de algoritmos de risco devem seguir.
 *
 * O uso de uma interface permite que o cliente (RiskAnalysisProcessor)
 * trate todas as implementações de forma polimórfica, alcançando
 * baixo acoplamento. O cliente não precisa saber qual algoritmo
 * específico está usando.
 */
public interface RiskAlgorithm {
    double calculate(FinancialDataContext context);
}