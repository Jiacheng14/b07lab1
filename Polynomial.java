import java.lang.Math;
import java.io.*;
import java.util.*;

public class Polynomial {

    private double[] coefficient;
    private int[] exp;

    public Polynomial() {
        this.coefficient = new double[0];
        this.exp = new int[0];
    }

    public Polynomial(double[] coefficient, int[] exp) {
        this.coefficient = coefficient;
        this.exp = exp;
    }

    public Polynomial(File file) throws FileNotFoundException {
        this.coefficient = new double[0];
        this.exp = new int[0];
        String poly = new String();
        Scanner fileScanner = new Scanner(file);
        poly = fileScanner.nextLine();
        String element = new String();
        for (int i = 0; i < poly.length(); i++) {
            if ((poly.charAt(i) == '-' && i != 0) || poly.charAt(i) == '+' || i == poly.length() - 1) {
                if (i == poly.length() - 1) {
                    element += poly.charAt(i);
                }
                // if (!element.equals("")) {
                    String[] CnE = element.split("x");
                    double[] c = new double[this.coefficient.length + 1];
                    System.arraycopy(this.coefficient, 0, c, 0, this.coefficient.length);
                    if (CnE.length == 0) {
                        c[this.coefficient.length] = 1;
                    } else if (CnE[0].equals("")) {
                        c[this.coefficient.length] = 1;
                    } else if (CnE[0].equals("-")){
                        c[this.coefficient.length] = -1;
                    } else {
                        c[this.coefficient.length] = Double.parseDouble(CnE[0]);
                    }
                    this.coefficient = c;
                    int[] e = new int[this.exp.length + 1];
                    System.arraycopy(this.exp, 0, e, 0, this.exp.length);
                    if (CnE.length >= 2) {
                        e[this.exp.length] = Integer.parseInt(CnE[1]);
                    } else if (element.indexOf("x") != -1){
                        e[this.exp.length] = 1;
                    } else {
                        e[this.exp.length] = 0;
                    }
                    this.exp = e;
                    element = new String();
                
                if (poly.charAt(i) == '-') {
                    element += poly.charAt(i);
                }
            } else {
                element += poly.charAt(i);
            }
        }
        fileScanner.close();
    }

    public Polynomial add(Polynomial other) {
        double[] resultC = this.coefficient.clone();
        int[] resultE = this.exp.clone();
        for (int i = 0; i < other.exp.length; i++) {
            boolean flag = true;
            for (int j = 0; j < this.exp.length; j++) {
                if (other.exp[i] == this.exp[j]) {
                    flag = false;
                    resultC[j] += other.coefficient[i];
                }
            }
            if (flag) {
                double[] c = new double[resultC.length + 1];
                System.arraycopy(resultC, 0, c, 0, resultC.length);
                c[resultC.length] = other.coefficient[i];
                int[] e = new int[resultE.length + 1];
                System.arraycopy(resultE, 0, e, 0, resultE.length);
                e[resultE.length] = other.exp[i];
                resultC = c;
                resultE = e;
            }
        }
        Polynomial result = new Polynomial(resultC, resultE);
        return result;
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < this.coefficient.length; i++) {
            result += this.coefficient[i] * Math.pow(x, this.exp[i]);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }

    public Polynomial multiply(Polynomial other) {
        Polynomial result = new Polynomial();
        for (int i = 0; i < this.coefficient.length; i++) {
            for (int j = 0; j < other.coefficient.length; j++) {
                int[] resultE = new int[1];
                double[] resultC = new double[1];
                resultE[0] = this.exp[i] + other.exp[j];
                resultC[0] = this.coefficient[i] * other.coefficient[j];
                Polynomial resultP = new Polynomial(resultC, resultE);
                result = result.add(resultP);
            }
        }
        return result;
    }

    public void saveToFile(String fileName) throws IOException{
        String result = new String();
        for (int i = 0; i < this.coefficient.length; i++) {
            if (this.coefficient[i] > 0 && i != 0) {
                result += "+";
            }
            if (this.coefficient[i] == 1) {
                result += "";
            } else if (this.coefficient[i] == -1) {
                result += "-";
            } else if (this.coefficient[i] == (int)this.coefficient[i]) {
                result += (int)this.coefficient[i];
            } else {
                result += this.coefficient[i];
            }
            if (this.exp[i] == 0) {
                result += "";
            } else if (this.exp[i] == 1) {
                result += "x";
            } else {
                result += "x";
                result += this.exp[i];
            }
        }
        File file = new File(fileName);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(result);
        writer.close();
    }

    public void printPolynomial() {
        String result = new String();
        for (int i = 0; i < this.coefficient.length; i++) {
            if (this.coefficient[i] > 0 && i != 0) {
                result += "+";
            }
            if (this.coefficient[i] == 1) {
                result += "";
            } else if (this.coefficient[i] == -1) {
                result += "-";
            } else if (this.coefficient[i] == (int)this.coefficient[i]) {
                result += (int)this.coefficient[i];
            } else {
                result += this.coefficient[i];
            }
            if (this.exp[i] == 0) {
                result += "";
            } else if (this.exp[i] == 1) {
                result += "x";
            } else {
                result += "x";
                result += this.exp[i];
            }
        }
        System.out.println(result);
    }
}