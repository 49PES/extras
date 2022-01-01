public class MathC{
    // Custom Math class!
    
    // Return greatest common divisor of two numbers
    public static int gcd( int n, int d ) {
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

    // Return least common multiple of two numbers
    public static int lcm(int a, int b){
        return(a * b / gcd(a, b));
    }


    // Probability Suite - Factorials, Permutations, Combinations, Binomial Probability & Cumulative Distributions
    
    // For n, return n!
    public static int factorial(int num){
        int product = 1;
        for(int i = 1; i <= num; i++){ product *= i;}
        return product;
    }

    // n-permute-k operation = n!/(n - k)!, or n * (n - 1) * (n - 2) * ...(n - (k - 1)) {first k terms of n!}
    public static int permute(int elements, int chosen){
        int product = 1;
        for(int i = elements; i > elements - chosen; i--){product *= i;}
        return product;
    }

    // n-choose-k operation nCk = n! / (k! (n - k)!) = nPk/k!
    // optimized using permutation and factorial, factorial of the lower complement between n - k & k
    public static int choose(int elements, int chosen){
        if(elements - chosen < chosen) return permute(elements, elements - chosen) / factorial(elements - chosen);
        return permute(elements, chosen) / factorial(chosen);
    }

    // Binomial Probability 
    public static double binomPDF(int n, int r, double p){
        return choose(n, r) * Math.pow(p, r) * Math.pow(1 - p, n - r);
    }

    public static double binomCDF(int n, int r, double p, String type){
        double output = 0;
        if(type.equals("at most") || type.equals("<=")){
            for(int i = 0; i <= r; i++){
                output += binomPDF(n, i, p);
            }
        }

        else if(type.equals("at least") || type.equals(">=") ) {
            for(int i = r; i <= n; i++){
                output += binomPDF(n, i, p);
            }
        }
        
        else if(type.equals("<")){
            for(int i = 0; i < r; i++){
                output += binomPDF(n, i, p);
            }
        }
        
        else if(type.equals(">") ) {
            for(int i = r + 1; i <= n; i++){
                output += binomPDF(n, i, p);
            }
        }
        
        return output;
    }
    
    public static void binomCDFAll(int n, int r, double p){
        // Output similar to this: https://www.gigacalculator.com/calculators/binomial-probability-calculator.php
        String output = "";
        output += "Number of trials (n): " + n;
        output += "\nNumber of events (r): " + r;
        output += "\nProbability (p): " + p;
        output += "\n\nProbability of R = " + r + " events:  " + binomPDF(n, r, p);
        output += "\nProbability of R < " + r + " events:  " + binomCDF(n, r, p, "<");
        output += "\nProbability of R \u2264 " + r + " events:  " + binomCDF(n, r, p, "<=");
        output += "\nProbability of R > " + r + " events:  " + binomCDF(n, r, p, ">");
        output += "\nProbability of R \u2265 " + r + " events:  " + binomCDF(n, r, p, ">=");
        System.out.println(output);
    }
    
    // Trig functions Suite

    // Reciprocal Trig Functions
    public static double csc(double angle){ return 1.0 / Math.sin(angle); }
    public static double sec(double angle){ return 1.0 / Math.cos(angle); }
    public static double cot(double angle){ return 1.0 / Math.cos(angle); }

    // Trig functions w/ degree inputs
    public static double sind(double angle){ return Math.sin(angle * Math.PI / 180); }
    public static double cosd(double angle){ return Math.cos(angle * Math.PI / 180); }
    public static double tand(double angle){ return Math.tan(angle * Math.PI / 180);  }

    // Reciprocal trig funtions w/ degree inputs
    public static double cscd(double angle){ return 1.0 / MathC.sind(angle); }
    public static double secd(double angle){ return 1.0 / MathC.cosd(angle); }
    public static double cotd(double angle){ return 1.0 / MathC.tand(angle); }

    // Inverse Trig Functions w/ degree outputs
    public static double asind(double value){ return Math.asin(value) * 180 / Math.PI; }
    public static double acosd(double value){ return Math.acos(value) * 180 / Math.PI; }
    public static double atand(double value){ return Math.atan(value) * 180 / Math.PI; }

    
    public static void main(String[] args){
    /*    System.out.println("Hello World!");
        System.out.println(binomPDF(5, 1, 0.5) );
        System.out.println(binomPDF(5, 2, 0.5) );
        System.out.println(binomCDF(5, 2, 0.5, "at most") );
        System.out.println(binomCDF(5, 2, 0.5, "at least") ); */
        
    }
}
