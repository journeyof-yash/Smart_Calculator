import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator extends JFrame implements ActionListener {
    private JTextField display;
    private StringBuilder currentInput;
    private double result;
    private String operator;

    public calculator() {
        setTitle("Smart Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "CE", "", ""
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        setLayout(new BorderLayout(10, 10));
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        currentInput = new StringBuilder();
        result = 0;
        operator = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "C":
                currentInput.setLength(0);
                display.setText("");
                break;
            case "CE":
                currentInput.setLength(0);
                display.setText("");
                result = 0;
                operator = "";
                break;
            case "=":
                calculate();
                display.setText(String.valueOf(result));
                currentInput.setLength(0);
                operator = "";
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                calculate();
                operator = command;
                currentInput.setLength(0);
                break;
            default:
                currentInput.append(command);
                display.setText(currentInput.toString());
                break;
        }
    }

    private void calculate() {
        if (currentInput.length() > 0) {
            double number = Double.parseDouble(currentInput.toString());
            switch (operator) {
                case "":
                    result = number;
                    break;
                case "+":
                    result += number;
                    break;
                case "-":
                    result -= number;
                    break;
                case "*":
                    result *= number;
                    break;
                case "/":
                    result /= number;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            calculator calculator = new calculator();
            calculator.setVisible(true);
        });
    }
}
