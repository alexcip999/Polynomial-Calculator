package calculator;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Screen extends JFrame{
    private JPanel mainPanel;
    private JTextField textFieldFirst;
    private JTextField textFieldSecond;
    private JButton integralButton;
    private JButton derivativeButton;
    private JButton divisionButton;
    private JButton multiplicationButton;
    private JButton substractButton;
    private JButton addButton;
    private JLabel resultLabel;
    private JLabel quotientLabel;
    private JLabel reminderLabel;
    private JTextField textFieldDerivativeAndIntegral;
    private JLabel labelDerivativeAndIntegral;

    public Screen(){
        super("Calculator");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 850);

    }
    public  void addButtonListener(ActionListener listener){
        addButton.addActionListener(listener);
    }
    public void subButtonListener(ActionListener listener){
        substractButton.addActionListener(listener);
    }
    public void divButtonListener(ActionListener listener){
        divisionButton.addActionListener(listener);
    }
    public  void mulButtonListener(ActionListener listener){
        multiplicationButton.addActionListener(listener);
    }
    public  void derivativeButtonListener(ActionListener listener){
        derivativeButton.addActionListener(listener);
    }
    public  void integralButtonListener(ActionListener listener){
        integralButton.addActionListener(listener);
    }
    public JLabel getResultLabel() {
        return resultLabel;
    }

    public JLabel getQuotientLabel() {
        return quotientLabel;
    }

    public JLabel getReminderLabel() {
        return reminderLabel;
    }

    public JTextField getTextFieldFirst() {
        return textFieldFirst;
    }
    public JTextField getTextFieldSecond() {
        return textFieldSecond;
    }
    public JTextField getTextFieldDerivativeAndIntegral() {
        return textFieldDerivativeAndIntegral;
    }

}

