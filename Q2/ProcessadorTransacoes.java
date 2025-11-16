package Q2;

/**
 * DECISÃO DE DESIGN (Target Interface):
 * Esta é a interface Target. É o contrato que o nosso cliente(ex: o Main) conhece e depende.
 *
 * Ela usa tipos de dados claros e uma assinatura de método limpa, escondendo a complexidade do HashMap do sistema legado.
 */
public interface ProcessadorTransacoes {
    ModernResponse autorizar(String cartao, double valor, String moeda);
}