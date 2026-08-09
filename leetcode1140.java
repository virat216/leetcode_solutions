/*
============================================================
LEETCODE 1140 - STONE GAME II

Problem

Alice and Bob play a game using an array of piles.

piles[i] represents the number of stones in pile i.

Initially:

M = 1

On each turn, the current player can take
the first X remaining piles where:

1 <= X <= 2 * M

After taking X piles:

M = max(M, X)

Alice plays first.

Both players play optimally.

Return the maximum number of stones Alice can
obtain.

------------------------------------------------------------
Example

Input:

piles = [2, 7, 9, 4, 4]

Output:

10

Alice can obtain a maximum of 10 stones.

------------------------------------------------------------
INTUITION

This is a Dynamic Programming + Game Theory problem.

At every position, the player has multiple
choices.

The important state is:

index
M

where:

index = current pile from which we can take stones

M = current maximum allowed parameter

From this state, we can take:

1 to 2 * M piles.

------------------------------------------------------------
STATE

Let:

solve(index, M)

represent:

The maximum number of stones the CURRENT PLAYER
can obtain starting from index with the current
value of M.

------------------------------------------------------------
WHY DO WE CALCULATE CURRENT PLAYER'S SCORE?

Suppose there are some stones remaining.

Let:

suffix[index]

be the total number of stones remaining
from index onward.

If the opponent obtains:

opponent

then the current player gets:

suffix[index] - opponent

Therefore:

current player's score
=
total remaining stones
-
opponent's best score

This allows us to avoid separately calculating
Alice's and Bob's scores.

------------------------------------------------------------
SUFFIX ARRAY

We create:

suffix[i]

which stores the total number of stones from
i to the end.

Example:

piles = [2, 7, 9, 4, 4]

suffix:

index 0:

2 + 7 + 9 + 4 + 4 = 26

index 1:

7 + 9 + 4 + 4 = 24

index 2:

9 + 4 + 4 = 17

index 3:

4 + 4 = 8

index 4:

4

index 5:

0

So:

suffix = [26, 24, 17, 8, 4, 0]

------------------------------------------------------------
CHOICES

Suppose:

index = 0
M = 1

We can take:

1 <= X <= 2

So there are two choices:

X = 1

or

X = 2

------------------------------------------------------------
CHOICE X = 1

Take:

piles[0]

New state:

index = 1

M = max(1, 1)

= 1

------------------------------------------------------------
CHOICE X = 2

Take:

piles[0] + piles[1]

New state:

index = 2

M = max(1, 2)

= 2

------------------------------------------------------------
TRANSITION

For every possible X:

1 <= X <= 2 * M

we calculate:

opponent =
solve(index + X, max(M, X))

Then:

current =
suffix[index] - opponent

Take the maximum current score.

Therefore:

best =
max(best, suffix[index] - opponent)

------------------------------------------------------------
IMPORTANT OPTIMIZATION

Suppose:

2 * M >= remaining piles

Then the current player can take ALL remaining
piles.

There is no reason to explore further choices.

Therefore:

if (2 * M >= n - index)

return suffix[index]

------------------------------------------------------------
MEMOIZATION

The same:

index + M

state can be reached through different choices.

Without memoization, we would repeatedly solve
the same states.

So we store:

dp[index][M]

------------------------------------------------------------
DRY RUN

piles:

[2, 7, 9, 4, 4]

Initial:

index = 0
M = 1

Allowed:

X = 1 or 2

------------------------------------------------------------
If X = 1:

Alice takes:

2

Remaining:

[7, 9, 4, 4]

Bob gets to play with:

index = 1
M = 1

------------------------------------------------------------
If X = 2:

Alice takes:

2 + 7 = 9

Remaining:

[9, 4, 4]

New:

index = 2
M = 2

Bob can now take up to:

2 * 2 = 4

piles.

------------------------------------------------------------
The DP explores all possible choices for both
players and assumes both players play optimally.

Eventually the maximum score Alice can guarantee
is:

10

------------------------------------------------------------
WHY:

suffix[index] - opponent

WORKS

Suppose:

Total remaining stones = 20

If the opponent can obtain:

12

then the current player must obtain:

20 - 12

= 8

So instead of calculating the current player's
score directly, we calculate the opponent's
best possible score.

Then subtract it from the total.

------------------------------------------------------------
ALGORITHM

1. Create suffix sum array.

2. Create:

Integer[][] dp

where:

dp[index][M]

stores the maximum stones the current player
can obtain.

3. Start:

solve(0, 1)

4. For every state:

Try X from 1 to 2 * M.

5. Calculate opponent's best score.

6. Current player's score:

suffix[index] - opponent

7. Take the maximum.

8. Store the result in dp.

------------------------------------------------------------
TIME COMPLEXITY

O(n^3)

There are O(n^2) possible states.

For each state, we may try O(n) choices.

Therefore:

O(n^2 * n)

= O(n^3)

------------------------------------------------------------
SPACE COMPLEXITY

O(n^2)

The DP table contains:

index × M

states.

The suffix array requires:

O(n)

additional space.

------------------------------------------------------------
KEY CONCEPTS

✓ Dynamic Programming
✓ Memoization
✓ Game Theory
✓ Minimax
✓ Suffix Sum
✓ State = index + M
✓ Optimal Substructure

------------------------------------------------------------
PATTERN

Game Problem

↓

Current Player vs Opponent

↓

Calculate Opponent's Best Score

↓

Total Remaining - Opponent

↓

Dynamic Programming

============================================================
*/
import java.util.*;

public class Main {

    public static int stoneGameII(int[] piles) {

        int n = piles.length;

        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        Integer[][] dp = new Integer[n][n + 1];

        return solve(0, 1, suffix, dp, n);
    }

    private static int solve(int index, int m, int[] suffix,
                             Integer[][] dp, int n) {

        if (2 * m >= n - index) {
            return suffix[index];
        }

        if (dp[index][m] != null) {
            return dp[index][m];
        }

        int best = 0;

        for (int x = 1; x <= 2 * m; x++) {

            int opponent = solve(
                index + x,
                Math.max(m, x),
                suffix,
                dp,
                n
            );

            int current = suffix[index] - opponent;

            best = Math.max(best, current);
        }

        return dp[index][m] = best;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of piles: ");
        int n = sc.nextInt();

        int[] piles = new int[n];

        System.out.println("Enter pile values:");

        for (int i = 0; i < n; i++) {
            piles[i] = sc.nextInt();
        }

        int result = stoneGameII(piles);

        System.out.println("Maximum stones Alice can obtain: " + result);

        sc.close();
    }
}


