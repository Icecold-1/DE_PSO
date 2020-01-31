package com.company;

public class Problems {
    int D;
    double max, min;

    public int getD() {
        return D;
    }

    public void setD(int d) {
        D = d;
    }

    public double[] Problems(int problem, double[] y) {
        double[] solution;
        switch (problem) {
            case 0:
                solution = Sphere(y, -5.12, 5.12);
                break;
            case 1:
                solution = DixonPrice(y, -10, 10);
                break;
            case 2:
                solution = Rastrigin(y,-5.12, 5.12);
                break;
            case 3:
                solution = Sumsquare(y, -10, 10);
                break;
            case 4:
                solution = Zakharov(y, -5, 10);
                break;
            case 5:
                solution = BoothFunction(y, -10, 10);
                break;
            case 6:
                solution = GoldensteinPrice(y, -2, 2);
                break;
            case 7:
                solution = Matyas(y, -10, 10);
                break;
            case 8:
                solution = Powell(y, -4, 5);
                break;
            case 9:
                solution = Rosenbrock(y, -4, 5);
                break;
            case 10:
                solution = Schwefel(y);
                break;
            case 11:
                solution = Beale(y);
                break;
            case 12:
                solution = Ackley(y);
                break;
            default:
                solution=null;
        }
        return solution;
    }

}
