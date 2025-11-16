package Q2;

public class Main {
    public static void main(String[] args) {
        // 1. Instancia o sistema legado (real)
        SistemaBancarioLegado sistemaLegado = new SistemaBancarioLegado();

        // 2. Instancia o Adaptador, injetando o sistema legado
        // DECISÃO (Polimorfismo): O cliente depende da interface `ProcessadorTransacoes`, não da implementação concreta do Adapter.
        ProcessadorTransacoes meuProcessador = new LegadoAdapter(sistemaLegado);

        // 3. O cliente usa a interface moderna e limpa.
        // Ele não tem ideia da complexidade do HashMap ou dos códigos de moeda.
        System.out.println("Cliente: Autorizando R$ 150,99 em BRL...");
        ModernResponse respBRL = meuProcessador.autorizar("1234-5678-9012-3456", 150.99, "BRL");
        System.out.println("Cliente: Resposta recebida -> " + respBRL);

        System.out.println("\n");

        System.out.println("Cliente: Autorizando $ 50,00 em USD...");
        ModernResponse respUSD = meuProcessador.autorizar("9876-5432-1098-7654", 50.00, "USD");
        System.out.println("Cliente: Resposta recebida -> " + respUSD);
    }
}