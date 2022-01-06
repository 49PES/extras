import java.util.Arrays;

class Matrix{
    private double[][] matrix;
    Matrix(double[] elements, int rows, int columns){
        if (rows * columns != elements.length) {throw new RuntimeException("Invalid dimensions for matrix multiplication.");}
        matrix = new double[rows][columns];
        int index = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                matrix[i][j] = elements[index];
                index++;
            }
        }
    }
   
    public void multiply(Matrix Transform){
	    // Transform this matrix by the transform matrix
	    double[][] A = this.matrix;
	    double[][] B = Transform.matrix;
	    
	    // Throw a run time exception if the # of columns of A != # of rows of B
	    // Matrix multiplication would be invalid otherwise
	    if(A[0].length != B.length){throw new RuntimeException("Mismatch in dimensions.");}
	    
	    // The product matrix has as many rows as A and as many columns as B
	    // (e. g. 4 x 3 matrix * 3 x 2 matrix = 4 x 2 matrix) 
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
	   this.matrix = product;
	}
	
	public void rotateCW(double angle){
	   // Rotates a 2 x 2 matrix clockwise by the given angle quantity 
	    double[] transformElements = new double[4];
	    transformElements[0] = Math.cos(angle * Math.PI / 180);
	    transformElements[1] = Math.sin(angle * Math.PI / 180);
	    transformElements[2] = -transformElements[1]; 
	    transformElements[3] = transformElements[0];
	    
	    Matrix Rotate = new Matrix(transformElements, 2, 2);
	    this.multiply(Rotate);
	    
	}
    public String matrixToString(){
        String output = "";    
        for(int i = 0; i < matrix.length; i++){output += "\n" + Arrays.toString(matrix[i]);}
        return output;
    }
  public static void main(String[] args) {
		double[] elements = {2, 0, 0, 0, 2, 0, 0, 0, 2};
		Matrix identityCrisis = new Matrix(elements, 3, 3);
		System.out.println(identityCrisis.matrixToString() );
		double[] elements2 = {1, 2, 21, 7, 8, 21, 11, 8, 21};
		Matrix numbers = new Matrix(elements2, 3, 3);
		System.out.println(numbers.matrixToString() );
		numbers.multiply(identityCrisis);
		System.out.println(numbers.matrixToString() );
		
		double[] bobby = {5, 7, 9, 11};
		Matrix odd = new Matrix(bobby, 2, 2);
		System.out.println(odd.matrixToString() ); 
		odd.rotateCW(90);
		System.out.println(odd.matrixToString() ); 
	}
}
