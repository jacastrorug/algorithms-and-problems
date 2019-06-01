package TheoryNumbers;

import java.util.Arrays;

public class SieveEratosthenes {

	/**
	 * Complexity O(n log n)
	 * @param limit Integer
	 * @return
	 */
	static long[] getPrimes(int limit) {
		boolean[] arr = new boolean[limit + 1];
		long[] res = new long[limit];
		arr[0] = arr[1] = true;
		int K = 0;
		for (int i = 0; i < arr.length; i++)
			if (!arr[i]) {
				res[K++] = i;
				for (int j = 2 * i; j < arr.length; j += i)
					arr[j] = true;

			}
		
		return Arrays.copyOfRange(res, 0, K);
	}
}
