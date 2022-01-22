public class QuadraticSolver{
    public static String quadSolver(int[] coefficients){
      // Quadratic Formula - (-b +/- sqrt(b^2 - 4ac))/2a
      int a = coefficients[0];
      int b = coefficients[1];
      int c = coefficients[2];

      int denominator = 2 * a;
      int discriminant = b * b - 4 * a * c; // Discriminant: b^2 - 4ac, value inside the square root
      String rootOne = "", rootTwo = "", i = "";

      if(discriminant < 0){
        discriminant *= -1; // Factor out sqrt(-1) from the discriminant as "i"
        i += "i"; // If there are imaginary roots, then "i" will be added to the string output; otherwise i is an empty string
      }

      int maxSquare = maxSquareFactor(discriminant); // Factor out the greatest square from the discriminant
      discriminant /= (maxSquare * maxSquare); // Here on out, discriminant represents the (simplified / factored out) value inside the square root

      // Determine the GCD of the -b, max square factor of the discriminant, and the 2a denominator & simplify
      int gcd = MathC.gcd(MathC.gcd(b, maxSquare), denominator);
      b /= gcd; maxSquare /= gcd; denominator /= gcd;

      if(b != 0 && denominator != 1){rootOne += "("; rootTwo += "(";} // Parentheses only really needed when roots look like (a + b)/c, when a != 0 & c != 1

      if(b != 0){rootOne += -b + " + "; rootTwo += -b + " - ";}
      else {rootTwo += "-";} // if b is 0, then it is redundant to incorporate it into the string output

      rootOne += maxSquare + i;
      rootTwo += maxSquare + i;

      if(discriminant != 1){
        rootOne += "\u221A(" + discriminant + ")";
        rootTwo += "\u221A(" + discriminant + ")";
      }

      if(b != 0 && denominator != 1){rootOne += ")"; rootTwo += ")";}

      if(denominator != 1){rootOne += "/" + denominator; rootTwo += "/" + denominator;} // /1 is redundant, only add the denominator when it isn't equal to 1

      return rootOne + ", " + rootTwo;
    }

    // Easier to use mSF than MathC simplify sqrt and string parsing ...
    static int maxSquareFactor(int val){
        int max = 1;
        int num = val;
        for(int i = 2; i <= num; i++){
            while(num % (i * i) == 0){
                max *= i;
                num /= (i * i);
                // Factor out i^2 from num and multiply the max by i to avoid redundant work
                // e. g. factoring out 100^2 from a value can be factored out as 2^2 * 2^2 * 5^2 * 5^2 instead
            }
        }
        return max;
    }
}
