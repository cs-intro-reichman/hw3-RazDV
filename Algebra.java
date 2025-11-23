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
        if (x2 > 0) {
            for (int i = 0; i < x2; i++) x1++;
        } else {
            for (int i = 0; i > x2; i--) x1--;
        }
        return x1;
    }

	// Returns x1 - x2
    public static int minus(int x1, int x2) {
        if (x2 > 0) {
            for (int i = 0; i < x2; i++) x1--;
        } else {
            for (int i = 0; i > x2; i--) x1++;
        }
        return x1;
    }


	// Returns x1 * x2
    public static int times(int x1, int x2) {
        if (x1 == 0 || x2 == 0) return 0;

        int plusOrMinus = 1;

        // fix x1 sign
        if (x1 < 0) {
            x1 = minus(0, x1); 
            plusOrMinus = minus(0, plusOrMinus);
        }
        // fix x2 sign
        if (x2 < 0) {
            x2 = minus(0, x2); 
            plusOrMinus = minus(0, plusOrMinus);
        }

        int ans = 0;

        for (int i = 0; i < x2; i++) {
            ans = plus(ans, x1);
        }

        if (plusOrMinus < 0) ans = minus(0, ans);

        return ans;
    }



	// Returns x^n (for n >= 0)
    public static int pow(int x, int n) {
        if (n == 0) return 1;

        int ans = 1;
        for (int i = 0; i < n; i++) {
            ans = times(ans, x);
        }
        return ans;
    }

	// Returns the integer part of x1 / x2 
    public static int div(int x1, int x2) {
        if (x2 == 0) return 0;

        int plusOrMinus = 1;

        if (x1 < 0) {
            x1 = minus(0, x1);
            plusOrMinus = minus(0, plusOrMinus);
        }
        if (x2 < 0) {
            x2 = minus(0, x2);
            plusOrMinus = minus(0, plusOrMinus);
        }

        int ans = 0;
        while (x1 >= x2) {
            x1 = minus(x1, x2);
            ans++;
        }

        if (plusOrMinus < 0) ans = minus(0, ans);

        return ans;
    }


	// Returns x1 % x2
    public static int mod(int x1, int x2) {
        int q = div(x1, x2);
        return minus(x1, times(q, x2));
    }

	// Returns the integer part of sqrt(x) 
    public static int sqrt(int x) {
        if (x <= 0) return 0;
        int Low = 0;
        int High = x;
        int ans = 0;

        while (Low <= High) {
            int mid = div(plus(Low, High), 2);
            int midSq = times(mid, mid);

            if (midSq == x) return mid;

            if (midSq < x) {
                ans = mid;
                Low = plus(mid, 1);
            } else {
                High = minus(mid, 1);
            }
        }
        return ans;
    }
}