// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3  = 5
	    System.out.println(minus(7,2));  // 7 - 2 = 5
   		System.out.println(minus(2,7));  // 2 - 7 = -5
 		System.out.println(times(3,4));  // 3 * 4 = 12
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2 = 10
   		System.out.println(pow(5,3));      // 5^3 = 125
   		System.out.println(pow(3,5));      // 3^5 = 243
   		System.out.println(div(12,3));   // 12 / 3 = 4!!
   		System.out.println(div(5,5));    // 5 / 5  = 1!!
   		System.out.println(div(25,7));   // 25 / 7 = 3!!
   		System.out.println(mod(25,7));   // 25 % 7         -- 4
   		System.out.println(mod(120,6));  // 120 % 6    ---  0
   		System.out.println(sqrt(36));   // --- 6
		System.out.println(sqrt(263169)); // 513
   		System.out.println(sqrt(76123));  // ---- 275 (int of 275.903969)
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		for (int i = 0; i < x2; i++){
			x1++;
		}
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		for (int i = 0; i < x2; i++){
			x1--;
		}
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		int originalX1 = x1;
		for (int i = 1; i < x2; i++){
			x1 = plus(x1,originalX1);
		}
		return x1;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		if (n == 0) return 1;
		else {
			int originalX = x;
			for (int i = 1; i < n; i++){
				x = times(x,originalX);
		}
			return x;
		}
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		int times = 1;
		if (minus(x1,x2) < 0) {
			return 0; }
			while (minus(x1,x2) >= x2){
				times++;
				x1 = minus(x1,x2);
			}
			return times;
		}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		if (minus(x1,x2) < 0) {
			return x1;
		}
		else 
		return minus(x1, times(x2,div(x1,x2)));
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		int L = 0;
		int H = x;
		int ans = 0;
		
		while (minus(H,L) > 0) {
		int mid = div(plus(L,H),2);
		int midSquare = times(mid,mid);

		if (midSquare == x) return mid;
		
		if (midSquare < x) {
			ans = mid;
			L = plus(mid,1); //because it gets stuck and I know that mid is not the root, but it may be the integer root.
		} else {
			H = minus(mid,1); // because it gets stuck, and I know that mid is not the root. but I don't set it as ans because the integer root needs to be smaller or equal the the root.
		}
	}	  	  
	return ans;
	}
}