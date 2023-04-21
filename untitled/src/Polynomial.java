import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class Polynomial {
     private LinkedList<Monomial> monomials;

    public Polynomial() {
        monomials = new LinkedList<>();
    }

    public static Polynomial build(String input)
    {
        Polynomial ans = new Polynomial();
        String [] splited = input.split("[ ]+");
        for (int i = 0; i < splited.length; i++) {
            if (splited[i].indexOf('/') == -1)
            {
                Monomial a = new Monomial(i,new ScalarInteger(Integer.parseInt(splited[i])));
                ans.getMonomials().addLast(a);
            }
            else {
                String [] splited2 = splited[i].split("/");
                Monomial a = new Monomial(i,
                        new ScalarRational(Integer.parseInt(splited2[0]),Integer.parseInt(splited2[1])));
                ans.getMonomials().addLast(a);
            }
        }
        return ans;
    }
    public Polynomial add(Polynomial p)
    {
        Polynomial ans = new Polynomial();
        Iterator<Monomial> thisIt = this.monomials.iterator();
        Iterator<Monomial> pIt = p.monomials.iterator();
        while(thisIt.hasNext() && pIt.hasNext()){
            Monomial a = thisIt.next();
            Monomial b = pIt.next();
            ans.getMonomials().addLast(a.add(b));
        }
        while (thisIt.hasNext())
            ans.getMonomials().addLast( thisIt.next());
        while (pIt.hasNext())
            ans.getMonomials().addLast( pIt.next() );
        return  ans;
    }
    public Polynomial mul(Polynomial p)
    {
        int zeros = 0;
        Polynomial ans = new Polynomial();
        Iterator<Monomial> thisIt = this.monomials.iterator();
        while (thisIt.hasNext())
        {
            Iterator<Monomial> pIt = p.monomials.iterator();
            Polynomial holder = new Polynomial();
            Monomial a = thisIt.next();
            for (int i = 0; i < zeros; i++) {
                holder.monomials.addLast(new Monomial(i,new ScalarInteger(0)));
            }
            while (pIt.hasNext())
            {
                Monomial b =  pIt.next();
                holder.monomials.addLast(a.mul(b));
            }
            ans = ans.add(holder);
            zeros++;
        }
        return  ans;

    }
    public Scalar evaluate(Scalar s)
    {
        Scalar ans = new ScalarInteger(0);
        for (Monomial monomial: monomials)
            ans = ans.add(monomial.evaluate(s));
        return ans;
    }
    public Polynomial derivative()
    {
        Polynomial ans = new Polynomial();
        Iterator<Monomial> thisIt = this.monomials.iterator();
        if (thisIt.hasNext()){
           thisIt.next();
        }
        while (thisIt.hasNext()){
            ans.monomials.addLast((thisIt.next()).derivative());
        }
        return ans;
    }

    @Override
    public String toString() {
        String ans = "";
        for(Monomial m : monomials)
        {
            if (m.sign() != 0)
                ans = ans + m.toString() + " + ";
        }
        return  ans.substring(0,ans.length()-1).replace("+ -","- ");
    }
    public LinkedList<Monomial> getMonomials() {
        return monomials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynomial that = (Polynomial) o;
        return monomials.equals(that.monomials);
    }
}
