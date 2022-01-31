import java.util.Arrays;
import java.util.Scanner;
public class Polynomial {
  private int[] polynomial;
  Polynomial(int[] values, String type){
    if(type.equals("coefficients")) polynomial = values;
    if(type.equals("roots")) polynomial = polyGenerator(values);
  }

  static int conv(int[] f, int[] g, int n){
      // https://www.wikiwand.com/en/Convolution#/Discrete_convolution
      // (f * g)[n] = sum_{m = -infty}^{infty} f[m]g[n - m]

      int sum = 0, iterLength;
      if(f.length > g.length){iterLength = f.length;} else {iterLength = g.length;}
      for(int m = 0; m < iterLength; m++){
          if( !(m >= f.length || n - m >= g.length || n - m < 0) ){
              sum += f[m] * g[n - m];
          }
      }
      return sum;
  }

  static int[] multiply(int[] f, int[] g){
      int[] product = new int[f.length + g.length - 1];
      for(int i = 0; i < product.length; i++) {product[i] = conv(f, g, i);}
      return product;
  }

  public int[] getPolynomial(){return this.polynomial;}

  String polyToString(){
      int[] coefficients = polynomial;
      int numTerms = coefficients.length;
      String output = "";
      if(coefficients.length == 0){return output;}
      if(coefficients.length == 1){return output + coefficients[0];}

      if(coefficients[0] == -1){output += "-";}
      else if(coefficients[0] == 1){}
      else{output += coefficients[0] + "";}

      output += "x";

      if(numTerms - 1 != 1){output += "^" + (numTerms - 1);}

      for(int i = 1; i < numTerms - 1; i++){
          if(coefficients[i] != 0){
              if(Math.abs(coefficients[i]) != 1){
                  if(coefficients[i] > 0){output += " + " + coefficients[i]; }
                  else{output += " - " + -coefficients[i];}
              }
              else{
                  if (coefficients[i] == -1 ){output += " - ";}
                  else{output += " + ";}
              }
              if(numTerms - 1 - i == 1){output += "x";}
              else{output += "x^" + (numTerms - 1 - i);}
          }
      }

      if(coefficients[numTerms - 1] > 0){output += " + " + coefficients[numTerms - 1];}
      else if(coefficients[numTerms - 1] < 0){output += " - " + -coefficients[numTerms - 1];}
      return output;
    }

  static int[] distribute(int[] polynomial, int term){
    for(int i = 0; i < polynomial.length; i++){polynomial[i] = polynomial[i] * term;}
    return polynomial;
  }

  void distribute(int term){
    distribute(this.polynomial, term);
  }

  static int[] add(int[] a, int[] b){
    if(a.length > b.length){
      for(int i = a.length - 1; i >= a.length - b.length; i--){
        a[i] = a[i] + b[i - (a.length - b.length)];
      }
      return a;
    }
    return add(b, a);
  }

  static int[] polyGenerator(int[] roots){
      if(roots.length == 1){
          int[] poly = new int[2];
          poly[0] = 1;
          poly[1] = -roots[0];
          return poly;
      }
      int[] factor = new int[2];
      factor[0] = 1; factor[1] = -roots[roots.length - 1];
      return multiply(factor, polyGenerator(Arrays.copyOfRange(roots, 0, roots.length - 1)));
  }
/*
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type; Polynomial temp = new Polynomial(new int[0], "coefficients");
        // int[] factor1 = {1, -4};
        // int[] factor2 = {1, -12, 50};
        // Polynomial a = new Polynomial(multiply(factor1, factor2), "coefficients");
        // System.out.println(a.polyToString());
        while(true){
          System.out.println("Would you like to provide the [roots] of your polynomial or the [coefficients]?");
          type = sc.next();

          if(type.toLowerCase().equals("coefficients") ){
          System.out.print("What is the degree of your polynomial? ");
          int degree = sc.nextInt();
          int[] values = new int[degree + 1];

          System.out.print("Enter the coefficients for the following terms: \n");
          for(int i = degree; i >= 0; i--){
            System.out.print("x^" + i + ": ");
            values[degree - i] = sc.nextInt();
          }

          temp = new Polynomial(values, "coefficients");
        }

          if(type.toLowerCase().equals("roots")){
          System.out.print("How many roots do you have? ");
          int numRoots = sc.nextInt();
          int[] roots = new int[numRoots];
          System.out.println("Enter the roots of your polynomial");
          for(int i = 0; i < roots.length; i++){
            System.out.print("r" + (i + 1) + ": ");
            roots[i] = sc.nextInt();
          }
          temp = new Polynomial(roots, "roots");
        }

          System.out.println("Your polynomial is: " + temp.polyToString());
          System.out.print("Would you like the roots of your polynomial? Y / N ");
          if(sc.next().equals("Y")){
            System.out.println(rootsSolver.rootsSolver(temp.polynomial));
          }
          System.out.print("Would you like to exit? Y / N ");
          String input = sc.next();
          if(input.equals("Y")){break;}
         }
    }
*/
}
