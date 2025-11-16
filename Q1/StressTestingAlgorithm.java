/**
 * DECISÃO DE DESIGN (Strategy Pattern - Concrete Strategy 3):
 * Terceira implementação concreta da Strategy.
 *
 * JUSTIFICATIVA (SRP): A única responsabilidade desta classe é
 * executar um "Stress Test" simulado.
 */
public class StressTestingAlgorithm implements RiskAlgorithm {
    @Override
    public double calculate(FinancialDataContext context) {
        System.out.println("Executando Stress Test (Cenário: Crise 2008)...");
        double result = context.getPortfolioValue() * 0.30;
        System.out.printf("Perda no Stress Test: $%.2f%n", result);
        return result;
    }
}