import ui.MenuPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

// ══════════════════════════════════════════════════════════════
//  CLASE · Main
// ══════════════════════════════════════════════════════════════
//  Punto de entrada del sistema NexUS MUN.
// ══════════════════════════════════════════════════════════════
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si el estilo del sistema no está disponible, utiliza el estilo predeterminado de Swing.
        }
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal ventana = new MenuPrincipal();
            ventana.setVisible(true);
        });
    }
}
