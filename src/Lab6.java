// Java 8
import java.text.DecimalFormat;

public class Lab6 {
    static final int RECTANGLE = 1;
    static final int TRAPEZE = 2;
    static final int SIMPSON = 3;

    public static final double REAL_ANSWER = 0.0999950;

    double round(double x, double delta) {
        if (delta <= 1E-9) {
            System.out.println("Wrong value of delta\n");
            return 0;
        }
        if (x > 0.0) {
            return (delta * (long) ((x / delta) + 0.5));
        } else {
            return (delta * (long) ((x / delta) - 0.5));
        }
    }

    double func(double x, double delta) {
        double s;
        s = Math.cos(Math.pow(x, 3));
        s = round(s, delta);
        return s;
    }

    double rectangle(double a, double b, int n, double delta) {
        double result = 0;
        int i;
        double h = (b - a) / n;
        double x = a;
        for (i = 0; i < n; i++) {
            result += func(x + h / 2, delta);
            x += h;
        }
        result *= h;
        System.out.println(" h: " + h);
        return result;
    }

    double trapeze(double a, double b, int n, double delta) {
        double result = 0;
        int i;
        double h = (b - a) / n;
        double x = a;
        for (i = 0; i < n; i++) {
            result += func(x, delta) + func(x + h, delta);
            x += h;
        }
        result *= h / 2;
        System.out.println(" h: " + h);
        return result;
    }

    double simpson(double a, double b, int m, double delta) {
        double result = 0;
        int i;
        double h = (b - a) / (2.0 * m);
        double x = a;
        for (i = 0; i < m; i++) {
            result += func(x, delta) + 4 * func(x + h, delta) + func(x + 2 * h, delta);
            x += 2 * h;
        }
        result *= h / 3;
        System.out.println(" h: " + h);
        return result;
    }

    public void run(double delta, double[] eps) {
        delta = 0.000001;
      //  eps[] = {0.01, 0.001, 0.0001};
        for (int i = 0; i < eps.length; i++) {
            run(delta, eps[i], i);
        }
    }

    public static void main(String[] args) {
       // new Lab6().run(delta, eps[]);
    }

    private void run(double delta, double eps, int i) {
        double a = 0;
        double b = 1.0;
        int method = RECTANGLE;
        do {
            int m;
            double i1 = 0;
            double i2 = 0;
            double di = 0;
            switch (method) {
                case RECTANGLE:
                    System.out.println("Rectangle: ");
                    break;
                case TRAPEZE:
                    System.out.println("Trapeze: ");
                    break;
                case SIMPSON:
                    System.out.println("Simpson: ");
                    break;
            }
            m = 0;
            do {
                m++;
                switch (method) {
                    case RECTANGLE:
                        i1 = rectangle(a, b, 2 * m, delta);
                        i2 = rectangle(a, b, 4 * m, delta);
                        di = Math.abs((i2 - i1) / 3);
                        break;
                    case TRAPEZE:
                        i1 = trapeze(a, b, 2 * m, delta);
                        i2 = trapeze(a, b, 4 * m, delta);
                        di = -Math.abs((i2 - i1) / 3);
                        break;
                    case SIMPSON:
                        i1 = simpson(a, b, m, delta);
                        i2 = simpson(a, b, 2 * m, delta);
                        di = -Math.abs((i2 - i1) / 15);
                        break;
                }
            }
            while (Math.abs(di) > eps);
            System.out.println("  Eps = " + eps + ": I = " + (i2 + di) + ", error = " + new DecimalFormat("0.00000000000000000000000").format(Math.abs(di)) + ", # of steps n = " + (2 * m));
            method += 1;
        } while (method <= SIMPSON);
    }
}
