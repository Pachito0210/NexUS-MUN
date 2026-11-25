// PAQUETE E IMPORTAR //
package repositories;
import model.*;
import entities.*;
import java.util.ArrayList;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  ComiteRepositorioTxt
// ══════════════════════════════════════════════════════════════
//  Guarda y recupera los comités, sus salones, delegaciones y delegados
//  mediante el archivo comites.txt.
//  * PERSISTENCIA *
//    - Convierte los comités en líneas de texto y reconstruye sus relaciones al cargarlos.
//  * COMPOSICIÓN *
//    - Reconstruye las delegaciones que administra cada comité.
//  * POLIMORFISMO *
//    - Recupera los delegados desde el repositorio general de Participantes.
// ══════════════════════════════════════════════════════════════
public class ComiteRepositorioTxt extends BaseRepositorioTxt<Comite> {

    // ─────────────────── ATRIBUTOS ───────────────────
    private SalonRepositorioTxt salones;
    private ParticipanteRepositorioTxt participantes;
    // ───────────────── CONSTRUCTORES ─────────────────
    public ComiteRepositorioTxt(SalonRepositorioTxt salones, ParticipanteRepositorioTxt participantes) {
        super("comites.txt", "|");
        this.salones = salones;
        this.participantes = participantes;
    }

    // ══════════════════════════════════════════════════════════════
    //  CLAVE
    // ══════════════════════════════════════════════════════════════
    // getClave()
    //  - COMO FUNCIONA: Devuelve el nombre del comité como identificador del objeto.
    //  - PARA QUÉ SIRVE: Permite buscar, actualizar y eliminar comités utilizando su nombre.
    //  * ABSTRACCIÓN *
    //    - Define qué atributo identifica a un comité sin que el CRUD necesite conocer sus demás atributos.
    @Override protected String getClave(Comite entity) {
        return entity.getNombre();
    }
    @Override protected boolean esValido(Comite c) {
        if (!esTextoSeguro(c.getNombre()) || !esTextoSeguro(c.getTema())
                || !esTextoSeguro(c.getFecha())) {
            return false;
        }
        for (Delegacion d : c.getDelegaciones()) {
            if (d == null) continue;
            String n = d.getNombre();
            if (!esTextoSeguro(n) || (n != null && (n.contains(";") || n.contains(":")))) {
                return false;
            }
        }
        return true;
    }

    // ══════════════════════════════════════════════════════════════
    //  CONVERSIÓN · OBJETO A TEXTO
    // ══════════════════════════════════════════════════════════════
    // toLinea()
    //  - COMO FUNCIONA: Convierte los atributos del comité, el nombre del salón y las delegaciones con sus IDs en una línea de texto.
    //  - PARA QUÉ SIRVE: Guardar toda la información necesaria para reconstruir el comité posteriormente.
    //  * PERSISTENCIA *
    //    - Guarda los IDs de los delegados en lugar de duplicar sus datos personales.
    @Override protected String toLinea(Comite entity) {
        String nombreSalon = "";
        if (entity.getSalon() != null) {
            nombreSalon = entity.getSalon().getNombre();
        }
        String datosDelegaciones = "";
        for (Delegacion delegacion : entity.getDelegaciones()) {
            if (delegacion == null) {
                continue;
            }
            String idDelegado = "";
            if (delegacion.getDelegado() != null) {
                idDelegado = delegacion.getDelegado().getIdParticipante();
            }
            if (!datosDelegaciones.isEmpty()) {
                datosDelegaciones += ";";
            }
            datosDelegaciones += delegacion.getNombre() + ":" + idDelegado;
        }
        return entity.getNombre() + delimitador
                + entity.getFecha() + delimitador
                + entity.getTema() + delimitador
                + entity.getCupoMaximo() + delimitador
                + nombreSalon + delimitador
                + datosDelegaciones;
    }

    // ══════════════════════════════════════════════════════════════
    //  CONVERSIÓN · TEXTO A OBJETO
    // ══════════════════════════════════════════════════════════════
    // fromLinea()
    //  - COMO FUNCIONA: Separa los datos del comité, recupera el salón y reconstruye cada delegación con su delegado correspondiente.
    //  - PARA QUÉ SIRVE: Recuperar los comités guardados y sus relaciones al cargar el archivo.
    //  * PERSISTENCIA *
    //    - Reconstruye las relaciones entre comités, salones, delegaciones y participantes utilizando sus identificadores.
    @Override protected Comite fromLinea(String linea) {
        String[] campos = dividir(linea);
        if (campos.length < 5) {
            return null;
        }
        Comite comite = new Comite();
        comite.setNombre(campos[0]);
        comite.setFecha(campos[1]);
        comite.setTema(campos[2]);
        try {
            comite.setCupoMaximo(Integer.parseInt(campos[3]));
        } catch (NumberFormatException e) {
            return null;
        }
        // NOTA: Para recuperar el salón por su nombre.
        if (!campos[4].isEmpty()) {
            Salon salon = salones.buscarPorNombre(campos[4]);
            if (salon != null) {
                comite.setSalon(salon);
            }
        }
        // NOTA: Para recuperar los delegados y sus delegaciones.
        if (campos.length >= 6 && !campos[5].isEmpty()) {
            String[] registrosDelegacion = campos[5].split(";");
            for (String registro : registrosDelegacion) {
                String[] datosDelegacion = registro.split(":", -1);
                if (datosDelegacion.length < 2) {
                    continue;
                }
                Delegacion delegacion = new Delegacion();
                delegacion.setNombre(datosDelegacion[0]);
                // Búsqueda por ID.
                if (!datosDelegacion[1].isEmpty()) {
                    Participante participante = participantes.buscarPorClave(datosDelegacion[1]);
                    if (participante instanceof Delegado) {
                        delegacion.asignarDelegado((Delegado) participante);
                    }
                }
                comite.asignarDelegacion(delegacion);
            }
        }
        return comite;
    }

    // ══════════════════════════════════════════════════════════════
    //  BÚSQUEDA
    // ══════════════════════════════════════════════════════════════
    // buscarPorNombre()
    //  - COMO FUNCIONA: Recorre los comités guardados y compara sus nombres con el nombre recibido.
    //  - PARA QUÉ SIRVE: Recuperar un comité específico utilizando su nombre.
    //  * ABSTRACCIÓN *
    //    - Permite buscar comités sin que otras clases conozcan cómo se almacenan.
    public Comite buscarPorNombre(String nombre) {
        ArrayList<Comite> comites = getDataList();
        if (comites == null) {
            return null;
        }
        for (Comite comite : comites) {
            if (comite.tieneElMismoNombreQue(nombre)) {
                return comite;
            }
        }
        return null;
    }
}
