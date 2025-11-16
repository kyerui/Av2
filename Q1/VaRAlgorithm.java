

/**
 * DECISÃO DE DESIGN (Strategy Pattern - Concrete Strategy 1):
 * Esta é uma implementação concreta da Strategy.
 *
 * JUSTIFICATIVA (SRP): A única responsabilidade desta classe é
 * calcular o "Value at Risk". Se a fórmula do VaR mudar, este é o
 * único lugar que precisará de alteração.
 */
public class VaRAlgorithm implements RiskAlgorithm {
    @Override
    public double calculate(FinancialDataContext context) {
        System.out.println("Calculando Value at Risk (VaR)...");
        double result = context.getPortfolioValue() * context.getVolatility() * 1.645; // Simulação para 95% de confiança
        System.out.printf("VaR (95%%, %d dias): $%.2f%n", context.getTimeHorizonDays(), result);
        return result;
    }
}