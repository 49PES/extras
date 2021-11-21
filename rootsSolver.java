public class Main{
	public static String quadSolver(int[] coefficients){
    int a = coefficients[0]; int b = coefficients[1]; int c = coefficients[2]; // [a, b, c] corresponds to ax^2 + bx + c = 0 
    int discriminant = (int) Math.pow(b, 2) - 4 * a * c; // Discriminant: b^2 - 4ac, to be used to test single/double real roots or negative roots
    
    if(discriminant >= 0){
        int maxSquare = maxSquareFactor(discriminant); // Factor out the greatest square from the discriminant
        discriminant = discriminant/((int) Math.pow(maxSquare, 2)); // Here on out, discriminant represents the (simplified) value inside the square root
        int gcd = gcdER(gcdER(b, maxSquare), 2 * a); // Determine the GCD of the -b, max square factor of the discriminant, and the 2a denominator for simplification purposes
        
        if(discriminant == 1){ 
            // When the discriminant is a perfect square, there can only be rational roots (0, 1, or 2 potential integer values)
            // There are situations where there is one integer root and one rational (non-integer) root 
            // e. g. 2x^2 -3x + 1 = 0 with roots 1 and 1/2 
            // Code checks for both integer, "positive" root -> integer, "negative" root -> integer, and then neither integer
            
            int gcdPos = gcdER(-b + maxSquare, 2 * a);
            int gcdNeg = gcdER(-b - maxSquare, 2 * a);
            // gcdPos and gcdNeg represent GCD's of numerator and denominator of "positive" and "negative" roots 
            // They are divided from the numerator and denominator to reduce to simplest form
            
            if(gcdPos == Math.abs(2 * a) && gcdNeg == Math.abs(2 * a)){
                return String.format("%d, %d", (-b + maxSquare)/(2 * a), (-b - maxSquare)/(2 * a) ); 
                // Both roots are integers when their GCD's for their numerators and denominators equal the absolute value of the denominator
            }
            
            if(gcdPos == Math.abs(2 * a)){ 
                return String.format("%d, %d/%d", (-b + maxSquare)/(2 * a), (-b - maxSquare)/gcdNeg, (2 * a)/gcdNeg );
                // If the "positive" root (+ sqrt() instead of - sqrt()) is an integer 
                // (when GCD of numerator and denominator equals the absolute value of the denominator)
                // return the positive root as an integer and the negative root 
                // Same logic applied below
            }
            if(gcdNeg == Math.abs(2 * a)){
                return String.format("%d/%d, %d", (-b + maxSquare)/gcdPos, (2 * a)/gcdPos, (-b - maxSquare)/(2 * a) );
            }
            
            // If neither root is an integer, simplify the (rational) roots
            return String.format("%1$d/%2$d, %3$d/%4$d", (-b + maxSquare)/gcdPos, (2 * a)/gcdPos, (-b - maxSquare)/gcdNeg, (2 * a)/gcdNeg); 
        }
        
        if(maxSquare == 1){
            return String.format("(%1$d + √%2$d)/%3$d, (%1$d - √%2$d)/%3$d", -b, discriminant, 2 * a); // Irrational where discriminant has no square factor
        }
         if(gcd == Math.abs(2 * a)){
            return String.format("%1$d + %2$d√%3$d, %1$d - %2$d√%3$d", -b/gcd, maxSquare/gcd, discriminant); // Irrational w/out a denominator
        }
        
        
        if(maxSquare == gcd){return String.format("(%1$d + √%2$d)/%3$d, (%1$d - √%2$d)/%3$d", -b/gcd, discriminant, (2 * a)/gcd);} // Irrational where the max square factor factored from the sqrt() is equal to the gcd (e.g. 2sqrt(7) with gcd of 2 -> sqrt(7))
        return String.format("(%1$d + %2$d√%3$d)/%4$d, (%1$d - %2$d√%3$d)/%4$d", -b/gcd, maxSquare/gcd, discriminant, (2 * a)/gcd);
        }
    
    else{
        discriminant *= -1; // Factor out "i" from the discriminant, incorporate i into the string instead
        int maxSquare = maxSquareFactor(discriminant);
        int gcd = gcdER(gcdER(-b, maxSquare), 2 * a);
        discriminant = discriminant/((int) Math.pow(maxSquare, 2)); // Here on out, discriminant represents the (simplified) value inside the square root
        
        if(gcd == Math.abs(2 * a)){
            if(discriminant == 1){
                if(gcd == maxSquare){return String.format("%d + i, %d - i", -b/gcd);} 
                return String.format("%1$d + %2$di, %1$d - %2$di", -b/gcd, maxSquare/gcd);
            } 
            return String.format("%1$d + %2$di√%3$d, %1$d - %2$di√%3$d", -b/gcd, maxSquare/gcd, discriminant);
        }
        if(discriminant == 1){return String.format("(%1$d + %2$di)/%3$d, (%1$d - i%2$d)/%3$d", -b/gcd, maxSquare/gcd, (2 * a)/gcd);} 
        
        return String.format("(%1$d + %2$di√%3$d)/%4$d, (%1$d - %2$di√%3$d)/%4$d", -b/gcd, maxSquare/gcd, discriminant, (2 * a)/gcd);
        
    }
    

	}
	
	
	public static int gcdER( int a, int b ) {
        a = Math.abs(a);
        b = Math.abs(b);
	// Euclidean method of calculating GCD - decrease larger value by smaller value until both values equal each other, and return that value
	if ( a == b ) { return a;   }
        else if ( a > b ) { return gcdER( b, a - b );  }
        else { return gcdER( a, b - a ); }
     }

	public static int maxSquareFactor(int val){
	    int max = 1;
	    int num = val;
	    for(int i = 2; i <= num; i++){
	        while(num % Math.pow(i, 2) == 0){
	            max *= i;
	            num /= Math.pow(i, 2); 
			// Factor out i^2 from num and multiply the max by i to avoid redundant work 
			// e. g. factoring out 100^2 from a value can be factored out as 2^2 * 5^2 * 5^2 instead		
	        }
	    }
	   return max;
	}
	
     public static String rootsSolver(int[] coefficients){
        if(coefficients.length == 3){return quadSolver(coefficients);} // Base case for polynomials length 3+
        
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
            
            // Simplify numerator and denominator by dividing them by their GCD
            numerator /= gcdER(numerator, denominator); 
            denominator /= gcdER(numerator, denominator);                          
            
            // For any given value as ax^b, a rational root can be represented as n/d, so ax^b = a(n/d)^b 
            // If you multiply all of the terms by the leading term's degree, then the terms can be represented as a(n)^(b)d^(degree - b)
            // e. g. (5(-4/5)^3     - 16(-4/5)^2      + 9(-4/5)^1 +   20 )          * 5^3 = 
            //       5(-4)^3(5)^0  - 16(-4)^2(5)^1   +9(-4)^1(5)^2  + 20(-4)^0(5)^3

            for(int k = 0; k < numTerms; k++){
                sumNumPos += coefficients[k] * (int) Math.pow(numerator, numTerms - k - 1) * (int) Math.pow(denominator, k);
                sumNumNeg += coefficients[k] * (int) Math.pow(-numerator, numTerms - k - 1) * (int) Math.pow(denominator, k);
            }
            
            if(sumNumPos == 0){
                // Apply synthetic division to get quotient polynomial to be recursed upon
                int[] quotient = syntheticDivision(coefficients, numerator, denominator); 
                
                // For integer values (where denominator is 1) - just print the numerator and recurse 
                if(denominator == 1) {return String.format("%d, ", numerator) + rootsSolver(quotient);} 
                
                // Otherwise, print numerator/denominator and recurse
                else{return String.format("%d/%d, ", numerator, denominator) + rootsSolver(quotient);} 
            }
            
            if(sumNumNeg == 0){
                int[] quotient = syntheticDivision(coefficients, -numerator, denominator);
                if(denominator == 1) {return String.format("%d, ", numerator) + rootsSolver(quotient);} 
                else{return String.format("%d/%d, ", numerator, denominator) + rootsSolver(quotient);}
            }
         }
        
        }
        return "";
    } 
    
    public static int[] syntheticDivision(int[] polynomial, int numerator, int denominator){
    /* An application of synthetic division! Involves a rational root (numerator/denominator) that is a factor of the polynomial
    First coefficient of the quotient polynomial is the same as the first coefficient of the original polynomial
    Thereafter, multiply the previous coefficient by the root (numerator/denominator),
    and add the corresponding coefficient in the original polynomial */
    
    // P. S. using (quotient[i - 1] * numerator)/denominator avoids potential issues with integer combinations 
    // Multiplying quotient[i - 1] * numerator/denominator might lead to an erroneous value 
    // e.g. 3 * 2/3 returning 0 vs (3 * 2)/3 returning 2 as expected - hence int numerator and denominator used instead of double fraction
    
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
	    
		System.out.println(quadSolver(coefficients1));
		System.out.println(rootsSolver(coefficients2));
		System.out.println(rootsSolver(coefficients3));
		System.out.println(quadSolver(coefficients4));
		System.out.println(rootsSolver(coefficients5));
		System.out.println(rootsSolver(coefficients6));
	}
}
