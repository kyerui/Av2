package Q4;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * DECISÃO DE DESIGN (Context Object):
 * Este objeto é passado pela cadeia (CoR) e acumula o estado.
 * Ele é essencial para habilitar os requisitos complexos que
 * transpassam todos os validadores (circuit breaker, rollback).
 */
public class ValidationContext {
    private final DocumentoFiscal documento;
    private final List<String> failures = new ArrayList<>();
    
    // Rastreia os validadores que executaram para permitir o rollback
    private final Stack<Validator> executedStack = new Stack<>();

    public ValidationContext(DocumentoFiscal documento) {
        this.documento = documento;
    }

    public DocumentoFiscal getDocumento() { return documento; }

    public void addFailure(String message) {
        System.err.println("FALHA: " + message);
        this.failures.add(message);
    }

    public int getFailureCount() {
        return failures.size();
    }

    public boolean isFailed() {
        return !failures.isEmpty();
    }

    public void pushExecutedValidator(Validator validator) {
        this.executedStack.push(validator);
    }

    public Stack<Validator> getExecutedStack() {
        return executedStack;
    }
    
    public List<String> getFailures() {
        return failures;
    }
}