import java.io.*;

public class Driver {
    public static void main(String [] args) throws IOException {
    Polynomial p = new Polynomial();
    System.out.println(p.evaluate(3));
    double [] c1 = {6,5};
    int[] e1 = {0, 1};
    Polynomial p1 = new Polynomial(c1,e1);
    double [] c2 = {-2,-9};
    int[] e2 = {0, 1};
    Polynomial p2 = new Polynomial(c2,e2);
    Polynomial s = p1.add(p2);
    Polynomial s2 = p1.multiply(p2);
    s.printPolynomial();
    s2.printPolynomial();
    Polynomial s3 = new Polynomial(new File("a.txt"));
    s3.printPolynomial();
    p2.saveToFile("p2.txt");

    
    

    // System.out.println("s(0.1) = " + s.evaluate(0.1));
    // if(s.hasRoot(1))
    // System.out.println("1 is a root of s");
    // else
    // System.out.println("1 is not a root of s");
    }
}