import java.lang.Math;

public class Polynomial {

    private double[] coefficient;

    public Polynomial() {
        coefficient = new double[1];
        coefficient[0] = 0;
    }

    public Polynomial(double[] coefficient) {
        this.coefficient = coefficient;
    }

    public Polynomial add(Polynomial other) {
        int len = this.coefficient.length > other.coefficient.length ? this.coefficient.length : other.coefficient.length;
        double[] resultC = new double[len];
        for (int i = 0; i < len; i++) {
            resultC[i] = 0;
        }
        for (int i = 0; i < this.coefficient.length; i++) {
            resultC[i] += this.coefficient[i];
        }
        for (int i = 0; i < other.coefficient.length; i++) {
            resultC[i] += other.coefficient[i];
        }
        Polynomial result = new Polynomial(resultC);
        return result;
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < this.coefficient.length; i++) {
            result += this.coefficient[i] * Math.pow(x, i);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }
}