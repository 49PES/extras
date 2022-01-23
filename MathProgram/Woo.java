import java.util.Scanner;
import java.util.ArrayList;

public class Woo{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int task;
    Polynomial tempPoly = new Polynomial(new int[1], "roots");
    Triangle tempTriangle = new Triangle();
    System.out.println("Welcome to this custom math class!\nPlease enter numerical values to navigate.");

    while(true){
      System.out.println("\nWhat would you like to do?\n[1] General Math Utilities \n[2] Polynomials\n[3] Triangles\n[4] Probability \n[5] Trigonometry\n[6] End session");
      task = sc.nextInt();
      // General Math Util
      if(task == 1){
        while(true){
          System.out.println("What would you like to calculate? \n[1] Greatest Common Divisor \n[2] Least Common Multiple \n[3] Simplify a square root \n[4] Factors of a number \n[5] Rectangular to Polar Form \n[6] Polar to Rectangular Form \n[7] Exit Math Utilities");
          int mathType = sc.nextInt();

          // GCD
          if(mathType == 1){
            ArrayList<Integer> values = new ArrayList<Integer>();
            System.out.println("Input the values (minimum of 2!) for calculating the GCD of, and enter 0 when you are done");
            int val = sc.nextInt();
            while(values.size() < 2 || val != 0){
              if(val != 0) {values.add(val);}
              val = sc.nextInt();
            }
            int gcd = values.get(0);
            for(int i = 1; i < values.size(); i++){
              gcd = MathC.gcd(gcd, values.get(i));
            }
            System.out.println("These were the values you inputted: " + values);
            System.out.println("This is their GCD: " + gcd);
          }

          // Essentially a copy of GCD code // LCM
          if(mathType == 2){
            ArrayList<Integer> values = new ArrayList<Integer>();
            System.out.println("Input the values (minimum of 2!) for calculating the LCM of, and enter 0 when you are done");
            int val = sc.nextInt();
            while(values.size() < 2 || val != 0){
              if(val != 0) {values.add(val);}
              val = sc.nextInt();
            }
            int lcm = values.get(0);
            for(int i = 1; i < values.size(); i++){
              lcm = MathC.lcm(lcm, values.get(i));
            }
            System.out.println("These were the values you inputted: " + values);
            System.out.println("This is their LCM: " + lcm);
          }

          // Square Root
          if(mathType == 3){
            while (true){

              System.out.println("What value would you like to simplify the square root of?");
              int square = sc.nextInt();
              if (square > 1 || square < 0){
                System.out.println("\u221A" + square + " -> " + MathC.simplifySqrt(square));
                break;
              }
              else{
                System.out.println(" Invalid input - input is between 0 and 1");
              }
            }

          }

          // Factor
          if(mathType == 4){
            System.out.println("Input the value you want to determine the factors of");
            int val = sc.nextInt();
            System.out.println("The factors of " + val + " are " + MathC.factors(val) );
          }

          // RectToPolar
          if(mathType == 5){
            System.out.print("What is your x coordinate? ");
            double x = sc.nextDouble();
            System.out.print("What is your y coordinate? ");
            double y = sc.nextDouble();
            System.out.println("(" + x + ", " + y + ") ->\n" + MathC.rectToPolar(x, y));
          }

          // PolarToRect
          if(mathType == 6){
            System.out.print("What is your magnitude? ");
            double r = sc.nextDouble();
            System.out.print("What is your angle? ");
            double t = sc.nextDouble();
            System.out.println("Magnitude: " + r + ", Angle: " + t + " -> " + MathC.polarToRect(r, t) );
          }

          //break
          if(mathType == 7) break;
        }}

      // Polynomials
      if(task == 2){
        int type;
        while(true){
        System.out.println("What values of your polynomial would you like to provide? \n[1] Roots \n[2] Coefficients?");
        type = sc.nextInt();

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
          System.out.println(RootsSolver.rootsSolver(tempPoly.getPolynomial()));
        }
        System.out.print("Would you like to exit Polynomial? Y / N ");
        String input = sc.next();
        if(input.equals("Y")){break;}
       }
     }

     // Triangles
      if(task == 3){
        while(true){
        System.out.println("Please input the values for your triangle, inputting 0 where unknown");
        double sA, sB, sC, aA, aB, aC;
        System.out.print("Side A = "); sA = sc.nextDouble();
        System.out.print("Side B = "); sB = sc.nextDouble();
        System.out.print("Side C = "); sC = sc.nextDouble();
        System.out.print("Angle A = "); aA = sc.nextDouble();
        System.out.print("Angle B = "); aB = sc.nextDouble();
        System.out.print("Angle C = "); aC = sc.nextDouble();
        tempTriangle = new Triangle(sA, sB, sC, aA, aB, aC);
        System.out.println( tempTriangle.toString() );

        System.out.print("Would you like to exit Triangle? Y / N ");
        String input = sc.next();
        if(input.equals("Y")){break;}
      }}

      // Probability
      if(task == 4){
        int probabilityTask = 0; int n, r; double p;
        while(true){
        System.out.println("What would you like to do? \n[1] Factorials \n[2] Permutations \n[3] Combinations \n[4] Binomial Expansions \n[5] Binomial Distributions \n[6] Exit Probability");
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
          int a, b, x;
          System.out.println("Expanding (ax + b)^x. Enter your a, b, and x");
          System.out.print("a? "); a = sc.nextInt();
          System.out.print("b? "); b = sc.nextInt();
          System.out.print("x? "); x = sc.nextInt();
          System.out.println(Probability.binomExpansion(a, b, x));
        }
        if(probabilityTask == 5){
          System.out.println("P(r) = nCr * p^r * (1 - p)^r");
          System.out.print("n? "); n = sc.nextInt();
          System.out.print("r? "); r = sc.nextInt();
          System.out.print("p? "); p = sc.nextDouble();
          Probability.binomCDFAll(n, r, p);
        }
        if(probabilityTask == 6){break;}
      }
      }

      // Trigonometry
      if(task == 5){
        boolean inRadians = true; int trigType;
        while(true) {
          System.out.println("What would you like to do? \n[1] Change radians/degrees \n[2] Evaluate a trig function \n[3] Exit Trigonometry" );
          trigType = sc.nextInt();
          if(trigType == 1){
            if(inRadians) {inRadians = false; System.out.println("Changed to degree mode\n");}
            else{inRadians = true; System.out.println("Changed to radian mode\n");}
          }
          if(trigType == 2){
            System.out.println("What what you like to evaluate? \n[1] Sin \n[2] Cosine \n[3] Tangent \n[4] Cosecant \n[5] Secant \n[6] Cotangent \n[7] Arcsin \n[8] Arccos \n[9] Arctan" );
            int trigFxn = sc.nextInt();
            System.out.println("What input would you like to input?");
            int val = sc.nextInt();
            if(trigFxn == 1){
              if(inRadians){System.out.println("sin(" + val + ") = " + Math.sin(val) ); }
              else {System.out.println("sin(" + val + "\u00b0) = " + MathC.sind(val) );}
            }
            if(trigFxn == 2){
              if(inRadians){System.out.println("cos(" + val + ") = " + Math.cos(val) ); }
              else {System.out.println("cos(" + val + "\u00b0) = " + MathC.cosd(val) );}
            }
            if(trigFxn == 3){
              if(inRadians){System.out.println("tan(" + val + ") = " + Math.tan(val) ); }
              else {System.out.println("tan(" + val + "\u00b0) = " + MathC.tand(val) );}
            }
            if(trigFxn == 4){
              if(inRadians){System.out.println("csc(" + val + ") = " + MathC.csc(val) ); }
              else {System.out.println("csc(" + val + "\u00b0) = " + MathC.cscd(val) ); }
            }
            if(trigFxn == 5){
              if(inRadians){System.out.println("sec(" + val + ") = " + MathC.sec(val) ); }
              else {System.out.println("sec(" + val + "\u00b0) = " + MathC.secd(val) ); }
            }
            if(trigFxn == 6){
              if(inRadians){System.out.println("cot(" + val + ") = " + MathC.cot(val) ); }
              else {System.out.println("cot(" + val + "\u00b0) = " + MathC.cotd(val) ); }
            }
            if(trigFxn == 7){
              if(inRadians){System.out.println("arcsin(" + val + ") = " + Math.asin(val));}
              else{System.out.println("arcsin(" + val + ") = " + MathC.asind(val) + "\u00b0");}
            }
            if(trigFxn == 8){
              if(inRadians){System.out.println("arccos(" + val + ") = " + Math.acos(val));}
              else{System.out.println("arccos(" + val + ") = " + MathC.acosd(val) + "\u00b0");}
            }
            if(trigFxn == 9){
              if(inRadians){System.out.println("arctan(" + val + ") = " + Math.atan(val));}
              else{System.out.println("arctan(" + val + ") = " + MathC.atand(val) + "\u00b0");}
            }
          }

          if(trigType == 3) break;
        }
      }

      // Break
      if(task == 6){break;}
    }

  }

}
