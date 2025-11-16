package Q4;

/**
 * DECISÃO DE DESIGN (Template Method + CoR):
 * Esta classe abstrata centraliza a lógica de orquestração da cadeia, evitando duplicação de código.
 *
 * O método `validate` é o Template Method, que gerencia:
 * 1. (Requisito 3) Circuit Breaker
 * 2. (Requisito 2) Lógica Condicional de "pular"
 * 3. (Requisito 4) Registro de Rollback
 * 4. (Restrição) Gerenciamento de Timeout (simulado)
 * 5. A chamada para o próximo elo.
 */
public abstract class AbstractValidator implements Validator {

    protected Validator next;
    private final long timeoutMillis;
    private final String name;

    public AbstractValidator(String name, long timeoutMillis) {
        this.name = name;
        this.timeoutMillis = timeoutMillis;
    }

    @Override
    public Validator setNext(Validator next) {
        this.next = next;
        return next; // Permite "chaining" (v1.setNext(v2.setNext(v3)))
    }

    /**
     * Requisito 4: Implementação default de rollback (não faz nada).
     * Apenas validadores que modificam o estado (como Database)
     * precisam sobrescrever este método.
     */
    @Override
    public void rollback(ValidationContext context) {
        // Default: A maioria dos validadores não precisa de rollback.
    }

    /**
     * Hook para lógica condicional (Requisito 2).
     * Por padrão, um validador não pula.
     */
    protected boolean shouldSkip(ValidationContext context) {
        return false;
    }

    /**
     * Método de validação principal (Template Method)
     */
    @Override
    public void validate(ValidationContext context) {
        // 1. (Requisito 3) Lógica do Circuit Breaker
        if (context.getFailureCount() >= 3) {
            System.out.println("CIRCUIT BREAKER: > 3 falhas. Pulando " + name);
            // NÃO chama o próximo elo, interrompendo a cadeia.
            return;
        }

        // 2. (Requisito 2) Lógica Condicional de "Pular"
        if (shouldSkip(context)) {
            System.out.println("SKIP: " + name + " pulado devido a falhas anteriores.");
            // Pula esta validação, mas continua a cadeia.
            callNext(context);
            return;
        }

        System.out.println("--- Executando Validador: " + name + " ---");
        
        // 3. (Requisito 4) Registro para Rollback
        context.pushExecutedValidator(this);

        // 4. (Restrição) Simulação de Timeout
        long startTime = System.currentTimeMillis();
        try {
            // Executa a lógica de negócio real
            runValidation(context);
        } catch (InterruptedException e) {
            context.addFailure(name + ": Interrompido.");
        }
        long duration = System.currentTimeMillis() - startTime;

        if (duration > this.timeoutMillis) {
            context.addFailure(name + ": TIMEOUT (executou por " + duration + "ms)");
        }
        
        System.out.println("--- " + name + " finalizado. (" + duration + "ms) ---");

        // 5. Chama o próximo elo da cadeia
        callNext(context);
    }

    // Helper para chamar o próximo
    private void callNext(ValidationContext context) {
        if (next != null) {
            next.validate(context);
        }
    }

    /**
     * O método que as subclasses devem implementar com sua lógica de validação específica.
    */
    protected abstract void runValidation(ValidationContext context) throws InterruptedException;
}