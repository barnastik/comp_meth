package barnastik.task6;
import java.util.Scanner;

public class Cauchy {
    final double x0 = 0;
    final double y0 = 1;
    double h;
    int N;
    double[] points;
    double[] der;
    double[] values;
    double[] taylor;
    double[] eulerOne;
    double[] eulerTwo;
    double[] eulerThree;
    double[] rengue;
    double[] adams;
    double[] pointsn;
    double[] valuesn;

    public Cauchy() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите параметры задачи:");
        System.out.print("h = ");
        this.h = input.nextDouble();
        System.out.print("N = ");
        this.N = input.nextInt();

        this.points = new double[this.N + 1];
        this.points[0] = x0;
        for (int i = 1; i <= this.N; i++) {
            this.points[i] = this.x0 + i * this.h;
        }

        this.values = new double[this.N + 1];
        this.values[0] = y0;
        for (int i = 1; i <= this.N; i++) {
            this.values[i] = y(this.points[i]);
        }

        this.pointsn = new double[this.N + 3];
        for (int i = -2; i <= this.N; i++) {
            this.pointsn[i+2] = this.x0 + i * this.h;
        }

        this.valuesn = new double[this.N + 3];
        for (int i = -2; i <= this.N; i++) {
            this.valuesn[i+2] = y(this.pointsn[i+2]);
        }

        // Инициализация всех массивов
        this.taylor = new double[this.N + 3];
        this.eulerOne = new double[this.N + 1];
        this.eulerTwo = new double[this.N + 1];
        this.eulerThree = new double[this.N + 1];
        this.rengue = new double[this.N + 1];
        this.adams = new double[this.N + 1];
    }

    public void solve() {
        getEulerOne();
        getEulerTwo();
        getEulerThree();
        getRengue();
        getTaylor();
        getAdams();

        System.out.println("Точные значения");
        for (int i = -2; i < this.N + 1; i++) {
            System.out.println("x_(" + i + ") = " + pointsn[i+2] + ", y_(" + i + ") = " + valuesn[i+2]);
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Метод Тейлора");
        for (int i = -2; i < this.N + 1; i++) {
            System.out.println("x_(" + i + ") = " + pointsn[i+2] + ", y_(" + i + ") = " + taylor[i+2] + ", |yn - y(xn)| = " + Math.abs(taylor[i+2] - valuesn[i+2]));
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Метод Эйлера 1:");
        for (int i = 0; i < this.N + 1; i++) {
            System.out.println("x_(" + i + ") = " + points[i] + ", y_(" + i + ") = " + eulerOne[i] + ", |yn - y(xn)| = " + Math.abs(eulerOne[i] - values[i]));
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Метод Эйлера 2:");
        for (int i = 0; i < this.N + 1; i++) {
            System.out.println("x_(" + i + ") = " + points[i] + ", y_(" + i + ") = " + eulerTwo[i] + ", |yn - y(xn)| = " + Math.abs(eulerTwo[i] - values[i]));
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Метод Эйлера 3:");
        for (int i = 0; i < this.N + 1; i++) {
            System.out.println("x_(" + i + ") = " + points[i] + ", y_(" + i + ") = " + eulerThree[i] + ", |yn - y(xn)| = " + Math.abs(eulerThree[i] - values[i]));
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Метод Рунге-Кутта:");
        for (int i = 0; i < this.N + 1; i++) {
            System.out.println("x_(" + i + ") = " + points[i] + ", y_(" + i + ") = " + rengue[i] + ", |yn - y(xn)| = " + Math.abs(rengue[i] - values[i]));
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Метод Адамса:");
        for (int i = 3; i < this.N + 1; i++) {
            System.out.println("x_(" + i + ") = " + points[i] + ", y_(" + i + ") = " + adams[i] + ", |yn - y(xn)| = " + Math.abs(adams[i] - values[i]));
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Свойдная таблица результатов для всех пяти методов:");
        System.out.println("    МЕТОД                  y" + this.N + "             АБСОЛЮТНАЯ ПОГРЕШНОСТЬ");
        System.out.println("Метод Тейлора     " + taylor[this.N] + "       " + Math.abs(taylor[this.N] - values[this.N]));
        System.out.println("Метод Эйлера 1     " + eulerOne[this.N] + "       " + Math.abs(eulerOne[this.N] - values[this.N]));
        System.out.println("Метод Эйлера 2     " + eulerTwo[this.N] + "        " + Math.abs(eulerTwo[this.N] - values[this.N]));
        System.out.println("Метод Эйлера 3     " + eulerThree[this.N] + "       " + Math.abs(eulerThree[this.N] - values[this.N]));
        System.out.println("Метод Рунге-Кутта  " + rengue[this.N] + "       " + Math.abs(rengue[this.N] - values[this.N]));
        System.out.println("Метод Адамса       " + adams[this.N] + "       " + Math.abs(adams[this.N] - values[this.N]));
        System.out.println("------------------------------------------------------------------------");
    }

    public void getTaylor() {
        this.taylor = new double[this.N + 3];
        this.der = new double[6];
        this.der[0] = 1;
        this.der[1] = 0;
        this.der[2] = -1;
        this.der[3] = 2;
        this.der[4] = -3;
        this.der[5] = 4;
        for (int k = -2; k <= this.N; k++) {
            for (int j = 0; j <= 5; j++) {
                this.taylor[k+2] += (this.der[j] * Math.pow((this.pointsn[k+2] - this.points[0]),j)) / fact(j);
            }
        }
    }

    public double fact(int j) {
        if (j == 0) {
            return 1;
        }
        else {
            return j * fact(j - 1);
        }
    }

    public void getEulerOne() {
        this.eulerOne = new double[this.N + 1];
        this.eulerOne[0] = this.y0;
        for (int i = 1; i <= this.N; i++) {
            this.eulerOne[i] = this.eulerOne[i - 1] + this.h * f(this.points[i - 1], this.eulerOne[i - 1]);
        }
    }

    public void getEulerTwo() {
        this.eulerTwo = new double[this.N + 1];
        this.eulerTwo[0] = this.y0;
        for (int i = 1; i <= this.N; i++) {
            this.eulerTwo[i] = this.eulerTwo[i - 1] + this.h * f(this.points[i - 1] + this.h / 2, this.eulerTwo[i - 1] + this.h / 2 * f(this.points[i - 1], this.eulerTwo[i - 1]));
        }
    }

    public void getEulerThree() {
        this.eulerThree = new double[this.N + 1];
        this.eulerThree[0] = this.y0;
        for (int i = 1; i <= this.N; i++) {
            this.eulerThree[i] = this.eulerThree[i - 1] + this.h / 2 * (f(this.points[i - 1], this.eulerThree[i - 1]) + f(this.points[i], this.eulerThree[i - 1] + this.h * f(this.points[i - 1], this.eulerThree[i - 1])));
        }
    }

    public void getRengue() {
        this.rengue = new double[this.N + 1];
        this.rengue[0] = this.y0;
        double k1, k2, k3, k4;
        for (int i = 1; i <= this.N; i++) {
            k1 = this.h * f(this.points[i - 1], this.rengue[i - 1]);
            k2 = this.h * f(this.points[i - 1] + this.h / 2, this.rengue[i - 1] + k1 / 2);
            k3 = this.h * f(this.points[i - 1] + this.h / 2, this.rengue[i - 1] + k2 / 2);
            k4 = this.h * f(this.points[i - 1] + this.h, this.rengue[i - 1] + k3);
            this.rengue[i] = this.rengue[i - 1] + (k1 + 2 * k2 + 2 * k3 + k4) / 6;
        }
    }

    public void getAdams() {
        double matrix[][] = new double[6][N + 3]; // Убедитесь, что матрица имеет правильные размеры
        this.adams = new double[this.N + 1];
        for (int i = 0; i < 5; i++) {
            matrix[0][i] = this.valuesn[i];
        }
        for (int i = 0; i < 5; i++) {
            matrix[1][i] = this.h * f(this.pointsn[i], matrix[0][i]);
        }
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 6 - i; j++) {
                matrix[i][j] = matrix[i - 1][j + 1] - matrix[i - 1][j];
            }
        }

        for (int i = 1; i < this.N - 1; i++) { // Исправлено условие цикла
            matrix[0][4 + i] = matrix[0][3 + i] + matrix[1][3 + i] + matrix[2][3 + i - 1] / 2
                    + 5 * matrix[3][3 + i - 2] / 12 + 3 * matrix[4][3 + i - 3] / 8
                    + 251 * matrix[5][3 + i - 4] / 720;

            matrix[1][4 + i] = h * f(pointsn[4 + i], matrix[0][4 + i]);

            matrix[2][4 + i - 1] = matrix[1][4 + i] - matrix[1][4 + i - 1];

            matrix[3][4 + i - 2] = matrix[2][4 + i - 1] - matrix[2][4 + i - 2];

            matrix[4][4 + i - 3] = matrix[3][4 + i - 2] - matrix[3][4 + i - 3];

            matrix[5][4 + i - 4] = matrix[4][4 + i - 3] - matrix[4][4 + i - 4];
        }
        for (int i = 3; i <= N; i++) {
            this.adams[i] = matrix[0][i + 2];
        }
    }

    double f(double X, double Y) {
        return (-Y + Math.exp(-X));
    }

    double y(double X) {
        return ((X + 1) / Math.exp(X));
    }

    public static void main(String[] args) {
        System.out.println("Задание 7. Численное решение Задачи Коши для обыкновенного дифференциального уравнения первого порядка.");
        System.out.println("Исходное задача Коши: y'(x) = -y(x) + exp^(-x), y(0) = 1");
        System.out.println("Точное решение задачи Коши: y(x) = (x+1)/exp^(x)");
        while (true) {
            Cauchy test = new Cauchy();
            test.solve();
        }
    }
}