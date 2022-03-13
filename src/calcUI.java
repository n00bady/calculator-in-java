import javax.swing.*;
import java.awt.*;

public class calcUI extends mainCalculator {
    private static final float button_font_size = 20f;
    private static final String[][] button_labels = {
            {"7", "8", "9", " "},
            {"4", "5", "6", " "},
            {"1", "2", "3", " "},
            {"0", ".", "=", "+"}
    };

    private static final int GAP = 4;
    private JPanel mainPanel = new JPanel(new BorderLayout(GAP, GAP));
    private JPanel buttonPanel = new JPanel();
    private JTextField display = new JTextField();

    public calcUI() {
        int rows = button_labels.length;
        int cols = button_labels[0].length;

        buttonPanel.setLayout(new GridLayout(rows, cols, GAP, GAP));
        for (String[] btnLabelRow : button_labels) {
            for (String btnLabel : btnLabelRow) {
                if(btnLabel.trim().isEmpty()) {
                    buttonPanel.add(new JLabel());
                } else {
                    JButton btn = createButton(btnLabel);
                    buttonPanel.add(btn);
                }
            }
        }
        display.setFont(display.getFont().deriveFont(button_font_size));
        display.setEditable(false);
        display.setFocusable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.ORANGE);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(display, BorderLayout.PAGE_START);
    }

    private JButton createButton(String btnLabel) {
        JButton button = new JButton(btnLabel);
        button.setFont(button.getFont().deriveFont(button_font_size));
        return button;
    }

    public JComponent getMainComponent() {
        return mainPanel;
    }

    public static void createAndShowGui() {
        calcUI mainPanel = new calcUI();

        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel.getMainComponent());
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
