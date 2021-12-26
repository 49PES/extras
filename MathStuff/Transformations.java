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
        /*
        Rational midX = new Rational(a.add(b.multiply(m)).subtract(c.multiply(m) ).divide( m.multiply(m).add(1) ) ); */
        Rational midX = new Rational();
        /* Rational midY = new Rational(); */
        
        Rational aCopy = new Rational(a);
        Rational bCopy = new Rational(b);
        Rational cCopy = new Rational(c);
        Rational mCopy = new Rational(m);
        
        bCopy.multiply(m);
        cCopy.multiply(m);
        mCopy.multiply(m);
        mCopy.add(new Rational(1, 1) );
        
        midX.add(a);
        midX.add(bCopy);
        midX.subtract(cCopy);
        midX.divide(mCopy);
        
        Rational midY = new Rational(midX);
        midY.multiply(m);
        midY.add(c);
        
        
        /*
        midX = midX.add(a).add( b.multiply(m) ).subtract(c.multiply(m) );
        midX = midX.divide(m.multiply(m).add(1)  );
        midY = midY.add(midX.multiply(m).add(c)); 
        */
	    
	    Rational reflectedXCor = new Rational(midX);
	    Rational reflectedYCor = new Rational(midY);
	    
	    reflectedXCor.multiply(new Rational(2));
	    reflectedXCor.subtract(a);
	    reflectedYCor.multiply(new Rational(2));
	    reflectedYCor.subtract(b);
	    
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
	    
	    double xCor = constants[0].floatValue();
	    double temp = constants[0].floatValue();
	    double yCor = constants[1].floatValue();
	    double cos = Math.cos(angle * Math.PI / 180 );
	    double sin = Math.sin(angle * Math.PI / 180);
	    
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
