package com.company;
import java.util.Random;

public class DE {
    public double CR; //Crossover probability
    public double F; //Differential Weight
    public int NP; //pop_size
    double x[][]; //pop_size X dimension
    double f[]; //fitness
    double fy;
    public double max=5.12;
    public double min=-5.12;
    public int D;
    int eval;
    int R;
    int a,b,c;
    int MAX_EVALUATION;
    int SelectedProblem;
    Random ran = new Random();
    double S_best;
    Problems p = new Problems();

    void DE(double CRat, double Fit, int Dim, int pop, int MAX_EVAL, int SP) {
        CR = CRat; //Crossover Probability - Možnost križanja
        F = Fit; //Differential Weight - Utež za mutacijo
        D = Dim; //Dimenzija
        NP = pop; //Populacija
        MAX_EVALUATION = MAX_EVAL; //Maksimalno število evaluacij
        SelectedProblem = SP; //Izbrana optimizacija
    }

    void init() {
        x = new double[NP][];
        f = new double[NP];

        p.setMinMax(SelectedProblem);
        p.setD(D);

        double min = p.getMin();
        double max = p.getMax();

        //Generiramo naključne posameznike v populaciji
        for (int i = 0; i < NP; i++) {
            x[i] = vrniRND(D, min, max);
            f[i] = p.Problems(SelectedProblem,x[i]);
            S_best = getBestSolution(x[i]);
        }
    }
    //Vrnemo naključne podatke znotraj iskanega območja
    private double[] vrniRND(int D, double min, double max) {
        double[] r = new double[D];
        for (int i = 0; i < D; i++)
            r[i] = ran.nextDouble()*(max-min)+min;

        return r;
    }
    //Vrnemo najboljšo rešitev
    private double getBestSolution(double[] y) {
        double best = y[0];
        for (int i = 1; i < y.length; i++)
            if(best<y[i])
                best = y[i];
        return best;
    }

    private double mean(double[] r) {
        double mean = 0;
        for (int i = 0; i < r.length; i++)
            mean = mean + r[i];
        return mean/r.length;
    }
    private void standardDeviation(double[] r) {
        double temp = 0;
        double mean = mean(r);
        for (int i = 0; i < r.length; i++)
            temp = temp + Math.pow(r[i]-mean, 2);
        System.out.print("Std: " + Math.sqrt(temp/(r.length-1)));
    }

    public double exec() {
        init();
        while (eval < MAX_EVALUATION) {
            for(int i = 0; i < NP; i++) {
                //izberemo 3 naključne posameznike, ki so med seboj različni
                do
                    a = ran.nextInt(NP);
                while (i==a);
                do
                    b = ran.nextInt(NP);
                while ((i==b) || (a==b));
                do
                    c = ran.nextInt(NP);
                while ((i==c) || (a==c) || (b==c));
                R = ran.nextInt(D);
                //Ustvarimo novega posameznika
                double[] y = new double[D];
                for(int j = 0; j < D; j++){
                    if((ran.nextDouble()<CR) || (j==R))
                        y[j] = x[a][j] + F*(x[b][j]-x[c][j]);
                    else
                        y[j]=x[i][j];
                    fy = p.Problems(SelectedProblem,y);
                    eval++;
                    //če je fitnes obravnavanega posameznika večji od novega posameznika, ga zamenjamo
                    if(f[i]>fy){
                        f[i]=fy;
                        x[i]=y;
                    }
                    if(eval>=MAX_EVALUATION)
                        break;
                }
                x[i]=y;
                p.Problems(SelectedProblem,x[i]);
                S_best = getBestSolution(x[i]);
            }
        }
        standardDeviation(f);
        System.out.print("Avg: " + mean(f));
        return S_best;
    }
}