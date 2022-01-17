import java.util.Scanner;
public class MathC{
    // Custom Math class!

    // Return greatest common divisor of two numbers
    public static int gcd( int n, int d ) {
        int a, b, x;
        a = n;
        b = d;
        while( a % b != 0 ) {
            x = a;
            a = b;
            b = x % b;
        }

        return b;
    }

    // Return least common multiple of two numbers
    public static int lcm(int a, int b){
        return(a * b / gcd(a, b));
    }

    // Trig functions Suite

    // Reciprocal Trig Functions
    public static double csc(double angle){ return 1.0 / Math.sin(angle); }
    public static double sec(double angle){ return 1.0 / Math.cos(angle); }
    public static double cot(double angle){ return 1.0 / Math.cos(angle); }

    // Trig functions w/ degree inputs
    public static double sind(double angle){ return Math.sin(angle * Math.PI / 180); }
    public static double cosd(double angle){ return Math.cos(angle * Math.PI / 180); }
    public static double tand(double angle){ return Math.tan(angle * Math.PI / 180);  }

    // Reciprocal trig funtions w/ degree inputs
    public static double cscd(double angle){ return 1.0 / MathC.sind(angle); }
    public static double secd(double angle){ return 1.0 / MathC.cosd(angle); }
    public static double cotd(double angle){ return 1.0 / MathC.tand(angle); }

    // Inverse Trig Functions w/ degree outputs
    public static double asind(double value){ return Math.asin(value) * 180 / Math.PI; }
    public static double acosd(double value){ return Math.acos(value) * 180 / Math.PI; }
    public static double atand(double value){ return Math.atan(value) * 180 / Math.PI; }


}
