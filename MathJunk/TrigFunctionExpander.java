import java.util.Arrays;

public class Main
{
	public static String trigFxnExpand(String[] angles, String type){
	    if(angles.length == 2){
	        if(type.equals("cos")) {return String.format("(cos(%s)cos(%s) - sin(%s)sin(%s))", angles[0], angles[1], angles[0], angles[1]); }
	        else if (type.equals("sin")) {return String.format("(sin(%s)cos(%s) + sin(%s)cos(%s))", angles[0], angles[1], angles[1], angles[0]); }
	    }

	    String[] copy = Arrays.copyOfRange(angles, 1, angles.length);
	    if(type.equals("cos")){return String.format("cos(%s)%s - sin(%s)%s", angles[0], trigFxnExpand(copy, "cos"), angles[0], trigFxnExpand(copy, "sin")); }
	    else {return String.format("sin(%s)%s + cos(%s)%s", angles[0], trigFxnExpand(copy, "cos"), angles[0], trigFxnExpand(copy, "sin")); }

	}

	public static void main(String[] args) {
		System.out.println(trigFxnExpand(new String[] {"a", "b"}, "sin") );
		System.out.println(trigFxnExpand(new String[] {"a", "b"}, "cos") );
		System.out.println(trigFxnExpand(new String[] {"a", "b", "c"}, "sin") );
		System.out.println(trigFxnExpand(new String[] {"a", "a", "a", "a", "a"}, "sin") );
		System.out.println(trigFxnExpand(new String[] {"a", "a", "a", "a", "a"}, "cos") );
	}
}
