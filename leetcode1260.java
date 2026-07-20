/*
============================================================
LEETCODE 1260 - Shift 2D Grid

Problem

You are given an m × n integer matrix grid
and an integer k.

In one shift operation,

1. Every element moves one position to the right.

2. The last element of a row moves to the
first position of the next row.

3. The last element of the entire grid moves
to the first position of the grid.

Return the grid after performing
k shift operations.

------------------------------------------------------------
Example

Input

grid =

1 2 3
4 5 6
7 8 9

k = 1

After one shift

9 1 2
3 4 5
6 7 8

Output

[[9,1,2],[3,4,5],[6,7,8]]

------------------------------------------------------------
Intuition

Instead of shifting the grid one step at a time,

we can directly calculate where every element
should go.

Imagine the 2D grid as a single 1D array.

Example

Grid

1 2 3
4 5 6
7 8 9

Equivalent 1D array

Index

0 1 2 3 4 5 6 7 8

Value

1 2 3 4 5 6 7 8 9

If every element shifts by k positions,

the new index becomes

newIndex = (oldIndex + k) % totalElements

After finding the new index,

convert it back into row and column.

------------------------------------------------------------
Approach

1. Find

rows

columns

total elements

2. Reduce unnecessary shifts

k %= totalElements

3. Create an empty result grid.

4. Traverse every element.

Convert

(row,col)

into

1D index.

5. Compute the new index after shifting.

6. Convert the new index back into

(newRow,newCol).

7. Place the element at its new position.

------------------------------------------------------------
Algorithm

1. Compute

rows

cols

total = rows × cols

2. Compute

k %= total

3. Create result matrix.

4. For every element

oldIndex = row × cols + col

newIndex = (oldIndex + k) % total

5. Convert

newIndex

back into

newRow

newCol

6. Store the element.

7. Return the result.

------------------------------------------------------------
Dry Run

Grid

1 2 3
4 5 6
7 8 9

k = 1

Element

1

Old Index = 0

New Index = 1

Move to

(0,1)

--------------------------------------------

Element

2

Old Index = 1

New Index = 2

Move to

(0,2)

--------------------------------------------

...

--------------------------------------------

Element

9

Old Index = 8

New Index = 0

Move to

(0,0)

Final Grid

9 1 2
3 4 5
6 7 8

------------------------------------------------------------
Why It Works

Every element has exactly one old position
and exactly one new position.

Using index mapping,

every element is moved directly to its
final location.

No repeated shifting is required.

Thus,

every element is processed exactly once.

------------------------------------------------------------
Time Complexity

O(m × n)

Every element is visited exactly once.

------------------------------------------------------------
Space Complexity

O(m × n)

A new shifted grid is created.

------------------------------------------------------------
Functions Used

ArrayList

Stores the shifted grid.

add()

Initializes each row.

set()

Places each element at its correct position.

============================================================
*/




import java.util.*;

public class Main {

    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {

        int rows = grid.length;
        int cols = grid[0].length;
        int total = rows * cols;

        k %= total;

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < rows; i++) {

            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < cols; j++) {
                row.add(0);
            }

            result.add(row);
        }

        for (int i = 0; i < total; i++) {

            int oldRow = i / cols;
            int oldCol = i % cols;

            int newIndex = (i + k) % total;

            int newRow = newIndex / cols;
            int newCol = newIndex % cols;

            result.get(newRow).set(newCol, grid[oldRow][oldCol]);
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        int[][] grid = new int[rows][cols];

        System.out.println("Enter grid elements:");

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                grid[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        List<List<Integer>> ans = shiftGrid(grid, k);

        System.out.println("\nShifted Grid:");

        for (List<Integer> row : ans) {
            System.out.println(row);
        }

        sc.close();
    }
}
