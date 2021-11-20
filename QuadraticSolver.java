public class QuadraticSolver{
	public static String quadSolver(int[] coefficients){
	    int a = coefficients[0]; int b = coefficients[1]; int c = coefficients[2];
	    double posTerm, negTerm;
	    
	    int discriminant = (int) Math.pow(b, 2) - 4 * a * c;
	    
	    if(discriminant > 0){
	        posTerm = (-b + Math.sqrt(discriminant))/(2 * a);
	        if((double) (int) posTerm == posTerm){
	            negTerm = (-b - Math.sqrt(discriminant))/(2 * a);  
	            return (String.valueOf((int) posTerm) + ", " + String.valueOf((int) negTerm));} 
	       else{
	            int maxSquare = maxSquareFactor(discriminant);
	            discriminant = discriminant/((int) Math.pow(maxSquare, 2));
	            int gcd = gcdER(gcdER(b, maxSquare), 2 * a);
	            
	            if(maxSquare == 1){
	                return "(" + -b + " + √" + discriminant + ")/" + String.valueOf(2 * a) + 
	                ", (" + -b + " - √" + discriminant + ")/" + String.valueOf(2 * a);}
	                
                if(gcd == 2 * a){
                    return String.valueOf(-b/gcd) + " + " + String.valueOf(maxSquare/gcd) + "√" + discriminant + 
	                ", " + String.valueOf(-b/gcd) + " - " + String.valueOf(maxSquare/gcd) + "√" + discriminant;
                }
                
                if(gcd == -2 * a){
                    return String.valueOf(b/gcd) + " + " + String.valueOf(maxSquare/gcd) + "√" + discriminant + 
	                ", " + String.valueOf(b/gcd) + " - " + String.valueOf(maxSquare/gcd) + "√" + discriminant;
                }
                
                if(gcd > 1){
                    if(maxSquare/gcd != 1){
                        return "(" + String.valueOf(-b/gcd) + " + " + String.valueOf(maxSquare/gcd) + "√" + discriminant + ")/" + String.valueOf((2 * a)/gcd) + 
                            ", (" + String.valueOf(-b/gcd) + " + " + String.valueOf(maxSquare/gcd) + "√" + discriminant + ")/" + String.valueOf((2 * a)/gcd);}
                    else{
                        return "(" + String.valueOf(-b/gcd) + " + √" + discriminant + ")/" + String.valueOf((2 * a)/gcd) + 
                            ", (" + String.valueOf(-b/gcd) + " + √" + discriminant + ")/" + String.valueOf((2 * a)/gcd);}
                    }        
                } 	            
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
	    for(int i = 1; i <= Math.sqrt(val); i++){
	        if(val/(Math.pow(i, 2)) % 1.0 == 0.0){max = i;}
	    }
	    return max;
	}
	public static void main(String[] args) {
		System.out.println("Hello World");
		int[] quad1 = {1, 6, 5};
		int[] quad2 = {1, -6, 5};
		int[] quad3 = {7, -100, 59};
		System.out.println(quadSolver(quad1));
		System.out.println(quadSolver(quad2));
		System.out.println(quadSolver(quad3));
	}
}
