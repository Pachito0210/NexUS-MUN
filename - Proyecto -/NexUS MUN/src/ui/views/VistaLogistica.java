package ui.views;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import ui.controllers.ControladorPrincipal;

public class VistaLogistica extends VistaBase {

    private VBox listaEmergencias;

    public VistaLogistica(ControladorPrincipal controlador) {
        super(
                controlador,
                "Control Logistico y Operaciones en Tiempo Real",
                "Entrega de kits (RF-04), refrigerios/almuerzos (RF-06) y cola de emergencias en sala (RF-08)."
        );
        construirVista();
    }

    @Override
    public void construirVista() {
        // Tarjeta 1: Validacion de entregas por ID
        VBox cardEntregas = new VBox(10);
        cardEntregas.getStyleClass().add("card");
        Label titEnt = new Label("Registro Rapido de Entregas por ID");
        titEnt.getStyleClass().add("card-title");

        TextField txtIdParticipante = new TextField();
        txtIdParticipante.setPromptText("Ingresa ID (prueba con DE001, DE002 o SP001)");

        ComboBox<String> cmbJornada = new ComboBox<>();
        cmbJornada.getItems().addAll("Refrigerio Manana", "Almuerzo Dia 1", "Refrigerio Tarde");
        cmbJornada.getSelectionModel().selectFirst();

        HBox botonesEntrega = new HBox(10);
        Button btnKit = new Button("Entregar Kit (RF-04)");
        btnKit.getStyleClass().add("btn-primary");

        Button btnComida = new Button("Entregar Refrigerio/Almuerzo (RF-06)");
        btnComida.getStyleClass().add("btn-gold");

        btnKit.setOnAction(e -> {
            String id = txtIdParticipante.getText().trim().toUpperCase();
            String res = controlador.entregarKit(id);
            Alert.AlertType tipo = res.startsWith("OK") ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING;
            controlador.mostrarAlerta(tipo, "Control de Kits", res);
        });

        btnComida.setOnAction(e -> {
            String id = txtIdParticipante.getText().trim().toUpperCase();
            String res = controlador.entregarComida(id, cmbJornada.getValue());
            Alert.AlertType tipo = res.startsWith("OK") ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING;
            controlador.mostrarAlerta(tipo, "Control de Alimentacion", res);
        });

        botonesEntrega.getChildren().addAll(btnKit, btnComida);
        cardEntregas.getChildren().addAll(titEnt, txtIdParticipante, cmbJornada, botonesEntrega);

        // Tarjeta 2: Solicitudes logisticas de emergencia (RF-08)
        VBox cardEmergencia = new VBox(10);
        cardEmergencia.getStyleClass().add("card");
        Label titEm = new Label("Nueva Solicitud Logistica de Emergencia (RF-08)");
        titEm.getStyleClass().add("card-title");

        TextField txtSalon = new TextField();
        txtSalon.setPromptText("Salon o ubicacion (ej. B-104)");
        TextField txtDetalle = new TextField();
        txtDetalle.setPromptText("Tipo de solicitud (ej. Falla de proyector, hojas de trabajo)");

        ComboBox<String> cmbUrgencia = new ComboBox<>();
        cmbUrgencia.getItems().addAll("Alta", "Media", "Baja");
        cmbUrgencia.getSelectionModel().selectFirst();

        Button btnEnviarEm = new Button("Registrar en Cola de Pendientes");
        btnEnviarEm.getStyleClass().add("btn-danger");
        btnEnviarEm.setOnAction(e -> {
            if (txtSalon.getText().isBlank() || txtDetalle.getText().isBlank()) {
                controlador.mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", "Indica el salon y el detalle.");
                return;
            }
            controlador.registrarEmergencia(txtSalon.getText(), txtDetalle.getText(), cmbUrgencia.getValue());
            txtSalon.clear();
            txtDetalle.clear();
            actualizarDatos();
        });

        listaEmergencias = new VBox(6);
        cardEmergencia.getChildren().addAll(
                titEm, txtSalon, txtDetalle, cmbUrgencia, btnEnviarEm, new Separator(),
                new Label("Cola de Pendientes Visible para Secretaria de Logistica:"), listaEmergencias
        );

        contenedor.getChildren().addAll(cardEntregas, cardEmergencia);
        actualizarDatos();
    }

    @Override
    public void actualizarDatos() {
        listaEmergencias.getChildren().clear();
        for (String item : controlador.getColaEmergencias()) {
            Label badge = new Label(item);
            if (item.startsWith("[ALTA]")) badge.getStyleClass().add("badge-alta");
            else if (item.startsWith("[MEDIA]")) badge.getStyleClass().add("badge-media");
            else badge.getStyleClass().add("badge-baja");
            listaEmergencias.getChildren().add(badge);
        }
    }
}