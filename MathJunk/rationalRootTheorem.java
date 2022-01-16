public class rationalRootTheorem{
	public static String rrt(int[] coefficients){
	 int[] firstFactors = factors(coefficients[0] ); // Determine leading term's factors
	 int[] lastFactors = factors(coefficients[coefficients.length - 1]); // Determine final term's factors
	 int numTerms = coefficients.length; // # of terms
	 int sumNum = 0; // Stores the sum of the "numerator"
	 
	 for(int i = 0; i < firstFactors.length; i++){
	     for(int j = 0; j < lastFactors.length; j++){
	        sumNum = 0;
	        int numerator = firstFactors[i];
	        int denominator = lastFactors[j];
	        
	        for(int k = 0; k < numTerms; k++){
	            sumNum += coefficients[k] * (int) Math.pow(numerator, numTerms - k - 1) * (int) Math.pow(denominator, k);
	        }
	        if (sumNum == 0){return lastFactors[j] + "/" + firstFactors[i]; }
	        
	        sumNum = 0;
	        for(int k = 0; k < coefficients.length; k++){
	            sumNum += coefficients[k] * (int) Math.pow(-numerator, numTerms - k - 1) * (int) Math.pow(denominator, k);
	        }
	        if(sumNum == 0){return "-" + lastFactors[j] + "/" + firstFactors[i];}
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
		// System.out.println(rrt(new int[] {5, -16, 9, 20}));
		// System.out.println(rrt(new int[] {2, -1, 0, 9}));
		// System.out.println(rrt(new int[] {3, -2, 1, -10})); 
		// System.out.println(rrt(new int[] {2, -9, 15, -9})); 
		// System.out.println(rrt(new int[] {3, 4, 2, -4}));
		// System.out.println(rrt(new int[] {5, 8, 13, 6})); 
	}
}
