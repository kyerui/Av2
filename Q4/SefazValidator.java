package Q4;

public class SefazValidator extends AbstractValidator {
    public SefazValidator() { super("Consulta Online SEFAZ", 3000); } // Timeout longo

    /**
     * Este validador só executa se os anteriores passarem.
     */
    @Override
    protected boolean shouldSkip(ValidationContext context) {
        return context.isFailed();
    }

    @Override
    protected void runValidation(ValidationContext context) throws InterruptedException {
        // Simula uma chamada de rede demorada
        Thread.sleep(1000);
        System.out.println("SEFAZ: Documento autorizado online.");
        
        // linha abaixo simula uma falha da SEFAZ
        // context.addFailure("SEFAZ: Recusado. Lote em processamento (999).");
        
        // linha abaixo simula um TIMEOUT
        // Thread.sleep(3500);
    }
}