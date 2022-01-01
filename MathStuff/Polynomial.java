import java.util.Arrays;
public class Polynomial {
    public static int conv(int[] f, int[] g, int n){
        // https://www.wikiwand.com/en/Convolution#/Discrete_convolution
        // (f * g)[n] = sum_{m = -infty}^{infty} f[m]g[n - m]

        int sum = 0, iterLength;
        if(f.length > g.length){iterLength = f.length;} else {iterLength = g.length;}
        for(int m = 0; m < iterLength; m++){
            if( !(m > f.length || n - m >= g.length || n - m < 0) ){
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
        if(coefficients[0] == -1){output += "-";}
        else if(coefficients[0] != 1){output += coefficients[0] + "";}
        output += "x^" + (numTerms - 1);

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

    public static void main(String[] args) {
        int[] list1 = {1, 4, 3};
        int[] list2 = {2, 5, 1};
        System.out.println("(" + polyToString(list1) + ")(" + polyToString(list2) + ") = " );
        System.out.println(polyToString(multiply(list1, list2)));
    }

}
