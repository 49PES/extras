public class Transformations{
	public static Rational[] reflectPoint(Rational[] constants){
        /* Reflecting (a, b) over y = mx + c 
        y = mx + c, 
        perp slope = -1/m, perp line that passes through (a, b): y = (-1/m)(x - a) + b
        mx + c = (-1/m)(x - a) + b 
        m^2(x)  + cm = a - x + bm 
        m^2(x) + x = a + bm - cm 
        x(m^2 + 1) = a + bm - cm 
        x = (a + bm - cm)/(m^2 + 1) 
        Intersection point of the perpendicular lines = midpoint of the initial & reflected points
        */
        Rational a = constants[0];
        Rational b = constants[1];
        Rational m = constants[2];
        Rational c = constants[3];
        
	Rational aCopy = new Rational(a);
        Rational bCopy = new Rational(b);
        Rational cCopy = new Rational(c);
        Rational mCopy = new Rational(m);	
        
        Rational midX = new Rational();
        
        
        /* Determine x coordinate of mid point between initial point and reflected point */
        bCopy.multiply(m); // bm
        cCopy.multiply(m); // cm
        mCopy.multiply(m); mCopy.add(new Rational(1)); // m^2 + 1
        
        midX.add(a); // a
        midX.add(bCopy); // a + bm
        midX.subtract(cCopy); // a + bm - cm
        midX.divide(mCopy); // (a + bm - cm) / (m^2 + 1) = x
        /*                                                                         */
		
        Rational midY = new Rational(midX); // y = x
        midY.multiply(m); 		   // y = mx
        midY.add(c);                       // y = mx + c
            
		
	Rational reflectedXCor = new Rational(midX);
	Rational reflectedYCor = new Rational(midY);
	    
	reflectedXCor.multiply(new Rational(2)); // 2x
	reflectedXCor.subtract(a); // 2x - a
	    
	reflectedYCor.multiply(new Rational(2)); // 2y
	reflectedYCor.subtract(b); // 2y - a

	    reflectedXCor.reduce();
	    reflectedYCor.reduce();
	    System.out.println( reflectedXCor.toString() );
	    System.out.println( reflectedYCor.toString() );
	    
	    Rational[] reflectedCoordinates = new Rational[2];
	    reflectedCoordinates[0] = reflectedXCor;
	    reflectedCoordinates[1] = reflectedYCor;
	    return reflectedCoordinates;
	}
	
	public static Rational[] reflectPoint(int[] constants){
		Rational[] rationals = new Rational[4];
		for(int i = 0; i < 4; i++) { rationals[i] = new Rational(constants[i]); }
		return reflectPoint(rationals);
	}
	
	public static double[] rotatePoint(Rational[] constants, int angle){
	    /* Rotate a point clockwise by the given angle (given in degrees) */
	    /* Clockwise Rotation matrix: 
	    	[cos@  sin@]
		[-sin@ cos@]
	   Applied to a point, [a, b],
	   [cos@ sin@]  [a]  = [acos@ + bsin@] // rotated x coordinate
	   [-sin@ cos@] [b]  = [-asin@ + bcos@] // rotated y coordinate
	   
	   Return the rotated coordinates
	    */
		
	    double xCor = constants[0].floatValue();
	    double temp = constants[0].floatValue();
	    double yCor = constants[1].floatValue();
	    double cos = MathC.cosd(angle);
	    double sin = MathC.sind(angle);
	    
	    xCor = xCor * cos + yCor * sin;
	    yCor = temp * -sin  + yCor * cos;
	    
	    System.out.println("(" + xCor + ", " + yCor + ")");
	    double[] coordinates = new double[2];
	    coordinates[0] = xCor; coordinates[1] = yCor;
	    return coordinates;
	}
	public static float[] dilatePoint(float[] dilationCenter, float[] point, float dilationFactor){
	    float a = dilationCenter[0], b = dilationCenter[1], c = point[0], d = point[1];
	    float[] dilatedCoordinates = new float[2];
	    dilatedCoordinates[0] = a + dilationFactor * (c - a);
	    dilatedCoordinates[1] = b + dilationFactor * (d - b);
	    return dilatedCoordinates;
	}
	
	public static float[] translatePoint(float[] coordinates, float[] translation){
	    coordinates[0] += translation[0];
	    coordinates[1] += translation[1];
	    return coordinates;
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		Rational[] problemOne = new Rational[4];
		problemOne[0] = new Rational(2, 1);
		problemOne[1] = new Rational();
		problemOne[2] = new Rational(2, 1);
		problemOne[3] = new Rational(1, 1);
		reflectPoint(problemOne);
		
		Rational[] problemTwo = new Rational[4];
		problemTwo[0] = new Rational(8);
		problemTwo[1] = new Rational();
		problemTwo[2] = new Rational(2);
		problemTwo[3] = new Rational(1);
		reflectPoint(problemTwo);
		
		Rational[] initialCoordinates = new Rational[2];
		initialCoordinates[0] = new Rational(4);
		initialCoordinates[1] = new Rational(-2);
		rotatePoint(initialCoordinates, 90); // (4, -2) after a 90 CW rotation -> (-2, -4)
        	float[] dC = {5.0F, 5.0F}; float[] p = {7.0F, 6.0F};
        	System.out.println(Arrays.toString(dilatePoint(dC, p, 4.0F)));  // dilate (7, 6) by a factor of 4 about (5, 5)
        	System.out.println(Arrays.toString(translatePoint(dC, p))) ; // translate (5, 5) by a vector (7, 6)
		
	}
}
