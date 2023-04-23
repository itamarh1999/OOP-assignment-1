import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PolynomialTest {
    private Polynomial polynomialA = Polynomial.build("1 -2 3");
    private Polynomial polynomialB = Polynomial.build("0 1 2 3");
    private Polynomial polynomialC = Polynomial.build("3 2/3");
    private Polynomial polynomialD = Polynomial.build("-4 3");


    @Test
    public void build() {
        Assert.assertEquals("expected 1 -2x +3x^2",
                Polynomial.build("1 -2 3"),
                polynomialA);
    }

    @Test
    public void add1() {
        Assert.assertEquals("expected 1 - x + 5x^2 + 3x^3",
                Polynomial.build("1 -1 5 3"),
                polynomialA.add(polynomialB));
    }
    public void add2() {
        Assert.assertEquals("expected 4 - 4/3x + 3",
                Polynomial.build("4 -4/3 3"),
                polynomialA.add(polynomialC));
    }

    @Test
    public void mul() {
        Assert.assertEquals("- 12 + 19/3 + 2",
                Polynomial.build("-12 19/3 2/1"),
                polynomialC.mul(polynomialD));
    }

    @Test
    public void evaluateInteger() {
        Assert.assertEquals("expected 9",
                new ScalarInteger(9),
                polynomialA.evaluate(new ScalarInteger(2)));
    }
    @Test
    public void evaluateRational() {
        Assert.assertEquals("expected 11/8",
                new ScalarRational(3,4),
                polynomialA.evaluate(new ScalarRational(1,2)));

    }

    @Test
    public void derivative() {
        Assert.assertEquals("expected 1 + 4 + 9",
                Polynomial.build("1 4 9"),
                polynomialB.derivative());
    }

    @Test
    public void testEquals() {
        Assert.assertEquals("expected true",
                true,
                polynomialA.equals(Polynomial.build("1 -4/2 3")));
    }
}