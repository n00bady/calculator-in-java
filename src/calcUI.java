import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Arrays;

public class calcUI extends mainCalculator implements KeyListener {
    private static final float button_font_size = 20f;
    private static final String[][] button_labels = {
            {"%", "AC", "C", "<-"},
            {"x^2", "log", "sqrt", "/"},
            {"7", "8", "9", "*"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "+"},
            {"+/-", "0", ".", "="}
    };

    private static final int GAP = 4;
    private JPanel mainPanel = new JPanel(new BorderLayout(GAP, GAP));
    private JPanel buttonPanel = new JPanel();
    private JTextField display2 = new JTextField();
    private JTextField display = new JTextField();
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
        frame.addKeyListener(mainPanel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        String keystr = Character.toString(key);

        // 8 == backspace
        if (key == 8) {
            if (!display.getText().isEmpty()) {
                display.setText(display.getText().substring(0, display.getText().length() - 1));
            } else {
                System.err.println("display.getText() is empty! Nothing to delete back to.");
            }
        }

        // Store 1st number and operation
        switch (key){
            case '+':
                num = Double.parseDouble(display.getText());
                operator = '+';
                display.setText("");
                break;
            case '-':
                num = Double.parseDouble(display.getText());
                operator = '-';
                display.setText("");
                break;
            case '*':
                num = Double.parseDouble(display.getText());
                operator = '*';
                display.setText("");
                break;
            case '/':
                num = Double.parseDouble(display.getText());
                operator = '/';
                display.setText("");
                break;
            default:
                System.err.println("\uD83E\uDD14");
                break;
        }

        // Do the calculation with the 2nd number given
        // 13 = CR but keycodes doesn't work for this but works for backspace ???
        // the newline char will suffice for now...
        if (key == '\n') {
            switch (operator) {
                case '+':
                    num2 = Double.parseDouble(display.getText());
                    result = num + num2;
                    break;
                case '-':
                    num2 = Double.parseDouble(display.getText());
                    result = num - num2;
                    break;
                case '*':
                    num2 = Double.parseDouble(display.getText());
                    result = num * num2;
                    break;
                case '/':
                    num2 = Double.parseDouble(display.getText());
                    result = num / num2;
                    break;
                default:
                    System.err.println("Something gone wrong!");
                    break;
            }
            // Display the result and then put the result in the 1st num variable
            display.setText(String.valueOf(result));
            num = result;
        }

        if (numbers.contains(keystr) || keystr.equals(".")) {
            display.setText(display.getText().concat(keystr));
        }
    }

    // IDE thinks I need those even when they are empty so I left them here... ¯\_(ツ)_/¯
    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    // most if could be replaced with simple switch
    // some exception & error handling needed
    private class SimpleListener implements ActionListener {
        public void actionPerformed(ActionEvent ae){

            if (ae.getActionCommand() == "+/-"){
                double neg = Double.parseDouble(display.getText().toString());
                neg *= -1;
                display.setText(String.valueOf(neg));
            }

            if (ae.getActionCommand() == "<-") {
                String text = display.getText();
                if(text.isEmpty()){
                    //display.setText("0");
                } else {
                    display.setText(text.substring(0, text.length() - 1));
                }
            }

            if (ae.getActionCommand() == "x^2"){
                double num = Double.parseDouble(display.getText().toString());
                num = num*num;
                display.setText(String.valueOf(num));
            }

            if (ae.getActionCommand() == "sqrt"){
                double num = Double.parseDouble(display.getText().toString());
                num = Math.sqrt(num);
                display.setText(String.valueOf(num));
            }

            if (ae.getActionCommand() == "AC"){
                num = 0;
                num2 = 0;
                result = 0;
                operator = ' ';
                display.setText("");
            }
            if (ae.getActionCommand() == "C"){
                display.setText("");
            }

            if (ae.getActionCommand() == "+"){
                num = Double.parseDouble(display.getText());
                operator = '+';
                display.setText("");
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
