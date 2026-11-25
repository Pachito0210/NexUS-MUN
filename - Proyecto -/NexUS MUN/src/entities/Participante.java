// PAQUETE MODELS //
package entities;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Participante
// ══════════════════════════════════════════════════════════════
//  Persona inscrita en el evento. Define lo común (ID, nombre y correo) para todos los roles.
//  * ABSTRACCIÓN *
//    - Participante no se instancia directamente, solo define lo común de cada rol.
//  * HERENCIA *
//    - Delegado, Sponsor, Presidente, MiembroOrganizacion y CentroCrisis heredan sus datos.
//  * POLIMORFISMO POR SOBRECARGA *
//    - Cada subclase sobrescribe getRol() y responde con su propio rol.
// ══════════════════════════════════════════════════════════════
public abstract class Participante {

    // ─────────────────── ATRIBUTOS ───────────────────
    private String idParticipante;
    private String nombre;
    private String correoElectronico;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Participante() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public String getIdParticipante() {
        return idParticipante;
    }
    public void setIdParticipante(String idParticipante) {
        this.idParticipante = idParticipante;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    // ══════════════════════════════════════════════════════════════
    //  ROL
    // ══════════════════════════════════════════════════════════════
    // getRol()
    //  - COMO FUNCIONA: Declara un metodo abstracto que cada subclase debe implementar para devolver su rol.
    //  - PARA QUÉ SIRVE: Permite identificar el tipo de participante sin tener que preguntar directamente qué clase es.
    //  * POLIMORFISMO *
    //    - Cada subclase proporciona su propia implementación de un mismo metodo.
    public abstract String getRol();

    // ══════════════════════════════════════════════════════════════
    //  GESTIÓN DE CORREO
    // ══════════════════════════════════════════════════════════════
    // setCorreoElectronico()
    //  - COMO FUNCIONA: Asigna el correo al participante únicamente si pasa la validación.
    //  - PARA QUÉ SIRVE: Evita almacenar correos con un formato inválido.
    //  * ENCAPSULAMIENTO *
    //    - Controla cómo se modifica el atributo privado correoElectronico.
    public void setCorreoElectronico(String correoElectronico) {
        if (esCorreoValido(correoElectronico)) {
            this.correoElectronico = correoElectronico;
        }
    }
    // esCorreoValido()
    //  - COMO FUNCIONA: Comprueba que el correo tenga texto antes y después de @, un dominio y una extensión.
    //  - PARA QUÉ SIRVE: Verifica de manera básica que el correo tenga un formato válido antes de asignarlo.
    //  * ABSTRACCIÓN *
    //    - Centraliza la validación del correo en un metodo reutilizable.
    public static boolean esCorreoValido(String correo) {
        if (correo == null || !correo.contains("@")) {
            return false;
        }
        String[] partes = correo.split("@");
        if (partes.length != 2) {
            return false;
        }
        if (partes[0].isEmpty() || partes[1].isEmpty()) {
            return false;
        }
        if (!partes[1].contains(".")) {
            return false;
        }
        return true;
    }

}