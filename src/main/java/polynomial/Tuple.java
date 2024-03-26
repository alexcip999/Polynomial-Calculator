package polynomial;

public class Tuple<T, T1> {
    public Polynom quotient;
    public Polynom remainder;
    public Tuple(Polynom quotient, Polynom remainder) {
        this.quotient = quotient;
        this.remainder = remainder;
    }
    public Polynom getQuotient() {
        return quotient;
    }
    public Polynom getRemainder() {
        return remainder;
    }


}
