// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
	// Expects to get three command-line arguments: loan amount (double),
	// interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double balance = loan;

		for (int i = 0; i < n; i++) {
			balance = (balance - payment) * (1 + rate / 100.0);
		}

		return balance;
	}
	
	// Brute force search
	public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;

		// Start with loan/n (definitely too small → f(g) > 0)
		double g = loan / n;

		// Increase until ending balance becomes ≤ 0
		while (endBalance(loan, rate, n, g) > 0) {
			g += epsilon;
			iterationCounter++;
		}

		return g;
	}
    
	// Bisection search
	public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		iterationCounter = 0;

		// L is too small → f(L) > 0
		double L = loan / n;

		// H must be large enough so f(H) < 0
		double H = loan;  
		while (endBalance(loan, rate, n, H) > 0) {
			H *= 2;   // expand interval until we bracket the root
		}

		// Bisection loop
		while (H - L > epsilon) {
			double mid = (L + H) / 2;
			iterationCounter++;

			double fL = endBalance(loan, rate, n, L);
			double fM = endBalance(loan, rate, n, mid);

			if (fL * fM > 0) {
				// root is between mid and H
				L = mid;
			} else {
				// root is between L and mid
				H = mid;
			}
		}

		return (L + H) / 2;
	}
}
