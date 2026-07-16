/*
============================================================
LEETCODE 51 - N-Queens

Problem

The n-queens puzzle is the problem of placing n queens
on an n × n chessboard such that no two queens attack
each other.

A queen attacks another queen if both are in

1. Same Row
2. Same Column
3. Same Diagonal

Return all possible valid board configurations.

------------------------------------------------------------
Example

Input

n = 4

Output

[
 [".Q..",
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",
  "Q...",
  "...Q",
  ".Q.."]
]

------------------------------------------------------------
Intuition

We place queens one row at a time.

Suppose we are currently placing the queen in

row = 2

Then rows

3,4,5...

are still empty.

Therefore,

we only need to check whether any queen exists

• Above in the same column

• Upper-left diagonal

• Upper-right diagonal

If all three are safe,

we place the queen.

After exploring that choice,

we remove the queen and try another column.

This process is called Backtracking.

------------------------------------------------------------
Approach

1. Create an empty chessboard.

2. Fill every cell with '.'

3. Start placing queens from row 0.

4. For every column

      Check whether placing a queen is safe.

5. If safe

      Place the queen.

      Move to the next row.

6. After recursion

      Remove the queen.

7. If all rows are completed

      Convert the board into List<String>

      Store the solution.

------------------------------------------------------------
Algorithm

1. Initialize board with '.'

2. Call

      nQueens(0)

3. If row == n

      Convert board into strings.

      Store answer.

4. For every column

      Check isSafe()

5. If safe

      Place queen.

      Solve next row.

      Remove queen.

6. Return all solutions.

------------------------------------------------------------
How isSafe() Works

Before placing a queen,

three directions are checked.

------------------------------------------------------------

1. Same Column

Example

Q . . .

. . . .

. ? . .

. . . .

If a queen already exists in the same column,

we cannot place another queen.

------------------------------------------------------------

2. Upper Left Diagonal

Example

Q . . .

. ? . .

. . . .

. . . .

Both queens attack diagonally.

------------------------------------------------------------

3. Upper Right Diagonal

Example

. . Q .

. ? . .

. . . .

. . . .

Again,

both queens attack diagonally.

------------------------------------------------------------

Notice

We never check

Current Row

because

there is never more than one queen in the current row.

We also never check

Lower Rows

because they haven't been processed yet.

------------------------------------------------------------
Why It Works

Every recursive call places exactly one queen.

Each placement satisfies

• Unique Row

• Unique Column

• Unique Diagonal

Whenever a placement becomes invalid,

Backtracking removes the queen
and explores another position.

Eventually,

every valid configuration is generated.

------------------------------------------------------------
Time Complexity

There are at most

N!

possible placements.

For every placement,

isSafe()

takes

O(N)

Overall

O(N × N!)

------------------------------------------------------------
Space Complexity

Board

O(N²)

Recursion Stack

O(N)

Overall

O(N²)

------------------------------------------------------------
Functions Used

Arrays.fill()

Initializes board with '.'

new String(char[])

Converts each row into String.

============================================================
*/


import java.util.*;

public class Main {

    public static List<List<String>> solveNQueens(int n) {

        List<List<String>> result = new ArrayList<>();

        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        nQueens(0, n, board, result);

        return result;
    }

    public static boolean isSafe(char[][] board, int row, int col, int n) {

        // Check column
        for (int i = 0; i < row; i++) {

            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {

            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {

            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    public static void nQueens(int row,
                               int n,
                               char[][] board,
                               List<List<String>> result) {

        if (row == n) {

            List<String> solution = new ArrayList<>();

            for (char[] r : board) {
                solution.add(new String(r));
            }

            result.add(solution);

            return;
        }

        for (int col = 0; col < n; col++) {

            if (isSafe(board, row, col, n)) {

                board[row][col] = 'Q';

                nQueens(row + 1, n, board, result);

                board[row][col] = '.';
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of n: ");

        int n = sc.nextInt();

        List<List<String>> ans = solveNQueens(n);

        System.out.println("\nPossible Configurations:\n");

        for (List<String> board : ans) {

            for (String row : board) {
                System.out.println(row);
            }

            System.out.println();
        }

        sc.close();
    }
}
