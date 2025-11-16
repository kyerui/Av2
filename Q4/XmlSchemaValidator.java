package Q4;

public class XmlSchemaValidator extends AbstractValidator {
    public XmlSchemaValidator() { super("XML Schema", 500); }

    @Override
    protected void runValidation(ValidationContext context) {
        // Lógica de validação do XML...
        if (!context.getDocumento().getXmlContent().contains("<NFe>")) {
            context.addFailure("XML Schema: Tag <NFe> não encontrada.");
        }
    }
}