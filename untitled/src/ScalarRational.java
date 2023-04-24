import java.util.Objects;

public class ScalarRational implements Scalar {
    private int numerator;
    private int denominator;

    public ScalarRational(int numerator, int denominator)
    {
        this.denominator = denominator;
        this.numerator = numerator;
    }

    @Override
    public Scalar add(Scalar s) {
        return s.add(this);
    }

    @Override
    public Scalar add(ScalarInteger s) {
        int newNumerator = this.denominator *  s.getNumber() + numerator;
        return new ScalarRational(newNumerator, this.denominator).reduce();
    }

    @Override
    public Scalar add(ScalarRational s) {
        int newNumerator = (this.numerator *  s.getDenominator())
                + (this.denominator *s.getNumerator());
        int newDenominator = this.denominator * s.getDenominator();
        return (new ScalarRational(newNumerator,newDenominator).reduce());
    }

    @Override
    public Scalar mul(Scalar s) {
       return s.mul(this);
    }

    @Override
    public Scalar mul(ScalarInteger s) {
        int newNumerator = this.numerator * s.getNumber();
        return new ScalarRational(newNumerator, this.denominator).reduce();
    }

    @Override
    public Scalar mul(ScalarRational s) {
        int newNumerator = this.numerator * s.getNumerator();
        int newDenominator = this.denominator * s.getDenominator();
        return new ScalarRational(newNumerator,newDenominator).reduce();
    }

    @Override
    public Scalar neg() {
        return new ScalarRational(-numerator,denominator).reduce();
    }

    @Override
    public Scalar power(int exponent) {
        if (exponent == 0)
            return new ScalarInteger(1);
        int newNumerator = this.numerator;
        int newDenominator = this.denominator;
        for (int i = 1; i < exponent; i++) {
            newNumerator = newNumerator * this.numerator;
            newDenominator = newDenominator * this.denominator;
        }
        return new ScalarRational(newNumerator,newDenominator);
    }

    @Override
    public int sign() {
        if (((this.numerator > 0 && this.denominator > 0) || (this.numerator < 0 && this.denominator < 0)))
            return 1;
        else if (((this.numerator > 0 && this.denominator < 0) || (this.numerator < 0 && this.denominator > 0)))
            return -1;
        else if (this.numerator ==  0)
            return 0;
        return 0;
    }

    public ScalarRational reduce(){
        int newNumerator = this.numerator;
        int newDenominator = this.denominator;
        for (int i = 2; i <= Math.abs(newNumerator) && i <= Math.abs(newDenominator); i++) {
                while ((newNumerator % i == 0) && (newDenominator % i == 0)) {
                    newNumerator = newNumerator / i;
                    newDenominator = newDenominator / i;
                }
         }
        if ((newDenominator<0 && newNumerator>0) || (newDenominator<0 && newNumerator<0)){
            newDenominator *= -1;
            newNumerator *= -1;
        }

        return new ScalarRational(newNumerator,newDenominator);
    }

    @Override
    public String toString() {
        reduce();
        int outcome = this.numerator % this.denominator;
        if (outcome == 0 )
            return (this.numerator/this.denominator) + "";
        else {
            if ((this.denominator < 0 && this.numerator > 0) || (this.denominator < 0 && this.numerator < 0))
                return this.numerator * -1 + "/" + this.denominator * -1;
            else
                return this.numerator + "/" + this.denominator;
        }
    }


    public int getNumerator() {
        return numerator;
    }


    public int getDenominator() {
        return denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ScalarRational) {
            ScalarRational that = (ScalarRational) o;
            ScalarRational thisRed = this.reduce();
            ScalarRational thatRed = that.reduce();
            return thatRed.numerator == thisRed.numerator && thatRed.denominator == thisRed.denominator;
        }
        else if (o instanceof ScalarInteger){
            ScalarRational thisRed = this.reduce();
            ScalarInteger that = (ScalarInteger) o;
            if (thisRed.numerator % thisRed.denominator == 0)
                return ((thisRed.numerator / thisRed.denominator) == that.getNumber());
            return false;
        }
        return false;
    }

}
