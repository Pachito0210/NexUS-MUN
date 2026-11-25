package ui.views;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import ui.controllers.ControladorPrincipal;

public class VistaReportesCierre extends VistaBase {

    private Label lblKpiParticipantes;
    private Label lblKpiKits;
    private Label lblKpiComidas;
    private Label lblEstadoEdicion;

    public VistaReportesCierre(ControladorPrincipal controlador) {
        super(
                controlador,
                "Evaluacion Academica, Reportes y Cierre de Edicion",
                "Calificaciones (RF-07), resumen operativo en tiempo real (RF-09) y cierre formal (RF-10)."
        );
        construirVista();
    }

    @Override
    public void construirVista() {
        // Fila de KPIs en tiempo real (RF-09)
        HBox filaKpis = new HBox(14);
        lblKpiParticipantes = new Label("0");
        lblKpiKits = new Label("0");
        lblKpiComidas = new Label("0");

        filaKpis.getChildren().addAll(
                crearTarjetaKpi(lblKpiParticipantes, "Participantes Inscritos"),
                crearTarjetaKpi(lblKpiKits, "Kits Entregados"),
                crearTarjetaKpi(lblKpiComidas, "Refrigerios / Almuerzos")
        );

        // Tarjeta de Calificacion Academica (RF-07)
        VBox cardNotas = new VBox(10);
        cardNotas.getStyleClass().add("card");
        Label titNotas = new Label("Registro de Calificacion Academica (Escala 1.0 a 5.0)");
        titNotas.getStyleClass().add("card-title");

        TextField txtIdNota = new TextField();
        txtIdNota.setPromptText("ID del delegado (ej. DE001)");
        TextField txtNota = new TextField();
        txtNota.setPromptText("Nota entre 1.0 y 5.0");

        Button btnGuardarNota = new Button("Guardar Calificacion");
        btnGuardarNota.getStyleClass().add("btn-primary");
        btnGuardarNota.setOnAction(e -> {
            try {
                double val = Double.parseDouble(txtNota.getText().trim());
                boolean ok = controlador.calificarDelegado(txtIdNota.getText().trim().toUpperCase(), val);
                if (!ok) {
                    controlador.mostrarAlerta(Alert.AlertType.ERROR, "Nota invalida", "Verifica que el ID exista y la nota este entre 1.0 y 5.0.");
                } else {
                    controlador.mostrarAlerta(Alert.AlertType.INFORMATION, "Nota registrada", "Calificacion guardada en la planilla.");
                    txtIdNota.clear();
                    txtNota.clear();
                }
            } catch (NumberFormatException ex) {
                controlador.mostrarAlerta(Alert.AlertType.WARNING, "Formato numerico (RNF-02)", "Ingresa un numero valido para la calificacion.");
            }
        });

        cardNotas.getChildren().addAll(titNotas, txtIdNota, txtNota, btnGuardarNota);

        // Tarjeta de Cierre Formal de Edicion (RF-10)
        VBox cardCierre = new VBox(10);
        cardCierre.getStyleClass().add("card");
        Label titCierre = new Label("Cierre Formal de la Edicion MUNUS (RF-10)");
        titCierre.getStyleClass().add("card-title");

        lblEstadoEdicion = new Label("Estado actual del evento: ACTIVO");
        lblEstadoEdicion.setStyle("-fx-font-weight: bold; -fx-text-fill: #166534;");

        Button btnCerrar = new Button("Cerrar Edicion y Guardar Base de Datos");
        btnCerrar.getStyleClass().add("btn-danger");
        btnCerrar.setOnAction(e -> {
            controlador.cerrarEdicion();
            actualizarDatos();
            controlador.mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Edicion Finalizada",
                    "El estado del evento se actualizo a FINALIZADO y los datos quedaron listos para persistencia."
            );
        });

        cardCierre.getChildren().addAll(titCierre, lblEstadoEdicion, btnCerrar);
        contenedor.getChildren().addAll(filaKpis, cardNotas, cardCierre);
        actualizarDatos();
    }

    private VBox crearTarjetaKpi(Label lblValor, String descripcion) {
        VBox box = new VBox(4);
        box.getStyleClass().add("card");
        box.setPrefWidth(220);
        lblValor.getStyleClass().add("kpi-number");
        Label lblDesc = new Label(descripcion);
        lblDesc.getStyleClass().add("kpi-label");
        box.getChildren().addAll(lblValor, lblDesc);
        return box;
    }

    @Override
    public void actualizarDatos() {
        lblKpiParticipantes.setText(String.valueOf(controlador.getDelegadosRegistrados().size()));
        lblKpiKits.setText(String.valueOf(controlador.getTotalKitsEntregados()));
        lblKpiComidas.setText(String.valueOf(controlador.getTotalComidasEntregadas()));

        if (controlador.isEdicionCerrada()) {
            lblEstadoEdicion.setText("Estado actual del evento: FINALIZADO");
            lblEstadoEdicion.setStyle("-fx-font-weight: bold; -fx-text-fill: #DC2626;");
        }
    }
}