public class QuadraticSolver{
	public static String quadSolver(int[] coefficients){
    int a = coefficients[0]; int b = coefficients[1]; int c = coefficients[2]; // [a, b, c] corresponds to ax^2 + bx + c = 0 
    int discriminant = (int) Math.pow(b, 2) - 4 * a * c; // Discriminant: b^2 - 4ac, to be used to test real roots for disc >= 0 or imaginary roots for disc < 0
    
    if(discriminant >= 0){
        int maxSquare = maxSquareFactor(discriminant); // Factor out the greatest square from the discriminant
        discriminant = discriminant/((int) Math.pow(maxSquare, 2)); // Here on out, discriminant represents the (simplified) value inside the square root
        int gcd = Maff.gcd(Maff.gcd(b, maxSquare), 2 * a); // Determine the GCD of the -b, max square factor of the discriminant, and the 2a denominator for simplification purposes
        
        if(discriminant == 1){ 
            // When the discriminant is a perfect square, there can only be rational roots (0, 1, or 2 potential integer values)
            // There are situations where there is one integer root and one rational (non-integer) root 
            // e. g. 2x^2 -3x + 1 = 0 with roots 1 and 1/2 
            // Code checks for both integer, "positive" root -> integer, "negative" root -> integer, and then neither integer
            
            int gcdPos = Maff.gcd(-b + maxSquare, 2 * a);
            int gcdNeg = Maff.gcd(-b - maxSquare, 2 * a);
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
        int gcd = Maff.gcd(Maff.gcd(-b, maxSquare), 2 * a);
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
	
  
	
	public static void main(String[] args) {
	    
	}
}
