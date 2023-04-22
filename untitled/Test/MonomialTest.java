import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonomialTest {
    private Monomial monomialIntegerA = new Monomial(2,new ScalarInteger(7));
    private Monomial monomialIntegerB = new Monomial(2,new ScalarInteger(-3));
    private Monomial monomialIntegerC = new Monomial(1,new ScalarInteger(12));
    private Monomial monomialRationalA = new Monomial(2,new ScalarRational(-1,3));
    private Monomial monomialRationalB  = new Monomial(2, new ScalarRational(4,1));

    @Test
    void addIntegers() {
        Assert.assertEquals("expected 4x^2",
                new Monomial(2,new ScalarInteger(4)),
                monomialIntegerA.add(monomialIntegerB));
    }
    @Test
    void addRationals() {
        Assert.assertEquals("expected 11/3x^2",
            new Monomial(2,new ScalarRational(11,3)),
            monomialRationalA.add(monomialRationalB));
    }
    @Test
    void addBoth() {
        Assert.assertEquals("expected 20/3x^2",
                new Monomial(2,new ScalarRational(20,3)),
                monomialIntegerA.add(monomialRationalA));
    }
    @Test
    void addNull() {
        Assert.assertEquals("Null",
                null,
                monomialIntegerA.add(monomialIntegerC));
    }

    @Test
    void mulIntegers() {
        Assert.assertEquals("expected -21x^4",
                new Monomial(4, new ScalarInteger(-21)),
                monomialIntegerA.mul(monomialIntegerB));
    }
    @Test
    void mulRationals() {
        Assert.assertEquals("expected -4/3x^4",
                new Monomial(4, new ScalarRational(-4,3)),
                monomialRationalA.mul(monomialRationalB));
    }
    @Test
    void mulBoth() {
        Assert.assertEquals("expected -7/3x^4",
                new Monomial(4, new ScalarRational(-7,3)),
                monomialIntegerA.mul(monomialRationalA));
    }
    @Test
    void mulDiffCoef() {
        Assert.assertEquals("expected -36x^3",
                new Monomial(3, new ScalarInteger(-36)),
                monomialIntegerB.mul(monomialIntegerC));
    }

    @Test
    void evaluateInteger() {
        Assert.assertEquals("expected 14",
                new ScalarInteger(28),
                monomialIntegerA.evaluate(new ScalarInteger(2)));
    }
    @Test
    void evaluateRational() {
        Assert.assertEquals("expected -4/3",
                new ScalarRational(-4,3),
                monomialRationalA.evaluate(new ScalarInteger(2)));

    }

    @Test
    void derivativeInteger() {
        Assert.assertEquals("expected 14x",
                new Monomial(1,new ScalarInteger(14)),
                monomialIntegerA.derivative());
    }
    @Test
    void derivativeRational() {
        Assert.assertEquals("expected -2/3x",
                new Monomial(1,new ScalarRational(-2,3)),
                monomialRationalA.derivative());
    }

    @Test
    void signInteger() {
        Assert.assertEquals("exepcted 1",
                1,
                monomialIntegerA.sign());
    }
    @Test
    void signRational() {
        Assert.assertEquals("expected -1",
                -1,
                monomialRationalA.sign());
    }

    @Test
    void testEqualsRational() {
        Assert.assertEquals("expected true",
                true,
                monomialRationalB.equals(new Monomial(2,new ScalarRational(8,2))));
    }
    @Test
    void testEqualsBoth() {
        Assert.assertEquals("expected true",
                true,
                monomialRationalB.equals(new Monomial(2,new ScalarInteger(4))));
    }
}