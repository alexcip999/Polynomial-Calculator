package polynomial;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynom {
    //public  HashMap<Integer, Integer> parameters = new HashMap<>();
    private HashMap<Integer, Double> parameters = new HashMap<>();

    public HashMap<Integer, Double> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<Integer, Double> parameters) {
        this.parameters = parameters;
    }

    public void putParameters(String expression){
        Pattern termPattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher termMatcher = termPattern.matcher(expression);

        while(termMatcher.find()){
            String term = termMatcher.group();
            processTerm(term);
        }
    }

    public  void processTerm(String term){
        Pattern coefficientPowerPattern = Pattern.compile("([+-]?\\d*)(x(\\^(\\d+)))?|([+-]?)(x)");

        Matcher matcher = coefficientPowerPattern.matcher((term));

        if(matcher.matches()){
            if(matcher.group(5) != null){
                int coefficient = (matcher.group(5).equals("-")) ? -1 : 1;
                int power = 1;
                //parameters.put(power, coefficient);
                parameters.put(power, (double) coefficient);
            }else {
                String coefficientStr = matcher.group(1);
                String powerStr = matcher.group(4);

                coefficientStr = (coefficientStr == null || coefficientStr.isEmpty() || coefficientStr.equals("+")) ? "1" : coefficientStr;

                int coefficient = (coefficientStr.equals("-")) ? -1 : Integer.parseInt((coefficientStr));
                int power = (powerStr != null) ? Integer.parseInt(powerStr) : 0;

                //parameters.put(power, coefficient);
                parameters.put(power, (double) coefficient);
            }
        }
    }
}
