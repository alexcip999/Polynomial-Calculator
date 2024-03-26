package polynomial;

import java.text.DecimalFormat;
import java.util.*;

public class Operations {

    public static Polynom add(Polynom polynom1, Polynom polynom2){
        Polynom polynom = new Polynom();
        for(Map.Entry<Integer, Double> entry1 : polynom1.getParameters().entrySet()){
            for(Map.Entry<Integer, Double> entry2 : polynom2.getParameters().entrySet()){
                if(entry1.getKey().equals(entry2.getKey())){
                    Double coef = entry1.getValue() + entry2.getValue();
                    Integer power = entry1.getKey();
                    polynom.getParameters().put(power, coef);
                }
            }
        }
        for(Map.Entry<Integer, Double> entry : polynom1.getParameters().entrySet()){
            if(!polynom.getParameters().containsKey(entry.getKey())){
                polynom.getParameters().put(entry.getKey(), entry.getValue());
            }
        }
        for(Map.Entry<Integer, Double> entry : polynom2.getParameters().entrySet()){
            if(!polynom.getParameters().containsKey(entry.getKey())){
                polynom.getParameters().put(entry.getKey(), entry.getValue());
            }
        }

        polynom.getParameters().entrySet().removeIf(entry -> entry.getValue().equals(0.0));



        return polynom;
    }
    public static Polynom sub(Polynom polynom1, Polynom polynom2){
        Polynom polynom = new Polynom();
        for(Map.Entry<Integer, Double> entry1 : polynom1.getParameters().entrySet()){
            for(Map.Entry<Integer, Double> entry2 : polynom2.getParameters().entrySet()){
                if(entry1.getKey().equals(entry2.getKey())){
                    Double coef = entry1.getValue() - entry2.getValue();
                    Integer power = entry1.getKey();
                    polynom.getParameters().put(power, coef);

                }
            }
        }
        for(Map.Entry<Integer, Double> entry : polynom1.getParameters().entrySet()){
            if(!polynom.getParameters().containsKey(entry.getKey())){
                polynom.getParameters().put(entry.getKey(), entry.getValue());
            }
        }
        for(Map.Entry<Integer, Double> entry : polynom2.getParameters().entrySet()){
            if(!polynom.getParameters().containsKey(entry.getKey())){
                polynom.getParameters().put(entry.getKey(), -1 * entry.getValue());
            }
        }

        polynom.getParameters().entrySet().removeIf(entry -> entry.getValue().equals(0.0));


        return polynom;
    }
    public static Tuple div(Polynom polynomDividend, Polynom polynomDispenser){
        Polynom resultQuotient = new Polynom();

        List<Integer> keysPolynomDividend = new ArrayList<>(polynomDividend.getParameters().keySet());
        keysPolynomDividend.sort(Collections.reverseOrder());

        List<Integer> keysPolynomDispenser = new ArrayList<>(polynomDispenser.getParameters().keySet());
        keysPolynomDispenser.sort(Collections.reverseOrder());

        Iterator<Integer> iteratorDevidend = keysPolynomDividend.iterator();
        Iterator<Integer> iteratorDispenser = keysPolynomDispenser.iterator();
        Integer powerDispenser = iteratorDispenser.next();
        while(iteratorDevidend.hasNext()){
            Integer powerDividend = iteratorDevidend.next();

            if(powerDividend >= powerDispenser){
                Integer powerQuotient = powerDividend - powerDispenser;

                Double coefficientDividend = polynomDividend.getParameters().get(powerDividend);
                Double coefficientDispenser = polynomDispenser.getParameters().get(powerDispenser);

                Double coefficientQuotient = coefficientDividend / coefficientDispenser;

                Polynom polynomQuotient = new Polynom();
                polynomQuotient.getParameters().put(powerQuotient, coefficientQuotient);
                resultQuotient.getParameters().put(powerQuotient, coefficientQuotient);

                Polynom polynomAuxiliar = mul(polynomQuotient, polynomDispenser);
                polynomQuotient.getParameters().clear();

                polynomDividend = sub(polynomDividend, polynomAuxiliar);

            }
        }
        return new Tuple(resultQuotient, polynomDividend);
    }
    public static Polynom mul(Polynom polynom1, Polynom polynom2){
        Polynom polynom = new Polynom();
        for(Map.Entry<Integer, Double> entry1 : polynom1.getParameters().entrySet()){
            for(Map.Entry<Integer, Double> entry2 : polynom2.getParameters().entrySet()){
                Double coef = entry1.getValue() * entry2.getValue();
                Integer power = entry1.getKey() + entry2.getKey();
                if(polynom.getParameters().containsKey(power)){
                    polynom.getParameters().replace(power, coef + polynom.getParameters().get(power));
                }else {
                    polynom.getParameters().put(entry1.getKey() + entry2.getKey(), entry1.getValue() * entry2.getValue());
                }
            }
        }
        return polynom;
    }
    public static Polynom derivative(Polynom polynom){
        Polynom polynomDerivative = new Polynom();
        for(Map.Entry<Integer, Double> entry : polynom.getParameters().entrySet()){
            Double coef = entry.getValue() * entry.getKey();
            int power = entry.getKey() - 1;
            if(power >= 0){
                polynomDerivative.getParameters().put(power, coef);
            }
        }
        return polynomDerivative;
    }
    public static Polynom integral(Polynom polynom){
        Polynom polynomIntegral = new Polynom();
        for(Map.Entry<Integer, Double> entry : polynom.getParameters().entrySet()){
            int power = entry.getKey() + 1;
            Double coef = entry.getValue() / power;
            polynomIntegral.getParameters().put(power, coef);
        }
        return polynomIntegral;
    }



    public String toExpression(Polynom polynom) {
        StringBuilder expression = new StringBuilder();
        List<Integer> keys = new ArrayList<>(polynom.getParameters().keySet());
        keys.sort(Collections.reverseOrder());
        for(Integer key : keys){
            DecimalFormat coefficient = new DecimalFormat("#.##");
            String coef = coefficient.format(polynom.getParameters().get(key));
            if(key.equals(0)){
                if(polynom.getParameters().get(key) > 0){
                    expression.append("+").append(coef);
                }else{
                    expression.append(coef);
                }
            }
            if(key >= 1){
                if(polynom.getParameters().get(key) > 0){
                    if(polynom.getParameters().get(key).equals(1.0)){
                        expression.append("+").append("x^").append(key);
                    }else {
                        expression.append("+").append(coef).append("x^").append(key);
                    }
                }
                if(polynom.getParameters().get(key) < 0){
                    if(polynom.getParameters().get(key).equals(-1.0)){
                        expression.append("-x^").append(key);
                    }else{
                        expression.append(coef).append("x^").append(key);
                    }
                }
            }
        }
        if(expression.charAt(0) == '+'){
            expression.deleteCharAt(0);
        }

        return expression.toString();
    }
}

//x^4+x^2-10
//x^3-x^1
//6x^2-3x^1+12
//-7x^2+4x^1-3