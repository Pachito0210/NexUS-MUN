package ui;

import entities.*;
import model.*;
import repositories.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

// ══════════════════════════════════════════════════════════════
//  CLASE · MenuPrincipal
// ══════════════════════════════════════════════════════════════
//  Interfaz gráfica principal de NexUS MUN.
//  * COMPOSICIÓN *
//    - Utiliza los repositorios para acceder a la información guardada.
//  * INTERFAZ GRÁFICA *
//    - Permite navegar por las funciones implementadas y pendientes.
// ══════════════════════════════════════════════════════════════
public class MenuPrincipal extends JFrame {

    private InstitucionRepositorioTxt instituciones;
    private ParticipanteRepositorioTxt participantes;
    private SalonRepositorioTxt salones;
    private ComiteRepositorioTxt comites;

    private final Color fondo = new Color(14, 24, 42);
    private final Color tarjeta = new Color(24, 39, 63);
    private final Color acento = new Color(37, 190, 173);
    private final Color texto = new Color(237, 243, 250);
    private final Color secundario = new Color(165, 181, 202);

    public MenuPrincipal() {
        // Crear repositorios en orden de dependencia.
        instituciones = new InstitucionRepositorioTxt();
        participantes = new ParticipanteRepositorioTxt(instituciones);
        salones = new SalonRepositorioTxt();
        comites = new ComiteRepositorioTxt(salones, participantes);

        configurarVentana();
        mostrarInicio();
    }

    private void configurarVentana() {
        setTitle("NexUS MUN | Sistema de gestión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 680);
        setMinimumSize(new Dimension(850, 580));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(fondo);
    }

    private void mostrarInicio() {
        getContentPane().removeAll();

        JPanel principal = new JPanel(new BorderLayout(24, 24));
        principal.setBackground(fondo);
        principal.setBorder(new EmptyBorder(28, 34, 28, 34));

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setOpaque(false);
        JPanel titulo = new JPanel();
        titulo.setOpaque(false);
        titulo.setLayout(new BoxLayout(titulo, BoxLayout.Y_AXIS));
        JLabel logo = new JLabel("NexUS MUN");
        logo.setForeground(texto);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 34));
        JLabel subtitulo = new JLabel("CENTRO DE GESTIÓN · MODELO DE NACIONES UNIDAS");
        subtitulo.setForeground(secundario);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        titulo.add(logo);
        titulo.add(Box.createVerticalStrut(5));
        titulo.add(subtitulo);
        encabezado.add(titulo, BorderLayout.WEST);

        JLabel estado = new JLabel("●  SISTEMA LOCAL");
        estado.setForeground(acento);
        estado.setFont(new Font("Segoe UI", Font.BOLD, 12));
        encabezado.add(estado, BorderLayout.EAST);

        JPanel grid = new JPanel(new GridLayout(3, 2, 16, 16));
        grid.setOpaque(false);
        grid.add(crearTarjeta("PARTICIPANTES", "Delegados, sponsors y organización", "Gestionar personas inscritas", () -> menuParticipantes()));
        grid.add(crearTarjeta("INSTITUCIONES", "Universidades, colegios y organizaciones", "Registrar y consultar instituciones", () -> menuInstituciones()));
        grid.add(crearTarjeta("COMITÉS", "Comités, delegaciones y asignaciones", "Administrar comités", () -> menuComites()));
        grid.add(crearTarjeta("SALONES", "Espacios y capacidades", "Administrar espacios físicos", () -> menuSalones()));
        grid.add(crearTarjeta("LOGÍSTICA", "Kits, comidas, materiales y solicitudes", "Módulo en desarrollo", () -> menuLogistica()));
        grid.add(crearTarjeta("REPORTES", "Listados y estadísticas del evento", "Módulo en desarrollo", () -> menuReportes()));

        JPanel pie = new JPanel(new BorderLayout());
        pie.setOpaque(false);
        JLabel aviso = new JLabel("Selecciona un módulo para comenzar.");
        aviso.setForeground(secundario);
        aviso.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JButton salir = crearBoton("Cerrar aplicación", new Color(66, 79, 98));
        salir.addActionListener(e -> dispose());
        pie.add(aviso, BorderLayout.WEST);
        pie.add(salir, BorderLayout.EAST);

        principal.add(encabezado, BorderLayout.NORTH);
        principal.add(grid, BorderLayout.CENTER);
        principal.add(pie, BorderLayout.SOUTH);
        add(principal, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private JPanel crearTarjeta(String nombre, String descripcion, String detalle, Runnable accion) {
        JPanel panel = new JPanel(new BorderLayout(8, 10));
        panel.setBackground(tarjeta);
        panel.setBorder(new EmptyBorder(20, 22, 18, 22));
        JLabel titulo = new JLabel(nombre);
        titulo.setForeground(acento);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JLabel desc = new JLabel(descripcion);
        desc.setForeground(texto);
        desc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLabel det = new JLabel(detalle);
        det.setForeground(secundario);
        det.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JButton abrir = crearBoton("Abrir módulo  →", acento);
        abrir.setForeground(new Color(8, 28, 40));
        abrir.addActionListener(e -> accion.run());
        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.add(titulo);
        info.add(Box.createVerticalStrut(4));
        info.add(desc);
        info.add(Box.createVerticalStrut(2));
        info.add(det);
        panel.add(info, BorderLayout.CENTER);
        panel.add(abrir, BorderLayout.SOUTH);
        return panel;
    }

    private JButton crearBoton(String textoBoton, Color color) {
        JButton boton = new JButton(textoBoton);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setBackground(color);
        boton.setForeground(texto);
        boton.setFocusPainted(false);
        boton.setBorder(new EmptyBorder(11, 16, 11, 16));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return boton;
    }

    private void mostrarMenu(String titulo, String[] opciones, Runnable[] acciones) {
        StringBuilder menu = new StringBuilder(titulo + "\n\n");
        for (int i = 0; i < opciones.length; i++) {
            menu.append(i + 1).append(". ").append(opciones[i]).append("\n");
        }
        menu.append("\n0. Volver al inicio");
        String seleccion = JOptionPane.showInputDialog(this, menu.toString(), titulo, JOptionPane.PLAIN_MESSAGE);
        if (seleccion == null || seleccion.trim().equals("0")) {
            mostrarInicio();
            return;
        }
        try {
            int numero = Integer.parseInt(seleccion.trim());
            if (numero >= 1 && numero <= acciones.length) {
                acciones[numero - 1].run();
            } else {
                mensaje("Opción no válida.");
            }
        } catch (NumberFormatException ex) {
            mensaje("Escribe el número de una opción.");
        }
    }

    // ══════════════════════════════════════════════════════════════
    //  INSTITUCIONES · FUNCIONAL
    // ══════════════════════════════════════════════════════════════
    private void menuInstituciones() {
        mostrarMenu("INSTITUCIONES", new String[]{"Registrar institución", "Ver instituciones", "Buscar institución", "Editar/eliminar (pendiente)"}, new Runnable[]{
                () -> registrarInstitucion(), () -> listarInstituciones(), () -> buscarInstitucion(), () -> pendiente("Edición y eliminación de instituciones")
        });
    }

    private void registrarInstitucion() {
        String nombre = pedir("Nombre de la institución:");
        if (nombre == null || nombre.trim().isEmpty()) return;
        String tipo = pedir("Tipo (Universidad, Colegio, Organización, etc.):");
        if (tipo == null || tipo.trim().isEmpty()) return;
        Institucion institucion = new Institucion();
        institucion.setNombre(nombre.trim());
        institucion.setTipo(tipo.trim());
        if (instituciones.insertData(institucion)) mensaje("Institución registrada correctamente.");
        else mensaje("No se pudo registrar. Puede que ya exista o los datos no sean válidos.");
    }

    private void listarInstituciones() {
        ArrayList<Institucion> lista = instituciones.getDataList();
        StringBuilder textoLista = new StringBuilder("INSTITUCIONES REGISTRADAS\n\n");
        if (lista.isEmpty()) textoLista.append("No hay instituciones registradas.");
        for (Institucion i : lista) textoLista.append("• ").append(i.getNombre()).append(" — ").append(i.getTipo()).append("\n");
        mostrarTexto("Instituciones", textoLista.toString());
    }

    private void buscarInstitucion() {
        String nombre = pedir("Nombre de la institución a buscar:");
        if (nombre == null) return;
        Institucion i = instituciones.buscarPorNombre(nombre.trim());
        if (i == null) mensaje("No se encontró la institución.");
        else mensaje("Nombre: " + i.getNombre() + "\nTipo: " + i.getTipo());
    }

    // ══════════════════════════════════════════════════════════════
    //  PARTICIPANTES · FUNCIONAL PARCIAL
    // ══════════════════════════════════════════════════════════════
    private void menuParticipantes() {
        mostrarMenu("PARTICIPANTES", new String[]{"Registrar participante", "Ver participantes", "Buscar por ID (pendiente)", "Editar/eliminar (pendiente)"}, new Runnable[]{
                () -> registrarParticipante(), () -> listarParticipantes(), () -> pendiente("Búsqueda específica de participantes"), () -> pendiente("Edición y eliminación de participantes")
        });
    }

    private void registrarParticipante() {
        String[] roles = {"Delegado", "Sponsor", "Presidente", "MiembroOrganizacion", "CentroCrisis", "JuntaDirectiva"};
        String rol = (String) JOptionPane.showInputDialog(this, "Selecciona el rol:", "Nuevo participante", JOptionPane.QUESTION_MESSAGE, null, roles, roles[0]);
        if (rol == null) return;
        String nombre = pedir("Nombre completo:");
        if (nombre == null || nombre.trim().isEmpty()) return;
        String correo = pedir("Correo electrónico:");
        if (correo == null) return;

        Participante p = null;
        if (rol.equals("Delegado")) {
            Delegado d = new Delegado();
            d.setInstitucion(pedirInstitucion());
            if (d.getInstitucion() == null) return;
            p = d;
        } else if (rol.equals("Sponsor")) {
            Sponsor s = new Sponsor();
            s.setInstitucion(pedirInstitucion());
            if (s.getInstitucion() == null) return;
            p = s;
        } else if (rol.equals("Presidente")) {
            Presidente presidente = new Presidente();
            presidente.setInstitucion(pedirInstitucion());
            if (presidente.getInstitucion() == null) return;
            p = presidente;
        } else if (rol.equals("MiembroOrganizacion")) {
            MiembroOrganizacion m = new MiembroOrganizacion();
            String cargo = pedir("Cargo:");
            if (cargo == null) return;
            m.setCargo(cargo.trim());
            p = m;
        } else if (rol.equals("CentroCrisis")) {
            p = new CentroCrisis();
        } else if (rol.equals("JuntaDirectiva")) {
            JuntaDirectiva j = new JuntaDirectiva();
            String cargo = pedir("Cargo:");
            if (cargo == null) return;
            j.setCargo(cargo.trim());
            p = j;
        }

        p.setNombre(nombre.trim());
        p.setCorreoElectronico(correo.trim());
        if (participantes.insertData(p)) {
            mensaje("Participante registrado. ID asignado: " + p.getIdParticipante());
        } else {
            mensaje("No se pudo registrar. Revisa el correo y que no esté duplicado.");
        }
    }

    private Institucion pedirInstitucion() {
        ArrayList<Institucion> lista = instituciones.getDataList();
        if (lista.isEmpty()) {
            mensaje("Primero registra una institución en el módulo Instituciones.");
            return null;
        }
        String[] nombres = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) nombres[i] = lista.get(i).getNombre();
        String elegido = (String) JOptionPane.showInputDialog(this, "Institución:", "Asignar institución", JOptionPane.QUESTION_MESSAGE, null, nombres, nombres[0]);
        if (elegido == null) return null;
        return instituciones.buscarPorNombre(elegido);
    }

    private void listarParticipantes() {
        ArrayList<Participante> lista = participantes.getDataList();
        StringBuilder sb = new StringBuilder("PARTICIPANTES REGISTRADOS\n\n");
        if (lista.isEmpty()) sb.append("No hay participantes registrados.");
        for (Participante p : lista) {
            sb.append(p.getIdParticipante()).append(" | ").append(p.getRol()).append(" | ")
                    .append(p.getNombre()).append(" | ").append(p.getCorreoElectronico());
            if (p instanceof Delegado) sb.append(" | Institución: ").append(((Delegado) p).getInstitucion() == null ? "—" : ((Delegado) p).getInstitucion().getNombre());
            else if (p instanceof Sponsor) sb.append(" | Institución: ").append(((Sponsor) p).getInstitucion() == null ? "—" : ((Sponsor) p).getInstitucion().getNombre());
            else if (p instanceof Presidente) sb.append(" | Institución: ").append(((Presidente) p).getInstitucion() == null ? "—" : ((Presidente) p).getInstitucion().getNombre());
            else if (p instanceof MiembroOrganizacion) sb.append(" | Cargo: ").append(((MiembroOrganizacion) p).getCargo());
            else if (p instanceof JuntaDirectiva) sb.append(" | Cargo: ").append(((JuntaDirectiva) p).getCargo());
            sb.append("\n");
        }
        mostrarTexto("Participantes", sb.toString());
    }

    // ══════════════════════════════════════════════════════════════
    //  SALONES · FUNCIONAL PARCIAL
    // ══════════════════════════════════════════════════════════════
    private void menuSalones() {
        mostrarMenu("SALONES", new String[]{"Registrar salón", "Ver salones", "Buscar salón", "Asignar/liberar salón (pendiente)"}, new Runnable[]{
                () -> registrarSalon(), () -> listarSalones(), () -> buscarSalon(), () -> pendiente("Asignación y liberación automática de salones")
        });
    }

    private void registrarSalon() {
        String nombre = pedir("Nombre/identificador del salón:");
        if (nombre == null || nombre.trim().isEmpty()) return;
        String cap = pedir("Capacidad máxima:");
        if (cap == null) return;
        try {
            int capacidad = Integer.parseInt(cap.trim());
            Salon salon = new Salon();
            salon.setNombre(nombre.trim());
            if (!salon.setCapacidad(capacidad)) {
                mensaje("La capacidad debe ser mayor que cero.");
                return;
            }
            if (salones.insertData(salon)) mensaje("Salón registrado.");
            else mensaje("No se pudo registrar. Puede que el salón ya exista.");
        } catch (NumberFormatException ex) {
            mensaje("La capacidad debe ser un número entero.");
        }
    }

    private void listarSalones() {
        ArrayList<Salon> lista = salones.getDataList();
        StringBuilder sb = new StringBuilder("SALONES REGISTRADOS\n\n");
        if (lista.isEmpty()) sb.append("No hay salones registrados.");
        for (Salon s : lista) {
            sb.append(s.getNombre()).append(" | Capacidad: ").append(s.getCapacidad()).append(" | ")
                    .append(s.estaDisponible() ? "Disponible" : "Asignado a: " + s.getNombreComiteAsignado()).append("\n");
        }
        mostrarTexto("Salones", sb.toString());
    }

    private void buscarSalon() {
        String nombre = pedir("Nombre del salón a buscar:");
        if (nombre == null) return;
        Salon s = salones.buscarPorNombre(nombre.trim());
        if (s == null) mensaje("No se encontró el salón.");
        else mensaje("Salón: " + s.getNombre() + "\nCapacidad: " + s.getCapacidad() + "\nEstado: " + (s.estaDisponible() ? "Disponible" : "Asignado a " + s.getNombreComiteAsignado()));
    }

    // ══════════════════════════════════════════════════════════════
    //  COMITÉS · FUNCIONAL PARCIAL
    // ══════════════════════════════════════════════════════════════
    private void menuComites() {
        mostrarMenu("COMITÉS", new String[]{"Crear comité", "Ver comités", "Buscar comité", "Delegaciones y asignaciones (pendiente)"}, new Runnable[]{
                () -> registrarComite(), () -> listarComites(), () -> buscarComite(), () -> pendiente("Asignación de delegaciones y gestión completa de comité")
        });
    }

    private void registrarComite() {
        String nombre = pedir("Nombre del comité:");
        if (nombre == null || nombre.trim().isEmpty()) return;
        String fecha = pedir("Fecha (ej. 28/11/2026):");
        if (fecha == null || fecha.trim().isEmpty()) return;
        String tema = pedir("Tema:");
        if (tema == null || tema.trim().isEmpty()) return;
        String cupoTexto = pedir("Cupo máximo de delegaciones:");
        if (cupoTexto == null) return;
        try {
            int cupo = Integer.parseInt(cupoTexto.trim());
            if (cupo <= 0) { mensaje("El cupo debe ser mayor que cero."); return; }
            Comite c = new Comite();
            c.setNombre(nombre.trim());
            c.setFecha(fecha.trim());
            c.setTema(tema.trim());
            c.setCupoMaximo(cupo);
            if (comites.insertData(c)) mensaje("Comité creado. La asignación de salón y delegaciones aún está pendiente.");
            else mensaje("No se pudo crear. Verifica que no exista un comité con ese nombre.");
        } catch (NumberFormatException ex) {
            mensaje("El cupo debe ser un número entero.");
        }
    }

    private void listarComites() {
        ArrayList<Comite> lista = comites.getDataList();
        StringBuilder sb = new StringBuilder("COMITÉS REGISTRADOS\n\n");
        if (lista.isEmpty()) sb.append("No hay comités registrados.");
        for (Comite c : lista) {
            sb.append(c.getNombre()).append(" | Fecha: ").append(c.getFecha()).append(" | Tema: ").append(c.getTema())
                    .append(" | Cupo: ").append(c.getCupoMaximo()).append(" | Delegaciones: ").append(c.getDelegaciones().size())
                    .append(" | Salón: ").append(c.getSalon() == null ? "Sin asignar" : c.getSalon().getNombre()).append("\n");
        }
        mostrarTexto("Comités", sb.toString());
    }

    private void buscarComite() {
        String nombre = pedir("Nombre del comité a buscar:");
        if (nombre == null) return;
        Comite c = comites.buscarPorNombre(nombre.trim());
        if (c == null) mensaje("No se encontró el comité.");
        else mensaje("Comité: " + c.getNombre() + "\nFecha: " + c.getFecha() + "\nTema: " + c.getTema() + "\nCupo: " + c.getCupoMaximo() + "\nDelegaciones: " + c.getDelegaciones().size());
    }

    // ══════════════════════════════════════════════════════════════
    //  MÓDULOS PENDIENTES
    // ══════════════════════════════════════════════════════════════
    private void menuLogistica() {
        mostrarMenu("LOGÍSTICA", new String[]{"Kits", "Comidas", "Materiales e insumos", "Solicitudes logísticas", "Asignación y entregas"}, new Runnable[]{
                () -> pendiente("Gestión de kits"), () -> pendiente("Gestión de comidas"), () -> pendiente("Materiales e insumos"), () -> pendiente("Solicitudes logísticas"), () -> pendiente("Asignación y entregas")
        });
    }

    private void menuReportes() {
        mostrarMenu("REPORTES", new String[]{"Reporte de participantes", "Reporte de instituciones", "Reporte de comités", "Reporte logístico"}, new Runnable[]{
                () -> pendiente("Reportes de participantes"), () -> pendiente("Reportes de instituciones"), () -> pendiente("Reportes de comités"), () -> pendiente("Reportes logísticos")
        });
    }

    private void pendiente(String funcion) {
        mensaje("⏳ FALTA POR IMPLEMENTAR\n\n" + funcion + "\n\nEsta opción ya está contemplada en el menú y se habilitará cuando se desarrolle el módulo.");
    }

    // ══════════════════════════════════════════════════════════════
    //  APOYO DE INTERFAZ
    // ══════════════════════════════════════════════════════════════
    private String pedir(String pregunta) {
        return JOptionPane.showInputDialog(this, pregunta);
    }

    private void mensaje(String contenido) {
        JOptionPane.showMessageDialog(this, contenido, "NexUS MUN", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarTexto(String titulo, String contenido) {
        JTextArea area = new JTextArea(contenido, 18, 60);
        area.setEditable(false);
        area.setFont(new Font("Consolas", Font.PLAIN, 13));
        area.setBackground(new Color(240, 244, 248));
        area.setForeground(new Color(25, 38, 55));
        area.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane scroll = new JScrollPane(area);
        JOptionPane.showMessageDialog(this, scroll, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
