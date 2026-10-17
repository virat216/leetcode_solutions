import java.util.*;

public class Main {

    public static int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = nums[i];
        }

        Arrays.sort(order, (a, b) -> {
            int sumA = digitSum(a);
            int sumB = digitSum(b);

            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }

            return Integer.compare(a, b);
        });

        Map<Integer, Integer> target = new HashMap<>();

        for (int i = 0; i < n; i++) {
            target.put(order[i], i);
        }

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            int j = i;
            int cycleSize = 0;

            while (!visited[j]) {
                visited[j] = true;
                j = target.get(nums[j]);
                cycleSize++;
            }

            swaps += Math.max(0, cycleSize - 1);
        }

        return swaps;
    }

    private static int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {18, 43, 34, 16};
        System.out.println(minSwaps(nums));
    }
}

/*
LeetCode 3551: Minimum Swaps to Sort by Digit Sum

BRUTE FORCE:
Sort the elements by digit sum and then numerical value.
Repeatedly place misplaced elements into their correct
positions. A naive implementation can take O(n^2) time.

OPTIMAL APPROACH:
1. Sort the values by (digitSum, value).
2. Map each value to its target sorted index.
3. Treat each original index as a node in a permutation.
4. Count each cycle using a visited array.
5. A cycle of length k requires k - 1 swaps.

WHY IT WORKS:
Every element must reach its target position.
A cycle of k misplaced elements can be resolved in
exactly k - 1 swaps. Summing this across all cycles
gives the minimum number of swaps.

DRY RUN:
nums = [18, 43, 34, 16]
Sorted = [16, 34, 43, 18]

Cycles: (0 -> 3 -> 0), (1 -> 2 -> 1)
Each cycle has length 2 and requires one swap.
Total = 1 + 1 = 2.

TIME COMPLEXITY: O(n log n)
SPACE COMPLEXITY: O(n)

PATTERN:
Sorting + HashMap + Permutation Cycle Detection.
*/


