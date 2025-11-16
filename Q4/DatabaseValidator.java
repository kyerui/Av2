package Q4;


public class DatabaseValidator extends AbstractValidator {
    private boolean modificouBanco = false;
    
    public DatabaseValidator() { super("Duplicidade (Banco de Dados)", 1500); }

    @Override
    protected void runValidation(ValidationContext context) {
        // Lógica de consulta/inserção...
        System.out.println("Verificando duplicidade para " + context.getDocumento().getNumeroDocumento());
        // Simula uma modificação (ex: lock otimista ou registro temporário)
        modificouBanco = true;
    }

    /**
     * Implementação de Rollback.
     * Se este validador executou E a cadeia falhou, este método será chamado pelo Main para desfazer a modificação.
     */
    @Override
    public void rollback(ValidationContext context) {
        if (modificouBanco) {
            System.out.println("ROLLBACK (Database): Revertendo inserção/lock para " + 
                               context.getDocumento().getNumeroDocumento());
            modificouBanco = false;
        }
    }
}