/* Determines all factors of a given value by prime factoring it and creating all possible combinations of values with those prime factors */

import java.util.ArrayList;

public class Factor{
	public static ArrayList<Integer> basesAndExponents(int num){
	    ArrayList<Integer> list = new ArrayList<Integer>(); 
	    int value = num;
	    for(int i = 2; i <= value; i++){
	        while(value % i == 0){
	            list.add(i);
	            value /= i;
	        }
	    }
	    return list;
	} 
	public static ArrayList<Integer> bases(ArrayList<Integer> list){
	    ArrayList<Integer> bases = new ArrayList<Integer>(); 
	    for(int i = 0; i < list.size(); i++){
	        if(bases.indexOf(list.get(i) ) == -1){bases.add(list.get(i)); }
	    }
	    return bases;
	}
	public static ArrayList<Integer> exponents(ArrayList<Integer> basesAndExponents, ArrayList<Integer> bases){
	    ArrayList<Integer> exponents = new ArrayList<Integer>();
      int index;
	    for(int i = 0; i < bases.size(); i++){
	        exponents.add(0);
	        for(int j = 0; j < basesAndExponents.size(); j++){
	            index = exponents.size() - 1;
	            if(basesAndExponents.get(j) == bases.get(i)){exponents.set(index, exponents.get(index) + 1); }
	        }
	    }
	    return exponents;
	}
	public static void main(String[] args) {
		System.out.println(basesAndExponents(5040));
		System.out.println(bases(basesAndExponents(5040)));
		System.out.println(exponents(basesAndExponents(5040), bases(basesAndExponents(5040))));
	}
}
