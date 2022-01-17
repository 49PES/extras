public class rootsSolver{

	public static String rootsSolver(int[] coefficients){
        if(coefficients.length == 2){
					// mx + b = 0, x = -b/m
					Rational root = new Rational(-coefficients[1], coefficients[0]);
					root.reduce();
					return root.toString();
				}

				if(coefficients.length == 3){return QuadraticSolver.quadSolver(coefficients);} // Base case for polynomials length 3+

        int numTerms = coefficients.length;
        int[] firstFactors = factors(coefficients[0]); // Determine leading term's factors
        int[] lastFactors = factors(coefficients[numTerms - 1]); // Determine final term's factors
        int sumNumPos = 0; int sumNumNeg = 0;

        for(int i = 0; i < firstFactors.length; i++){
         for(int j = 0; j < lastFactors.length; j++){
            sumNumPos = 0; sumNumNeg = 0;

            // Application of the Rational Root Theorem!
            int numerator = lastFactors[j];  // Factors of final term -> numerator of potential factor
            int denominator = firstFactors[i]; // Factors of leading term -> denominator of potential factor

            // Simplify the fraction
	    			int gcd = MathC.gcd(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;

            // For any given value as ax^b, a rational root can be represented as n/d, so ax^b = a(n/d)^b
            // If you multiply all of the terms by the leading term's degree, then the terms can be represented as a(n)^(b)d^(degree - b)
						// Avoids floating point inaccuracies by keeping everything as ints
						// e. g. (5(-4/5)^3     - 16(-4/5)^2      + 9(-4/5)^1 +   20 )  * 5^3 =
            //       5(-4)^3(5)^0  - 16(-4)^2(5)^1   +9(-4)^1(5)^2  + 20(-4)^0(5)^3

						// e. g. 4/7 - 3/21 - 3/7 = 0/21 <- only the numerator being 0 is relevant
						for(int k = 0; k < numTerms; k++){
								// Try n/d and -n/d simultaneously
                sumNumPos += coefficients[k] * (int) Math.pow(numerator, numTerms - k - 1) * (int) Math.pow(denominator, k);
                sumNumNeg += coefficients[k] * (int) Math.pow(-numerator, numTerms - k - 1) * (int) Math.pow(denominator, k);
            }

						// According to the remainder theorem, for a polynomial P(x), if P(b) = 0, then "b" is a root of P(x)
						// Factor out (x - b) from the polynomial accordingly and repeat
            if(sumNumPos == 0 || sumNumNeg == 0){
                if(sumNumNeg == 0) numerator *= -1;

								int[] quotient = syntheticDivision(coefficients, numerator, denominator); // Apply synthetic division to get quotient polynomial to be recursed upon
                if(denominator == 1) {return numerator + ", " + rootsSolver(quotient);}
                else{return numerator + "/" + denominator + ", " + rootsSolver(quotient);}
            }
         }
        }
        return "No rational roots found!";
    }

  public static int[] syntheticDivision(int[] polynomial, int numerator, int denominator){
    /* An application of synthetic division! Involves a rational root (numerator/denominator) that is a factor of the polynomial
    First coefficient of the quotient polynomial is the same as the first coefficient of the original polynomial
    Thereafter, multiply the previous coefficient by the root (numerator/denominator),
    and add the corresponding coefficient in the original polynomial */

    // P. S. using (quotient[i - 1] * numerator)/denominator avoids potential issues with integer combinations
    // Multiplying quotient[i - 1] * (numerator/denominator) might lead to an erroneous value
    // e.g. 3 * (2/3) returning 0 vs (3 * 2)/3 returning 2 as expected - hence int numerator and denominator used instead of double fraction

        int[] quotient = new int[polynomial.length - 1];
        quotient[0] = polynomial[0];
        for(int i = 1; i < quotient.length; i++){
            quotient[i] = (quotient[i - 1] * numerator)/denominator + polynomial[i];
        }
        return quotient;
    }

	public static int[] factors(int val){
		 // return an array of all the (positive) factors of a given val
			if (val == 0){
            	return new int[1]; // if the last term of a polynomial is 0, then it is factorable by x and x = 0 is a factor
							// e. g. 5x^3 + 40x^2 + 30x + 0 = 0  being factored as x(5x^2 + 40x + 30)= 0, x = 0 is a factor
      }

	    val = Math.abs(val); // Use the absolute value of the value while finding factors - (+) and (-) factors are both applied anyway
	    int counter = 0;
	    for(int i = 1; i <= val; i++){
	        if (val % i == 0){counter++;}
	    }
	    int[] factors = new int[counter];
	    int index = 0;
	    for(int i = 1; i <= val; i++){
	        if(val % i == 0){factors[index] = i; index++;}
	    }
	    return factors;
	}
}
