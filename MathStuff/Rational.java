// Clyde "Thluffy" Sinclair
// APCS p0
// HW49 -- implementing an interface from standard library
// 2021-12-23r

/**
 * class Rational (v3)
 * stores a rational number (p/q s.t. p,q ints && q!=0)
 * facilitates
 * multiplication
 * division
 * reduction
 * comparison
 **/

public class Rational implements Comparable
{
  // Instance variables for numerator and denominator
  private int _numerator;
  private int _denominator;


  /***
   * Default constructor (no parameters)
   * @return a new Rational with value 0/1
   ***/
  public Rational()
  {
    _numerator = 0;
    _denominator = 1;
  }
    public Rational(int num){
        this(num, 1);
    }
    public Rational(Rational other){
        this(other._numerator, other._denominator);
    }
  /***
   * Overloaded constructor
   * @param numerator
   * @param denominator
   * if an invalid denominator is attempted, should print a message and set the number to 0/1
   ***/
  public Rational( int n, int d )
  {
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


  // toString
  // returns a string representation of the rational number (formatting of your choice)
  public String toString()
  {
    if(_denominator == 1) return _numerator + "";
    return _numerator + " / " + _denominator;
  }


  // floatValue
  // returns floating point approximation of the number
  // uses the most precise floating point primitive
  public double floatValue()
  {
    return (double)_numerator / _denominator;
  }


  // multiply
  // takes a Rational parameter and multiplies it by this Rational
  // does not return a value
  // modifies this object, leaves parameter alone
  // need not reduce
  public void multiply( Rational r )
  {
    _numerator   = this._numerator   * r._numerator;
    _denominator = this._denominator * r._denominator;
  }


  // divide
  // works the same as multiply, except the operation is division
  public void divide( Rational r )
  {
    if ( r._numerator != 0 ) {
      _numerator   = _numerator   * r._denominator;
      _denominator = _denominator * r._numerator;
    }
    else {
      System.out.println( "Div by 0 error. Values unchanged." );
    }
  }


  //increases this Rational by value of input Rational
  public void add( Rational r )
  {
    _numerator = _numerator * r._denominator + r._numerator * _denominator;
    _denominator = _denominator * r._denominator;
  }


  //decreases this Rational by value of input Rational
  public void subtract( Rational r )
  {
    _numerator = _numerator * r._denominator - r._numerator * _denominator;
    _denominator = _denominator * r._denominator;
  }


  //reduces this Rational to simplest fraction
  public void reduce()
  {
    int g = gcd();
    _numerator = _numerator / g;
    _denominator = _denominator / g;

    if (_denominator < 0) {
      _numerator *= -1;
      _denominator *= -1;
    }
  }


  //calculates GCD/GCF of numerator and denominator
  public int gcd()
  {
    return gcd( _numerator, _denominator );
  }


  //calculates GCD/GCF of two int inputs
  public static int gcd( int n, int d )
  {
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


  /***
   * boolean equals(Object) -- tells whether 2 Objs are equivalent
   * pre:  other is an instance of class Rational
   * post:
   * @return true if this and other are aliases (pointers to same
   *   Object), or if this and other have matching attribute values.
   *   (which in this case indicates equivalent fractions)
   ***/
  public boolean equals( Object other )
  {
    if ( !(other instanceof Rational) ) {
      System.out.println("not a rational");
      return false;
    }

    //First, reduce both fractions.
    //...thus allowing for direct comparison of attributes
    this.reduce();
    ((Rational)other).reduce();

    return this == other //check for aliases
      ||
      ( this._numerator == ((Rational)other)._numerator
        && this._denominator == ((Rational)other)._denominator);
  }//end equals()


  /***
   * int compareTo(Object) -- tell which of two Rationals is greater
   *  pre:
   *  post:
   * @throws exception if input not an instance of class Rational.
   * @return 0 if this Rational equiv to Rational argument,
   *         negative integer if this < other,
   *         positive integer if this > other.
   ***/
  public int compareTo( Object other )
  {
    // If other is not a Rational, throw an exception
    // This will exit the function, generating a runtime error
    if ( ! (other instanceof Rational) ) {
      // ClassCastException specified by Java API.
      // Input String is optional; gives diagnostics info.
      throw new ClassCastException("\nMy first error message! "
                                   + " compareTo() input not a Rational");
    }

    int d = _denominator * ((Rational)other)._denominator;

    //just for clarity below...
    int thisNumerator   = _numerator * d;
    int otherNumerator  = d * ((Rational)other)._numerator;

    return thisNumerator - otherNumerator;
  }

}//end class
