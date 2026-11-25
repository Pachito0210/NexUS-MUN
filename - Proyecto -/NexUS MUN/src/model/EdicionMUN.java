package models;

import services.GestorComites;
import services.GestorLogistica;

import java.util.ArrayList;
import java.util.List;

public class EdicionMUN {

    private String nombre;
    private List<Participante> participantes;
    private List<Institucion> instituciones;
    private GestorComites gestorComites;
    private GestorLogistica gestorLogistica;

    public EdicionMUN() {
        this.participantes = new ArrayList<>();
        this.instituciones = new ArrayList<>();
        this.gestorComites = new GestorComites();
        this.gestorLogistica = new GestorLogistica();
    }

    public EdicionMUN(String nombre) {
        this();
        this.nombre = nombre == null ? null : nombre.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre == null ? null : nombre.trim();
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes == null ? new ArrayList<>() : participantes;
    }

    public List<Institucion> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(List<Institucion> instituciones) {
        this.instituciones = instituciones == null ? new ArrayList<>() : instituciones;
    }

    public GestorComites getGestorComites() {
        return gestorComites;
    }

    public void setGestorComites(GestorComites gestorComites) {
        this.gestorComites = gestorComites == null ? new GestorComites() : gestorComites;
    }

    public GestorLogistica getGestorLogistica() {
        return gestorLogistica;
    }

    public void setGestorLogistica(GestorLogistica gestorLogistica) {
        this.gestorLogistica = gestorLogistica == null ? new GestorLogistica() : gestorLogistica;
    }

    public ResultadoOperacion registrarParticipante(Participante participante) {
        if (participante == null) {
            return new ResultadoOperacion(false, "El participante no puede ser nulo.");
        }
        if (participante.getNombre() == null || participante.getNombre().isBlank()) {
            return new ResultadoOperacion(false, "El participante debe tener un nombre.");
        }
        if (!Participante.esCorreoValido(participante.getCorreoElectronico())) {
            return new ResultadoOperacion(false,
                    "El correo '" + participante.getCorreoElectronico() + "' no es válido.");
        }
        if (buscarPorCorreo(participante.getCorreoElectronico()) != null) {
            return new ResultadoOperacion(false,
                    "Ya existe un participante con el correo " + participante.getCorreoElectronico() + ".");
        }
        String id = participante.getIdParticipante();
        if (id != null) {
            for (Participante existente : participantes) {
                if (existente != null && id.equalsIgnoreCase(existente.getIdParticipante())) {
                    return new ResultadoOperacion(false, "Ya existe un participante con el ID " + id + ".");
                }
            }
        }
        participantes.add(participante);
        return new ResultadoOperacion(true,
                participante.getRol() + " " + participante.getNombre() + " registrado con ID " + id + ".");
    }

    public ResultadoOperacion registrarInstitucion(Institucion institucion) {
        if (institucion == null) {
            return new ResultadoOperacion(false, "La institución no puede ser nula.");
        }
        if (institucion.getNombre() == null || institucion.getNombre().isBlank()) {
            return new ResultadoOperacion(false, "La institución debe tener un nombre.");
        }
        for (Institucion existente : instituciones) {
            if (existente != null && existente.tieneElMismoNombreQue(institucion.getNombre())) {
                return new ResultadoOperacion(false,
                        "La institución '" + institucion.getNombre() + "' ya está registrada.");
            }
        }
        instituciones.add(institucion);
        return new ResultadoOperacion(true, "Institución '" + institucion.getNombre() + "' registrada.");
    }

    public Institucion buscarInstitucionPorNombre(String nombre) {
        if (nombre == null) {
            return null;
        }
        for (Institucion institucion : instituciones) {
            if (institucion != null && institucion.tieneElMismoNombreQue(nombre)) {
                return institucion;
            }
        }
        return null;
    }

    public Participante buscarPorCorreo(String correo) {
        if (correo == null) {
            return null;
        }
        String buscado = correo.trim();
        for (Participante participante : participantes) {
            if (participante != null && participante.getCorreoElectronico() != null
                    && participante.getCorreoElectronico().trim().equalsIgnoreCase(buscado)) {
                return participante;
            }
        }
        return null;
    }

    public List<Delegado> getDelegadosDe(Institucion institucion) {
        List<Delegado> resultado = new ArrayList<>();
        if (institucion == null) {
            return resultado;
        }
        for (Participante participante : participantes) {
            if (participante instanceof Delegado delegado) {
                Institucion suInstitucion = delegado.getInstitucion();
                if (suInstitucion != null && (suInstitucion == institucion
                        || suInstitucion.tieneElMismoNombreQue(institucion.getNombre()))) {
                    resultado.add(delegado);
                }
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        return "EdicionMUN '" + nombre + "': " + participantes.size() + " participantes, "
                + instituciones.size() + " instituciones";
    }
}