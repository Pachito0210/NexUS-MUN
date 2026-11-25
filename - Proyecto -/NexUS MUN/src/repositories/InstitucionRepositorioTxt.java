// PAQUETE E IMPORTAR //
package repositories;
import entities.Institucion;
import java.util.ArrayList;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  InstitucionRepositorioTxt
// ══════════════════════════════════════════════════════════════
//  Guarda las instituciones en instituciones.txt, una por línea (nombre|tipo).
//  * HERENCIA *
//    - Hereda el CRUD completo de BaseRepositorioTxt y define cómo convertir una Institucion a línea.
//  * POLIMORFISMO *
//    - Sobrescribe getClave, toLinea y fromLinea con el comportamiento propio de Institucion.
// ══════════════════════════════════════════════════════════════
public class InstitucionRepositorioTxt extends BaseRepositorioTxt<Institucion> {

    // ───────────────── CONSTRUCTORES ─────────────────
    public InstitucionRepositorioTxt() {
        super("instituciones.txt", "|");
    }

    // ══════════════════════════════════════════════════════════════
    //  CONVERSIÓN
    // ══════════════════════════════════════════════════════════════
    // getClave()
    //  - COMO FUNCIONA: Devuelve el nombre de la institución como identificador del objeto.
    //  - PARA QUÉ SIRVE: Permite buscar, actualizar y eliminar instituciones usando su nombre.
    //  * ABSTRACCIÓN *
    //    - Define qué atributo identifica a una Institucion sin que el CRUD necesite conocer sus atributos.
    @Override protected String getClave(Institucion entity) {
        return entity.getNombre();
    }
    @Override protected boolean esValido(Institucion e) {
        return esTextoSeguro(e.getNombre()) && esTextoSeguro(e.getTipo());
    }
    // toLinea()
    //  - COMO FUNCIONA: Concatena el nombre y el tipo de la institución separados por el delimitador.
    //  - PARA QUÉ SIRVE: Permite convertir una Institucion en una línea de texto que pueda almacenarse en el archivo.
    //  * ABSTRACCIÓN *
    //    - Define cómo representar una Institucion en texto sin que el gestor de archivos conozca el modelo.
    @Override protected String toLinea(Institucion entity) {
        return entity.getNombre() + delimitador + entity.getTipo();
    }
    // fromLinea()
    //  - COMO FUNCIONA: Divide la línea en campos, verifica que tenga al menos dos y crea una Institucion asignándole el nombre y el tipo.
    //  - PARA QUÉ SIRVE: Permite recuperar una Institucion a partir de la información guardada en el archivo TXT.
    //  * POLIMORFISMO *
    //    - Implementa la reconstrucción de una Institucion mediante el método abstracto heredado de BaseRepositorioTxt.
    @Override protected Institucion fromLinea(String linea) {
        String[] campos = dividir(linea);
        if (campos.length < 2) {
            return null;
        }
        Institucion institucion = new Institucion();
        institucion.setNombre(campos[0]);
        institucion.setTipo(campos[1]);
        return institucion;
    }

    // ══════════════════════════════════════════════════════════════
    //  BÚSQUEDA
    // ══════════════════════════════════════════════════════════════
    // buscarPorNombre()
    //  - COMO FUNCIONA: Recorre las instituciones guardadas y compara sus nombres con el nombre recibido.
    //  - PARA QUÉ SIRVE: Recuperar una institución existente para asignarla a un participante.
    //  * ABSTRACCIÓN *
    //    - Permite buscar una institución sin que el repositorio de participantes conozca cómo se almacenan.
    public Institucion buscarPorNombre(String nombre) {
        ArrayList<Institucion> instituciones = getDataList();
        for (Institucion institucion : instituciones) {
            if (institucion.tieneElMismoNombreQue(nombre)) {
                return institucion;
            }
        }
        return null;
    }

}
