package ui.controllers;

import javafx.scene.control.Alert;
import java.util.*;

public class ControladorPrincipal {

    // Estructuras temporales para que la UI funcione de inmediato.
    // Cuando tus companeros terminen sus metodos en services/, reemplazas estas listas
    // por llamadas directas a GestorComites, GestorLogistica y GestorReportes.
    private final Map<String, Integer> cuposComites = new LinkedHashMap<>();
    private final Map<String, Integer> inscritosComites = new LinkedHashMap<>();
    private final Set<String> paisesAsignados = new HashSet<>();
    private final Map<String, String> delegadosRegistrados = new LinkedHashMap<>();
    private final Set<String> kitsEntregados = new HashSet<>();
    private final Set<String> comidasEntregadas = new HashSet<>();
    private final List<String> colaEmergencias = new ArrayList<>();
    private final Map<String, Double> notasDelegados = new LinkedHashMap<>();
    private boolean edicionCerrada = false;

    public ControladorPrincipal() {
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        cuposComites.put("AGNU - Asamblea General", 30);
        inscritosComites.put("AGNU - Asamblea General", 24);

        cuposComites.put("CSNU - Consejo de Seguridad", 15);
        inscritosComites.put("CSNU - Consejo de Seguridad", 15); // Cupo lleno de ejemplo

        cuposComites.put("ECOSOC - Consejo Economico", 25);
        inscritosComites.put("ECOSOC - Consejo Economico", 18);

        delegadosRegistrados.put("DE001", "Laura Gomez (Colegio Sabana)");
        delegadosRegistrados.put("DE002", "Andres Martinez (Universidad de La Sabana)");
        delegadosRegistrados.put("SP001", "Camila Torres (Sponsor Institucional)");

        colaEmergencias.add("[ALTA] Salon A-203: Falla en microfono de Mesa Directiva");
        colaEmergencias.add("[MEDIA] Auditorio Principal: Reposicion de agua para delegados");
    }

    // RF-01: Crear comite
    public boolean registrarComite(String nombre, int cupo) {
        if (edicionCerrada) return false;
        if (nombre == null || nombre.isBlank() || cupo < 1) return false;
        cuposComites.put(nombre.trim(), cupo);
        inscritosComites.putIfAbsent(nombre.trim(), 0);
        return true;
    }

    // RF-02: Inscribir delegado con validacion
    public String inscribirParticipante(String id, String nombre, String institucion, String opcion1, String opcion2) {
        if (edicionCerrada) return "La edicion ya fue cerrada formalmente.";
        if (delegadosRegistrados.containsKey(id)) return "El ID " + id + " ya esta registrado.";

        delegadosRegistrados.put(id, nombre + " (" + institucion + ")");
        int ocupados1 = inscritosComites.getOrDefault(opcion1, 0);
        int max1 = cuposComites.getOrDefault(opcion1, 0);

        if (ocupados1 < max1) {
            inscritosComites.put(opcion1, ocupados1 + 1);
            return "OK: Inscrito en su 1ra opcion (" + opcion1 + ").";
        }
        int ocupados2 = inscritosComites.getOrDefault(opcion2, 0);
        int max2 = cuposComites.getOrDefault(opcion2, 0);
        if (ocupados2 < max2) {
            inscritosComites.put(opcion2, ocupados2 + 1);
            return "OK: 1ra opcion llena. Reubicado automaticamente en 2da opcion (" + opcion2 + ").";
        }
        return "ALERTA: Ambas opciones estan llenas. Requiere revision de Junta Directiva.";
    }

    // RF-03: Asignar pais en comite
    public String asignarRepresentacion(String idDelegado, String comite, String pais) {
        if (edicionCerrada) return "Edicion cerrada.";
        if (!delegadosRegistrados.containsKey(idDelegado)) return "ERROR: El ID de participante no existe.";
        String llave = comite + "::" + pais.toLowerCase();
        if (paisesAsignados.contains(llave)) {
            return "ERROR: El pais " + pais + " ya esta ocupado en " + comite + ".";
        }
        paisesAsignados.add(llave);
        return "OK: " + idDelegado + " asignado como " + pais + " en " + comite + ".";
    }

    // RF-04: Entrega de kit sin duplicados
    public String entregarKit(String id) {
        if (!delegadosRegistrados.containsKey(id)) return "ERROR: ID no encontrado.";
        if (kitsEntregados.contains(id)) {
            return "DUPLICADO: Este kit ya fue entregado al participante " + id + ".";
        }
        kitsEntregados.add(id);
        return "OK: Kit entregado exitosamente a " + delegadosRegistrados.get(id) + ".";
    }

    // RF-06: Entrega de refrigerio / almuerzo
    public String entregarComida(String id, String jornada) {
        if (!delegadosRegistrados.containsKey(id)) return "ERROR: ID no encontrado.";
        String clave = id + "_" + jornada;
        if (comidasEntregadas.contains(clave)) {
            return "DUPLICADO: Este refrigerio y/o almuerzo ya fue entregado en " + jornada + ".";
        }
        comidasEntregadas.add(clave);
        return "OK: " + jornada + " registrado para " + delegadosRegistrados.get(id) + ".";
    }

    // RF-08: Solicitud logistica de emergencia
    public void registrarEmergencia(String salon, String tipo, String urgencia) {
        colaEmergencias.add(0, "[" + urgencia.toUpperCase() + "] Salon " + salon + ": " + tipo);
    }

    // RF-07: Calificar delegado (1 a 5)
    public boolean calificarDelegado(String id, double nota) {
        if (!delegadosRegistrados.containsKey(id) || nota < 1.0 || nota > 5.0) return false;
        notasDelegados.put(id, nota);
        return true;
    }

    // RF-10: Cierre formal de edicion
    public void cerrarEdicion() {
        this.edicionCerrada = true;
    }

    public Map<String, Integer> getCuposComites() { return cuposComites; }
    public Map<String, Integer> getInscritosComites() { return inscritosComites; }
    public Map<String, String> getDelegadosRegistrados() { return delegadosRegistrados; }
    public List<String> getColaEmergencias() { return colaEmergencias; }
    public int getTotalKitsEntregados() { return kitsEntregados.size(); }
    public int getTotalComidasEntregadas() { return comidasEntregadas.size(); }
    public Map<String, Double> getNotasDelegados() { return notasDelegados; }
    public boolean isEdicionCerrada() { return edicionCerrada; }

    public void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}