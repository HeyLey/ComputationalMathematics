import java.util.Locale;
import java.util.Scanner;

// Java8

public class Lab2 {

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

    // решает уравнение с помощью метода бисекции
    double bisect(double left, double right, double eps, double delta) {
// eps - точность с которой надо решить уравнение
        double e = Math.abs(eps * 2);
        double fLeft = func(left, delta);
        double fRight = func(right, delta);
        double x = (left + right) / 2.0;
        double y;
        if (fLeft * fRight > 0.0) {
            System.out.println("n");
            return 0;
        }
        if (eps <= 0.0) {
            System.out.println("n");
            return 0;
        }

        n = 0;

        if (fLeft == 0.0) return left;
        if (fRight == 0.0) return right;
        while ((right - left) >= e) {
            x = 0.5 * (right + left);  //деление
            y = func(x, delta);
            if (y == 0.0) return (x);
            if (y * fLeft < 0.0)
                right = x;
            else {
                left = x;
                fLeft = y;
            }
            n++;
        }
        return x;
    }

    public void run() {   // запуск
        Scanner s = new Scanner(System.in);
        s.useLocale(Locale.ENGLISH);

      /*  System.out.println("0 = cos(x^2) - 10 * x");
        System.out.println("----------------------------------");
        System.out.println("Enter delta: ");
// точность с которой вычисляем функцию
        double delta = s.nextDouble();
        System.out.println("Enter epsylon: ");
        double eps = s.nextDouble();*/

        double delta = s.nextDouble();
        double[] eps = {0.1, 0.01, 0.001, 0.0001, 0.00001, 0.000001};

        for (int i = 0; i < eps.length; i++) {
            run(delta, eps[i], i);
        }
    }

    private void run(double delta, double eps, int i) {
        double left = -1;
        double right = 1;
        System.out.println();


       // System.out.println("Delta: " + delta);
      //  System.out.println("Eps: " + eps);

        double result = bisect(left, right, eps, delta);
        System.out.println("Result: " + result);
        double[] res = {0.1, 0.099995};
        if(i > 5) {
        System.out.println("Dist: " + Math.abs(res[0] - result)); }
        else {System.out.println("Dist: " + Math.abs(res[1] - result)); }
        System.out.println("n: " + n);
    }

    public static void main(String[] args) {

        Lab2 bisect = new Lab2();
        bisect.run();
    }
}


