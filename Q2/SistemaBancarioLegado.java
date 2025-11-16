package Q2;

import java.util.HashMap;
import java.util.UUID;

/**
 * DECISÃO DE DESIGN (Adaptee):
 * Esta é a classe do sistema legado (Adaptee).
 * Ela é incompatível com nosso sistema moderno:
 * 1. Usa um método com nome diferente (processarTransacao).
 * 2. Recebe um HashMap genérico, o que é difícil de manter.
 * 3. Retorna um objeto de resposta legado (LegacyResponse).
 */
public class SistemaBancarioLegado {

    public LegacyResponse processarTransacao(HashMap<String, Object> parametros) {
        System.out.println("[LEGADO] Recebido para processamento: " + parametros);

        if (!parametros.containsKey("COD_MOEDA") || !parametros.containsKey("TIPO_TRANSACAO")) {
            System.out.println("[LEGADO] ERRO: Campos obrigatórios ausentes.");
            return new LegacyResponse(400, null); // 400 Bad Request
        }

        System.out.println("[LEGADO] Processando pagamento de " + parametros.get("VALOR_TOTAL") +
                           " na moeda " + parametros.get("COD_MOEDA") +
                           " para o cartão " + parametros.get("CARTAO_NUM"));

        String authId = UUID.randomUUID().toString().substring(0, 8);
        return new LegacyResponse(200, authId); // 200 OK
    }
}