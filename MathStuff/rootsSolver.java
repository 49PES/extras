public class rootsSolver{
	
	public static String rootsSolver(int[] coefficients){
        if(coefficients.length == 2){
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
            // e. g. (5(-4/5)^3     - 16(-4/5)^2      + 9(-4/5)^1 +   20 )          * 5^3 = 
            //       5(-4)^3(5)^0  - 16(-4)^2(5)^1   +9(-4)^1(5)^2  + 20(-4)^0(5)^3

            for(int k = 0; k < numTerms; k++){
                sumNumPos += coefficients[k] * (int) Math.pow(numerator, numTerms - k - 1) * (int) Math.pow(denominator, k);
                sumNumNeg += coefficients[k] * (int) Math.pow(-numerator, numTerms - k - 1) * (int) Math.pow(denominator, k);
            }
            
            if(sumNumPos == 0 || sumNumNeg == 0){
                if(sumNumNeg == 0){numerator *= -1;}
		    
                int[] quotient = syntheticDivision(coefficients, numerator, denominator); // Apply synthetic division to get quotient polynomial to be recursed upon 
                if(denominator == 1) {return String.format("%d, ", numerator) + rootsSolver(quotient);} 
                else{return String.format("%d/%d, ", numerator, denominator) + rootsSolver(quotient);} 
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
	    val = Math.abs(val);
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
	
	public static void main(String[] args) {
	    int[] coefficients1 = {1, 6, 5}; // x^2 + 6x + 5 = 0
	    int[] coefficients2 = {2, 11, 47, -124, -510}; // 2x^4 + 11x^3 + 47x^2 - 124 = 0
	    int[] coefficients3 = {3, 7, -31, -167, -52}; // 3x^4 + 7x^3 - 31x^2 -167x - 52 = 0
	    int[] coefficients4 = {2, -3, 1}; 
	    int[] coefficients5 = {2, -9, 23, -31, 15};
	    int[] coefficients6 = {3, 41, 193, 279, -116};
	    
		System.out.println(rootsSolver(coefficients2));
		System.out.println(rootsSolver(coefficients3));
		System.out.println(rootsSolver(coefficients5));
		System.out.println(rootsSolver(coefficients6));
	}
}
