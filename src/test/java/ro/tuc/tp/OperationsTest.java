package ro.tuc.tp;


import org.junit.jupiter.api.Test;
import polynomial.Operations;
import polynomial.Polynom;
import polynomial.Tuple;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationsTest {
    @Test
    public void addTest(){
        Polynom polynom1 = new Polynom();
        Polynom polynom2 = new Polynom();
        Polynom resultPolynom = new Polynom();
        polynom1.putParameters("4x^5-3x^4+x^2-8x^1+1");
        polynom2.putParameters("3x^4-x^3+x^2+2x^1-1");
        resultPolynom.putParameters("4x^5-x^3+2x^2-6x^1");
        assertEquals(Operations.add(polynom1, polynom2).getParameters(), resultPolynom.getParameters());
    }

    @Test
    public void subTest(){
        Polynom polynom1 = new Polynom();
        Polynom polynom2 = new Polynom();
        Polynom resultPolynom = new Polynom();
        polynom1.putParameters("4x^5-3x^4+x^2-8x^1+1");
        polynom2.putParameters("3x^4-x^3+x^2+2x^1-1");
        resultPolynom.putParameters("4x^5-6x^4+x^3-10x^1+2");
        assertEquals(Operations.sub(polynom1, polynom2).getParameters(), resultPolynom.getParameters());
    }

    @Test
    public void mulTest(){
        Polynom polynom1 = new Polynom();
        Polynom polynom2 = new Polynom();
        Polynom resultPolynom = new Polynom();
        polynom1.putParameters("3x^2-x^1+1");
        polynom2.putParameters("x^1-2");
        resultPolynom.putParameters("3x^3-7x^2+3x^1-2");
        assertEquals(Operations.mul(polynom1, polynom2).getParameters(), resultPolynom.getParameters());
    }

    @Test
    public void divTest(){
        Polynom polynom1 = new Polynom();
        Polynom polynom2 = new Polynom();
        Polynom resultPolynomQuotient = new Polynom();
        Polynom resultPolynomRemainder = new Polynom();
        polynom1.putParameters("x^3-2x^2+6x^1-5");
        polynom2.putParameters("x^2-1");
        resultPolynomQuotient.putParameters("x^1-2");
        resultPolynomRemainder.putParameters("7x^1-7");
        Tuple tuple = Operations.div(polynom1, polynom2);
        assertEquals(tuple.quotient.getParameters(), resultPolynomQuotient.getParameters());
        assertEquals(tuple.remainder.getParameters(), resultPolynomRemainder.getParameters());
    }

    @Test
    public void derivativeTest(){
        Polynom polynom = new Polynom();
        Polynom resultPolynom = new Polynom();
        polynom.putParameters("x^3-2x^2+6x^1-5");
        resultPolynom.putParameters("3x^2-4x^1+6");
        assertEquals(Operations.derivative(polynom).getParameters(), resultPolynom.getParameters());
    }

    @Test
    public void integralTest(){
        Polynom polynom = new Polynom();
        Polynom resultPolynom = new Polynom();
        polynom.putParameters("x^3+4x^2+5");
        resultPolynom.getParameters().put(4, 1.0 / 4);
        resultPolynom.getParameters().put(3, 4.0 / 3);
        resultPolynom.getParameters().put(1, 5.0);
        assertEquals(Operations.integral(polynom).getParameters(), resultPolynom.getParameters());
    }

}
