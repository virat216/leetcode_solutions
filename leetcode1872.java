/*
============================================================
LEETCODE 1872 - STONE GAME VIII

Problem

Alice and Bob play a game with an array of stones.

Initially, all stones are in a row.

On each turn, a player must:

1. Choose an index i where i >= 1.
2. Take all stones from index 0 through i.
3. Calculate their sum.
4. Add that sum to the player's score.
5. Replace those stones with one stone having
   that sum.

The game continues until only one stone remains.

Both players play optimally.

Return the maximum score difference:

Alice's score - Bob's score

Alice wants to maximize the difference.

Bob wants to minimize it.

------------------------------------------------------------
IMPORTANT OBSERVATION

After the first move, the remaining game can be
represented using prefix sums.

Define:

prefix[i] = stones[0] + stones[1] + ... + stones[i]

For example:

stones = [-1, 2, -3, 4]

prefix:

index:   0   1   2   3
         ↓   ↓   ↓   ↓

        -1   1  -2   2

------------------------------------------------------------
WHY PREFIX SUM?

Suppose a player chooses index i.

The first i + 1 stones are removed and replaced
with their sum.

That sum is exactly:

prefix[i]

Therefore every possible move is represented
by a prefix sum.

------------------------------------------------------------
GAME DP

We define:

dp[i]

as the maximum score difference the current player
can obtain from the state represented by prefix[i].

The game has a useful property:

If we choose prefix[i], we gain:

prefix[i]

and the opponent gets the state represented by
a later prefix.

Therefore:

current difference =
prefix[i] - opponent's best difference

------------------------------------------------------------
BASE CASE

Consider the state where all stones have already
been combined.

The only possible score difference is:

prefix[n - 1]

Therefore:

dp[n - 1] = prefix[n - 1]

------------------------------------------------------------
TRANSITION

Working from right to left:

dp[i] = max(
    dp[i + 1],
    prefix[i] - dp[i + 1]
)

The two possibilities are:

1. Skip this prefix as the first useful move.

2. Choose this prefix.

If we choose prefix[i]:

our score increases by prefix[i]

but the opponent gets:

dp[i + 1]

So:

prefix[i] - dp[i + 1]

We take the maximum.

------------------------------------------------------------
WHY RIGHT TO LEFT?

dp[i] depends on:

dp[i + 1]

Therefore we calculate:

dp[n - 1]

then:

dp[n - 2]

then:

dp[n - 3]

...

until:

dp[1]

------------------------------------------------------------
IMPORTANT

We do NOT need dp[0].

The first valid move must combine at least
two stones, so i starts from 1.

------------------------------------------------------------
DRY RUN

stones = [-1, 2, -3, 4]

Prefix sums:

prefix[0] = -1

prefix[1] = 1

prefix[2] = -2

prefix[3] = 2

------------------------------------------------------------
BASE

dp = prefix[3]

dp = 2

------------------------------------------------------------
i = 2

prefix[2] = -2

Option 1:

dp = 2

Option 2:

prefix[2] - dp

= -2 - 2
= -4

Take maximum:

dp = max(2, -4)

dp = 2

------------------------------------------------------------
i = 1

prefix[1] = 1

Option 1:

dp = 2

Option 2:

1 - 2
= -1

Therefore:

dp = max(2, -1)

dp = 2

------------------------------------------------------------
ANSWER

2

------------------------------------------------------------
OPTIMIZATION

We only need the value of:

dp[i + 1]

to calculate:

dp[i]

Therefore we don't actually need an entire
DP array.

We can use one variable:

int dp

Initially:

dp = prefix[n - 1]

Then update it from right to left.

------------------------------------------------------------
SPACE OPTIMIZATION

We can also store prefix sums directly inside
the stones array.

Instead of creating:

int[] prefix

we can convert:

stones[i]

into:

prefix[i]

This allows us to use:

O(1)

extra space.

------------------------------------------------------------
ALGORITHM

1. Convert stones into prefix sums.

2. Set:

   dp = stones[n - 1]

3. Traverse from:

   n - 2

   down to:

   1

4. For every i:

   dp = Math.max(
       dp,
       stones[i] - dp
   );

5. Return dp.

------------------------------------------------------------
WHY NOT START FROM i = 0?

The move must take at least two stones.

Therefore the first valid prefix is:

prefix[1]

So:

for (int i = n - 2; i >= 1; i--)

------------------------------------------------------------
TIME COMPLEXITY

O(n)

We calculate prefix sums once and perform
one backward traversal.

------------------------------------------------------------
SPACE COMPLEXITY

O(1)

We modify the input array directly and use
only a few variables.

------------------------------------------------------------
KEY CONCEPTS

✓ Dynamic Programming
✓ Game Theory
✓ Prefix Sum
✓ Score Difference
✓ Bottom-Up DP
✓ Space Optimization

------------------------------------------------------------
PATTERN

Prefix Sum
    ↓
Game State
    ↓
Current Score - Opponent's Best
    ↓
DP from right to left
    ↓
Optimize to one variable

============================================================
*/
import java.util.*;

public class Main {

    public static int stoneGameVIII(int[] stones) {

        int n = stones.length;

        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        int dp = stones[n - 1];

        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, stones[i] - dp);
        }

        return dp;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of stones: ");

        int n = sc.nextInt();

        int[] stones = new int[n];

        System.out.println("Enter stone values:");

        for (int i = 0; i < n; i++) {
            stones[i] = sc.nextInt();
        }

        int result = stoneGameVIII(stones);

        System.out.println(
            "Maximum score difference: " + result
        );

        sc.close();
    }
}
