package Q4;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Iniciando Pipeline de Validação ---");

        // 1. O Documento a ser validado
        DocumentoFiscal doc = new DocumentoFiscal("<NFe>...</NFe>", "12345");
        
        // 2. O Objeto de Contexto que será compartilhado
        ValidationContext context = new ValidationContext(doc);

        // 3. DECISÃO (CoR): Montagem da Cadeia (Chain)
        // Usamos o "return next" fluente do setNext()
        Validator chainHead = new XmlSchemaValidator();
        chainHead.setNext(new CertificadoValidator())
                 .setNext(new RegrasFiscaisValidator())
                 .setNext(new DatabaseValidator())
                 .setNext(new SefazValidator());

        // 4. Executa a cadeia
        chainHead.validate(context);

        System.out.println("\n--- Pipeline Finalizado ---");

        // 5. Verificação final e Orquestração de Rollback
        if (context.isFailed()) {
            System.out.println("RESULTADO: FALHA. " + context.getFailureCount() + " erro(s) encontrado(s):");
            context.getFailures().forEach(System.out::println);

            // 6. (Requisito 4) Lógica de Rollback LIFO
            System.out.println("\n--- Iniciando Rollback LIFO ---");
            Stack<Validator> executed = context.getExecutedStack();
            while (!executed.isEmpty()) {
                Validator v = executed.pop(); // Pega o último que executou
                v.rollback(context);
            }
        } else {
            System.out.println("RESULTADO: SUCESSO. Documento validado.");
        }
    }
}