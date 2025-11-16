package Q2;

// Classe de resposta do sistema legado.
public class LegacyResponse {
    private final int statusCode;
    private final String authorizationId;

    public LegacyResponse(int statusCode, String authorizationId) {
        this.statusCode = statusCode;
        this.authorizationId = authorizationId;
    }

    public int getStatusCode() { return statusCode; }
    public String getAuthorizationId() { return authorizationId; }
}