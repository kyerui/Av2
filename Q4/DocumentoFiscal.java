package Q4;

// Simples POJO para representar a NF-e
public class DocumentoFiscal {
    private String xmlContent;
    private String numeroDocumento;

    public DocumentoFiscal(String xmlContent, String numeroDocumento) {
        this.xmlContent = xmlContent;
        this.numeroDocumento = numeroDocumento;
    }
    public String getXmlContent() { return xmlContent; }
    public String getNumeroDocumento() { return numeroDocumento; }
}