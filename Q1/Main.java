
public class Main {
    
public static void main(String[] args) {
      // 1. Cria o "contexto complexo" com os dados financeiros
        FinancialDataContext data = new FinancialDataContext(1000000.0, 0.15, 10);

        // 2. Instancia as estratégias concretas
        // (Em um sistema real, elas poderiam ser Singletons ou gerenciadas
        // por um Factory, mas para este exemplo, a instanciação direta é clara)
        RiskAlgorithm varAlgo = new VaRAlgorithm();
        RiskAlgorithm esAlgo = new ExpectedShortfallAlgorithm();
        RiskAlgorithm stressAlgo = new StressTestingAlgorithm();

        // 3. Cria o processador de análise, iniciando com VaR
        System.out.println("Iniciando processador com VaR.");
        RiskAnalysisProcessor processor = new RiskAnalysisProcessor(varAlgo);
        processor.performAnalysis(data);

        System.out.println("\n");

        // 4. TROCA DE ESTRATÉGIA EM TEMPO DE EXECUÇÃO
        // O cliente (Main) decide que agora precisa de um Stress Test,
        // sem que o processador precise saber dos detalhes.
        processor.setAlgorithm(stressAlgo);
        processor.performAnalysis(data);

        System.out.println("\n");

        // 5. TROCA NOVAMENTE EM TEMPO DE EXECUÇÃO
        // Agora, o cliente muda para Expected Shortfall.
        processor.setAlgorithm(esAlgo);
        processor.performAnalysis(data);
    }
}