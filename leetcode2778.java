public class Main {

    public static int sumOfSquares(int[] nums) {

        int n = nums.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            if (n % (i + 1) == 0) {
                sum += nums[i] * nums[i];
            }
        }

        return sum;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4};

        System.out.println(sumOfSquares(nums));
    }
}
/*
LeetCode 2778 - Sum of Squares of Special Elements

BRUTE FORCE:
------------
Traverse every element in nums.

For each index i:
    If n % (i + 1) == 0,
    add nums[i] * nums[i] to the answer.

This checks every element directly.

Time Complexity: O(n)
Space Complexity: O(1)


OPTIMAL APPROACH:
-----------------
Use a single traversal.

Let:
    n = nums.length

For every index i:
    Check whether n % (i + 1) == 0.

If true:
    sum += nums[i] * nums[i]

Return sum.


WHY THIS APPROACH:
------------------
An element is special exactly when its 1-based
position divides the array length evenly.

Every element needs to be checked once, so a
single traversal is sufficient.


DRY RUN:
--------
nums = [2, 7, 1, 19]
n = 4

Index 0:
    4 % 1 == 0
    sum = 2 * 2 = 4

Index 1:
    4 % 2 == 0
    sum = 4 + 7 * 7 = 53

Index 2:
    4 % 3 != 0
    sum = 53

Index 3:
    4 % 4 == 0
    sum = 53 + 19 * 19 = 414

Answer = 414


TIME COMPLEXITY:
----------------
O(n), because every element is visited once.


SPACE COMPLEXITY:
-----------------
O(1), because only a constant number of variables
are used.


PATTERN:
--------
Array Traversal + Divisibility

Remember:
    n % (i + 1) == 0

The array index is 0-based, so use i + 1 to
calculate the element's 1-based position.
*/
