// PAQUETE E IMPORTAR //
package repositories;
import java.util.ArrayList;
import entities.*;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  ParticipanteRepositorioTxt
// ══════════════════════════════════════════════════════════════
//  Repositorio encargado de almacenar y administrar todos los participantes en un TXT.
//  * POLIMORFISMO *
//    - Maneja diferentes tipos de participantes mediante referencias de tipo Participante.
//  * PERSISTENCIA *
//    - Convierte los objetos en líneas de texto y permite reconstruirlos al cargar el archivo.
// ══════════════════════════════════════════════════════════════
public class ParticipanteRepositorioTxt extends BaseRepositorioTxt<Participante> {

    // ─────────────────── ATRIBUTOS ───────────────────
    private int contadorDelegados = 0;
    private int contadorSponsors = 0;
    private int contadorPresidentes = 0;
    private int contadorMiembrosOrganizacion = 0;
    private int contadorCentrosCrisis = 0;
    private int contadorJuntaDirectiva = 0;
    private InstitucionRepositorioTxt instituciones;
    // ───────────────── CONSTRUCTORES ─────────────────
    public ParticipanteRepositorioTxt(InstitucionRepositorioTxt instituciones) {
        super("participantes.txt", "|");
        this.instituciones = instituciones;
        sincronizarContadores();
    }

    // ══════════════════════════════════════════════════════════════
    //  GESTIÓN DE ID
    // ══════════════════════════════════════════════════════════════
    // generarId()
    //  - COMO FUNCIONA: Identifica el tipo de participante, aumenta su contador y construye el ID correspondiente.
    //  - PARA QUÉ SIRVE: Genera IDs consecutivos según el rol del participante.
    //  * POLIMORFISMO *
    //    - Permite identificar el tipo real del objeto mediante instanceof.
    private String generarId(Participante participante) {
        if (participante instanceof Delegado) {
            contadorDelegados++;
            return armarId("DE", contadorDelegados);
        }
        if (participante instanceof Sponsor) {
            contadorSponsors++;
            return armarId("SP", contadorSponsors);
        }
        if (participante instanceof Presidente) {
            contadorPresidentes++;
            return armarId("PR", contadorPresidentes);
        }
        if (participante instanceof MiembroOrganizacion) {
            contadorMiembrosOrganizacion++;
            return armarId("US", contadorMiembrosOrganizacion);
        }
        if (participante instanceof CentroCrisis) {
            contadorCentrosCrisis++;
            return armarId("CC", contadorCentrosCrisis);
        }
        if (participante instanceof JuntaDirectiva) {
            contadorJuntaDirectiva++;
            return armarId("JD", contadorJuntaDirectiva);
        }
        return null;
    }
    // armarId()
    //  - COMO FUNCIONA: Une el prefijo del rol con el número del contador, rellenado con ceros a 3 dígitos (ej: DE001).
    //  - PARA QUÉ SIRVE: Construir el formato de ID que usa todo el sistema.
    //  * ENCAPSULAMIENTO *
    //    - El formato del ID vive solo en el repositorio, que es quien los genera.
    private static String armarId(String prefijo, int numero) {
        return prefijo + String.format("%03d", numero);
    }
    // extraerNumeroDelId()
    //  - COMO FUNCIONA: Quita el prefijo de 2 letras del ID y convierte el resto a número (DE007 -> 7). Si no se puede, devuelve 0.
    //  - PARA QUÉ SIRVE: Recuperar el mayor número usado al cargar el TXT, para no repetir IDs.
    //  * PERSISTENCIA *
    //    - Permite continuar la numeración después de recuperar los datos guardados.
    private static int extraerNumeroDelId(String id) {
        if (id == null || id.length() <= 2) {
            return 0;
        }
        try {
            return Integer.parseInt(id.substring(2));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    // asignarId()
    //  - COMO FUNCIONA: Genera un ID y lo asigna al participante mediante setIdParticipante().
    //  - PARA QUÉ SIRVE: Identificar al participante antes de guardarlo.
    //  * ENCAPSULAMIENTO *
    //    - Utiliza el setter de Participante para modificar su identificador.
    private void asignarId(Participante participante) {
        participante.setIdParticipante(generarId(participante));
    }
    // sincronizarContadores()
    //  - COMO FUNCIONA: Recorre los participantes guardados y actualiza cada contador con el número mayor encontrado.
    //  - PARA QUÉ SIRVE: Evita repetir IDs después de cargar los participantes guardados.
    //  * PERSISTENCIA *
    //    - Mantiene la continuidad de los identificadores después de recuperar los datos.
    private void sincronizarContadores() {
        ArrayList<Participante> participantes = getDataList();
        if (participantes == null) {
            return;
        }
        for (Participante participante : participantes) {
            if (participante == null) {
                continue;
            }
            int numero = extraerNumeroDelId(
                    participante.getIdParticipante()
            );
            if (participante instanceof Delegado) {
                if (numero > contadorDelegados) {
                    contadorDelegados = numero;
                }
            }
            else if (participante instanceof Sponsor) {
                if (numero > contadorSponsors) {
                    contadorSponsors = numero;
                }
            }
            else if (participante instanceof Presidente) {
                if (numero > contadorPresidentes) {
                    contadorPresidentes = numero;
                }
            }
            else if (participante instanceof MiembroOrganizacion) {
                if (numero > contadorMiembrosOrganizacion) {
                    contadorMiembrosOrganizacion = numero;
                }
            }
            else if (participante instanceof CentroCrisis) {
                if (numero > contadorCentrosCrisis) {
                    contadorCentrosCrisis = numero;
                }
            }
            else if (participante instanceof JuntaDirectiva) {
                if (numero > contadorJuntaDirectiva) {
                    contadorJuntaDirectiva = numero;
                }
            }
        }
    }

    // ══════════════════════════════════════════════════════════════
    //  CRUD · INSERTAR
    // ══════════════════════════════════════════════════════════════
    // insertData()
    //  - COMO FUNCIONA: Genera y asigna un ID al participante y luego lo envía al repositorio base para guardarlo.
    //  - PARA QUÉ SIRVE: Registrar nuevos participantes en el archivo TXT con su identificador.
    //  * HERENCIA *
    //    - Reutiliza el metodo insertData() de BaseRepositorioTxt para almacenar los datos.
    @Override public boolean insertData(Participante participante) {
        if (participante == null || !esValido(participante)) {
            return false;
        }
        asignarId(participante);
        return super.insertData(participante);
    }

    // ══════════════════════════════════════════════════════════════
    //  CONVERSIÓN · OBJETO A TEXTO
    // ══════════════════════════════════════════════════════════════
    // toLinea()
    //  - COMO FUNCIONA: Convierte el participante en una línea de texto separando sus datos mediante el delimitador.
    //  - PARA QUÉ SIRVE: Guardar los participantes en el archivo TXT.
    //  * ABSTRACCIÓN *
    //    - Define cómo se representa cada tipo de participante en el archivo.
    @Override protected String toLinea(Participante participante) {
        String cargo = "";
        String nombreInstitucion = "";
        if (participante instanceof MiembroOrganizacion) {
            cargo = ((MiembroOrganizacion) participante).getCargo();
        }
        else if (participante instanceof JuntaDirectiva) {
            cargo = ((JuntaDirectiva) participante).getCargo();
        }
        else if (participante instanceof Delegado) {
            if (((Delegado) participante).getInstitucion() != null) {
                nombreInstitucion = ((Delegado) participante).getInstitucion().getNombre();
            }
        }
        else if (participante instanceof Sponsor) {
            if (((Sponsor) participante).getInstitucion() != null) {
                nombreInstitucion = ((Sponsor) participante).getInstitucion().getNombre();
            }
        }
        else if (participante instanceof Presidente) {
            if (((Presidente) participante).getInstitucion() != null) {
                nombreInstitucion = ((Presidente) participante).getInstitucion().getNombre();
            }
        }
        return participante.getIdParticipante() + delimitador
                + participante.getRol() + delimitador
                + participante.getNombre() + delimitador
                + participante.getCorreoElectronico() + delimitador
                + cargo + delimitador
                + nombreInstitucion;
    }

    // ══════════════════════════════════════════════════════════════
    //  CONVERSIÓN · TEXTO A OBJETO
    // ══════════════════════════════════════════════════════════════
    // fromLinea()
    //  - COMO FUNCIONA: Lee los datos de una línea del TXT y crea el objeto correspondiente según su rol.
    //  - PARA QUÉ SIRVE: Reconstruir los participantes guardados cuando se carga el archivo.
    //  * POLIMORFISMO *
    //    - Permite guardar y recuperar diferentes tipos de participantes utilizando el tipo general Participante.
    @Override public Participante fromLinea(String linea) {
        String[] datos = dividir(linea);
        if (datos.length < 6) {
            return null;
        }
        String id = datos[0];
        String rol = datos[1];
        String nombre = datos[2];
        String correo = datos[3];
        Participante participante = null;
        if (rol.equals("Delegado")) {
            Delegado delegado = new Delegado();
            delegado.setInstitucion(instituciones.buscarPorNombre(datos[5]));
            participante = delegado;
        } else if (rol.equals("Sponsor")) {
            Sponsor sponsor = new Sponsor();
            sponsor.setInstitucion(instituciones.buscarPorNombre(datos[5]));
            participante = sponsor;
        } else if (rol.equals("Presidente")) {
            Presidente presidente = new Presidente();
            presidente.setInstitucion(instituciones.buscarPorNombre(datos[5]));
            participante = presidente;
        } else if (rol.equals("MiembroOrganizacion")) {
            MiembroOrganizacion miembro = new MiembroOrganizacion();
            miembro.setCargo(datos[4]);
            participante = miembro;
        } else if (rol.equals("CentroCrisis")) {
            CentroCrisis centro = new CentroCrisis();
            participante = centro;
        } else if (rol.equals("JuntaDirectiva")) {
            JuntaDirectiva junta = new JuntaDirectiva();
            junta.setCargo(datos[4]);
            participante = junta;
        }
        if (participante != null) {
            participante.setIdParticipante(id);
            participante.setNombre(nombre);
            participante.setCorreoElectronico(correo);
        }
        return participante;
    }

    // ══════════════════════════════════════════════════════════════
    //  CLAVE
    // ══════════════════════════════════════════════════════════════
    // getClave()
    //  - COMO FUNCIONA: Devuelve el ID del participante como clave de búsqueda.
    //  - PARA QUÉ SIRVE: Identificar un participante de manera única dentro del repositorio.
    //  * ABSTRACCIÓN *
    //    - Define el criterio utilizado por el repositorio base para identificar los objetos.
    @Override protected String getClave(Participante participante) {
        return participante.getIdParticipante();
    }
    @Override protected boolean esValido(Participante p) {
        if (!esTextoSeguro(p.getNombre()) || !esTextoSeguro(p.getCorreoElectronico())) {
            return false;
        }
        if (p instanceof MiembroOrganizacion) {
            return esTextoSeguro(((MiembroOrganizacion) p).getCargo());
        }
        if (p instanceof JuntaDirectiva) {
            return esTextoSeguro(((JuntaDirectiva) p).getCargo());
        }
        return true;
    }

    // ══════════════════════════════════════════════════════════════
    //  CONSULTAS · TOTAL DE PARTICIPANTES
    // ══════════════════════════════════════════════════════════════
    // getCantidadParticipantes()
    //  - COMO FUNCIONA: Obtiene la lista de participantes guardados y devuelve la cantidad de registros.
    //  - PARA QUÉ SIRVE: Conocer el número total de participantes registrados en el MUN.
    //  * ABSTRACCIÓN *
    //    - Permite consultar el total sin conocer cómo se almacenan internamente los participantes.
    public int getCantidadParticipantes() {
        ArrayList<Participante> participantes = getDataList();
        if (participantes == null) {
            return 0;
        }
        return participantes.size();
    }

}