public class rootsSolver{
	public static String quadSolver(int[] coefficients){
    int a = coefficients[0]; int b = coefficients[1]; int c = coefficients[2];
    double posTerm, negTerm;
    
    int discriminant = (int) Math.pow(b, 2) - 4 * a * c;
    
    if(discriminant >= 0){
        posTerm = (-b + Math.sqrt(discriminant))/(2 * a);
        if(posTerm % 1 == 0){ 
            negTerm = (-b - Math.sqrt(discriminant))/(2 * a);  
            return String.format("%d, %d", (int) posTerm, (int) negTerm);}   // Integer values, like -5 and -1
        
        int maxSquare = maxSquareFactor(discriminant); // Factor out the greatest square from the discriminant
        discriminant = discriminant/((int) Math.pow(maxSquare, 2)); // Here on out, discriminant represents the (simplified) value inside the square root
        int gcd = gcdER(gcdER(b, maxSquare), 2 * a); // Determine the gcd of the -b, max square factor of the discriminant, and the 2a denominator for simplification purposes
        
        if(discriminant == 1){
            return String.format("%1$d/%3$d, %2$d/%3$d", (-b + maxSquare)/gcd, (-b - maxSquare)/gcd, (2 * a)/gcd); // Purely rational values, like 7/3 or 11/3
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
        if ( a == b ) {
            return a;
         }
        else if ( a > b ) {
            return gcdER( b, a - b );
        }
        else {
         return gcdER( a, b - a );
        }
     }

	public static int maxSquareFactor(int val){
	    int max = 1;
	    int num = val;
	    for(int i = 2; i <= num; i++){
	        while(num % Math.pow(i, 2) == 0){
	            max *= i;
	            num /= Math.pow(i, 2);
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
            // Application of the Rational Root Theorem
            int numerator = lastFactors[j];  // Factors of final term -> numerator of potential factor
            int denominator = firstFactors[i]; // Factors of leading term -> denominator of potential factor
             
             // For any given value as ax^b, a rational root can be represented as n/d, so ax^b = a(n/d)^b 
             // If you multiply all of the terms by the leading term's degree, then the terms can be represented as a(n)^(b)d^(degree - b)
             // e. g. (5(-4/5)^3     - 16(-4/5)^2      + 9(-4/5)^1 +   20 )          * 5^3 = 
             //       5(-4)^3(5)^0  - 16(-4)^2(5)^1   +9(-4)^1(5)^2  + 20(-4)^0(5)^3
             
            for(int k = 0; k < numTerms; k++){
                sumNumPos += coefficients[k] * (int) Math.pow(numerator, numTerms - k - 1) * (int) Math.pow(denominator, k);
                sumNumNeg += coefficients[k] * (int) Math.pow(-numerator, numTerms - k - 1) * (int) Math.pow(denominator, k);
            }
            if (sumNumPos == 0 || sumNumNeg == 0){
                int[] copyArray = new int[coefficients.length - 1];
                copyArray[0] = coefficients[0];
                for(int l = 1; l < copyArray.length; l++){
                    if(sumNumPos == 0) {copyArray[l] = (copyArray[l - 1] * numerator)/denominator + coefficients[l];}
                    else {copyArray[l] = (copyArray[l - 1] * -numerator)/denominator + coefficients[l];}
                }
                if(sumNumPos == 0){
                    if(denominator == 1) {return String.format("%d, ", numerator) + rootsSolver(copyArray);} 
                    else{return String.format("%d/%d, ", numerator, denominator) + rootsSolver(copyArray);}
                }
                if(denominator == 1) {return String.format("%d, ", -numerator) + rootsSolver(copyArray); }
                else{return String.format("%d/%d, ", -numerator, denominator) + rootsSolver(copyArray);}
            }
         }
        
    }
    return "";
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
		System.out.println("Hello World");
		System.out.println(quadSolver(coefficients1));
		System.out.println(rootsSolver(coefficients2));
	}
}
