public class Reflect{
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
	}
}
