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
    Random ran = new Random();
    double S_best;

    void DE(double CRat, double Fit, int Dim, int pop, int MAX_EVAL) {
        CR = CRat; //Crossover Probability - Možnost križanja
        F = Fit; //Differential Weight - Utež za mutacijo
        D = Dim; //Dimenzija
        NP = pop; //Populacija
        MAX_EVALUATION = MAX_EVAL; //Maksimalno število evaluacij
    }

    void init() {
        x = new double[NP][];
        f = new double[NP];

        //Generiramo naključne posameznike v populaciji
        for (int i = 0; i < NP; i++) {
            x[i] = vrniRND(D, min, max);
            f[i] = sphere(x[i]);
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
    //funkcija za določitev optimizacije
    public double sphere(double[] d) {
        double r=0;
        for(int i = 0; i < d.length; i++)
            r = r + Math.pow(d[i], 2);
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
                    fy = sphere(y);
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
                sphere(x[i]);
                S_best = getBestSolution(x[i]);
            }
        }
        return S_best;
    }
}