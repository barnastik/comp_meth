package barnastik.task3;

import java.util.ArrayList;
import java.util.Scanner;

public class Diff {
    double a;
    double step;
    int number;
    ArrayList<Double> arguments;
    ArrayList<Double> valuesE;
    String EXPRESSIONE;

    public Diff() {
        ArrayList<Double> inputArguments = new ArrayList<>();
        ArrayList<Double> inputValuesE = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        System.out.print("Введите первый узел разбиения a = ");
        this.a = input.nextDouble();

        System.out.print("Введите шаг разбиения h = ");
        this.step = input.nextDouble();
        while (this.step <= 0) {
            System.out.print("Недопустимое значение! Повторите ввод: ");
            this.step = input.nextDouble();
        }

        System.out.print("Введите число значений в таблице (m + 1) = ");
        this.number = input.nextInt();
        while (this.number < 3) {
            System.out.print("Недопустимое значение! Повторите ввод: ");
            this.number = input.nextInt();
        }

        System.out.println("Исходная таблично-заданная функция f(x)=exp(3x): ");
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < this.number; i++) {
            inputArguments.add(this.a + this.step * i);
            inputValuesE.add(Fe(inputArguments.get(i)));
            System.out.println("x" + i + " = " + inputArguments.get(i) + " : f(x" + i + ") = " + inputValuesE.get(i));
        }
        System.out.println("-----------------------------------------------------");

        this.arguments = inputArguments;
        this.valuesE = inputValuesE;
        this.EXPRESSIONE = "f(x) = exp(3 * x)";
    }

    public double Fe(double x) {
        return Math.exp(3 * x);
    }

    public double dFe(double x) {
        return 3 * Math.exp(3 * x);
    }

    public double ddFe(double x) {
        return 9 * Math.exp(3 * x);
    }

    public void findDerivatives() {
        System.out.println("Функция f(x)=exp(3x)");
        System.out.println("        xi                     f(xi)                 f'(xi)ЧД         | f'(xi)Т - f'(xi)ЧД |         f''(xi)ЧД        | f''(xi)Т - f''(xi)ЧД |");
        for (int i = 0; i < this.number; i++) {
            double dfe = dfe(i);
            double ddfe = ddfe(i);
            System.out.printf("%18.16f      %18.16f      %18.16f      %18.16f      %18.16f      %18.16f",
                    this.arguments.get(i),
                    this.valuesE.get(i),
                    dfe,
                    Math.abs(dFe(this.arguments.get(i)) - dfe),
                    ddfe,
                    Math.abs(ddFe(this.arguments.get(i)) - ddfe)
            );
            System.out.println();
        }

        System.out.println("Ещё формулы");

        for (int i = 0; i < this.number; i++) {
            double dnfe = dnfe(i);
            double ddfe = ddfe(i);
            System.out.printf("%18.16f      %18.16f      %18.16f      %18.16f      %18.16f      %18.16f",
                    this.arguments.get(i),
                    this.valuesE.get(i),
                    dnfe,
                    Math.abs(dFe(this.arguments.get(i)) - dnfe),
                    ddfe,
                    Math.abs(ddFe(this.arguments.get(i)) - ddfe)
            );
            System.out.println();
        }
    }

    public double dfe(int index) {
        if (index == 0) {
            return (-3 * this.valuesE.get(index) +
                    4 * this.valuesE.get(index + 1) -
                    this.valuesE.get(index + 2)) /
                    (2 * this.step); //
        } else if (index == this.number - 1) {
            return (3 * this.valuesE.get(index) -
                    4 * this.valuesE.get(index - 1) +
                    this.valuesE.get(index - 2)) /
                    (2 * this.step);
        }
        return (this.valuesE.get(index + 1) - this.valuesE.get(index - 1)) / (2 * this.step);
    }

    public double dnfe(int index) {
        if (index == 1 || index == this.number - 2)
            return dfe(index);
        if (index == 0) {
            return (-11 * this.valuesE.get(index) +
                    18 * this.valuesE.get(index + 1) -
                    9 * this.valuesE.get(index + 2) +
                    2 * this.valuesE.get(index + 3)) /
                    (6 * this.step);
        } else if (index == this.number - 1) {
            return (11 * this.valuesE.get(index) -
                    18 * this.valuesE.get(index - 1) +
                    9 * this.valuesE.get(index - 2) -
                    2 * this.valuesE.get(index - 3)) /
                    (6 * this.step);
        }
        return (this.valuesE.get(index - 2) -
                8 * this.valuesE.get(index - 1) +
                8 * this.valuesE.get(index + 1) -
                this.valuesE.get(index + 2)) /
                (12 * this.step);
    }

    public double ddfe(int index) {
        if (index == 0 || index == this.number - 1) {
            return 0.0 / 0.0;
        }
        return ((this.valuesE.get(index + 1) -
                2 * this.valuesE.get(index) +
                this.valuesE.get(index - 1)) /
                (this.step * this.step));
    }

    public static void main(String[] args) {
        System.out.println("Задание №3.2: НАХОЖДЕНИЕ ПРОИЗВОДНЫХ ТАБЛИЧНО-ЗАДАННОЙ ФУНКЦИИ ПО ФОРМУЛАМ ЧИСЛЕННОГО ДИФФЕРЕНЦИРОВАНИЯ");
        System.out.println("Рассматриваемая функция: f(x) = exp(3x)");
        int a = 1;
        Scanner input = new Scanner(System.in);
        while (a == 1){
            Diff test = new Diff();
            test.findDerivatives();
            System.out.println("Введите 1, чтобы продолжить");
            a = input.nextInt();
        }
    }
}
