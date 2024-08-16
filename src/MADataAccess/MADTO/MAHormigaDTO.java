package MADataAccess.MADTO;


public class MAHormigaDTO {
    private int maIdHormiga;
    private String maTipoHormiga;
    private String maSexoNombre;
    private String maProvinciaNombre;
    private String maGenoAlimentoNombre;
    private String maIngestaNativaNombre;
    private String maEstado;
    private String maFechaCreacion;
    private String maFechaModificacion;

    // Constructor
    public MAHormigaDTO() {}

    public MAHormigaDTO(int maIdHormiga, String maTipoHormiga, String maSexoNombre, String maProvinciaNombre, 
                        String maGenoAlimentoNombre, String maIngestaNativaNombre, String maEstado,
                        String maFechaCreacion, String maFechaModificacion) {
                            
        this.maIdHormiga = maIdHormiga;
        this.maTipoHormiga = maTipoHormiga;
        this.maSexoNombre = maSexoNombre;
        this.maProvinciaNombre = maProvinciaNombre;
        this.maGenoAlimentoNombre = maGenoAlimentoNombre;
        this.maIngestaNativaNombre = maIngestaNativaNombre;
        this.maEstado = maEstado;
        this.maFechaCreacion = maFechaCreacion;
        this.maFechaModificacion = maFechaModificacion;
    }

    // Getters and Setters
    public int getMaIdHormiga() {
        return maIdHormiga;
    }

    public void setMaIdHormiga(int maIdHormiga) {
        this.maIdHormiga = maIdHormiga;
    }

    public String getMaTipoHormiga() {
        return maTipoHormiga;
    }

    public void setMaTipoHormiga(String maTipoHormiga) {
        this.maTipoHormiga = maTipoHormiga;
    }

    public String getMaSexoNombre() {
        return maSexoNombre;
    }

    public void setMaSexoNombre(String maSexoNombre) {
        this.maSexoNombre = maSexoNombre;
    }

    public String getMaProvinciaNombre() {
        return maProvinciaNombre;
    }

    public void setMaProvinciaNombre(String maProvinciaNombre) {
        this.maProvinciaNombre = maProvinciaNombre;
    }

    public String getMaGenoAlimentoNombre() {
        return maGenoAlimentoNombre;
    }

    public void setMaGenoAlimentoNombre(String maGenoAlimentoNombre) {
        this.maGenoAlimentoNombre = maGenoAlimentoNombre;
    }

    public String getMaIngestaNativaNombre() {
        return maIngestaNativaNombre;
    }

    public void setMaIngestaNativaNombre(String maIngestaNativaNombre) {
        this.maIngestaNativaNombre = maIngestaNativaNombre;
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
        return getClass().getName()
               + "\n maIdHormiga             " + getMaIdHormiga()
               + "\n maTipoHormiga           " + getMaTipoHormiga()
               + "\n maSexoNombre            " + getMaSexoNombre()
               + "\n maProvinciaNombre       " + getMaProvinciaNombre()
               + "\n maGenoAlimentoNombre    " + getMaGenoAlimentoNombre()
               + "\n maIngestaNativaNombre   " + getMaIngestaNativaNombre()
               + "\n maEstado                " + getMaEstado()
               + "\n maFechaCreacion         " + getMaFechaCreacion()
               + "\n maFechaModificacion     " + getMaFechaModificacion();
    }
}




    


