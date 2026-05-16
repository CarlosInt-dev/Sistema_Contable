package module_3.Class;

public class Empresa {
    private String name, idCorrelative, type, socialReason, nrc, nit, giro;

    public Empresa() {
    }
    public Empresa(
            String name,
            String idCorrelative,
            String type,
            String socialReason,
            String nrc,
            String nit,
            String giro){
        this.name=name;
        this.idCorrelative=idCorrelative;
        this.type=type;
        this.socialReason=socialReason;
        this.nrc=nrc;
        this.nit=nit;
        this.giro=giro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCorrelative() {
        return idCorrelative;
    }

    public void setIdCorrelative(String idCorrelative) {
        this.idCorrelative = idCorrelative;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }
}
