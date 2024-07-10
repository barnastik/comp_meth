package barnastik.task4;

import java.util.Scanner;

public class Integgration {
    double a;
    double b;
    double J;

    public double f(double X) {
        return (Math.pow(X, 3) + 5 * Math.pow(X, 1) - 1);
    }

    public double F(double X) {
        return (Math.pow(X, 4)/4 + 5 * Math.pow(X, 2)/2 - 1*Math.pow(X, 1));
    }

    public double p(double X) {
        return 1;
    }

    double pdeg0(double x) {
        return 3;
    }

    double pdeg0int(double x) {
        return 3 * x;
    }

    double pdeg1(double x) {
        return 2 * x + 4;
    }

    double pdeg1int(double x) {
        return Math.pow(x, 2) + 4 * x;
    }

    double pdeg2(double x) {
        return Math.pow(x, 2) * 5 - 2 * x - 10;
    }

    double pdeg2int(double x) {
        return 5 * Math.pow(x, 3) / 3 - Math.pow(x, 2) - 10 * x;
    }

    double pdeg3(double x) {
        return Math.pow(x, 3) + 5 * Math.pow(x, 1) - 1;
    }

    double pdeg3int(double x) {
        return Math.pow(x, 5) / 5 + 2 * Math.pow(x, 3) / 3 + 7 * x;
    }

    public Integgration() {
        Scanner input = new Scanner(System.in);

        System.out.print("Введите начало отрезка интегрирования: a = ");
        this.a = input.nextDouble();

        System.out.print("Введите конец отрезка интегрирования: b = ");
        this.b = input.nextDouble();
    }

    public void findIntegral(int k) {
        switch (k) {
            case 0 -> {
                System.out.println("Работаем с многочленом степени 0: 3");
                this.J = pdeg0int(this.b) - pdeg0int(this.a);
                System.out.println("Точное значение интеграла: J = " + this.J);
            }
            case 1 -> {
                System.out.println("Работаем с многочленом степени 1: 2*x + 6");
                this.J = pdeg1int(this.b) - pdeg1int(this.a);
                System.out.println("Точное значение интеграла: J = " + this.J);
            }
            case 2 -> {
                System.out.println("Работаем с многочленом степени 2: 5*x^2-2*x-10");
                this.J = pdeg2int(this.b) - pdeg2int(this.a);
                System.out.println("Точное значение интеграла: J = " + this.J);
            }
            case 3 -> {
                System.out.println("Работаем с многочленом степени 3: x^3+2*x^2+7");
                this.J = pdeg3int(this.b) - pdeg3int(this.a);
                System.out.println("Точное значение интеграла: J = " + this.J);
            }
            default -> {
                System.out.println("Работаем с функцией e^x * sin(x) + x^2");
                this.J = F(this.b) - F(this.a);
                System.out.println("Точное значение интеграла: J = " + this.J);
            }
        }

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Квадратурная формула: ЛЕВЫХ ПРЯМОУГОЛЬНИКОВ");
        double leftJ = left_square(this.a, this.b, k);
        System.out.println("Значение: J(h) = " + leftJ);
        System.out.println("Абсолютная фактическая погрешность: |J - J(h)| = " + Math.abs(J - leftJ));

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Квадратурная формула: СРЕДНИХ ПРЯМОУГОЛЬНИКОВ");
        double middleJ = middle_square(this.a, this.b, k);
        System.out.println("Значение: J(h) = " + middleJ);
        System.out.println("Абсолютная фактическая погрешность: |J - J(h)| = " + Math.abs(J - middleJ));

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Квадратурная формула: ПРАВЫХ ПРЯМОУГОЛЬНИКОВ");
        double rightJ = right_square(this.a, this.b, k);
        System.out.println("Значение: J(h) = " + rightJ);
        System.out.println("Абсолютная фактическая погрешность: |J - J(h)| = " + Math.abs(J - rightJ));

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Квадратурная формула: ТРАПЕЦИЙ");
        double trapezeJ = trap(this.a, this.b, k);
        System.out.println("Значение: J(h) = " + trapezeJ);
        System.out.println("Абсолютная фактическая погрешность: |J - J(h)| = " + Math.abs(J - trapezeJ));

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Квадратурная формула: СИМПСОНА");
        double simpsonJ = simp(this.a, this.b, k);
        System.out.println("Значение: J(h) = " + simpsonJ);
        System.out.println("Абсолютная фактическая погрешность: |J - J(h)| = " + Math.abs(J - simpsonJ));

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Квадратурная формула: 3/8");
        double third_eightJ = _38_(this.a, this.b, k);
        System.out.println("Значение: J(h) = " + third_eightJ);
        System.out.println("Абсолютная фактическая погрешность: |J - J(h)| = " + Math.abs(J - third_eightJ));

        System.out.println("#########################################################################");
    }


    double left_square(double a, double b, int c) {
        return switch (c) {
            case 0 -> (b - a) * pdeg0(a);
            case 1 -> (b - a) * pdeg1(a);
            case 2 -> (b - a) * pdeg2(a);
            case 3 -> (b - a) * pdeg3(a);
            default -> (b - a) * f(a);
        };
    }

    double right_square(double a, double b, int c) {
        return switch (c) {
            case 0 -> (b - a) * pdeg0(b);
            case 1 -> (b - a) * pdeg1(b);
            case 2 -> (b - a) * pdeg2(b);
            case 3 -> (b - a) * pdeg3(b);
            default -> (b - a) * f(b);
        };
    }

    double middle_square(double a, double b, int c) {
        return switch (c) {
            case 0 -> (b - a) * pdeg0((a + b) / 2);
            case 1 -> (b - a) * pdeg1((a + b) / 2);
            case 2 -> (b - a) * pdeg2((a + b) / 2);
            case 3 -> (b - a) * pdeg3((a + b) / 2);
            default -> (b - a) * f((a + b) / 2);
        };
    }

    double trap(double a, double b, int c) {
        return switch (c) {
            case 0 -> ((b - a) / 2) * (pdeg0(a) + pdeg0(b));
            case 1 -> ((b - a) / 2) * (pdeg1(a) + pdeg1(b));
            case 2 -> ((b - a) / 2) * (pdeg2(a) + pdeg2(b));
            case 3 -> ((b - a) / 2) * (pdeg3(a) + pdeg3(b));
            default -> ((b - a) / 2) * (f(a) + f(b));
        };
    }

    double simp(double a, double b, int c) {
        return switch (c) {
            case 0 -> ((b - a) / 6) * (pdeg0(a) + 4 * pdeg0((a + b) / 2) + pdeg0(b));
            case 1 -> ((b - a) / 6) * (pdeg1(a) + 4 * pdeg1((a + b) / 2) + pdeg1(b));
            case 2 -> ((b - a) / 6) * (pdeg2(a) + 4 * pdeg2((a + b) / 2) + pdeg2(b));
            case 3 -> ((b - a) / 6) * (pdeg3(a) + 4 * pdeg3((a + b) / 2) + pdeg3(b));
            default -> ((b - a) / 6) * (f(a) + 4 * f((a + b) / 2) + f(b));
        };
    }

    double _38_(double a, double b, int c) {
        double h = (b - a) / 3;
        double k1 = 1.0 / 8;
        double k2 = 3.0 / 8;
        return switch (c) {
            case 0 -> ((b - a) * (pdeg0(a) / 8 + (3 * pdeg0(a + h)) / 8 + (3 * pdeg0(a + 2 * h)) / 8 + pdeg0(b) / 8));
            case 1 -> ((b - a) * (pdeg1(a) / 8 + (3 * pdeg1(a + h)) / 8 + (3 * pdeg1(a + 2 * h)) / 8 + pdeg1(b) / 8));
            case 2 -> ((b - a) * (pdeg2(a) / 8 + (3 * pdeg2(a + h)) / 8 + (3 * pdeg2(a + 2 * h)) / 8 + pdeg2(b) / 8));
            case 3 -> ((b - a) * (pdeg3(a) / 8 + (3 * pdeg3(a + h)) / 8 + (3 * pdeg3(a + 2 * h)) / 8 + pdeg3(b) / 8));
            default -> ((b - a) * (f(a) / 8 + 3 * f(a + h) / 8 + 3 * f(a + 2 * h) / 8 + f(b) / 8));
        };
    }



    public static void main(String[] args) {
        System.out.println("Задание №4: Приближённое вычисление интеграла по квадратурным формулам");
        Scanner input = new Scanner(System.in);
        while (true) {
            Integgration test = new Integgration();
            System.out.println("Выберите функцию с которой хотите работать \n " +
                    "0-3 - многочлены соответствующих степеней \n" +
                    "любое другое значение - заранее заданная функция ");
            int k = input.nextInt();
            System.out.println(k);
            test.findIntegral(k);
        }
    }

    /*public class IntegrationTable {
        public static void main(String[] args) {
            Integgration test = new Integgration();
            String[] methods = {"Левые прямоугольники", "Средние прямоугольники", "Правые прямоугольники",
                    "Трапеции", "Симпсона", "3/8"};
            String[] polynomials = {"0", "1", "2", "3"};

            System.out.println("Методы / Многочлены\t0\t1\t2\t3");
            for (String method : methods) {
                System.out.print(method + "\t");
                for (String polynomial : polynomials) {
                    double integral = test.calculateIntegral(Integer.parseInt(polynomial));
                    double error = test.calculateError(integral, Integer.parseInt(polynomial), method);
                    System.out.print(error + "\t");
                }
                System.out.println();
            }
        }
    }
    public void calculateErrorsForPolynomials() {
        // Массив многочленов
        int[] polynomialDegrees = {0, 1, 2, 3};
        // Массив методов
        String[] methods = {"Left Square Method", "Right Square Method", "Trapezoidal Method", "Simpson's Method"};

        // Создание таблицы для хранения значений погрешностей
        double[][] errorsTable = new double[polynomialDegrees.length][methods.length];

        // Заполнение таблицы
        for (int i = 0; i < polynomialDegrees.length; i++) {
            for (int j = 0; j < methods.length; j++) {
                double integral = calculateIntegral(polynomialDegrees[i]);
                double error = calculateIntegral(integral, polynomialDegrees[i], methods[j]);
                errorsTable[i][j] = error;
            }
        }

        // Вывод таблицы
        System.out.println("Polynomial Degree | Method               | Error");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < polynomialDegrees.length; i++) {
            for (int j = 0; j < methods.length; j++) {
                System.out.printf("%-17d | %-20s | %-10f\n", polynomialDegrees[i], methods[j], errorsTable[i][j]);
            }
        }
    }


    public double calculateIntegral(double integral, int polynomialDegree, String method) {
        double exactValue;
        switch (polynomialDegree) {
            case 0:
                exactValue = pdeg0int(b) - pdeg0int(a);
                break;
            case 1:
                exactValue = pdeg1int(b) - pdeg1int(a);
                break;
            case 2:
                exactValue = pdeg2int(b) - pdeg2int(a);
                break;
            case 3:
                exactValue = pdeg3int(b) - pdeg3int(a);
                break;
            default:
                exactValue = F(b) - F(a);
        }
        return exactValue;
    }


    // Пример вызова функции
    public static void main(String[] args) {
        Integgration obj = new Integgration();
        obj.calculateErrorsForPolynomials();
    }*/


}
