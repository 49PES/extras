public class QuadraticSolver{
	public static String quadSolver(int[] coefficients){
    int a = coefficients[0]; int b = coefficients[1]; int c = coefficients[2];
    double posTerm, negTerm;
    
    int discriminant = (int) Math.pow(b, 2) - 4 * a * c;
    
    if(discriminant >= 0){
        posTerm = (-b + Math.sqrt(discriminant))/(2 * a);
        if((double) (int) posTerm == posTerm){
            negTerm = (-b - Math.sqrt(discriminant))/(2 * a);  
            return String.format("%d, %d", (int) posTerm, (int) negTerm);}  
       else{
            int maxSquare = maxSquareFactor(discriminant);
            discriminant = discriminant/((int) Math.pow(maxSquare, 2));
            int gcd = gcdER(gcdER(b, maxSquare), 2 * a);
            if(discriminant == 1){
                return String.format("%1$d/%3$d, %2$d/%3$d", (-b + maxSquare)/gcd, (-b - maxSquare)/gcd, (2 * a)/gcd);
            }
            if(maxSquare == 1){
                return String.format("(%1$d + √%2$d)/%3$d, (%1$d - √%2$d)/%3$d", -b, discriminant, 2 * a); 
            }
             if(gcd == 2 * a){
                return String.format("%1$d + %2$d√%3$d, %1$d - %2$d√%3$d", -b/gcd, maxSquare/gcd, discriminant);
            }
            if(-gcd == 2 * a){
                return String.format("%1$d + %2$d√%3$d, %1$d - %2$d√%3$d", b/gcd, maxSquare/gcd, discriminant);
            } 
            
            if(gcd > 1){
                if(maxSquare/gcd != 1){
                    return String.format("(%1$d + %2$d√%3$d)/%4$d, (%1$d - %2$d√%3$d)/%4$d", -b/gcd, maxSquare/gcd, discriminant, (2 * a)/gcd);
                }
                else{
                    return String.format("(%1$d + √%2$d)/%3$d, (%1$d - √%2$d)/%3$d", -b/gcd, discriminant, (2 * a)/gcd);
                }
                }        
            } 	            
        }
    
    else{
        discriminant *= -1;
        int maxSquare = maxSquareFactor(discriminant);
        int gcd = gcdER(gcdER(-b, maxSquare), 2 * a);
        if(gcd == 2 * a){
            if(discriminant == 1){return String.format("%1$d + i%2$d, %1$d - i%2$d", -b/gcd, maxSquare/gcd);}
        }
        /* if(discriminant != 1){
            return String.format("");
        } */
    }
    return "";

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
	    int[] strawberry = {1, -10, 13};
		System.out.println("Hello World");
		System.out.println(quadSolver(strawberry));
	}
}
