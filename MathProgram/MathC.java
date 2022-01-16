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

/**
    // Probability Suite - Factorials, Permutations, Combinations, Binomial Probability & Cumulative Distributions

    // For n, return n!
    public static int factorial(int num){
        int product = 1;
        for(int i = 1; i <= num; i++){ product *= i;}
        return product;
    }

    // n-permute-k operation = n!/(n - k)!, or n * (n - 1) * (n - 2) * ...(n - (k - 1)) {first k terms of n!}
    public static int permute(int elements, int chosen){
        int product = 1;
        for(int i = elements; i > elements - chosen; i--){product *= i;}
        return product;
    }

    // n-choose-k operation nCk = n! / (k! (n - k)!) = nPk/k!
    // optimized using permutation and factorial, factorial of the lower complement between n - k & k
    public static int choose(int elements, int chosen){
        if(elements - chosen < chosen) return permute(elements, elements - chosen) / factorial(elements - chosen);
        return permute(elements, chosen) / factorial(chosen);
    }

    // Binomial Probability
    public static double binomPDF(int n, int r, double p){
        return choose(n, r) * Math.pow(p, r) * Math.pow(1 - p, n - r);
    }

    public static double binomCDF(int n, int r, double p, String type){
        double output = 0;
        if(type.equals("at most") || type.equals("<=")){
            for(int i = 0; i <= r; i++){
                output += binomPDF(n, i, p);
            }
        }

        else if(type.equals("at least") || type.equals(">=") ) {
            for(int i = r; i <= n; i++){
                output += binomPDF(n, i, p);
            }
        }

        else if(type.equals("<")){
            for(int i = 0; i < r; i++){
                output += binomPDF(n, i, p);
            }
        }

        else if(type.equals(">") ) {
            for(int i = r + 1; i <= n; i++){
                output += binomPDF(n, i, p);
            }
        }

        return output;
    }

    public static void binomCDFAll(int n, int r, double p){
        // Output similar to this: https://www.gigacalculator.com/calculators/binomial-probability-calculator.php
        String output = "";
        output += "Number of trials (n): " + n;
        output += "\nNumber of events (r): " + r;
        output += "\nProbability (p): " + p;
        output += "\n\nProbability of R = " + r + " events:  " + binomPDF(n, r, p);
        output += "\nProbability of R < " + r + " events:  " + binomCDF(n, r, p, "<");
        output += "\nProbability of R \u2264 " + r + " events:  " + binomCDF(n, r, p, "<=");
        output += "\nProbability of R > " + r + " events:  " + binomCDF(n, r, p, ">");
        output += "\nProbability of R \u2265 " + r + " events:  " + binomCDF(n, r, p, ">=");
        System.out.println(output);
    }
**/
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


    public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      int task;
      Polynomial tempPoly = new Polynomial(new int[1], "roots");
      Triangle tempTriangle = new Triangle();
      System.out.println("Welcome to this custom math class!");
      while(true){
        System.out.println("\nWhat would you like to do?\n[1] Polynomials\n[2] Triangles\n[3] Probability\n[4] End MathC session");
        task = sc.nextInt();
        if(task == 1){
          int type;
          while(true){
          type = sc.nextInt();
          System.out.println("What values of your polynomial would you like to provide? \n[1] Roots \n[2] Coefficients?");

          if(type == 1){
          System.out.print("How many roots do you have? ");
          int numRoots = sc.nextInt();
          int[] roots = new int[numRoots];
          System.out.println("Enter the roots of your polnomial");
          for(int i = 0; i < roots.length; i++){
            System.out.print("r" + (i + 1) + ": ");
            roots[i] = sc.nextInt();
          }
          tempPoly = new Polynomial(roots, "roots");
          }

          if(type == 2){
          System.out.print("What is the degree of your polynomial? ");
          int degree = sc.nextInt();
          int[] values = new int[degree + 1];

          System.out.print("Enter the coefficients for the following terms: \n");
          for(int i = degree; i >= 0; i--){
            System.out.print("x^" + i + ": ");
            values[degree - i] = sc.nextInt();
          }

          tempPoly = new Polynomial(values, "coefficients");
        }

          System.out.println("Your polynomial is: " + tempPoly.polyToString());
          System.out.print("Would you like the roots of your polynomial? Y / N ");
          if(sc.next().equals("Y")){
            System.out.println(rootsSolver.rootsSolver(tempPoly.getPolynomial()));
          }
          System.out.print("Would you like to exit Polynomial? Y / N ");
          String input = sc.next();
          if(input.equals("Y")){break;}
         }
       }
        if(task == 2){
          while(true){
          System.out.println("Please input the values for your triangle, inputting 0 where unknown");
          double sA, sB, sC, aA, aB, aC, area;
          System.out.print("Side A = "); sA = sc.nextDouble();
          System.out.print("Side B = "); sB = sc.nextDouble();
          System.out.print("Side C = "); sC = sc.nextDouble();
          System.out.print("Angle A = "); aA = sc.nextDouble();
          System.out.print("Angle B = "); aB = sc.nextDouble();
          System.out.print("Angle C = "); aC = sc.nextDouble();
          System.out.print("Area = "); area = sc.nextDouble();
          tempTriangle = new Triangle(sA, sB, sC, aA, aB, aC, area);
          System.out.println( tempTriangle.toString() );

          System.out.print("Would you like to exit Triangle? Y / N ");
          String input = sc.next();
          if(input.equals("Y")){break;}
        }}
        if(task == 3){
          int probabilityTask = 0; int n, r; double p;
          while(probabilityTask != 5){
          System.out.println("What would you like to do? \n[1] Factorials \n[2] Permutations \n[3] Combinations \n[4] Binomial Distributions \n[5] Exit Probability");
          probabilityTask = sc.nextInt();
          if(probabilityTask == 1){
            System.out.println("n! = n * (n - 1) * (n - 2) * ... * 2 * 1 (& 0! = 1)");
            System.out.print("n? "); n = sc.nextInt();
            System.out.println(Probability.factorial(n) + "\n");
           }
          if(probabilityTask == 2){
            System.out.println("nPr = (n!) / (n - r)!" );
            System.out.print("n? "); n = sc.nextInt();
            System.out.print("r? "); r = sc.nextInt();
            System.out.println(n + "P" + r + " = " + Probability.permute(n, r));
          }
          if(probabilityTask == 3){
            System.out.println("nCr = (n!) / (r! * (n - r)!) " );
            System.out.print("n? "); n = sc.nextInt();
            System.out.print("r? "); r = sc.nextInt();
            System.out.println(n + "C" + r + " = " + Probability.choose(n, r) + "\n");
          }
          if(probabilityTask == 4){
            System.out.println("P(r) = nCr * p^r * (1 - p)^r");
            System.out.print("n? "); n = sc.nextInt();
            System.out.print("r? "); r = sc.nextInt();
            System.out.print("p? "); p = sc.nextDouble();
            Probability.binomCDFAll(n, r, p);
          }
        }
        }
        if(task == 4){break;}
      }

    }
}
