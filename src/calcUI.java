import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Arrays;

public class calcUI extends mainCalculator {
    private static final float button_font_size = 20f;
    private static final String[][] button_labels = {
            {" ", " ", " ", "C"},
            {"7", "8", "9", "+"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "*"},
            {"0", ".", "=", "/"}
    };

    private static final int GAP = 4;
    private JPanel mainPanel = new JPanel(new BorderLayout(GAP, GAP));
    private JPanel buttonPanel = new JPanel();
    private JTextField display = new JTextField();
    private String temp,temp1;
    private double num,num2,result;
    char operator;
    List<String> numbers = Arrays.asList("1","2","3","4","5","6","7","8","9","0");

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
        display.setText("");

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
        public void actionPerformed(ActionEvent ae){

            if (ae.getActionCommand() == "C"){
                display.setText("");
            }

            if (ae.getActionCommand() == "+"){
                //temp = display.getText();
                num = Double.parseDouble(display.getText());
                operator = '+';
                display.setText("");
                //System.out.println(num);
            }
            if (ae.getActionCommand() == "-"){
                num = Double.parseDouble(display.getText());
                operator = '-';
                display.setText("");
            }
            if (ae.getActionCommand() == "*"){
                num = Double.parseDouble(display.getText());
                operator = '*';
                display.setText("");
            }
            if (ae.getActionCommand() == "/"){
                num = Double.parseDouble(display.getText());
                operator = '/';
                display.setText("");
            }
            if (ae.getActionCommand() == "="){
                num2 = Double.parseDouble(display.getText());
                if(operator=='+'){
                    result=num+num2;
                }
                if(operator=='-'){
                    result=num-num2;
                }
                if(operator=='*'){
                    result=num*num2;
                }
                if(operator=='/'){
                    result=num/num2;
                }
                display.setText(String.valueOf(result));
                num = result;
                System.out.println(result);
            }
            if (ae.getActionCommand() == "."){
                display.setText(display.getText().concat("."));
            }

            String btnName = ae.getActionCommand();
            if(numbers.contains(ae.getActionCommand())) {
                display.setText(display.getText().concat(btnName));
            }
        }
    }
}
