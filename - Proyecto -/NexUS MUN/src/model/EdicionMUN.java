// PAQUETE E IMPORTAR //
package model;
import java.util.List;

import entities.Delegado;
import entities.Participante;
import entities.Institucion;
import logistic.ResultadoOperacion;
import services.GestorComites;
import services.GestorLogistica;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  EdicionMUN
// ══════════════════════════════════════════════════════════════
//  Edición del evento MUN. Agrupa a los participantes, las instituciones y los gestores de comités y logística.
//  * COMPOSICIÓN *
//    - EdicionMUN contiene a GestorComites, GestorLogistica y a los Participantes; sin la edición no tienen razón de existir
// ══════════════════════════════════════════════════════════════
public class EdicionMUN {

    // ─────────────────── ATRIBUTOS ───────────────────
    private String nombre;
    private List<Participante> participantes;
    private List<Institucion> instituciones;
    private GestorComites gestorComites;
    private GestorLogistica gestorLogistica;
    // ───────────────── CONSTRUCTORES ─────────────────
    public EdicionMUN() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public String getNombre() { return null; }
    public void setNombre(String nombre) { }
    public List<Participante> getParticipantes() { return null; }
    public void setParticipantes(List<Participante> participantes) { }
    public List<Institucion> getInstituciones() { return null; }
    public void setInstituciones(List<Institucion> instituciones) { }
    public GestorComites getGestorComites() { return null; }
    public void setGestorComites(GestorComites gestorComites) { }
    public GestorLogistica getGestorLogistica() { return null; }
    public void setGestorLogistica(GestorLogistica gestorLogistica) { }

    // ══════════════════════════════════════════════════════════════
    //  REGISTRO
    // ══════════════════════════════════════════════════════════════
    // registrarParticipante()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public ResultadoOperacion registrarParticipante(Participante participante) {
        return null;
    }
    // registrarInstitucion()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public ResultadoOperacion registrarInstitucion(Institucion institucion) {
        return null;
    }

    // ══════════════════════════════════════════════════════════════
    //  CONSULTAS
    // ══════════════════════════════════════════════════════════════
    // buscarPorCorreo()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public Participante buscarPorCorreo(String correo) {
        return null;
    }
    // getDelegadosDe()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public List<Delegado> getDelegadosDe(Institucion institucion) {
        return null;
    }

}
