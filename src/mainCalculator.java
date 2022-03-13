import javax.swing.*;

public class mainCalculator {
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                calcUI.createAndShowGui();
            }
        });
    }
}
