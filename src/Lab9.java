public class Lab9 {

    static double diff(int m, int k, double[] f) {
        if (m <= 1) return f[k + 1] - f[k];
        return diff(m - 1, k + 1, f) - diff (m - 1, k, f);
    }

    static int findClosest(double x0, int n, double[] x) {
        double prevDist = x[n - 1] - x[0], dist;
        for (int k = 1; k < n; k++) {
            dist = Math.abs(x0 - x[k]);
            if (dist > prevDist) {
                if (x[k] > x0) {
                    return k - 1;
                }
            } else {
                return k;
            }
            prevDist = dist;
        }
        return 0;
    }

    static double stirling(int n, double x0, double[] x, double[] f) {
        int c = (n - 1) >> 1;
        double sum = f[c], q = (x0 - x[c]) / (x[1] - x[0]);
        double t = q / 2.0;
        for (int i = 1; i < c; i++) {
            sum += t * (diff(2 * i - 1, c - i, f) + diff(2 * i - 1, c - i + 1, f) + q * diff(2 * i, c - i, f) / i);
            t = t / (4.0 * i * i + 2.0 * i);
            for (int j = (i - 1) * (i - 1) + 1; j <= i * i; j++) t *= (q * q - j);
        }
        return sum;
    }


    static double newton(int n, double x0, double[] x, double[] f) {
        double sum = f[0], t = 1, q = (x0 - x[0]) / (x[1] - x[0]);
        for (int i = 1; i < n; i++) {
            t *= (q - i + 1) / i;
            sum += t * diff(i, 0, f);
        }
        return sum;
    }

    static double newtonReverse(int n, double x0, double[] x, double[] f) {
        double sum = f[n-1], t = 1, q = (x0 - x[n]) / (x[1] - x[0]);
        for (int i = 1; i < n; i++) {
            t *= (q + i - 1) / i;
            sum += t * diff(i, n - i, f);
        }
        return sum;
    }

    public static void main(String[] args) {

        int n = 10;

        double x[] = {0.3680, 0.5890, 0.8110, 1.0320, 1.2530, 1.4740, 1.6960, 1.9170, 2.1380, 2.3590, 2.5810};
        double f[] = {-0.5030, -0.1240, 0.0300, 0.0250, -0.0720, -0.2000, -0.2910, -0.2820, -0.1070, 0.2970, 1.0010};
        double z[] = {2.0690, 2.0390, 1.6990};
        double res = 0;

        int t, c;
        c = (n - 1) >> 1;

        for (int i = 0; i < 3; i++) {
            t = findClosest(z[i], n, x);

            if (c == t) {
                res = stirling(n, z[i], x, f);
            } else if (c < t) {
                res = newton(n, z[i], x, f);
            }
            else {
                res = newtonReverse(n, z[i], x, f);
            }
            System.out.println("f(" + z[i] + ") = " + (res/3));
        }
    }
}