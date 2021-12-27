public class Maff{
    
    // Return greatest common divisor of two numbers
    public static int gcd( int n, int d ) {
        int a, b, x;
        a = n;
        b = d;
        while( a % b != 0 ) {
          x = a;
          a = b;
          b = x % b;
    }

    return b;
  } 

	// Return least common multiple of two numbers
	public static int lcm(int a, int b){
	    return(a * b / gcd(a, b));
	}
	
    // For n, return n!
	public static int factorial(int num){
	    int product = 1;
	    for(int i = 1; i <= num; i++){ product *= i;}
	    return product;
	}
	
	// n-permute-k operation = n!/(n - k)!, or n * (n - 1) * (n - 2) * ...(n - (k - 1)) {first k terms of n!}
	public static int permute(int elements, int chosen){
	    int product = 1;
	    for(int i = elements; i > elements - chosen; i--){product *= i;}
	    return product;
	}
	
	// n-choose-k operation nCk = n! / (k! (n - k)!) = nPk/k!
	// optimized using permutation and factorial, factorial of the lower complement between n - k & k
	public static int choose(int elements, int chosen){
	    if(elements - chosen < chosen) return permute(elements, chosen) / factorial(chosen);
	    else return permute(elements, elements - chosen) / factorial(elements - chosen);
	}
	
	public static double heron(int a, int b, int c){
	    double s = (double) (a + b + c) / 2.0;
	    s *= (s - a) * (s - b) * (s - c);
	    return Math.sqrt(s);
	}
	
	public static double distance(int x1, int y1, int x2, int y2){
	    return Math.sqrt( Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2) );
	}
	
	/* public static double lawOfCosines(float a, float b, float c, String type){
	    // Law of Cosines (measures in degrees)
	    // c^2 = a^2 + b^2 - 2abcos@
	    
	    if(type.equals("hypotenuse")){
	        // a, b = sides, c = angle, determine hypotenuse length
	        // c = sqrt(a^2 + b^2 - 2abcos@)
	        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) - ( 2 * a * b * Math.cos(c * Math.PI / 180) ) ); 
	    }
	    else if(type.equals("leg")){
	        // a = side, b = hypotenuse, c = angle
	        // a = sqrt(c^2 - b^2 - 2abcos@)
	        return Math.sqrt(Math.pow(a, 2) - Math.pow(b, 2) + ( 2 * a * b * Math.cos(c * Math.PI / 180) ) );
	    }
	    else if(type.equals("angle") ){
	        // a, b, c = sides 
	        // c^2 = a^2 + b^2 - 2abcos@
	        // c^2 - a^2 - b^2 = -2abcos@
	        // arccos((a^2 + b^2 - c^2)/(2ab)) = @ 
	        
	        // Multiply @ by pi/180 to get @ in degrees
	        
	        return Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2) ) / (2 * a * b)  ) * 180 / Math.PI;
	    } 
	    
	    System.out.println("Invalid type, try again! 'hypotenuse', 'leg', or 'angle' ");   
	    return 0;
	} */
	
	/* public static double lawOfSines(float a, float b, float c, String type){
	    // sinA/a = sinB/b  <- Two sides and an angle, or two angles and a side
	    if(type.equals("side")){
	        // a = side 1, b = angle 1, c = angle 2 
	        // a/sinA = b/sinB, asinB/sinA = b 
	        return (double) (a * Math.sin(c * 180 / Math.PI)  / Math.sin(b * 180 / Math.PI)  );
	    }
	    
	    else if (type.equals("angle") ){
	        // a = side 1, b = side 2, c = angle 1 
	        // sinA/a = sinB/b 
	        // bsinA/a = sinB 
	        // B = arcsin(bsinA/a)
	        return (double) Math.asin(b * Math.sin(c * 180 / Math.PI) / a ) * 180 / Math.PI ;
	    }
	    
	    System.out.println("Invalid type! Try 'side' or 'angle' ");
	    return 0;
	} */
	public static double pythag(float a, float b, String type){
	    if(type.equals("hypotenuse") ) {return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) );}
	    else if (type.equals("leg") ) {return Math.sqrt(Math.pow(b, 2) - Math.pow(a, 2) ); }
	    System.out.println("Invalid type! Try 'hypotenuse' or 'leg'.");
	    return 0;
	}
    public static void main(){
    }
}
