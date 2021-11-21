public class QuadraticSolver{
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
    
        if(gcd == Math.abs(2 * a)){
            if(discriminant == 1){
                if(gcd == maxSquare){return String.format("%d + i, %d - i", -b/gcd;} 
                return String.format("%1$d + %2$di, %1$d - %2$di", -b/gcd, maxSquare/gcd);
            } 
            return String.format("%1$d + %2$di√%3$d, %1$d - %2$di√%3$d", -b/gcd, maxSquare/gcd, discriminant);
        }
        if(discriminant == 1){return String.format("(%1$d + %2$di)/%3$d, (%1$d - i%2$d)/%3$d", -b/gcd, maxSquare/gcd, (2 * a)/gcd);}
        return String.format("(%1$d + %2$di√%3$d)/%4$d, (%1$d - i%2$d√%3$d)/%4$d", -b/gcd, maxSquare/gcd, discriminant, (2 * a)/gcd);
        
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
	
	public static void main(String[] args) {
	    int[] strawberry = {1, 6, 5};
		System.out.println("Hello World");
		System.out.println(quadSolver(strawberry));
	}
}
