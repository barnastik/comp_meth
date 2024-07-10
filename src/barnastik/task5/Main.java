package barnastik.task5;

import java.util.Scanner;
/*
public class Main {

    static double f(double x) {
        return Math.sin(x);
    }

    static double rho(double x) {
        return Math.sqrt(x);
    }

    static double integralPol(Polynomial p, double a, double b) {
        double I = 0;
        for (Monomial monomial : p.getMonomials()) {
            double c = monomial.getCoef();
            int n = monomial.getPow();
            I += c / ((double) n + 1.5) * (Math.pow(b, (double) n + 1.5) - Math.pow(a, (double) n + 1.5));
        }
        return I;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("rho(x) = sqrt(x); f(x) = sin(x);");
        System.out.println("1. Интерполяционные квадратурные формулы");
        System.out.print("a = ");
        double a = scanner.nextDouble();
        System.out.print("b = ");
        double b = scanner.nextDouble();

        System.out.print("Число узлов N+1 = ");
        int N = scanner.nextInt();
        int[] arr_x = new int[N + 1];
        double[] arr_f = new double[N + 1];
        double[] arr_A = new double[N + 1];
        double h = (b - a) / N;
        arr_x[0] = a;
        arr_f[0] = f(a);
        for (int i = 1; i < N + 1; ++i) {
            arr_x[i] = (int) (arr_x[i - 1] + h);
            arr_f[i] = f(arr_x[i]);
        }

        for (int k = 0; k < N + 1; ++k) {
            Polynomial l = new Polynomial(1, 0);
            for (int i = 0; i < k; ++i)
                l = l.multiply(new Polynomial(1, 0).subtract(arr_x[i])).multiply(1.0 / (arr_x[k] - arr_x[i]));
            for (int i = k + 1; i < N + 1; ++i)
                l = l.multiply(new Polynomial(1, 0).subtract(arr_x[i])).multiply(1.0 / (arr_x[k] - arr_x[i]));

            arr_A[k] = integralPol(l, a, b);
        }

        System.out.printf("%15s%15s%15s\n", "x_k", "f_k", "A_k");
        for (int i = 0; i <= N; ++i)
            System.out.printf("%15s%15s%15s\n", arr_x[i], arr_f[i], arr_A[i]);

        int m = N;
        System.out.println("x^" + m);
        double I = integralPol(new Polynomial(1, m), a, b);
        System.out.printf("Точное значение I = %." + P + "f\n", I);

        double I_calc = 0;
        for (int i = 0; i <= N; ++i)
            I_calc += arr_A[i] * Math.pow(arr_x[i], m);

        System.out.printf("Вычисленное значение I_calc = sum {A_k * x_k ^ " + m + "} = %." + P + "f\n", I_calc);
        System.out.printf("Погрешность: |I - I_calc| = %." + P + "f\n", Math.abs(I - I_calc));

        System.out.println("sin(x) от 0 до 1");
        a = 0;
        b = 1;
        h = (b - a) / N;
        arr_x[0] = (int) a;
        arr_f[0] = f(a);
        for (int i = 1; i < N + 1; ++i) {
            arr_x[i] = (int) (arr_x[i - 1] + h);
            arr_f[i] = f(arr_x[i]);
        }

        for (int k = 0; k < N + 1; ++k) {
            Polynomial l = new Polynomial(1, 0);
            for (int i = 0; i < k; ++i)
                l = l.multiply(new Polynomial(1, 0).subtract(arr_x[i])).multiply(1.0 / (arr_x[k] - arr_x[i]));
            for (int i = k + 1; i < N + 1; ++i)
                l = l.multiply(new Polynomial(1, 0).subtract(arr_x[i])).multiply(1.0 / (arr_x[k] - arr_x[i]));

            arr_A[k] = integralPol(l, a, b);
        }

        System.out.printf("%15s%15s%15s\n", "x_k", "f_k", "A_k");
        for (int i = 0; i <= N; ++i)
            System.out.printf("%15s%15s%15s\n", arr_x[i], arr_f[i], arr_A[i]);

        double exactValue = 0.3642219320321323640738517593895805420756813999775795119282227820;
        System.out.printf("Точное значение I = %." + P + "f\n", exactValue);

        I_calc = 0;
        for (int i = 0; i <= N; ++i)
            I_calc += arr_A[i] * f(arr_x[i]);

        System.out.printf("Вычисленное значение I_calc = sum {A_k * sin(x_k)} = %." + P + "f\n", I_calc);
        System.out.printf("Погрешность: |I - I_calc| = %." + P + "f\n", Math.abs(exactValue - I_calc));
    }
} */

