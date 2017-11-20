package santiago.appreuniones.dto;

public class Estados_reunion_usuario {
    private int usuario;
    private Reunion reunion;
    private int estado_reunion;
    private int vigencia;

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Reunion getReunion() {
        return reunion;
    }

    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }

    public int getEstado_reunion() {
        return estado_reunion;
    }

    public void setEstado_reunion(int estado_reunion) {
        this.estado_reunion = estado_reunion;
    }

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    @Override
    public String toString() {
        return "Estados_reunion_usuario{" +
                "usuario=" + usuario +
                ", reunion=" + reunion +
                ", estado_reunion=" + estado_reunion +
                ", vigencia=" + vigencia +
                '}';
    }
}
