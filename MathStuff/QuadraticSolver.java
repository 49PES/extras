public class QuadraticSolver{
    public static String quadSolver(int[] coefficients){
        int a = coefficients[0], b = coefficients[1], c = coefficients[2]; // [a, b, c] corresponds to ax^2 + bx + c = 0
        int denominator = 2 * a;
        int discriminant = (int) Math.pow(b, 2) - 4 * a * c; // Discriminant: b^2 - 4ac, to be used to test real roots for disc >= 0 or imaginary roots for disc < 0
        String outputOne = ""; String outputTwo = "";

        if(discriminant >= 0){
            int maxSquare = maxSquareFactor(discriminant); // Factor out the greatest square from the discriminant
            discriminant /= ((int) Math.pow(maxSquare, 2)); // Here on out, discriminant represents the (simplified) value inside the square root

            int gcd = MathC.gcd(MathC.gcd(b, maxSquare), denominator); // Determine the GCD of the -b, max square factor of the discriminant, and the 2a denominator for simplification purposes

            b /= gcd; maxSquare /= gcd; denominator /= gcd;

            // RATIONAL!
            if(discriminant == 1 || discriminant == 0){
                // When the discriminant is a perfect square, there can only be rational roots (0, 1, or 2 potential integer values)
                // There are situations where there is one integer root and one rational (non-integer) root
                // e. g. 2x^2 -3x + 1 = 0 with roots 1 and 1/2
                if(discriminant == 1) {
                    Rational negRoot = new Rational((-b - maxSquare), denominator);
                    Rational posRoot = new Rational((-b + maxSquare), denominator);
                    negRoot.reduce();
                    posRoot.reduce();
                    return negRoot.toString() + ", " + posRoot.toString();
                }
                Rational doubRoot = new Rational(-b, denominator);
                doubRoot.reduce();
                return doubRoot.toString() + ", " + doubRoot.toString();
            }

            // IRRATIONAL!
            if(b != 0 && denominator != 1){outputOne += "("; outputTwo += "(";}

            if(b != 0){outputOne += -b + " + "; outputTwo += -b + " - ";}
            else {outputTwo += "-";}

            if(maxSquare != 1){outputOne += maxSquare + ""; outputTwo += maxSquare + "";}

            if(discriminant != 1){outputOne += "\u221A(" + discriminant + ")"; outputTwo += "\u221A(" + discriminant + ")";}

            if(b != 0 && denominator != 1){outputOne += ")"; outputTwo += ")";}

            if(denominator != 1){outputOne += "/" + denominator; outputTwo += "/" + denominator;}

            return outputOne + ", " + outputTwo;

        }

        else{
            // COMPLEX!
            discriminant *= -1; // Factor out "i" from the discriminant, incorporate i into the string instead
            int maxSquare = maxSquareFactor(discriminant);
            discriminant /= ((int) Math.pow(maxSquare, 2));

            int gcd = MathC.gcd(MathC.gcd(-b, maxSquare), denominator);
            b /= gcd; maxSquare /= gcd; denominator /= gcd;


            if(b != 0 && denominator != 1){outputOne += "("; outputTwo += "(";}

            if(b != 0){outputOne += -b + " + ";  outputTwo += -b + " - ";}
            else {outputTwo += "-";}

            if(maxSquare != 1){outputOne += maxSquare + "i"; outputTwo += maxSquare + "i";}
            else{outputOne += "i"; outputTwo += "i";}

            if(discriminant != 1){outputOne += "\u221A(" + discriminant + ")"; outputTwo += "\u221A(" + discriminant + ")";}

            if(b != 0 && denominator != 1){outputOne += ")"; outputTwo += ")";}

            if(denominator != 1){outputOne += "/" + denominator; outputTwo += "/" + denominator; }

            return outputOne + ", " + outputTwo;

        }


    }

    public static int maxSquareFactor(int val){
        int max = 1;
        int num = val;
        for(int i = 2; i <= num; i++){
            while(num % Math.pow(i, 2) == 0){
                max *= i;
                num /= (int) Math.pow(i, 2);
                // Factor out i^2 from num and multiply the max by i to avoid redundant work
                // e. g. factoring out 100^2 from a value can be factored out as 2^2 * 5^2 * 5^2 instead
            }
        }
        return max;
    }

    public static void main(String[] args){
        int[] coefficients1 = {1, 0, -1};
        System.out.println(QuadraticSolver.quadSolver(coefficients1));
        int[] coefficients2 = {2, 5, -3};
        System.out.println(QuadraticSolver.quadSolver(coefficients2));
        int[] coefficients3 = {1, 0, -2};
        System.out.println(QuadraticSolver.quadSolver(coefficients3));
        int[] coefficients4 = {1, -4, 4};
        System.out.println(QuadraticSolver.quadSolver(coefficients4));
        int[] coefficients5 = {1, 0, 1};
        System.out.println(QuadraticSolver.quadSolver(coefficients5));
        int[] coefficients6 = {2, 0, 13};
        System.out.println(QuadraticSolver.quadSolver(coefficients6));
    }
}
