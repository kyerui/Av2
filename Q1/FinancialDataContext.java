/**
 * DECISÃO DE DESIGN (POJO):
 * Este é um objeto simples (POJO) que encapsula o "contexto complexo"
 * mencionado no problema.
 *
 * Ao invés de passar múltiplos parâmetros (portfolioValue, volatility, etc.)
 * para os algoritmos, passamos este objeto único. Isso simplifica a
 * assinatura dos métodos e facilita a adição de novos parâmetros no futuro,
 * sem quebrar o contrato da interface RiskAlgorithm (Princípio Aberto/Fechado).
 */
public class FinancialDataContext {
    private double portfolioValue;
    private double volatility;
    private int timeHorizonDays;

    public FinancialDataContext(double portfolioValue, double volatility, int timeHorizonDays) {
        this.portfolioValue = portfolioValue;
        this.volatility = volatility;
        this.timeHorizonDays = timeHorizonDays;
    }

    public double getPortfolioValue() { return portfolioValue; }
    public double getVolatility() { return volatility; }
    public int getTimeHorizonDays() { return timeHorizonDays; }
}