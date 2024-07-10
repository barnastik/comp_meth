package barnastik.task1;

public class Interpolation {
    // Функция
    public static double function(double x) {
        return x*x - 20*Math.sin(x);
    }

    // Производная функции f(x)
    public static double df(double x) {
        return 2 * x - 20 * Math.cos(x);
    }

    // Метод Ньютона
    public static double newtonMethod(double x0, double epsilon) {
        double x = x0;
        double fx = function(x);
        int iterations = 0;

        while (Math.abs(fx) > epsilon) {
            double dfx = df(x);
            if (dfx == 0) {
                System.out.println("Производная равна нулю, невозможно продолжить.");
                return Double.NaN;
            }
            x = x - fx / dfx;
            fx = function(x);
            iterations++;
        }

        System.out.println("Количество итераций для метода Ньютона: " + iterations);
        return x;
    }

    // Метод итераций (простых итераций) для вычисления корня уравнения f(x) = 0
    public static double iterationMethod(double x0, double epsilon) {
        double x = x0;
        double newX = x0;
        double fx = function(x);

        int iterations = 0;
        while (Math.abs(fx) > epsilon) {
            newX = x - function(x) / df(x);
            fx = function(newX);
            x = newX;
            iterations++;
        }
        System.out.println("Количество итераций для метода итераций: " + iterations);

        return newX;
    }

    public static double modifiedNewtonMethod(double x0, double epsilon) {
        double x = x0;
        double fx = function(x);
        int iterations = 0;

        while (Math.abs(fx) > epsilon) {
            double fx0 = fx;
            double dfx = (function(x + epsilon) - fx0) / epsilon; // new derivative
            if (dfx == 0) {
                System.out.println("Производная равна нулю, невозможно продолжить.");
                return Double.NaN;
            }
            x = x - fx0 / dfx;
            fx = function(x);
            iterations++;
        }

        System.out.println("Количество итераций для модифицированного метода Ньютона: " + iterations);
        return x;
    }


    public static void main(String[] args) {
        double a = -1.0; // Левый конец интервала
        double b = 4.0; // Правый конец интервала
        double eps = 0.0001; // Точность
        System.out.println("Начальное приближение: -1");

        double x0 = -1.0; // начальное приближение для метода Ньютона
        double epsilonNewton = 0.000001; // требуемая точность для метода Ньютона
        double rootNewton = newtonMethod(a, epsilonNewton);
        System.out.println("Корень, найденный методом Ньютона: " + rootNewton);

        double epsilonIteration = 0.000001; // требуемая точность для метода итераций
        double rootIteration = iterationMethod(a, epsilonIteration);
        System.out.println("Корень, найденный методом итераций: " + rootIteration);
        double raz = rootIteration - rootNewton;
        System.out.println("Разность корней: " + raz);
        double epsilon = 1e-6; // Точность
        double root = modifiedNewtonMethod(a, epsilon);
        System.out.println("Корень уравнения: " + root);

        System.out.println("Начальное приближение: 4");

        double x0_1 = 4.0; // начальное приближение
        double epsilonNewton_1 = 0.000001; // требуемая точность для метода Ньютона
        double rootNewton_1 = newtonMethod(b, epsilonNewton_1);
        System.out.println("Корень, найденный методом Ньютона: " + rootNewton_1);

        double epsilonIteration_1 = 0.000001; // требуемая точность для метода итераций
        double rootIteration_1 = iterationMethod(b, epsilonIteration_1);
        System.out.println("Корень, найденный методом итераций: " + rootIteration_1);

        double raz_1 = rootIteration_1 - rootNewton_1;
        System.out.println("Разность корней: " + raz_1);
        double epsilon_1 = 1e-6; // Точность
        double root_1 = modifiedNewtonMethod(b, epsilon_1);
        System.out.println("Корень уравнения: " + root_1);
    }
}
