package calculator;

import polynomial.Operations;
import polynomial.Polynom;
import polynomial.Tuple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerButton implements ActionListener {
    private final Screen screen;

    public ControllerButton(Screen screen){
        this.screen = screen;
        this.screen.addButtonListener(this);
        this.screen.subButtonListener(this);
        this.screen.mulButtonListener(this);
        this.screen.divButtonListener(this);
        this.screen.derivativeButtonListener(this);
        this.screen.integralButtonListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Operations operations = new Operations();
        Polynom polynom;
        Polynom polynomFirst = new Polynom();
        polynomFirst.putParameters(screen.getTextFieldFirst().getText());
        Polynom polynomSecond = new Polynom();
        polynomSecond.putParameters(screen.getTextFieldSecond().getText());
        Polynom polynomDerivativeAndIntegral = new Polynom();
        polynomDerivativeAndIntegral.putParameters(screen.getTextFieldDerivativeAndIntegral().getText());
        switch (e.getActionCommand()){
            case "Add" :
                polynom = operations.add(polynomFirst, polynomSecond);
                screen.getResultLabel().setText(operations.toExpression(polynom));
                break;
            case "Substract" :
                polynom = operations.sub(polynomFirst, polynomSecond);
                screen.getResultLabel().setText(operations.toExpression(polynom));
                break;
            case "Multiplication" :
                polynom = operations.mul(polynomFirst, polynomSecond);
                screen.getResultLabel().setText(operations.toExpression(polynom));
                break;
            case "Division" :
                Tuple tuple = operations.div(polynomFirst, polynomSecond);
                screen.getQuotientLabel().setText(operations.toExpression(tuple.quotient));
                screen.getReminderLabel().setText(operations.toExpression(tuple.remainder));
                break;
            case "Derivative" :
                polynom = operations.derivative(polynomDerivativeAndIntegral);
                screen.getResultLabel().setText(operations.toExpression(polynom));
                break;

            case "Integral" :
                polynom = operations.integral(polynomDerivativeAndIntegral);
                screen.getResultLabel().setText(operations.toExpression(polynom));
                break;
            default : break;
        }
    }
}
