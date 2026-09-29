class Solution {
    private char[][] grid;
    private byte[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int length = m + n - 1;

        if (length % 2 != 0 || grid[0][0] != '(' ||
            grid[m - 1][n - 1] != ')') {
            return false;
        }

        // 0: unknown, 1: false, 2: true
        memo = new byte[m][n][length + 1];
        return dfs(0, 0, 0);
    }

    private boolean dfs(int row, int col, int balance) {
        balance += grid[row][col] == '(' ? 1 : -1;
        int remaining = (m - 1 - row) + (n - 1 - col);

        if (balance < 0 || balance > remaining) return false;
        if (row == m - 1 && col == n - 1) return balance == 0;

        if (memo[row][col][balance] != 0) {
            return memo[row][col][balance] == 2;
        }

        boolean possible =
            (row + 1 < m && dfs(row + 1, col, balance)) ||
            (col + 1 < n && dfs(row, col + 1, balance));

        memo[row][col][balance] = (byte) (possible ? 2 : 1);
        return possible;
    }
}
/*
LeetCode 2267 - Check if There Is a Valid Parentheses String Path


BRUTE FORCE:
------------
Use DFS to explore every possible path from the top-left
to the bottom-right.

For every path:
    '(' -> balance + 1
    ')' -> balance - 1

If balance becomes negative, the path is invalid.

At the destination, the path is valid only when:

    balance == 0

Without memoization, the number of paths is exponential.

Time:  O(2^(m+n))
Space: O(m+n) for recursion


OPTIMAL APPROACH:
-----------------
Use DFS + Memoization.

The important state is:

    (row, col, balance)

This means:

    Can we reach the destination from (row, col)
    with the current parentheses balance?

There are only:

    m * n * (m+n)

possible states.

Each state is calculated only once and its result
is stored in:

    memo[row][col][balance]


BALANCE:
--------
For every cell:

    '(' -> balance + 1
    ')' -> balance - 1


PRUNING:
--------
1. If balance < 0:

   The parentheses prefix is already invalid.

   Return false.


2. If balance > remaining:

   There are not enough cells left to reduce the
   current balance to zero.

   Even if every remaining character is ')',
   balance cannot become zero.

   Therefore return false.


3. The total path length must be even.

   A valid parentheses string must contain an equal
   number of '(' and ')'.

   Therefore:

       (m + n - 1) % 2 == 0


4. The first cell must be '('.


5. The last cell must be ')'.


WHY THIS APPROACH:
------------------
The brute-force DFS explores the same state many times.

For example, multiple different paths can reach:

    (row = 3, col = 4, balance = 2)

From that point onward, the remaining problem is
exactly the same.

So we calculate this state once and memoize the result.

The state becomes:

    row + column + balance

which converts the exponential search into polynomial DP.


DRY RUN:
--------
For a path:

    ( -> ( -> ) -> )

The balances are:

    '(' -> 1
    '(' -> 2
    ')' -> 1
    ')' -> 0

The balance never becomes negative and ends at 0.

Therefore the path is valid.


TIME COMPLEXITY:
----------------
There are:

    m * n * (m+n)

possible states.

Each state performs O(1) work apart from recursive
transitions.

Therefore:

    O(m * n * (m+n))


SPACE COMPLEXITY:
-----------------
Memoization table:

    O(m * n * (m+n))

Recursion stack:

    O(m+n)

Overall:

    O(m * n * (m+n))


IMPORTANT PATTERN:
------------------
DFS + Memoization + State Compression

When a path problem has:
    Grid
    +
    Running value/balance
    +
    Many repeated states

use:

    (row, col, state)

as the memoization state.

For parentheses problems, the running state is:

    balance
*/
