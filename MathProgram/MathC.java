import java.util.ArrayList;

public class MathC{

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
    static double csc(double angle){ return 1.0 / Math.sin(angle); }
    static double sec(double angle){ return 1.0 / Math.cos(angle); }
    static double cot(double angle){ return 1.0 / Math.cos(angle); }

    // Trig functions w/ degree inputs
    static double sind(double angle){ return Math.sin(angle * Math.PI / 180); }
    static double cosd(double angle){ return Math.cos(angle * Math.PI / 180); }
    static double tand(double angle){ return Math.tan(angle * Math.PI / 180);  }

    // Reciprocal trig funtions w/ degree inputs
    static double cscd(double angle){ return 1.0 / MathC.sind(angle); }
    static double secd(double angle){ return 1.0 / MathC.cosd(angle); }
    static double cotd(double angle){ return 1.0 / MathC.tand(angle); }

    // Inverse Trig Functions w/ degree outputs
    static double asind(double value){ return Math.asin(value) * 180 / Math.PI; }
    static double acosd(double value){ return Math.acos(value) * 180 / Math.PI; }
    static double atand(double value){ return Math.atan(value) * 180 / Math.PI; }

    // Simplify a square root
    static String simplifySqrt(int num){
      String output = "", i = "";
      int val = num, maxSquareFactor = 1;

      if(val == 0){return "0";}

      if (val < 0) {
        i += "i";
        val *= -1;
      }

      for(int j = 2; j <= val; j++){
        while(val % (j * j) == 0){
          maxSquareFactor *= j;
          val /= (j * j);
        }
      }

      if(maxSquareFactor != 1) output += maxSquareFactor + ""; // "1" is redundant inside and outside of the square root
      output += i + ""; // Add "i" if imaginary
      if(val != 1) output +=  "\u221A" + val;

      return output;
    }

    static ArrayList<Integer> factors(int num){
      int val = Math.abs(num);
      ArrayList<Integer> factorList = new ArrayList<Integer>(1);

      for(int i = 1; i <= val; i++){
        if(num % i == 0){factorList.add(i);}
      }
      if(factorList.size() == 0){factorList.add(0); }
      return factorList;
    }

    static String rectToPolar(double x, double y){
      double r, t;
      r = Math.sqrt(x * x + y * y);
      t = MathC.atand(y / x);
      if(x < 0) t += 180;  // Arctan returns prinipal value from [-90, 90], if in Quadrants II or III, shift angle to [90, 270]
      if(x > 0 && y < 0) t += 360; // Add 360 to the arctan value because it could yield a negative angle value in Quadrant IV
      return "Magnitude: " + r + ",\nAngle : " + t;
    }

    static String polarToRect(double r, double t){
      double x, y;
      x = r * MathC.cosd(t);
      y = r * MathC.sind(t);
      return "(" + x + ", " + y + ")";
    }
}
