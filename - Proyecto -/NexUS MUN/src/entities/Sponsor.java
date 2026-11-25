package models;

public class Sponsor extends Participante {

    public static final String PREFIJO = "SP";
    private static int ultimoNumeroAsignado = 0;
    private Institucion institucion;

    public Sponsor() {}

    public Sponsor(String nombre, String correoElectronico, Institucion institucion) {
        super(generarId(), nombre, correoElectronico);
        this.institucion = institucion;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    @Override
    public String getRol() {
        return "Sponsor";
    }

    private static String generarId() {
        ultimoNumeroAsignado++;
        return armarId(PREFIJO, ultimoNumeroAsignado);
    }

    public static void sincronizarContador(int mayorNumero) {
        if (mayorNumero > ultimoNumeroAsignado) {
            ultimoNumeroAsignado = mayorNumero;
        }
    }
}