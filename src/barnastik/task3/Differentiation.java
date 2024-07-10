package barnastik.task3;


import java.util.ArrayList;
import java.util.Scanner;

public class Differentiation {
    double a;
    double b;
    double step;
    int number;
    ArrayList<Double> arguments;
    ArrayList<Double> values;
    ArrayList<Double> valuesE;
    String EXPRESSION;
    String EXPRESSIONE;

    public Differentiation() {
        ArrayList<Double> inputArguments = new ArrayList<>();
        ArrayList<Double> inputValues = new ArrayList<>();
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

        System.out.println("Исходная таблично-заданная функция f(x) = sin(x) - x*x/2: ");
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < this.number; i++) {
            inputArguments.add(this.a + this.step * i);
            inputValues.add(F(inputArguments.get(i)));
            System.out.println("x" + i + " = " + inputArguments.get(i) + " : f(x" + i + ") = " + inputValues.get(i));
        }
        System.out.println("-----------------------------------------------------");

        System.out.println("Исходная таблично-заданная функция f(x)=exp(3x): ");
        for (int i = 0; i < this.number; i++) {
            inputArguments.add(this.a + this.step * i);
            inputValuesE.add(Fe(inputArguments.get(i)));
            System.out.println("x" + i + " = " + inputArguments.get(i) + " : f(x" + i + ") = " + inputValuesE.get(i));
        }
        System.out.println("-----------------------------------------------------");

        this.arguments = inputArguments;
        this.values = inputValues;
        this.valuesE = inputValuesE;
        this.b = inputArguments.get(number - 1);
        this.EXPRESSIONE = "f(x) = exp(3 * x)";
        this.EXPRESSION = "f(x) = sin(x) - x*x/2:";
    }

    public double Fe(double x) {
        return Math.exp(3 * x);
    }

    public double dFe(double x) {
        return 3*Math.exp(3 * x);
    }

    public double ddFe(double x) {
        return 9*Math.exp(3 * x);
    }

    public double F(double x) {
        return Math.sin(x) - x*x/2;
    }

    public double dF(double x) {
        return Math.cos(x) - x;
    }

    public double ddF(double x) {
        return -Math.sin(x) - 1;
    }



    public void findDerivatives() {
        System.out.println("Функция f(x) = sin(x) - x*x/2:");
        System.out.println("        xi                     f(xi)                 f'(xi)ЧД         | f'(xi)Т - f'(xi)ЧД |         f''(xi)ЧД        | f''(xi)Т - f''(xi)ЧД |");
        for (int i = 0; i < this.number; i++) {
            double df = df(i);
            double ddf = ddf(i);
            System.out.printf("%18.16f      %18.16f      %18.16f      %18.16f      %18.16f      %18.16f",
                    this.arguments.get(i),
                    this.values.get(i),
                    df,
                    Math.abs(dF(this.arguments.get(i))-df),
                    ddf,
                    Math.abs(ddF(this.arguments.get(i))-ddf)
            );
            System.out.println();
        }

        System.out.println("Ещё формулы");

        for (int i = 0; i < this.number; i++) {
            double dnf = dnf(i);
            double ddf = ddf(i);
            System.out.printf("%18.16f      %18.16f      %18.16f      %18.16f      %18.16f      %18.16f",
                    this.arguments.get(i),
                    this.values.get(i),
                    dnf,
                    Math.abs(dF(this.arguments.get(i))-dnf),
                    ddf,
                    Math.abs(ddF(this.arguments.get(i))-ddf)
            );
            System.out.println();
        }

        System.out.println("Функция f(x)=exp(3x)");

        System.out.println("        xi                     f(xi)                 f'(xi)ЧД         | f'(xi)Т - f'(xi)ЧД |         f''(xi)ЧД        | f''(xi)Т - f''(xi)ЧД |");
        for (int i = 0; i < this.number; i++) {
            double dfe = dfe(i);
            double ddfe = ddfe(i);
            System.out.printf("%18.16f      %18.16f      %18.16f      %18.16f      %18.16f      %18.16f",
                    this.arguments.get(i),
                    this.valuesE.get(i),
                    dfe,
                    Math.abs(dFe(this.arguments.get(i))-dfe),
                    ddfe,
                    Math.abs(ddFe(this.arguments.get(i))-ddfe)
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
                    Math.abs(dFe(this.arguments.get(i))-dnfe),
                    ddfe,
                    Math.abs(ddFe(this.arguments.get(i))-ddfe)
            );
            System.out.println();
        }
    }

    public double df(int index) {
        if (index == 0) {
            return (-3 * this.values.get(index) +
                    4 * this.values.get(index + 1) -
                    this.values.get(index + 2)) /
                    (2 * this.step); //
        } else if (index == this.number - 1) {
            return (3 * this.values.get(index) -
                    4 * this.values.get(index - 1) +
                    this.values.get(index - 2)) /
                    (2 * this.step);
        }
        return (this.values.get(index + 1) - this.values.get(index - 1)) / (2 * this.step);
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

    public double dnf(int index) {
        if (index == 1 || index == this.number - 2)
            return df(index);
        if (index == 0) {
            return (-11 * this.values.get(index) +
                    18 * this.values.get(index + 1) -
                    9 * this.values.get(index + 2) +
                    2 * this.values.get(index + 3)) /
                    (6 * this.step);
        } else if (index == this.number - 1) {
            return (11 * this.values.get(index) -
                    18 * this.values.get(index - 1) +
                    9 * this.values.get(index - 2) -
                    2 * this.values.get(index - 3)) /
                    (6 * this.step);
        }
        return (this.values.get(index - 2) -
                8 * this.values.get(index - 1) +
                8 * this.values.get(index + 1) -
                this.values.get(index + 2)) /
                (12 * this.step);
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

    public double ddf(int index) {
        if (index == 0 || index == this.number - 1) {
            return 0.0 / 0.0;
        }
        return (this.values.get(index + 1) -
                2 * this.values.get(index) +
                this.values.get(index - 1)) /
                (this.step * this.step);
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
            Differentiation test = new Differentiation();
            test.findDerivatives();
            System.out.println("Введите 1, чтобы продолжить");
            a = input.nextInt();
        }
    }
}
