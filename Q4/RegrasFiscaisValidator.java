package Q4;

public class RegrasFiscaisValidator extends AbstractValidator {
    public RegrasFiscaisValidator() { super("Regras Fiscais (Impostos)", 800); }

    /**
     * Este validador só executa se os anteriores passarem.
     * Sobrescrevemos o "hook" shouldSkip.
     */
    @Override
    protected boolean shouldSkip(ValidationContext context) {
        return context.isFailed();
    }

    @Override
    protected void runValidation(ValidationContext context) {
        System.out.println("Cálculo de impostos OK.");
    }
}