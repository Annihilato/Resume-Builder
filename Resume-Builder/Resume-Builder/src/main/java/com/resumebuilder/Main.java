// Placeholder for Main.java

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Start Swing GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new ResumeGUI();
        });
    }
}
