package application.MathLogic;

import static java.lang.Math.pow;

public class Lines {
    private static double res;
    public static double elipse(double a, double b, double x, double y ){
        res = (a * b) / pow(a * a * y * y / (b * b)  + b * b * x * x / (a * a), (double) 3/2);
        return res ;// одинаково для элипса и гиперболы
    }
    public  static  double parable ( double p , double x, double y ){
        res = p * p / (pow (y * y + p * p, (double) 3/2 ));
        return res;
     }
    public static double elX(double a, double b, double x, double y ){
        res = (a * a - b * b ) / a * Math.pow(x/a, 3);
        return res ;
    }
    public static double elY(double a, double b, double x, double y ){
        res = (b * b - a * a ) / b * Math.pow(y/b, 3);
        return res ;
    }
    public static double hipX (double a , double b, double x , double y){
        res = Math.pow(x, 3) / (a * a ) * (1 + (b * b) / (a * a) ) ;
        return res;
    }
    public static double hipY (double a , double b, double x , double y){
        res = -Math.pow(y, 3) / (b * b ) * (1 + (a * a) / (b * b) );
        return res;
    }
     public static double parX (double p , double y){
        res = 3 / (2 * p) * y * y  +  p;
        return res;
     }
     public static double parY (double p, double y){
        res = - Math.pow(y, 3) / (p * p);
        return res;
     }
}
