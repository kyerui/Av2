package Q4;

package validation;
import ValidationContext;

/**
 * DECISÃO DE DESIGN (Chain of Responsibility - Handler):
 * Esta é a interface Handler.
 * - setNext(): Define o próximo elo da cadeia.
 * - validate(): Executa a lógica de validação.
 * - rollback(): (Requisito 4) Define um contrato para "desfazer" operações, que será chamado em LIFO pela Main.
 */
public interface Validator {
    Validator setNext(Validator next);
    void validate(ValidationContext context);
    void rollback(ValidationContext context);
}