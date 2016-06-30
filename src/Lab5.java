import java.util.Locale;
import java.util.Scanner;

public class Lab5 {
    int n = 0;
    public static final double REAL_ANSWER = 0.0999950;

    // округляет х с точностью delta
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

    // вычисляет функцию f в точке х и округляет результат
    double func(double x, double delta) {
        double s;
        s = Math.cos(x * x) - 10 * x;
        s = round(s, delta);
        return s;
    }

    double phifunc(double x, double delta) {
        double s;
        s = Math.cos(x * x)/10;
        s = round(s, delta);
        return s;
    }

    double iter(double x0, double eps, double delta) {
        double x1 = phifunc(x0, delta);
        double x2 = phifunc(x1, delta);
        n = 2;
        while((x1 - x2) * (x1 - x2) > Math.abs((2 * x1 - x0 - x2) * eps)) {
            x0 = x1;
            x1 = x2;
            x2 = phifunc(x1, delta);
            n++;
        }
        return x2;
    }

    public void run() {   // запуск
        Scanner s = new Scanner(System.in);
        s.useLocale(Locale.ENGLISH);
        System.out.println("Enter delta:");
        double delta = s.nextDouble();
        double[] eps = {0.1, 0.01, 0.001, 0.0001, 0.00001, 0.000001};
        for (int i = 0; i < eps.length; i++) {
            run(delta, eps[i], i);
        }
    }

    private void run(double delta, double eps, int i) {
        double right = 0.8;
        System.out.println();
        double result = iter(right, eps, delta);
        System.out.println("Result: " + result);
        double[] res = {0.1, REAL_ANSWER};
        System.out.println("n: " + n);
    }

    public static void main(String[] args) {
        Lab5 five = new Lab5();
        five.run();
    }
}