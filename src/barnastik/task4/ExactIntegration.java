package barnastik.task4;

import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;
public class ExactIntegration {
    double a;
    double b;
    double h;
    double w;
    double q;
    int N;
    int l;

    public double f(double X) {
        return Math.exp(X);
    }

    public double F(double X) {
        return Math.exp(X);
    }

    public double p(double X) {
        return 1;
    }

    public ExactIntegration() {
        Scanner input = new Scanner(System.in);

        System.out.print("Введите начало отрезка интегрирования: a = ");
        this.a = input.nextDouble();

        System.out.print("Введите конец отрезка интегрирования: b = ");
        this.b = input.nextDouble();

        System.out.print("Введите число промежутков деления [" + this.a + ", " + this.b + "]: N = ");
        int N = input.nextInt();
        while (N <= 0) {
            System.out.println("Недопустимое значение! Повторите ввод: ");
            N = input.nextInt();
        }
        this.N = N;
        this.h = (this.b - this.a) / this.N;
        System.out.println("Шаг деления: h = " + this.h);

        double w = 0;
        double q = 0;
        for (int k = 1; k < N; k++) {
            w += f(this.a + k * this.h);
            q += f((this.a + k * this.h) + this.h / 2);
        }
        this.w = w;
        this.q = q + f(this.a + this.h / 2);
    }

    public double[] findIntegral() {
        DoubleUnaryOperator function = Math::exp;
        double J = F(this.b) - F(this.a);
        System.out.println("Точное значение интеграла: J = " + J);

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Квадратурная формула: ЛЕВЫХ ПРЯМОУГОЛЬНИКОВ");
        double leftJ = leftRectangle();
        System.out.println("Значение: J(h) = " + leftJ);
        System.out.println("Абсолютная фактическая погрешность: |J - J(h)| = " + Math.abs(J - leftJ));
        System.out.println("Относительная погрешность: |(J - J(h)) / J| = " + Math.abs((J - leftJ) / J));
        System.out.println("Теоретическая погрешность:" + Math.pow((this.b-this.a),2)/(2*N)*findMax(function, this.a, this.b));

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Квадратурная формула: СРЕДНИХ ПРЯМОУГОЛЬНИКОВ");
        double middleJ = middleRectangle();
        System.out.println("Значение: J(h) = " + middleJ);
        System.out.println("Абсолютная фактическая погрешность: |J - J(h)| = " + Math.abs(J - middleJ));
        System.out.println("Относительная погрешность: |(J - J(h)) / J| = " + Math.abs((J - middleJ) / J));
        System.out.println("Теоретическая погрешность:" + Math.pow((this.b-this.a),3)/(24*N*N)*findMax(function, this.a, this.b));

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Квадратурная формула: ПРАВЫХ ПРЯМОУГОЛЬНИКОВ");
        double rightJ = rightRectangle();
        System.out.println("Значение: J(h) = " + rightJ);
        System.out.println("Абсолютная фактическая погрешность: |J - J(h)| = " + Math.abs(J - rightJ));
        System.out.println("Относительная погрешность: |(J - J(h)) / J| = " + Math.abs((J - rightJ) / J));
        System.out.println("Теоретическая погрешность:" + Math.pow((this.b-this.a),2)/(2*N)*findMax(function, this.a, this.b));


        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Квадратурная формула: ТРАПЕЦИЙ");
        double trapezeJ = trapeze();
        System.out.println("Значение: J(h) = " + trapezeJ);
        System.out.println("Абсолютная фактическая погрешность: |J - J(h)| = " + Math.abs(J - trapezeJ));
        System.out.println("Относительная погрешность: |(J - J(h)) / J| = " + Math.abs((J - trapezeJ) / J));
        System.out.println("Теоретическая погрешность:" + Math.pow((this.b-this.a),3)/(12*N*N)*findMax(function, this.a, this.b));

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Квадратурная формула: СИМПСОНА");
        double simpsonJ = simpson();
        System.out.println("Значение: J(h) = " + simpsonJ);
        System.out.println("Абсолютная фактическая погрешность: |J - J(h)| = " + Math.abs(J - simpsonJ));
        System.out.println("Относительная погрешность: |(J - J(h)) / J| = " + Math.abs((J - simpsonJ) / J));
        System.out.println("Теоретическая погрешность:" + Math.pow((this.b-this.a),5)/(2880*N*N*N*N)*findMax(function, this.a, this.b));

        System.out.println("#########################################################################");

        double[] values = new double[5];
        values[0] = leftJ;
        values[1] = middleJ;
        values[2] = rightJ;
        values[3] = trapezeJ;
        values[4] = simpsonJ;

        return values;
    }

    public double middleRectangle() {
        return this.h * this.q;
    }

    public double trapeze() {
        return (this.h / 2) * (f(this.a) + 2 * this.w + f(this.b));
    }

    public double simpson() {
        return (this.h / 6) * (f(this.a) + 4 * this.q + 2 * this.w + f(this.b));
    }

    public double leftRectangle() {
        return this.h * (f(this.a) + this.w);
    }

    public double rightRectangle() {
        return this.h * (this.w + f(this.b));
    }

    public static double findMax(DoubleUnaryOperator function, double a, double b) {
        int n = 100;
        double maxAbs = 0;
        double step = (b - a) / n;
        for (int i = 0; i <= n; i++) {
            double x = a + i * step;
            double fx = function.applyAsDouble(x);
            double absFx = Math.abs(fx);
            if (absFx > maxAbs) {
                maxAbs = absFx;
            }
        }
        return maxAbs;
    }


    public static void main(String[] args) {
        System.out.println("Задание №4.2: ПРИБЛИЖЕННОЕ ЗНАЧЕНИЕ ИНТЕГРАЛА ПО СОСТАВНЫМ КВАДРАТУРНЫМ ФОРМУЛАМ");
        while (true) {
            ExactIntegration test = new ExactIntegration();
            test.findIntegral();
        }
    }
}
