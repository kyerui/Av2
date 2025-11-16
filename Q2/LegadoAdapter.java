package Q2;

import java.util.HashMap;

/**
 * DECISÃO DE DESIGN (Adapter Pattern):
 * Esta classe é o Adapter.
 * 1. (Implementa Target): Ela implementa a interface moderna `ProcessadorTransacoes`.
 * 2. (Contém Adaptee): Ela "envolve" (wraps) a classe legada `SistemaBancarioLegado`.
 *
 * JUSTIFICATIVA (SRP): A única responsabilidade desta classe é a *tradução*.
 * Ela traduz a chamada moderna para a legada (ida) e a resposta
 * legada para a moderna (volta), cumprindo o requisito de bidirecionalidade.
 */
public class LegadoAdapter implements ProcessadorTransacoes {

    // Referência ao sistema legado (Adaptee)
    private final SistemaBancarioLegado legado;

    public LegadoAdapter(SistemaBancarioLegado legado) {
        this.legado = legado;
    }

    @Override
    public ModernResponse autorizar(String cartao, double valor, String moeda) {

        // 1. Criar o mapa esperado pelo legado
        HashMap<String, Object> parametrosLegados = new HashMap<>();
        parametrosLegados.put("CARTAO_NUM", cartao);
        parametrosLegados.put("VALOR_TOTAL", valor);

        // 2. (Requisito) Traduzir a moeda para o código do legado
        parametrosLegados.put("COD_MOEDA", converterMoedaParaCodigo(moeda));

        // 3. (Requisito) Adicionar campos obrigatórios do legado que não existem na interface moderna.
        parametrosLegados.put("TIPO_TRANSACAO", "DEBITO_ONLINE"); // Valor default

        // 4. Chamar o método legado
        LegacyResponse respostaLegada = legado.processarTransacao(parametrosLegados);

        return converterRespostaParaModerno(respostaLegada);
    }

    /**
     * Helper de tradução (Requisito: Mapeamento de Moeda)
     */
    private int converterMoedaParaCodigo(String moeda) {
        switch (moeda.toUpperCase()) {
            case "USD": return 1;
            case "EUR": return 2;
            case "BRL": return 3;
            default: return 99;
        }
    }

    /**
     * Helper de tradução (Requisito: Bidirecional)
     */
    private ModernResponse converterRespostaParaModerno(LegacyResponse respostaLegada) {
        boolean sucesso = (respostaLegada.getStatusCode() == 200);
        
        String codigo = sucesso ? respostaLegada.getAuthorizationId() : null;

        return new ModernResponse(sucesso, codigo);
    }
}