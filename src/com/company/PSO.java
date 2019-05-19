package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PSO {
    int index, S;
    double w, low, up, c1, c2;
    int MAX_EVAL, dimension;
    int eval = 0;
    ArrayList<List<Double>> p_position = new ArrayList<List<Double>>();
    ArrayList<List<Double>> p_velocity = new ArrayList<List<Double>>();
    ArrayList<List<Double>> p = new ArrayList<List<Double>>();
    List<Double> g = new ArrayList<>();
    double g_best;
    double p_best;
    double pos_best;
    Random r = new Random();

    public void PSO(double L, double U, int s, int max_eval, double C1, double C2, double weight, int dim) {
        low = L;
        up = U;
        S = s;
        MAX_EVAL = max_eval;
        c1 = C1;
        c2 = C2;
        w = weight;
        dimension = dim;
    }

    private double Fitness(List<Double> a){
        double f = 0;
        for(int i = 0; i < a.size(); i++)
            f += Math.pow(a.get(i), 2);
        return f;
    }

    private List<Double> UpdateVelocity(List<Double> vel, List<Double> pos, List<Double> pe) {
        List<Double> temp = new ArrayList<>();
        for(int i = 0; i < dimension; i++)
            temp.add(w*vel.get(i)+r.nextDouble()*c1*(pe.get(i)-pos.get(i)+r.nextDouble()*c2*(g.get(i)-pos.get(i))));
        return temp;
    }

    private List<Double> UpdatePosition(List<Double> vel, List<Double> pos){
        List<Double> temp = new ArrayList<>();
        for(int i = 0; i < dimension; i++)
            temp.add(vel.get(i) + pos.get(i));
        return temp;
    }

    public List<Double> init() {
        Particle particle = new Particle();
        particle.Particle(dimension, up, low);
        for(int i = 0; i < S; i++) {
            p_position.add(particle.GetRandomPosition());
            p.add(p_position.get(i));
            if(i==0) {
                g = p.get(i);
                g_best = Fitness(g);
            } else {
                p_best = Fitness(p.get(i));
                if(p_best < g_best){
                    g = p.get(i);
                }
            }
            p_velocity.add(particle.GetRandomVelocity());
        }
        while (eval<MAX_EVAL) {
            for (int i = 0; i < S-1; i++) {
                p_velocity.set(i+1, UpdateVelocity(p_velocity.get(i), p_position.get(i), p.get(i)));
                p_position.set(i+1, UpdatePosition(p_velocity.get(i), p_position.get(i)));
                p_best = Fitness(p.get(i));
                pos_best = Fitness(p_position.get(i));
                eval++;
                if(pos_best < p_best) {
                    p.set(i, p_position.get(i));
                    if(p_best < g_best)
                        g = p.get(i);
                }
            }
        }
        return g;
    }
}
