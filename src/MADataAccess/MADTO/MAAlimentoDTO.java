package MADataAccess.MADTO;

public class MAAlimentoDTO {
    private int maIdAlimento;
    private String maIngestaNativaNombre;
    private String maGenoAlimentoNombre;
    private String maEstado;
    private String maFechaCreacion;
    private String maFechaModificacion;

    // Constructor
    public MAAlimentoDTO() {}

    public MAAlimentoDTO(int maIdAlimento, String maIngestaNativaNombre, String maGenoAlimentoNombre, 
                         String maEstado, String maFechaCreacion, String maFechaModificacion) {
        this.maIdAlimento = maIdAlimento;
        this.maIngestaNativaNombre = maIngestaNativaNombre;
        this.maGenoAlimentoNombre = maGenoAlimentoNombre;
        this.maEstado = maEstado;
        this.maFechaCreacion = maFechaCreacion;
        this.maFechaModificacion = maFechaModificacion;
    }

    // Getters y Setters
    public int getMaIdAlimento() {
        return maIdAlimento;
    }

    public void setMaIdAlimento(int maIdAlimento) {
        this.maIdAlimento = maIdAlimento;
    }

    public String getMaIngestaNativaNombre() {
        return maIngestaNativaNombre;
    }

    public void setMaIngestaNativaNombre(String maIngestaNativaNombre) {
        this.maIngestaNativaNombre = maIngestaNativaNombre;
    }

    public String getMaGenoAlimentoNombre() {
        return maGenoAlimentoNombre;
    }

    public void setMaGenoAlimentoNombre(String maGenoAlimentoNombre) {
        this.maGenoAlimentoNombre = maGenoAlimentoNombre;
    }

    public String getMaEstado() {
        return maEstado;
    }

    public void setMaEstado(String maEstado) {
        this.maEstado = maEstado;
    }

    public String getMaFechaCreacion() {
        return maFechaCreacion;
    }

    public void setMaFechaCreacion(String maFechaCreacion) {
        this.maFechaCreacion = maFechaCreacion;
    }

    public String getMaFechaModificacion() {
        return maFechaModificacion;
    }

    public void setMaFechaModificacion(String maFechaModificacion) {
        this.maFechaModificacion = maFechaModificacion;
    }

    @Override
    public String toString() {
        return "ID: " + maIdAlimento + "\n" +
               "Ingesta Nativa: " + maIngestaNativaNombre + "\n" +
               "Geno Alimento: " + maGenoAlimentoNombre + "\n" +
               "Estado: " + maEstado + "\n" +
               "Fecha Creación: " + maFechaCreacion + "\n" +
               "Fecha Modificación: " + maFechaModificacion;
    }

    
}

