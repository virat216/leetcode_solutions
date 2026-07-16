/*
============================================================
LEETCODE 37 - Sudoku Solver

Problem

Write a program to solve a Sudoku puzzle by filling the
empty cells.

Rules of Sudoku

1. Every row must contain digits 1-9 exactly once.

2. Every column must contain digits 1-9 exactly once.

3. Every 3 × 3 sub-grid must contain digits 1-9 exactly once.

Empty cells are represented by '.'

The puzzle is guaranteed to have exactly one solution.

------------------------------------------------------------
Example

Input

5 3 . . 7 . . . .
6 . . 1 9 5 . . .
. 9 8 . . . . 6 .
8 . . . 6 . . . 3
4 . . 8 . 3 . . 1
7 . . . 2 . . . 6
. 6 . . . . 2 8 .
. . . 4 1 9 . . 5
. . . . 8 . . 7 9

Output

5 3 4 6 7 8 9 1 2
6 7 2 1 9 5 3 4 8
1 9 8 3 4 2 5 6 7
8 5 9 7 6 1 4 2 3
4 2 6 8 5 3 7 9 1
7 1 3 9 2 4 8 5 6
9 6 1 5 3 7 2 8 4
2 8 7 4 1 9 6 3 5
3 4 5 2 8 6 1 7 9

------------------------------------------------------------
Intuition

We solve Sudoku one empty cell at a time.

Whenever an empty cell is found,

try placing every digit from

1

to

9.

For every digit,

check whether it satisfies

• Row

• Column

• 3 × 3 Box

If the digit is valid,

place it and continue solving
the remaining board.

If later we reach a dead end,

remove the digit and try another one.

This process is called

Backtracking.

------------------------------------------------------------
Approach

1. Traverse the board.

2. Find the first empty cell.

3. Try digits

'1' to '9'.

4. If a digit is safe,

place it.

5. Solve the remaining board.

6. If recursion succeeds,

return true.

7. Otherwise,

remove the digit.

8. If no digit works,

return false.

------------------------------------------------------------
Algorithm

1. Search the board.

2. If no empty cell exists,

Sudoku is solved.

3. Otherwise,

try digits

1...9.

4. Check whether placing the digit is safe.

5. If safe,

place it.

Solve recursively.

6. If recursion returns true,

return true.

7. Else,

undo the move.

8. Return false.

------------------------------------------------------------
How isSafe() Works

Before placing a digit,

three checks are performed.

------------------------------------------------------------

1. Row Check

Example

5 3 . . 7 . . . .

Trying

5

again

×

Not Allowed.

------------------------------------------------------------

2. Column Check

Example

5

6

.

8

4

7

.

.

.

Trying

6

again

×

Not Allowed.

------------------------------------------------------------

3. 3 × 3 Box Check

Example

5 3 .

6 . .

. 9 8

Trying

9

again

×

Not Allowed.

------------------------------------------------------------
Why It Works

At every empty cell,

every possible digit

1...9

is explored.

Whenever a wrong digit is chosen,

Backtracking removes it.

Since every valid possibility is explored,

and invalid choices are discarded,

the unique Sudoku solution is eventually found.

------------------------------------------------------------
Time Complexity

Worst Case

O(9^(Empty Cells))

Each empty cell may try
up to 9 digits.

Pruning by row, column and box
makes it much faster in practice.

------------------------------------------------------------
Space Complexity

O(Empty Cells)

Due to recursion stack.

------------------------------------------------------------
Functions Used

solve()

Recursively fills empty cells.

isSafe()

Checks whether a digit can be placed.

============================================================
*/


import java.util.*;

public class Main {

    public static void solveSudoku(char[][] board) {

        solve(board);
    }

    private static boolean solve(char[][] board) {

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                if (board[row][col] == '.') {

                    for (char num = '1'; num <= '9'; num++) {

                        if (isSafe(board, row, col, num)) {

                            board[row][col] = num;

                            if (solve(board)) {
                                return true;
                            }

                            board[row][col] = '.';
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isSafe(char[][] board,
                                  int row,
                                  int col,
                                  char num) {

        // Check row
        for (int j = 0; j < 9; j++) {

            if (board[row][j] == num) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < 9; i++) {

            if (board[i][col] == num) {
                return false;
            }
        }

        // Check 3 × 3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {

            for (int j = startCol; j < startCol + 3; j++) {

                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        char[][] board = new char[9][9];

        System.out.println("Enter Sudoku Board:");

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                board[i][j] = sc.next().charAt(0);
            }
        }

        solveSudoku(board);

        System.out.println("\nSolved Sudoku:\n");

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                System.out.print(board[i][j] + " ");
            }

            System.out.println();
        }

        sc.close();
    }
}
