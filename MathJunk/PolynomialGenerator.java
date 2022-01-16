import java.util.Arrays;
public class PolynomialGenerator{
	public static int permutationsSum(int[] elements, int numChosen){
    /* Generate all possible lists of a chosen length. Determine the product of each list, and sum up all of the values.
    e. g. [2, 3, 5] with 2 elements chosen has the lists of [2, 3], [3, 5], [2, 5]. 
    Each has a product of 6, 15, and 10 respectively, and their sum is 31, so return 31 
    *** As of now, the method involves redundant recursion, so permutationsSum([2, 3, 5], 1) would return 20 (2 + 3 + 5 + 2 + 3 + 5) instead of 10 (2 + 3 + 5)
    Adjusted for by dividing by factorial in the polynomialGenerator method - Needs to be fixed ***
    */
        if(elements.length == numChosen){ 
	        int product = 1;
	        for(int i = 0; i < elements.length; i++){product *= elements[i];}
	        return product;
	    }
	    
	    int output = 0;
	    for(int i = 0; i < elements.length; i++){
	        output += permutationsSum(cutterCopy(elements, i), numChosen);
	    }    
	    
	    return output;
	}
	public static int[] cutterCopy(int[] elements, int notSelected){
	    // Copy elements of elements into array except the element at the index of the notSelected
	    
	    int[] copyArray = new int[elements.length - 1];
	    int index = 0;
	    for(int i = 0; i < elements.length; i++){
	        if(i != notSelected){
	            copyArray[index] = elements[i]; 
	            index++;
	        }
	    }
	    return copyArray;
	}
	
	public static int[] polynomialGenerator(int[] roots){
	    // For any given int array of roots, return an int array of the coefficients of the polynomial using Viete's formulas
	    // Index starts at leading term with 0, then increments
	    // If the index of a coefficient is even, then keep it positive, else negate it
	    // Apply permutationsSum as per Viete's formula - sum of the lists of the length index
	    
	    int[] polynomial = new int[roots.length + 1];
	    for(int i = 0; i < polynomial.length; i++){
	        int absTerm = permutationsSum(roots, i) / factorial(roots.length - i);
	        
	        if(i % 2 == 1){polynomial[i] = -absTerm;}
	        else{polynomial[i] = absTerm;}
	    }
	    return polynomial;
	}
	
	public static int factorial(int num)  throws IllegalArgumentException{
	    if(num < 0){throw new IllegalArgumentException();}
	    int output = 1;
	    for(int i = 1; i <= num; i++){output *= i;}
	    return output;
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		int[] roots = {2, 3, 5, 7};
		System.out.println(permutationsSum(roots, 2));
		System.out.println(permutationsSum(roots, 1));
		
		System.out.println(Arrays.toString(polynomialGenerator(roots)));
	}
}
