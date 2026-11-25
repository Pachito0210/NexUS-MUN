// PAQUETE E IMPORTAR //
package repositories;
import interfaces.IGuardable;
import models.EdicionMUN;
import models.ResultadoOperacion;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  RepositorioJson
// ══════════════════════════════════════════════════════════════
//  Guarda y carga la edición del evento en un archivo JSON, con copia de seguridad.
//  * INTERFAZ *
//    - Implementa IGuardable, que define cómo se guarda y carga la edición
// ══════════════════════════════════════════════════════════════
public class RepositorioJson implements IGuardable {

    // ─────────────────── ATRIBUTOS ───────────────────
    private String rutaArchivo;
    // ───────────────── CONSTRUCTORES ─────────────────
    public RepositorioJson() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public String getRutaArchivo() { return null; }
    public void setRutaArchivo(String rutaArchivo) { }

    // ══════════════════════════════════════════════════════════════
    //  PERSISTENCIA
    // ══════════════════════════════════════════════════════════════
    // guardar()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public ResultadoOperacion guardar(EdicionMUN edicion) {
        return null;
    }
    // cargar()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public EdicionMUN cargar() {
        return null;
    }
    // crearCopiaSeguridad()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public void crearCopiaSeguridad() {
    }

}
