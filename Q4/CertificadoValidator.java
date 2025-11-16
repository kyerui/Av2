package Q4;

public class CertificadoValidator extends AbstractValidator {
    public CertificadoValidator() { super("Certificado Digital", 1000); }

    @Override
    protected void runValidation(ValidationContext context) {
        // Lógica de validação do certificado (simulando uma falha)
        // context.addFailure("Certificado: Expirado.");
        System.out.println("Certificado OK.");
    }
}