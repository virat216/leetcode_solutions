/*
============================================================
LEETCODE 3193 - Count the Number of Inversions

Problem

You are given

n

and an array

requirements

where

requirements[i] = [end, cnt]

means that the prefix

perm[0...end]

must contain exactly

cnt

inversions.

Return the number of valid permutations
of

0,1,2,...,n-1

that satisfy all requirements.

Return the answer modulo

1,000,000,007.

------------------------------------------------------------
Example

Input

n = 3

requirements = [[2,1]]

Possible permutations

[0,2,1] ✓

[1,0,2] ✓

Only these satisfy

1 inversion

Output

2

------------------------------------------------------------
Intuition

Generating every permutation is impossible.

There are

n!

possible permutations.

Instead,

construct permutations gradually.

Suppose we already know all valid permutations
of length

i

with every possible inversion count.

Now insert the next largest number.

Since it is the largest,

placing it

at the end

creates

0

new inversions.

One position before the end

creates

1

new inversion.

...

At the beginning

creates

i

new inversions.

Thus,

one insertion generates every possible
increase in inversion count.

This naturally leads to Dynamic Programming.

------------------------------------------------------------
Approach

dp[i][j]

=

Number of ways to arrange

(i + 1)

numbers with exactly

j

inversions.

Transition

Insert the largest element into every
possible position.

If it creates

k

new inversions,

then

dp[i][j]

+=

dp[i-1][j-k]

After computing a row,

if there is a requirement for that prefix,

keep only that inversion count.

All other states become zero.

------------------------------------------------------------
Algorithm

1. Store inversion requirement for every prefix.

2. Initialize

dp[0][0] = 1

3. Build DP row by row.

4. For every inversion count,

try every insertion position.

5. Apply modulo.

6. If prefix has required inversion count,

remove all other states.

7. Return the last DP state.

------------------------------------------------------------
Dry Run

Example

n = 3

Requirement

Prefix 2

must have

1

inversion.

Initially

dp[0][0] = 1

------------------------

Length = 2

Possible inversions

0

1

dp

1 1

------------------------

Length = 3

Possible inversions

0

1

2

3

DP becomes

1

2

2

1

Requirement

Need exactly

1

Keep only

dp[2][1]

=

2

Answer

2

------------------------------------------------------------
Why It Works

When inserting the largest element,

only the number of elements after it
determines how many new inversions are added.

Trying every insertion position covers
every possible permutation exactly once.

Therefore,

Dynamic Programming counts every valid
permutation without explicitly generating it.

------------------------------------------------------------
Time Complexity

O(n × m × min(n, m))

where

m

is the maximum required inversion count.

------------------------------------------------------------
Space Complexity

O(n × m)

------------------------------------------------------------
Functions Used

Arrays.fill()

Initializes requirement array.

Math.max()

Finds maximum inversion count.

============================================================
*/

import java.util.*;

public class Main {

    static final int MOD = 1_000_000_007;

    public static int numberOfPermutations(int n, int[][] requirements) {

        int[] req = new int[n];

        Arrays.fill(req, -1);

        for (int[] r : requirements) {
            req[r[0]] = r[1];
        }

        if (req[0] > 0) {
            return 0;
        }

        req[0] = 0;

        int maxInv = 0;

        for (int x : req) {
            maxInv = Math.max(maxInv, x);
        }

        int[][] dp = new int[n][maxInv + 1];

        dp[0][0] = 1;

        for (int i = 1; i < n; i++) {

            int left = 0;
            int right = maxInv;

            if (req[i] != -1) {
                left = right = req[i];
            }

            for (int inv = left; inv <= right; inv++) {

                long ways = 0;

                for (int k = 0; k <= Math.min(i, inv); k++) {

                    ways += dp[i - 1][inv - k];
                    ways %= MOD;
                }

                dp[i][inv] = (int) ways;
            }
        }

        return dp[n - 1][req[n - 1]];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");

        int n = sc.nextInt();

        System.out.print("Enter number of requirements: ");

        int m = sc.nextInt();

        int[][] requirements = new int[m][2];

        System.out.println("Enter end and inversion count:");

        for (int i = 0; i < m; i++) {

            requirements[i][0] = sc.nextInt();
            requirements[i][1] = sc.nextInt();
        }

        int ans = numberOfPermutations(n, requirements);

        System.out.println("Number of Valid Permutations = " + ans);

        sc.close();
    }
}
