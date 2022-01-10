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
    
    Matrix(double[][] elements){
        matrix = elements;
    }   
   
    public void multiply(Matrix transform){
	    // Transform this matrix by the transform matrix
	    // [Transform][this]
	    double[][] A = transform.matrix;
	    double[][] B = this.matrix;
	    
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
	    transformElements[2] = -Math.sin(angle * Math.PI / 180); 
	    transformElements[3] = Math.cos(angle * Math.PI / 180); 
	    // [cos@ sin@ ]
	    // [-sin@ cos@]
	    Matrix rotate = new Matrix(transformElements, 2, 2);
	    this.multiply(rotate); 
	}
	
	public void rotateCCW(double angle){
	   // Rotates a 2 x 2 matrix counterclockwise by the given angle quantity 
	    rotateCW(-angle);
	}
	
	public void reflect(double m){
	    // reflect a 2D point / vector over the line y = mx
	  // Rotate point and reflection line until line is on x-axis
	  // Negate j hat to reflect over x-axis
	  // Rotate point and reflection line counterclockwise till line is back to original position
	  // https://math.stackexchange.com/questions/525082/reflection-across-a-line 
	  
	   double[] reflectionElements = new double[4];
	   reflectionElements[0] = (1 - m * m) / (m * m + 1);
	   reflectionElements[1] = 2 * m;
	   reflectionElements[2] = 2 * m;
	   reflectionElements[3] = (m * m - 1) / (m * m + 1);
        Matrix reflect = new Matrix(reflectionElements, 2, 2);
        this.multiply(Reflect);
	   
	}
	
	public void dilate(double scaleFactor){
	    if(this.matrix.length != this.matrix[0].length){ System.out.println("Not a square matrix - no change made");  return; }
	    double[][] dilationElements = new double[this.matrix.length][this.matrix.length];
	    for(int i = 0; i < dilationElements.length; i++){
	        dilationElements[i][i] = scaleFactor;
	    }
	    
	    Matrix dilation = new Matrix(dilationElements);
	    
	    this.multiply(dilation);
	}
	
    public String matrixToString(){
        String output = "";    
        for(int i = 0; i < matrix.length; i++){output += "\n" + Arrays.toString(matrix[i]);}
        return output;
    }
  
}
