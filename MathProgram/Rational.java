public class Rational implements Comparable{
    private int _numerator;
    private int _denominator;
    
    public Rational() {
        _numerator = 0;
        _denominator = 1;
    }

    public Rational(int num){
        this(num, 1); // Constructor for int(eger) values
    }

    public Rational(Rational other){
        this(other._numerator, other._denominator); // Copy constructor
    }

    public Rational( int n, int d ) {
        this();
        if ( d != 0 ) {
            _numerator = n;
            _denominator = d;
        }
        else {
            System.out.println( "Invalid number. " +
                    "Denominator set to 1." );
        }
    }


    public String toString() {
        if(_denominator == 1) return _numerator + "";
        return _numerator + "/" + _denominator;
    }

    public double floatValue() {
        return (double)_numerator / _denominator;
    }

    public void multiply( Rational r ) {
        _numerator   = this._numerator   * r._numerator;
        _denominator = this._denominator * r._denominator;
    }

    public void divide( Rational r ) {
        if ( r._numerator != 0 ) {
            _numerator   = _numerator   * r._denominator;
            _denominator = _denominator * r._numerator;
        }
        else {
            System.out.println( "Div by 0 error. Values unchanged." );
        }
    }

    public void add( Rational r ) {
        _numerator = _numerator * r._denominator + r._numerator * _denominator;
        _denominator = _denominator * r._denominator;
    }

    public void subtract( Rational r ) {
        _numerator = _numerator * r._denominator - r._numerator * _denominator;
        _denominator = _denominator * r._denominator;
    }

    public void reduce()  {
        int g = MathC.gcd(_numerator, _denominator);
        _numerator = _numerator / g;
        _denominator = _denominator / g;

        if (_denominator < 0) {
            _numerator *= -1;
            _denominator *= -1;
        }
    }

    public boolean equals( Object other )  {
        if ( !(other instanceof Rational) ) {
            System.out.println("not a rational");
            return false;
        }

        this.reduce();
        ((Rational)other).reduce();

        return this == other || ( this._numerator == ((Rational)other)._numerator && this._denominator == ((Rational)other)._denominator);
    }

    public int compareTo( Object other ) {
        if ( ! (other instanceof Rational) ) {
            throw new ClassCastException("\ncompareTo() input not a Rational");
        }

        int d = _denominator * ((Rational)other)._denominator;


        int thisNumerator   = _numerator * d;
        int otherNumerator  = d * ((Rational)other)._numerator;

        return (thisNumerator - otherNumerator) * (this._denominator * ((Rational)other)._denominator);
    }

}
