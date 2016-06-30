// Java8
import java.util.Scanner;

public class Lab1 {

    int n = 0;
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
    double func(double x, double c, double d, double delta) {
        double s;
        s = c * (x - d);
        s = round(s, delta);
        return s;
    }
    // решает уравнение с помощью метода бисекции
    double bisect(double left, double right, double eps, double c, double d, double delta) {
// eps - точность с которой надо решить уравнение
        double e = Math.abs(eps) * 2.0;
        double fLeft = func(left, c, d, delta);
        double fRight = func(right, c, d, delta);
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
            y = func(x, c, d, delta);
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
       /* System.out.println("f(x) = c(x - 1)");
        System.out.println("----------------------------------");
        System.out.println("Enter delta: ");
// точность с которой вычисляем функцию
        double delta = s.nextDouble();
        System.out.println("Enter c: ");
        double c = s.nextDouble();             // с = tg L
        System.out.println("Enter epsylon: ");
        double eps = s.nextDouble();
        System.out.println("Enter left: ");
        double left = s.nextDouble();
        System.out.println("Enter right: ");
        double right = s.nextDouble();
        System.out.println();
*/
        double d = 1;                         //  Пусть d = 1
        double c = 33;
        double left = -10;
        double right = 7;
        double eps[] = {0.1, 0.01, 0.001, 0.0001, 0.00001, 0.000001};
        double delta = s.nextDouble();

        for (int i = 0; i < eps.length; i++) {
            double result = bisect(left, right, eps[i], c, d, delta);
         //   System.out.println("Iter: " + (n + 1));
            System.out.println("Result " + (i+1) +": " + result);
        }
    }

    public static void main(String[] args) {

        Lab1 bisect = new Lab1();
        bisect.run();
    }
}
