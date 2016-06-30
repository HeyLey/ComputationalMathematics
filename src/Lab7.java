import java.text.DecimalFormat;

public class Lab7 {

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

    double integrate(double a, double b, int n, double A[], double x[], double delta) {
        double result = 0;
        double t1 = (b + a) / 2.0;
        double t2 = (b - a) / 2.0;
        double t;
        for (int k = 0; k < n; k++) {
            t = t1 + t2 * x[k];
            result += A[k] * func(t, delta);
        }
        result *= t2;
        return result;
    }

    public static void main(String[] args) {
        double eps = 0.05;
        double delta = 0.00000001;
        new Lab7().run(delta, eps);
    }

    public void run(double delta, double eps) {
        int n = 8;
        double[] x = {-0.96028986, -0.79666648, -0.52553242, -0.18343464, -0.18343464, -0.52553242, -0.79666648, -0.96028986};
        double[] A = {0.10122854, 0.22238103, 0.31370664, 0.36268378, 0.36268378, 0.31370664, 0.22238103, 0.10122854};
        double a = 0.0;
        double b = 1.0;
        double I = 0;
        while (eps <= b - a + 0.05) {
            I = integrate(a, a + eps, n, A, x, delta);
            System.out.println(/*"From " + a + " to " + (a + eps) + " = " + */new DecimalFormat("0.000000000").format(I));
            eps += 0.05;
        }
    }
}
