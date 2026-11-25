package ui.views;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import ui.controllers.ControladorPrincipal;

public class VistaComitesInscripcion extends VistaBase {

    private VBox listaEstadoComites;
    private ComboBox<String> cmbOpcion1;
    private ComboBox<String> cmbOpcion2;
    private ComboBox<String> cmbComiteAsig;

    public VistaComitesInscripcion(ControladorPrincipal controlador) {
        super(
                controlador,
                "Comites, Inscripciones y Asignacion",
                "Gestion de cupos (RF-01), inscripcion con prioridad (RF-02) y asignacion de paises (RF-03)."
        );
        construirVista();
    }

    @Override
    public void construirVista() {
        // Tarjeta 1: Estado de cupos en tiempo real
        VBox cardCupos = new VBox(10);
        cardCupos.getStyleClass().add("card");
        Label titCupos = new Label("Estado Actual de Comites");
        titCupos.getStyleClass().add("card-title");
        listaEstadoComites = new VBox(8);
        cardCupos.getChildren().addAll(titCupos, listaEstadoComites);

        // Tarjeta 2: Formulario de Inscripcion con validacion visual (RNF-02)
        VBox cardInscripcion = new VBox(10);
        cardInscripcion.getStyleClass().add("card");
        Label titInsc = new Label("Formulario de Inscripcion de Participante");
        titInsc.getStyleClass().add("card-title");

        TextField txtId = new TextField();
        txtId.setPromptText("ID (ej. DE003 o SP002)");
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre completo");
        TextField txtInstitucion = new TextField();
        txtInstitucion.setPromptText("Colegio o Universidad");

        cmbOpcion1 = new ComboBox<>();
        cmbOpcion1.setPromptText("1ra opcion de comite");
        cmbOpcion1.setMaxWidth(Double.MAX_VALUE);

        cmbOpcion2 = new ComboBox<>();
        cmbOpcion2.setPromptText("2da opcion de comite");
        cmbOpcion2.setMaxWidth(Double.MAX_VALUE);

        Button btnInscribir = new Button("Registrar Inscripcion");
        btnInscribir.getStyleClass().add("btn-primary");

        btnInscribir.setOnAction(e -> {
            limpiarErrores(txtId, txtNombre, txtInstitucion);
            boolean valido = true;

            if (txtId.getText().isBlank() || !txtId.getText().matches("^[A-Za-z]{2}\\d{3}$")) {
                txtId.getStyleClass().add("input-error");
                valido = false;
            }
            if (txtNombre.getText().isBlank() || txtNombre.getText().matches(".*\\d.*")) {
                txtNombre.getStyleClass().add("input-error");
                valido = false;
            }
            if (txtInstitucion.getText().isBlank()) {
                txtInstitucion.getStyleClass().add("input-error");
                valido = false;
            }
            if (cmbOpcion1.getValue() == null || cmbOpcion2.getValue() == null) {
                valido = false;
            }

            if (!valido) {
                controlador.mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Validacion de Formulario (RNF-02)",
                        "Revisa las casillas resaltadas: el ID debe tener formato como DE003, el nombre no debe llevar numeros y ambas opciones son obligatorias."
                );
                return;
            }

            String res = controlador.inscribirParticipante(
                    txtId.getText().toUpperCase(),
                    txtNombre.getText(),
                    txtInstitucion.getText(),
                    cmbOpcion1.getValue(),
                    cmbOpcion2.getValue()
            );
            controlador.mostrarAlerta(Alert.AlertType.INFORMATION, "Resultado de Inscripcion", res);
            txtId.clear();
            txtNombre.clear();
            txtInstitucion.clear();
            actualizarDatos();
        });

        cardInscripcion.getChildren().addAll(
                titInsc, txtId, txtNombre, txtInstitucion, cmbOpcion1, cmbOpcion2, btnInscribir
        );

        // Tarjeta 3: Asignacion de pais/comite (RF-03)
        VBox cardAsignar = new VBox(10);
        cardAsignar.getStyleClass().add("card");
        Label titAsig = new Label("Asignar Representacion (Pais / Comite)");
        titAsig.getStyleClass().add("card-title");

        TextField txtIdAsig = new TextField();
        txtIdAsig.setPromptText("ID del delegado (ej. DE001)");
        cmbComiteAsig = new ComboBox<>();
        cmbComiteAsig.setPromptText("Selecciona comite");
        cmbComiteAsig.setMaxWidth(Double.MAX_VALUE);
        TextField txtPais = new TextField();
        txtPais.setPromptText("Pais o representacion (ej. Colombia, Francia)");

        Button btnAsignar = new Button("Confirmar Asignacion");
        btnAsignar.getStyleClass().add("btn-gold");
        btnAsignar.setOnAction(e -> {
            if (txtIdAsig.getText().isBlank() || cmbComiteAsig.getValue() == null || txtPais.getText().isBlank()) {
                controlador.mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos", "Completa ID, comite y pais.");
                return;
            }
            String msg = controlador.asignarRepresentacion(
                    txtIdAsig.getText().toUpperCase(),
                    cmbComiteAsig.getValue(),
                    txtPais.getText()
            );
            Alert.AlertType tipo = msg.startsWith("OK") ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR;
            controlador.mostrarAlerta(tipo, "Asignacion de Delegacion", msg);
        });

        cardAsignar.getChildren().addAll(titAsig, txtIdAsig, cmbComiteAsig, txtPais, btnAsignar);

        contenedor.getChildren().addAll(cardCupos, cardInscripcion, cardAsignar);
        actualizarDatos();
    }

    private void limpiarErrores(TextField... campos) {
        for (TextField f : campos) {
            f.getStyleClass().remove("input-error");
        }
    }

    @Override
    public void actualizarDatos() {
        listaEstadoComites.getChildren().clear();
        cmbOpcion1.getItems().setAll(controlador.getCuposComites().keySet());
        cmbOpcion2.getItems().setAll(controlador.getCuposComites().keySet());
        cmbComiteAsig.getItems().setAll(controlador.getCuposComites().keySet());

        controlador.getCuposComites().forEach((nombre, max) -> {
            int actuales = controlador.getInscritosComites().getOrDefault(nombre, 0);
            double progreso = (double) actuales / max;

            Label lbl = new Label(nombre + " — " + actuales + "/" + max + (actuales >= max ? " (CUPO LLENO)" : ""));
            lbl.setStyle(actuales >= max ? "-fx-text-fill: #DC2626; -fx-font-weight: bold;" : "-fx-font-weight: bold;");

            ProgressBar barra = new ProgressBar(Math.min(1.0, progreso));
            barra.setMaxWidth(Double.MAX_VALUE);

            listaEstadoComites.getChildren().addAll(lbl, barra);
        });
    }
}