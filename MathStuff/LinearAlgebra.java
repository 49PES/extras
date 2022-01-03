import java.util.Arrays;
public class Main{
	public static double[][] multiply(double[][] A, double[][] B){
	    // Returns product AB
	    
	    // Throw a run time exception if the # of columns of A != # of rows of B
	    // Matrix multiplication would be invalid otherwise
	    if(A[0].length != B.length){throw new RuntimeException();}
	    
	    // The product matrix has as many rows as A and as many columns as B (e. g. 4 x 3 matrix * 3 x 2 matrix = 4 x 2 matrix) 
	    double[][] product = new double[A.length][B[0].length];
	    
	    for(int row = 0; row < A.length; row++){
	        for(int column = 0; column < B[0].length; column++){
	            // cr is a generic term that represents the # of columns of A / # of rows of B
	            // Used to take the dot product of a given row of A / column of B
	            for(int cr = 0; cr < A[0].length; cr++){
	                product[row][column] += A[row][cr] * B[cr][column]; 
	                // P_{a, b} = sum_{n = 0}^{cr} A_{a}{cr} B_{cr}{b}
	            }
	        }
	    }
	    return product;
	}
	/*
	public static double[][] rotateCW(double[][] matrix, double angle){
	    
	    double[][] CWRotation = {{Math.cos(angle),  -Math.sin(angle) }, {Math.sin(angle), Math.cos(angle)}};
	    return multiply(CWRotation, matrix); 
	} */
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		double[][] A = {{2, 0}, {0, 2} };
		double[][] B = {{5, 7}, {7, 5} };
		double[][] product = multiply(A, B);
		for(int i = 0; i < product.length; i++){
		    System.out.println(Arrays.toString(product[i] ) );
		}
		/*
		double[][] rotatedMatrixB = rotateCW(B, Math.PI / 2);
		for(int i = 0; i < rotatedMatrixB.length; i++){
		    System.out.println(Arrays.toString(product[i] ) );
		} */
		
	}
}
