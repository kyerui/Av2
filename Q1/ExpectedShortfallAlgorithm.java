

/**
 * DECISÃO DE DESIGN (Strategy Pattern - Concrete Strategy 2):
 * Esta é outra implementação concreta da Strategy.
 *
 * JUSTIFICATIVA (SRP): A única responsabilidade desta classe é
 * calcular o "Expected Shortfall".
 */
public class ExpectedShortfallAlgorithm implements RiskAlgorithm {
    @Override
    public double calculate(FinancialDataContext context) {
        System.out.println("Calculando Expected Shortfall (ES)...");
        double result = context.getPortfolioValue() * context.getVolatility() * 2.06; // Simulação para 95%
        System.out.printf("ES (95%%, %d dias): $%.2f%n", context.getTimeHorizonDays(), result);
        return result;
    }
}