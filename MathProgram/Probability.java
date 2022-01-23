import java.util.Arrays;
public class Probability{
  // Probability Suite
  // Factorials, Permutations, Combinations, Binomial Probability & Cumulative Distributions

  static int factorial(int num){
    // return n! = n * (n - 1) * (n - 2) * ... 2 * 1

    // Factorial invalid for negative ints
    if(num < 0){throw new RuntimeException("Invalid input - less than 0");}

    if(num == 0){return 1;} // 0! is 1
    int product = 1;
    for(int i = num; i > 0; i--){product *= i; }
    return product;
  }

  static int permute(int n, int r){
    // nPr = n! / (n - r)!

    if(n < r){throw new RuntimeException("Invalid input - r is greater than n");}
    if(n < 0 || r < 0){throw new RuntimeException("Invalid input - n < 0 or r < 0"); }

    // return factorial(n) / factorial(n - r);

    int product = 1;
    for(int val = n; val > n - r; val--){product *= val; } // Calculates first r terms in n! sequence
    return product;
   }

  static int choose(int n, int r){
      // nCr = n! / r!(n - r)!
      // return factorial(n) / ( factorial(n - r) * factorial(r)  )

      /* nCr = n! / r!(n - r)!
             = ( n! / (n - r)! )   * 1 / r!
             = nPr/r!
      */

      if(r < n - r) return permute(n, r) / factorial(r);
      return permute(n, n - r) / factorial(n - r);
   }

   static String binomExpansion(int a, int b, int n){
      // expand (ax + b)^n
     int[] coefficients = new int[n + 1];
     for(int i = n; i >= 0; i--){
         coefficients[n - i] = ((int) Math.pow(a, i) * (int) Math.pow(b, n - i)  * (int) choose(n, i));
     }

    Polynomial expansion = new Polynomial(coefficients, "coefficients");
    return expansion.polyToString();
   }



  static double binomPDF(int n, int r, double p){
    // Binomial Probability Distribution Function - return nCr * p^r * (1 - p)^(n - r)
    /* Example question: A basketball player scores on 62% of free throws
       He takes 11 free throws in a match - what is the probability that he scores
      __exactly__ 5 of them?  // 11C5 * (0.62)^5 * (0.38)^6  ->  binomPDF(11, 5, 0.62) //  */


     return choose(n, r) * Math.pow(p, r) * Math.pow(1 - p, n - r);
   }

  static double binomCDF(int n, int r, double p, String type){
     // Binomial Cumulative Distribution Function - sum of multiple binomial PDFs
     /* Example question: A basketball player scores on 62% of free throws
        He takes 11 free throws in a match - what is the probability that he scores
       __at least__ 5 of them?
      // binomPDF(11, 5, 0.62) + binomPDF(11, 6, 0.62) + ... + binomPDF(11, 11, 0.62) -> binomCDF(11, 5, 0.62, "at least")  //
        */

     // Return sum of binomPDF(n, a, p) to binomPDF(n, b, p) - all values a <= x <= b
     double sum = 0;

     // From 0 to r, inclusive
     if(type.equals("at most") || type.equals("<=")){
       for(int i = 0; i <= r; i++){sum += binomPDF(n, i, p);}
     }

     // From r to n, inclusive
     if(type.equals("at least") || type.equals(">=")){
      for(int i = r; i <= n; i++){sum += binomPDF(n, i, p);}
    }

     // From 0 to r, not including r
     if(type.equals("at most but not including") || type.equals("<")){
        for(int i = 0; i < r; i++){sum += binomPDF(n, i, p);}
    }

    // From r to n, not including r
    if(type.equals("at least but not including") || type.equals(">")){
        for(int i = r + 1; i <= n; i++){sum += binomPDF(n, i, p);}
    }

     return sum;
   }

  static void binomCDFAll(int n, int r, double p){
   // Provide a general output with R = r, R < r, R <= r, R > r, and R >= r
   // Took inspiration for the output from here: https://www.gigacalculator.com/calculators/binomial-probability-calculator.php
   String output = "";
   output += "Number of trials (n): " + n;
   output += "\nNumber of events (r): " + r;
   output += "\nProbability (p): " + p;

   output += "\n\nProbability of R = " + r + " events:  " + binomPDF(n, r, p);
   output += "\nProbability of R < " + r + " events:  " + binomCDF(n, r, p, "<");
   output += "\nProbability of R \u2264 " + r + " events:  " + binomCDF(n, r, p, "<="); // \u2264 is ≤
   output += "\nProbability of R > " + r + " events:  " + binomCDF(n, r, p, ">");
   output += "\nProbability of R \u2265 " + r + " events:  " + binomCDF(n, r, p, ">="); // \u2265 is ≥

   System.out.println(output);
  }

}
