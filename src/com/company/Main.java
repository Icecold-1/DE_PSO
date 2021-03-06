package com.company;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void selectedProblem(int problem) {
        if(problem==1)
            System.out.println("YOU HAVE SELECTED DE ALGORITHM");
        else if(problem == 2)
            System.out.println("YOU HAVE SELECTED PSO ALGORITHM");
        else {
            System.out.println("YOU HAVE SELECTED AN INVALID ALGORITHM");
            System.exit(0);
        }
    }

    public static void main(String[] args) {

        double D = Double.parseDouble(args[0]);
        double MAX_EVAL = Double.parseDouble(args[1]);
        double CR = Double.parseDouble(args[2]);
        double F = Double.parseDouble(args[3]);
        double population = Double.parseDouble(args[4]);
        double selectedProblem = Double.parseDouble(args[5]);
        int problem = Integer.parseInt(args[8]);

        double low = Double.parseDouble(args[0]);
        double up = Double.parseDouble(args[1]);
        int S = Integer.parseInt(args[2]);
        int MAX_ITERATION = Integer.parseInt(args[3]);
        double c1 = Double.parseDouble(args[4]);
        double c2 = Double.parseDouble(args[5]);
        double w = Double.parseDouble(args[6]);
        int dimension = Integer.parseInt(args[7]);


        selectedProblem(problem);
        if(problem == 1) {
            DE de = new DE();
            de.DE(CR, F, (int)D, (int)population, (int)MAX_EVAL, (int)selectedProblem);
            double result = de.exec();
            System.out.print("Min: " + result);
        }
        else if(problem == 2) {
            PSO pso = new PSO();
            pso.PSO(low, up, S, MAX_ITERATION, c1, c2, w, dimension);
            List<Double> result = pso.init();
            System.out.println("BEST g FOUND: " + result);
        }
    }
}
