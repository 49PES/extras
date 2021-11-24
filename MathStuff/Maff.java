public class Maff{
    // Euclidean method of calculating GCD using modular
    public static int gcd( int a, int b ) {
        a = Math.abs(a); b = Math.abs(b);
        if(b == 0){return a;}
	      return(gcd(b, a % b));
    }

	// Return least common multiple of two numbers
	public static int lcm(int a, int b){
	    return(a * b / gcd(a, b));
	}
	
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
	
	// n-choose-k operation nCk = n! / (k! (n - k)!) = nPk/k!, optimized using permutation and factorial, factorial of the lower complement between n - k & k
	public static int choose(int elements, int chosen){
	    if(elements - chosen < chosen) return permute(elements, chosen) / factorial(chosen);
	    else return permute(elements, elements - chosen) / factorial(elements - chosen);
	}
	
    public static void main(){
    }
}
