import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScalarRationalTest {
    ScalarInteger five = new ScalarInteger(5);
    ScalarInteger minusEight = new ScalarInteger(-8);
    ScalarInteger zero = new ScalarInteger(0);
    ScalarRational quarter = new ScalarRational(1,4);
    ScalarRational minustwoThirds = new ScalarRational(-2,3);
    ScalarRational minusOneAndHalf = new ScalarRational(3,-2);
    @Test
    public void addIntegerTest() {
        Assert.assertEquals("expected 1/4",quarter,quarter.add(zero));
        Assert.assertEquals("expected -33/4",new ScalarRational(-31,4)
                ,quarter.add(minusEight));
    }

    @Test
    public void addRationalTest() {
        Assert.assertEquals("expected 2/4",new ScalarRational(2,4),
                quarter.add(quarter));
        Assert.assertEquals("expected -13/6",new ScalarRational(-13,6),
                minustwoThirds.add(minusOneAndHalf));
    }

    @Test
    public void mulIntegerTest() {
        Assert.assertEquals("expected 0",zero,quarter.mul(zero));
        Assert.assertEquals("expected -2",new ScalarInteger(-2)
                ,quarter.mul(minusEight));
    }

    @Test
    public void MulRationalTest() {
        Assert.assertEquals("expected 1/16",new ScalarRational(1,16),
                quarter.mul(quarter));
        Assert.assertEquals("expected 0",new ScalarInteger(1)
                ,minusOneAndHalf.mul(minustwoThirds));

    }
    @Test
    public void negTest() {
        Assert.assertEquals("expected -1/4",new ScalarRational(-1,4)
                ,quarter.neg());
        Assert.assertEquals("expected 2/3",new ScalarRational(3,2),
                minusOneAndHalf.neg());

    }
    @Test
    public void powerTest() {
        Assert.assertEquals("expected 1/64",new ScalarRational(1,64),
                quarter.power(3));
        Assert.assertEquals("expected 2/3",new ScalarRational(2,3),
                minustwoThirds.neg());

    }
    @Test
    public void signTest() {
        Assert.assertEquals("expected -1",
                -1,minustwoThirds.sign());
        Assert.assertEquals("expected 1",
                1,quarter.sign());
        Assert.assertEquals("expected 0",
                0,new ScalarRational(0,2).sign());
    }

    @Test
    public void reduce() {
        Assert.assertEquals("expected 1/4",quarter,
                new ScalarRational(20,80));
        Assert.assertEquals("expected -2/3",new ScalarRational(12,-18).reduce(),
                minustwoThirds);
    }
    @Test
    public  void toStringTest(){
        Assert.assertEquals("expected 1/64","1/64",
                quarter.power(3).toString());
        Assert.assertEquals("expected 2/3","2/3",
                minustwoThirds.neg().toString());
    }
}