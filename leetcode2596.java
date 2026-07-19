/*
============================================================
LEETCODE 2596 - Check Knight Tour Configuration

(Recursive DFS Approach)

Problem

You are given an n × n grid containing numbers from

0 to n²-1.

The number at each cell represents the order in which
the knight visits that cell.

Return true if the given grid represents a valid
Knight's Tour, otherwise return false.

------------------------------------------------------------
Example

Input

0 3 6
5 8 1
2 7 4

The knight should move

0 → 1 → 2 → ... → 8

Every move must be a valid knight move.

------------------------------------------------------------
Intuition

The knight always follows a fixed sequence

0

↓

1

↓

2

↓

3

...

↓

n²-1

Instead of searching for every number in the grid,

we start from the cell containing 0
(which must be at (0,0))

and recursively try all eight possible knight moves
looking for the next expected value.

If one of those moves reaches the next number,

continue recursively.

If eventually we reach

n²-1,

the entire tour is valid.

Otherwise,

the tour is invalid.

------------------------------------------------------------
Approach

Start from

(0,0)

with expected value

0.

At every recursive call

1. Check whether the current position is valid.

2. Check whether the current cell contains the
expected number.

3. If expected value is

n²-1,

the complete tour has been verified.

4. Otherwise,

recursively try all eight knight moves
looking for

expected value + 1.

5. If any recursive call succeeds,

return true.

Otherwise,

return false.

------------------------------------------------------------
Algorithm

1. Start DFS from

(0,0)

with expected value = 0.

2. If position is outside the grid,

return false.

3. If grid[r][c] is not equal to expected value,

return false.

4. If expected value equals

n²-1,

return true.

5. Try all eight knight moves.

6. If any recursive call returns true,

return true.

7. Otherwise,

return false.

------------------------------------------------------------
Dry Run

Grid

0 11 16 5 20
17 4 19 10 15
12 1 8 21 6
3 18 23 14 9
24 13 2 7 22

Start

(0,0)

Expected = 0

↓

Try all eight knight moves.

Only one reaches

1

↓

Repeat.

Only one reaches

2

↓

Repeat.

Eventually

23

↓

24

Reached

Return true.

------------------------------------------------------------
Why It Works

Every recursive call expects exactly one number.

For example,

when expected value is

5,

the recursion only succeeds if one of the
eight knight moves lands on the cell containing

5.

Since every number from

0

to

n²-1

appears exactly once,

there is at most one valid recursive path.

If every expected value is reached in order,

the configuration is a valid Knight's Tour.

Otherwise,

the recursion eventually fails and returns false.

------------------------------------------------------------
Time Complexity

O(n²)

There are n² numbers in the grid.

For every expected value,

at most eight knight moves are checked.

Since 8 is a constant,

Overall

O(n²)

------------------------------------------------------------
Space Complexity

O(n²)

Due to recursion stack.

Maximum recursion depth

=

n²

------------------------------------------------------------
Functions Used

isValid()

Recursively verifies the Knight's Tour.

============================================================
*/

import java.util.*;

public class Main {

    static boolean isValid(int[][] grid, int r, int c, int n, int expVal) {

        if (r < 0 || c < 0 || r >= n || c >= n || grid[r][c] != expVal) {
            return false;
        }

        if (expVal == (n * n - 1)) {
            return true;
        }

        boolean ans1 = isValid(grid, r - 2, c + 1, n, expVal + 1);
        boolean ans2 = isValid(grid, r - 1, c + 2, n, expVal + 1);
        boolean ans3 = isValid(grid, r + 1, c + 2, n, expVal + 1);
        boolean ans4 = isValid(grid, r + 2, c + 1, n, expVal + 1);
        boolean ans5 = isValid(grid, r + 2, c - 1, n, expVal + 1);
        boolean ans6 = isValid(grid, r + 1, c - 2, n, expVal + 1);
        boolean ans7 = isValid(grid, r - 1, c - 2, n, expVal + 1);
        boolean ans8 = isValid(grid, r - 2, c - 1, n, expVal + 1);

        return ans1 || ans2 || ans3 || ans4 || ans5 || ans6 || ans7 || ans8;
    }

    static boolean checkValidGrid(int[][] grid) {

        return isValid(grid, 0, 0, grid.length, 0);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter grid size: ");

        int n = sc.nextInt();

        int[][] grid = new int[n][n];

        System.out.println("Enter the grid:");

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                grid[i][j] = sc.nextInt();
            }
        }

        System.out.println(checkValidGrid(grid));

        sc.close();
    }
}

