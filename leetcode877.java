/*
============================================================
LEETCODE 877 - STONE GAME

Problem

Alice and Bob are playing a game with
an even number of piles.

Each pile contains some stones.

Players take turns.

On every turn, a player can take

• Leftmost pile
or
• Rightmost pile

Both players play optimally.

The player with more stones wins.

Return

true

if Alice wins.

------------------------------------------------------------
Example

Input

piles = [5,3,4,5]

Output

true

Explanation

Alice chooses

5

Bob chooses

5

Alice chooses

4

Bob chooses

3

Alice = 9

Bob = 8

Alice wins.

------------------------------------------------------------
Intuition

Since both players play optimally,

we cannot greedily choose the larger pile.

Instead,

for every subarray,

calculate the maximum score difference

(Current Player − Opponent)

Suppose

Current player chooses left.

Difference

piles[left] − solve(left+1,right)

If current player chooses right,

Difference

piles[right] − solve(left,right−1)

Choose whichever gives a larger difference.

If the final difference is positive,

Alice wins.

------------------------------------------------------------
Approach

Use recursion + memoization.

solve(left,right)

returns

Maximum score difference current player
can obtain from

piles[left...right].

Transition

Take Left

piles[left] − solve(left+1,right)

Take Right

piles[right] − solve(left,right−1)

Return

maximum of both.

------------------------------------------------------------
Algorithm

1. Create DP table.

2. Define

solve(left,right)

3. Base Case

If

left == right

Return

piles[left]

4. Compute

takeLeft

takeRight

5. Store maximum.

6. Return

solve(0,n−1) > 0

------------------------------------------------------------
Dry Run

Input

[5,3,4,5]

------------------------------------

solve(0,3)

Take Left

5 − solve(1,3)

Take Right

5 − solve(0,2)

Eventually

Difference = 1

Since

1 > 0

Alice wins.

------------------------------------------------------------
Why It Works

The recursive function always returns

(Current Player Score − Opponent Score)

The recursive call already represents
the opponent playing optimally.

Subtracting that value gives the current
player's best possible advantage.

If the final difference is positive,

Alice collects more stones.

------------------------------------------------------------
Special Observation

For this particular problem,

Alice always wins.

Reason

• Number of piles is always even.

• Total stones are odd.

Alice can always choose either

all even-indexed piles

or

all odd-indexed piles,

whichever has the larger total.

Therefore,

LeetCode accepts

return true;

However,

the DP solution is much more useful
for interviews because the same idea
works for many Stone Game problems.

------------------------------------------------------------
Time Complexity

O(n²)

There are

n²

DP states.

------------------------------------------------------------
Space Complexity

O(n²)

DP table stores every interval.

------------------------------------------------------------
Functions Used

Math.max()

Returns the larger score difference.

============================================================
*/


import java.util.*;

public class Main {

    static int solve(int[] piles, int left, int right, Integer[][] dp) {

        if (left == right) {
            return piles[left];
        }

        if (dp[left][right] != null) {
            return dp[left][right];
        }

        int takeLeft = piles[left] - solve(piles, left + 1, right, dp);

        int takeRight = piles[right] - solve(piles, left, right - 1, dp);

        return dp[left][right] = Math.max(takeLeft, takeRight);
    }

    public static boolean stoneGame(int[] piles) {

        int n = piles.length;

        Integer[][] dp = new Integer[n][n];

        return solve(piles, 0, n - 1, dp) > 0;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of piles: ");

        int n = sc.nextInt();

        int[] piles = new int[n];

        System.out.println("Enter stones in each pile:");

        for (int i = 0; i < n; i++) {
            piles[i] = sc.nextInt();
        }

        System.out.println("Alice Wins: " + stoneGame(piles));

        sc.close();
    }
}
