// PAQUETE E IMPORTAR //
package entities;
import model.Comite;

import java.util.List;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  CentroCrisis
// ══════════════════════════════════════════════════════════════
//  Centro encargado de atender y gestionar los comités de crisis, y las crisis de los comités. Su ID empieza por "CC" (CC001, CC002...).
//  * HERENCIA *
//    - CentroCrisis ES UN Participante, hereda nombre, correo e ID y agrega sus miembros y comités.
//  * POLIMORFISMO POR SOBRECARGA *
//    - CentroCrisis sobrescribe getRol() de Participante y responde "CentroCrisis".
// ══════════════════════════════════════════════════════════════
public class CentroCrisis extends Participante {

    // ─────────────────── ATRIBUTOS ───────────────────
    private static String prefijoID = "CC";
    private static int ultimoNumeroAsignado = 0;
    private List<MiembroOrganizacion> miembros;
    private List<Comite> comites;
    // ───────────────── CONSTRUCTORES ─────────────────
    public CentroCrisis() { }

    // ══════════════════════════════════════════════════════════════
    //  ROL
    // ══════════════════════════════════════════════════════════════
    // getRol()
    //  - COMO FUNCIONA: Devuelve el nombre del rol correspondiente a esta clase.
    //  - PARA QUÉ SIRVE: Permite identificar a este participante como CentroCrisis.
    //  * POLIMORFISMO POR SOBRECARGA *
    //    - Implementa el metodo abstracto getRol() heredado de Participante.
    @Override public String getRol() {
        return "CentroCrisis";
    }

    // ══════════════════════════════════════════════════════════════
    //  MIEMBROS
    // ══════════════════════════════════════════════════════════════
    // setMiembros()
    //  - COMO FUNCIONA: Asigna la lista de miembros recibida al atributo miembros.
    //  - PARA QUÉ SIRVE: Permite establecer quiénes integran el Centro de Crisis.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo privado miembros.
    public void setMiembros(List<MiembroOrganizacion> miembros) {
        this.miembros = miembros;
    }
    // getMiembros()
    //  - COMO FUNCIONA: Devuelve la lista de miembros almacenada en el atributo miembros.
    //  - PARA QUÉ SIRVE: Permite consultar quiénes integran el Centro de Crisis.
    //  * ENCAPSULAMIENTO *
    //    - Permite acceder al atributo privado miembros desde otras clases.
    public List<MiembroOrganizacion> getMiembros() {
        return miembros;
    }

    // ══════════════════════════════════════════════════════════════
    //  COMITÉS
    // ══════════════════════════════════════════════════════════════
    // setComites()
    //  - COMO FUNCIONA: Asigna la lista de comités recibida al atributo comites.
    //  - PARA QUÉ SIRVE: Permite establecer los comités que gestiona el Centro de Crisis.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo privado comites.
    public void setComites(List<Comite> comites) {
        this.comites = comites;
    }
    // getComites()
    //  - COMO FUNCIONA: Devuelve la lista de comités almacenada en el atributo comites.
    //  - PARA QUÉ SIRVE: Permite consultar los comités gestionados por el Centro de Crisis.
    //  * ENCAPSULAMIENTO *
    //    - Permite acceder al atributo privado comites desde otras clases.
    public List<Comite> getComites() {
        return comites;
    }
    // atiende()
    //  - COMO FUNCIONA: Comprueba si el comité recibido se encuentra dentro de la lista de comités que gestiona.
    //  - PARA QUÉ SIRVE: Determina si este Centro de Crisis atiende un comité específico.
    //  * ABSTRACCIÓN *
    //    - Encapsula la comprobación de pertenencia de un comité a la lista.
    public boolean atiende(Comite comite) {
        return comites != null && comites.contains(comite);
    }

}