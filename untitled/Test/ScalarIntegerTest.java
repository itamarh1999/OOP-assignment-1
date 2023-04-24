import org.junit.Assert;
import org.junit.Test;

public class ScalarIntegerTest {
    ScalarInteger five = new ScalarInteger(5);
    ScalarInteger minusEight = new ScalarInteger(-8);
    ScalarInteger zero = new ScalarInteger(0);
    ScalarRational quarter = new ScalarRational(1,4);
    ScalarRational minustwoThirds = new ScalarRational(-2,3);

    @org.junit.Test
    public void addInteger() {
        Assert.assertEquals("expected: 5",new ScalarInteger(5),five.add(zero));
        Assert.assertEquals("expected : -3",new ScalarInteger(-3),five.add(minusEight));

    }
    @Test
    public void addRational()
    {
        Assert.assertEquals("expected : 21/4",
                new ScalarRational(21,4),five.add(quarter));
        Assert.assertEquals("expected : -2/3",
                new ScalarRational(-2,3),zero.add(minustwoThirds));

    }

    @org.junit.Test
    public void mulzero() {
        Assert.assertEquals("expected 0",zero,five.mul(zero));
    }
    @Test
    public void mulInteger() {
        Assert.assertEquals("expected 25",new ScalarInteger(25),five.mul(five));
        Assert.assertEquals("expected -40",new ScalarInteger(-40),five.mul(minusEight));
    }
    @Test
    public void mulRational() {
        Assert.assertEquals("expected -2",
                new ScalarInteger(-2),minusEight.mul(quarter));
        Assert.assertEquals("expected -10/3",
                new ScalarRational(-10,3),five.mul(minustwoThirds));
    }

    @org.junit.Test
    public void negTest() {
        Assert.assertEquals("expected 8",
                new ScalarInteger(8),minusEight.neg());
        Assert.assertEquals("expected -5",
                new ScalarInteger(-5),five.neg());
    }

    @org.junit.Test
    public void powerTest() {
        Assert.assertEquals("expected 125",
                new ScalarInteger(125),five.power(3));
    }

    @org.junit.Test
    public void signTest() {
        Assert.assertEquals("expected -1",
                -1,minusEight.sign());
        Assert.assertEquals("expected 1",
                1,five.sign());
        Assert.assertEquals("expected 0",
                0,zero.sign());
    }

    @org.junit.Test
    public void testToString() {
            Assert.assertEquals("expected -8", "-8",minusEight.toString());
            Assert.assertEquals("expected 5", "5",five.toString());
    }
    @org.junit.Test
    public void testEquals() {
        Assert.assertEquals("expected False"
                , false,minusEight.equals(five));
        Assert.assertEquals("expected true",
                true,five.equals(new ScalarInteger(5)));
        Assert.assertEquals("expected False"
                , false,minusEight.equals(minustwoThirds));
        Assert.assertEquals("expected true"
                , true,five.equals(new ScalarRational(25,5)));
    }
}