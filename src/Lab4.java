/**
 * Created by leyla on 30/10/15.
 */
import java.util.Locale;
import java.util.Scanner;

public class Lab4 {
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

    // вычисляет функцию f в точке х и округляет результат
    double dfunc(double x, double delta) {
        double s;
        s = -x * Math.sin(x * x) - 10;
        s = round(s, delta);
        return s;
    }

    double newton(double x, double eps, double delta) {
        double y, y1, dx;
        n = 0;
        do {
            y = func(x, delta);
            if (y == 0.0) return (x);

            y1 = dfunc(x, delta);
            if (y1 == 0.0) {
                System.out.println("Производная обратилась в ноль");
                System.exit(1);
            }

            dx = y / y1;
            x = x - dx;
            n++;
        } while (Math.abs(dx) > eps);
        return (x);
    }

    public void run() {   // запуск
        Scanner s = new Scanner(System.in);
        s.useLocale(Locale.ENGLISH);
        double delta = s.nextDouble();
        double[] eps = {0.1, 0.01, 0.001, 0.0001, 0.00001, 0.000001};
        for (int i = 0; i < eps.length; i++) {
            run(delta, eps[i], i);
        }
    }

    private void run(double delta, double eps, int i) {
        double right = 1;
        System.out.println();
        double result = newton(right, eps, delta);
        System.out.println("Result: " + result);
        double[] res = {0.1, 0.099995};
        System.out.println("n: " + n);
    }

    public static void main(String[] args) {
        Lab4 five = new Lab4();
        five.run();
    }
}