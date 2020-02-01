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

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMinMax(int problem) {
        switch (problem) {
            case 0:
                setMin(-5.12);
                setMax(5.12);
                break;
            case 1:
                setMin(-10);
                setMax(10);
                break;
            case 2:
                setMin(-5.12);
                setMax(5.12);
                break;
            case 3:
                setMin(-10);
                setMax(10);
                break;
            case 4:
                setMin(-5);
                setMax(10);
                break;
            case 5:
                setMin(-10);
                setMax(10);
                break;
            case 6:
                setMin(-2);
                setMax(2);
                break;
            case 7:
                setMin(-10);
                setMax(10);
                break;
            case 8:
                setMin(-4);
                setMax(5);
                break;
            case 9:
                setMin(-4);
                setMax(5);
                break;
            case 10:
                setMin(-500);
                setMax(500);
                break;
            case 11:
                setMin(-4.5);
                setMax(4.5);
                break;
            case 12:
                setMin(-15);
                setMax(30);
                break;
            default:
        }
    }

    public double Problems(int problem, double[] y) {
        double solution;
        switch (problem) {
            case 0:
                solution = Sphere(y);
                break;
            case 1:
                solution = DixonPrice(y);
                break;
            case 2:
                solution = Rastrigin(y);
                break;
            case 3:
                solution = Sumsquare(y);
                break;
            case 4:
                solution = Zakharov(y);
                break;
            case 5:
                solution = BoothFunction(y);
                break;
            case 6:
                solution = GoldensteinPrice(y);
                break;
            case 7:
                solution = Matyas(y);
                break;
            case 8:
                solution = Powell(y);
                break;
            case 9:
                solution = Rosenbrock(y);
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
                solution=Double.MAX_VALUE;
        }
        return solution;
    }

    private double Sphere(double[] x) {
        double temp = 0;
        for(int i = 0; i < x.length; i++) {
            temp = temp + Math.pow(x[i], 2);
        }
        return temp;
    }
    private double DixonPrice(double[] x) {
        double temp = Math.pow(x[0]-1,2);
        for(int i = 1; i < x.length; i++) {
            temp = temp + i+1*Math.pow(2*Math.pow(x[i],2)-x[i-1], 2);
        }
        return temp;
    }
    private double Rastrigin(double[] x) {
        double temp = 10*getD();
        for(int i = 0; i < x.length; i++) {
            temp = temp + Math.pow(x[i], 2) - 10 * Math.cos(2*Math.PI*x[i]);
        }
        return temp;
    }
    private double Sumsquare(double[] x) {
        double temp = Math.pow(x[0],2);
        for(int i = 1; i < x.length; i++) {
            temp = temp + i*Math.pow(x[i], 2);
        }
        return temp;
    }
    private double Zakharov(double[] x) {
        double temp = 0;
        for(int i = 0; i < x.length; i++) {
            temp = temp + Math.pow(x[i], 2);
        }
        for(int i = 0; i < x.length; i++) {
            temp = temp + Math.pow(0.5*i*x[i], 2);
        }
        for(int i = 0; i < x.length; i++) {
            temp = temp + Math.pow(0.5*i*x[i], 4);
        }
        return temp;
    }
    private double BoothFunction(double[] x) {
        return Math.pow(x[0]+2*x[1]-7,2)+Math.pow(2*x[0]+x[1]-5,2);
    }
    private double GoldensteinPrice(double[] x) {
        return (1+Math.pow(x[0]+x[1]+1,2)*(19-14*x[0]+3*Math.pow(x[0],2)+6*x[0]*x[1]+3*Math.pow(x[1],2)))*(30+Math.pow(2*x[0]-3*x[1],2)*
                (18-32*x[0]+12*Math.pow(x[0],2)+48*x[1]-36*x[0]*x[1]+27*Math.pow(x[1],2)));
    }
    private double Matyas(double[] x) {
        return 0.26*(Math.pow(x[0],2)+Math.pow(x[1],2))-0.48*x[0]*x[1];
    }
    private double Powell(double[] x) {
        double temp = Math.pow(x[0]+10*x[1],2)+5*Math.pow(x[2]-x[3],2)+Math.pow(x[1]-2*x[2],4)+10*Math.pow(x[0]-x[3],4);
        for(int i = 1; i < x.length/4-1; i++) {
            temp = temp + Math.pow(x[4*i-3]+10*x[4*i-2],2)+5*Math.pow(x[4*i-1]-x[4*i],2)+Math.pow(x[4*i-2]-2*x[4*i-1],4)+10*Math.pow(x[4*i-3]-x[4*i],4);
        }
        return temp;
    }
    private double Rosenbrock(double[] x) {
        double temp = 0;
        for(int i = 0; i < x.length-1; i++) {
            temp = temp + 100*Math.pow(x[i+1]-Math.pow(x[i],2),2)+Math.pow(x[i]-1,2);
        }
        return temp;
    }
    private double Schwefel(double[] x) {
        double temp = 418.9829*getD();
        for(int i = 0; i < x.length; i++) {
            temp = temp - x[i]*Math.sin(Math.sqrt(Math.abs(x[i])));
        }
        return temp;
    }
    private double Beale(double[] x) {
        return Math.pow(1.5-x[0]+x[0]*x[1],2)+Math.pow(2.25-x[0]+x[0]*Math.pow(x[1],2),2)+Math.pow(2.625-x[0]+x[0]*Math.pow(x[1],3),2);
    }
    private double Ackley(double[] x) {
        double s1 = 0, s2 = 0;
        for(int i = 0; i < x.length; i++) {
            s1 = s1+Math.pow(x[i],2);
            s2 = s2+Math.cos(2*Math.PI*x[i]);
        }
        return -20*Math.exp(-0.2*Math.sqrt(1/x.length*s1))-Math.exp(1/x.length*s2)+20+Math.exp(1);
    }
}
