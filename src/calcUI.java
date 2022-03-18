import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calcUI extends mainCalculator {
    private static final float button_font_size = 20f;
    private static final String[][] button_labels = {
            {"7", "8", "9", "+"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "*"},
            {"0", ".", "=", "/"}
    };

    private static final int GAP = 4;
    private JPanel mainPanel = new JPanel(new BorderLayout(GAP, GAP));
    private JPanel buttonPanel = new JPanel();
    private JTextField display = new JTextField();

    public calcUI() {
        int rows = button_labels.length;
        int cols = button_labels[0].length;
        SimpleListener ourlistener = new SimpleListener();

        buttonPanel.setLayout(new GridLayout(rows, cols, GAP, GAP));
        for (String[] btnLabelRow : button_labels) {
            for (String btnLabel : btnLabelRow) {
                if(btnLabel.trim().isEmpty()) {
                    buttonPanel.add(new JLabel());
                } else {
                    JButton btn = createButton(btnLabel);
                    btn.setFocusable(false);
                    btn.setBackground(Color.lightGray);
                    buttonPanel.add(btn);
                    btn.addActionListener(ourlistener);
                }
            }
        }
        display.setFont(display.getFont().deriveFont(button_font_size));
        display.setEditable(false);
        display.setFocusable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.ORANGE);
        display.setText("0");

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

    private class SimpleListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            String btnName = ae.getActionCommand();
            display.setText(btnName);

            if (ae.getActionCommand() == "+"){
                System.out.println("tester: +");
                display.setText("");
            }
            if (ae.getActionCommand() == "-"){
                System.out.println("tester: -");
            }
            if (ae.getActionCommand() == "*"){
                System.out.println("tester: *");
            }
            if (ae.getActionCommand() == "/"){
                System.out.println("tester: /");
            }
            if (ae.getActionCommand() == "="){
                System.out.println("tester: =");
            }
        }
    }
}
