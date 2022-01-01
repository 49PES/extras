import java.util.Arrays;
public class Polynomial {
    public static int conv(int[] f, int[] g, int n){
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

    public static int[] multiply(int[] f, int[] g){
        int[] product = new int[f.length + g.length - 1];
        for(int i = 0; i < product.length; i++) {product[i] = conv(f, g, i);}
        return product;
    }

    public static String polyToString(int[] coefficients){
        int numTerms = coefficients.length;
        String output = "";
        if(coefficients.length == 0){return output;}
        if(coefficients.length == 1){return output + coefficients[0];}
        
        if(coefficients[0] == -1){output += "-";}
        else if(coefficients[0] == 1){output += "x";}
        else{output += coefficients[0] + "x";}

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

    public static int[] polyGenerator(int[] roots){
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

    public static void main(String[] args) {
        int[] list1 = {1, 4, 3};
        int[] list2 = {2, 5, 1};
        System.out.println("(" + polyToString(list1) + ")(" + polyToString(list2) + ") = " );
        System.out.println(polyToString(multiply(list1, list2)));

        int[] roots1 = {2}; // (x - 2)
        int[] roots2 = {2, 3}; // (x - 2)(x - 3) -> x^2 - 5x + 6
        int[] roots3 = {2, 3, 4}; // (x - 2)(x - 3)(x - 4) = x^3 - 9x^2 + 26x - 24
        int[] roots4 = {2, 3, 4, 5}; // (x - 2)(x - 3)(x - 4)(x - 5) = x^4 - 14x^3 + 71x^2 - 154x + 120

        System.out.println(polyToString(polyGenerator(roots1)));
        System.out.println(polyToString(polyGenerator(roots2)));
        System.out.println(polyToString(polyGenerator(roots3)));
        System.out.println(polyToString(polyGenerator(roots4)));
    }

}
