// PAQUETE E IMPORTAR //
package repositories;
import model.Salon;
import java.util.ArrayList;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  SalonRepositorioTxt
// ══════════════════════════════════════════════════════════════
//  Guarda los salones en salones.txt, uno por línea (nombre|capacidad|comite asignado).
//  * HERENCIA *
//    - Hereda el CRUD completo de BaseRepositorioTxt y define cómo convertir un Salon a línea.
//  * POLIMORFISMO *
//    - Sobrescribe getClave, toLinea y fromLinea con el comportamiento propio de Salon.
// ══════════════════════════════════════════════════════════════
public class SalonRepositorioTxt extends BaseRepositorioTxt<Salon> {

    // ───────────────── CONSTRUCTORES ─────────────────
    public SalonRepositorioTxt() {
        super("salones.txt", "|");
    }

    // ══════════════════════════════════════════════════════════════
    //  CONVERSIÓN
    // ══════════════════════════════════════════════════════════════
    // getClave()
    //  - COMO FUNCIONA: Devuelve el nombre del salón como identificador del objeto.
    //  - PARA QUÉ SIRVE: Permite buscar, actualizar y eliminar salones usando su nombre.
    //  * ABSTRACCIÓN *
    //    - Define qué atributo identifica a un Salon sin que el CRUD necesite conocer sus atributos.
    @Override protected String getClave(Salon entity) {
        return entity.getNombre();
    }
    @Override protected boolean esValido(Salon e) {
        return esTextoSeguro(e.getNombre());
    }
    // toLinea()
    //  - COMO FUNCIONA: Concatena el nombre, la capacidad y el comité asignado separados por el delimitador.
    //  - PARA QUÉ SIRVE: Convertir un Salon en una línea de texto que pueda almacenarse en el archivo.
    //  * ABSTRACCIÓN *
    //    - Define cómo representar un Salon en texto sin que el gestor de archivos conozca el modelo.
    @Override protected String toLinea(Salon entity) {
        String comite = entity.getNombreComiteAsignado() == null
                ? ""
                : entity.getNombreComiteAsignado();

        return entity.getNombre() + delimitador
                + entity.getCapacidad() + delimitador
                + comite;
    }
    // fromLinea()
    //  - COMO FUNCIONA: Divide la línea en campos, verifica que tenga al menos dos y reconstruye el Salon con sus datos.
    //  - PARA QUÉ SIRVE: Recuperar un Salon a partir de la información guardada en el archivo TXT.
    //  * POLIMORFISMO *
    //    - Implementa la reconstrucción de un Salon mediante el método abstracto heredado de BaseRepositorioTxt.
    @Override protected Salon fromLinea(String linea) {
        String[] campos = dividir(linea);
        if (campos.length < 2) {
            return null;
        }
        Salon salon = new Salon();
        salon.setNombre(campos[0]);
        try {
            salon.setCapacidad(Integer.parseInt(campos[1]));
        } catch (NumberFormatException e) {
            return null;
        }
        if (campos.length >= 3 && !campos[2].isEmpty()) {
            salon.asignarComite(campos[2]);
        }
        return salon;
    }

    // ══════════════════════════════════════════════════════════════
    //  BÚSQUEDA
    // ══════════════════════════════════════════════════════════════
    // buscarPorNombre()
    //  - COMO FUNCIONA: Recorre los salones guardados y compara sus nombres con el nombre recibido.
    //  - PARA QUÉ SIRVE: Recuperar un salón existente para asignarlo a un comité.
    //  * ABSTRACCIÓN *
    //    - Permite buscar un salón sin que el comité conozca cómo se almacenan.
    public Salon buscarPorNombre(String nombre) {
        ArrayList<Salon> salones = getDataList();
        if (salones == null) {
            return null;
        }
        for (Salon salon : salones) {
            if (salon.tieneElMismoNombreQue(nombre)) {
                return salon;
            }
        }
        return null;
    }

}
