package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Particle {
    int dimension;
    List<Double> velocity = new ArrayList<>();
    List<Double> coords = new ArrayList<>();
    double up;
    double low;
    Random r = new Random();
    double lowVelocity, upVelocity;

    public void Particle(int dim, double upper, double lower) {
        dimension = dim;
        up = upper;
        low = lower;
        lowVelocity = -(upper-lower);
        upVelocity = (upper-lower);
    }

    public List<Double> GetRandomPosition() {
        coords.clear();
        for(int i = 0; i < dimension; i++)
            coords.add(r.nextDouble()*(up-low)+low);
        return coords;
    }

    public List<Double> GetRandomVelocity() {
        velocity.clear();
        for(int i = 0; i < dimension; i++)
            velocity.add(r.nextDouble()*(upVelocity-lowVelocity)+lowVelocity);
        return velocity;
    }
}
