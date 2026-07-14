/*
============================================================
LEETCODE 3336 - Find the Number of Subsequences With Equal GCD

Problem

Given an integer array nums,

form two disjoint subsequences from the array.

Both subsequences must be non-empty.

Return the number of ways such that

GCD(Subsequence1) == GCD(Subsequence2)

Return the answer modulo 1e9+7.

------------------------------------------------------------
Example

nums = [2,4,6]

Possible subsequences

S1 = [2]
S2 = [4,6]

GCD(S1) = 2

GCD(S2)

gcd(4,6)=2

Both GCDs are equal.

This contributes one valid answer.

------------------------------------------------------------
Intuition

For every element,

there are exactly three choices.

1. Ignore the element.

2. Put it inside the first subsequence.

3. Put it inside the second subsequence.

While processing the array,

we only need to know

Current GCD of first subsequence

Current GCD of second subsequence.

These two values completely describe the state.

Therefore,

Dynamic Programming on GCD states is sufficient.

------------------------------------------------------------
DP State

dp[g1][g2]

represents

Number of ways to process the current elements
such that

GCD(first subsequence) = g1

GCD(second subsequence) = g2

Initially

dp[0][0] = 1

Here,

GCD = 0 means

"No element has been selected yet."

------------------------------------------------------------
Approach

For every number x

we have three choices.

Choice 1

Skip x

GCDs remain unchanged.

Choice 2

Insert x into first subsequence.

New GCD becomes

gcd(g1, x)

Choice 3

Insert x into second subsequence.

New GCD becomes

gcd(g2, x)

Repeat for every element.

Finally,

count all states where

g1 == g2

and

g1 > 0

------------------------------------------------------------
Algorithm

1. Initialize

dp[0][0] = 1

2. Precompute all GCD values

gcd(a,b)

for

0 <= a,b <= 200

3. Traverse every element.

4. For every DP state

perform three transitions.

• Skip

• Add to first subsequence

• Add to second subsequence

5. Store results inside nextDP.

6. Replace dp by nextDP.

7. Sum all

dp[g][g]

for

g = 1...200

------------------------------------------------------------
Why It Works

Every element has exactly three independent choices.

Therefore,

every valid pair of subsequences is generated.

Instead of storing the entire subsequences,

we only store

their current GCDs.

Since

nums[i] <= 200,

possible GCD values are only

0...200.

Thus,

the DP remains small.

Finally,

all states where

g1 == g2

represent valid answers.

------------------------------------------------------------
Time Complexity

Let

N = nums.length

Maximum value = 200

States

201 × 201

Transitions

3

Overall

O(N × 201 × 201)

≈ O(N)

because

201 is a constant.

------------------------------------------------------------
Space Complexity

O(201 × 201)

------------------------------------------------------------
Functions Used

gcd()

Computes Greatest Common Divisor.

Precomputed GCD Table

Avoids repeated GCD calculations.

============================================================
*/


import java.util.*;

public class Main {

    static long mod = 1_000_000_007;

    static int gcd(int a, int b) {

        if (b == 0)
            return a;

        return gcd(b, a % b);
    }

    public static int subsequencePairCount(int[] nums) {

        long[][] dp = new long[201][201];

        dp[0][0] = 1;

        int[][] gcdDP = new int[201][201];

        for (int i = 0; i <= 200; i++) {

            for (int j = 0; j <= 200; j++) {

                gcdDP[i][j] = gcd(i, j);
            }
        }

        for (int num : nums) {

            long[][] nextDP = new long[201][201];

            for (int g1 = 0; g1 <= 200; g1++) {

                for (int g2 = 0; g2 <= 200; g2++) {

                    long cur = dp[g1][g2];

                    if (cur == 0)
                        continue;

                    nextDP[g1][g2] =
                            (nextDP[g1][g2] + cur) % mod;

                    int newG1 = gcdDP[g1][num];

                    nextDP[newG1][g2] =
                            (nextDP[newG1][g2] + cur) % mod;

                    int newG2 = gcdDP[g2][num];

                    nextDP[g1][newG2] =
                            (nextDP[g1][newG2] + cur) % mod;
                }
            }

            dp = nextDP;
        }

        long ans = 0;

        for (int g = 1; g <= 200; g++) {

            ans = (ans + dp[g][g]) % mod;
        }

        return (int) ans;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");

        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter array elements:");

        for (int i = 0; i < n; i++) {

            nums[i] = sc.nextInt();
        }

        int ans = subsequencePairCount(nums);

        System.out.println("Answer = " + ans);

        sc.close();
    }
}
