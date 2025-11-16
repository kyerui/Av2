package Q2;

// Classe de resposta moderna, limpa e fortemente tipada.
public class ModernResponse {
    private final boolean sucesso;
    private final String codigoAutorizacao;

    public ModernResponse(boolean sucesso, String codigoAutorizacao) {
        this.sucesso = sucesso;
        this.codigoAutorizacao = codigoAutorizacao;
    }

    public boolean isSucesso() { return sucesso; }
    public String getCodigoAutorizacao() { return codigoAutorizacao; }

    @Override
    public String toString() {
        return "ModernResponse{" +
                "sucesso=" + sucesso +
                ", codigoAutorizacao='" + codigoAutorizacao + '\'' +
                '}';
    }
}