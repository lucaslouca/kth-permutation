package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * The set [a,b,c,...] of size n contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order, We get the
 * following sequence (ie, for n = 3):
 * 
 * abc, acb, bac, bca, cab, cba
 * 
 * Given n and k, return the kth permutation sequence. (Note: Given n will be
 * between 1 and 10 inclusive.)
 * 
 * 
 * @author lucas
 *
 */
public class PermutationSequence {

	private static int factorial(int n) {
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}

		return result;
	}

	public static String solve(char[] s, int k) {
		StringBuffer sb = new StringBuffer();

		List<Character> symbols = new ArrayList<Character>();
		for (int i = 0; i < s.length; i++) {
			symbols.add(s[i]);
		}

		int n = symbols.size();
		int fact = factorial(n - 1);
		int i;
		while (n > 0) {
			i = k / fact;
			sb.append(symbols.get(i));
			symbols.remove(i);
			k = k % fact; // Rest
			fact /= Math.max(1, (n - 1)); // (n-1)!, (n-2)!, (n-3)!, ... 
			n--;
		}

		return sb.toString();
	}

	public static void main(String[] args) {

		// add chars a - j
		int n = 10;
		char[] s = new char[n];
		for (int c = 97; c < 97 + n; c++) {
			s[c - 97] = (char) c;
		}

		System.out.println(solve(s, 1088640));
	}

}
