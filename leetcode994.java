/*
============================================================
LEETCODE 994 - ROTTING ORANGES

Problem

We are given a grid where:

0 = Empty cell
1 = Fresh orange
2 = Rotten orange

Every minute, a rotten orange makes its adjacent
fresh oranges rotten.

Adjacent means:

up
down
left
right

We need to return the minimum number of minutes
required to rot all fresh oranges.

If it is impossible to rot every fresh orange,
return -1.

------------------------------------------------------------
EXAMPLE

Input:

[
    [2,1,1],
    [1,1,0],
    [0,1,1]
]

Output:

4

------------------------------------------------------------
INTUITION

This is a BFS problem.

Why?

All initially rotten oranges start spreading
at the SAME TIME.

For example:

2 1 1
1 1 0
0 1 1

The rotten orange at (0,0) spreads during
minute 1.

The newly rotten oranges spread during
minute 2.

And so on.

This is exactly how BFS works:

Level 0 -> initially rotten oranges
Level 1 -> oranges rotten after 1 minute
Level 2 -> oranges rotten after 2 minutes
Level 3 -> oranges rotten after 3 minutes
...

Therefore:

BFS level = 1 minute

------------------------------------------------------------
WHY MULTI-SOURCE BFS?

There can be multiple rotten oranges initially.

Example:

2 1 1 0
1 1 0 2

Both rotten oranges spread simultaneously.

Therefore, instead of starting BFS from one
rotten orange, we put ALL rotten oranges into
the queue initially.

This is called:

MULTI-SOURCE BFS

------------------------------------------------------------
QUEUE

We use:

Queue<int[]> queue

Each element stores:

[row, column]

For example:

[0, 0]

represents the cell at:

row = 0
column = 0

------------------------------------------------------------
STEP 1 - COUNT FRESH ORANGES

We need to know how many fresh oranges exist.

Why?

Because after BFS finishes, we need to determine
whether all fresh oranges became rotten.

We maintain:

int fresh

Every time a fresh orange becomes rotten:

fresh--

------------------------------------------------------------
STEP 2 - ADD ALL ROTTEN ORANGES

While scanning the grid:

If:

grid[i][j] == 2

add it to the queue.

If:

grid[i][j] == 1

increment fresh.

Example:

grid:

2 1 1
1 2 1

Queue:

[(0,0), (1,1)]

Fresh:

4

------------------------------------------------------------
STEP 3 - PROCESS ONE MINUTE

At the beginning of every minute:

int size = queue.size();

This is very important.

The current queue contains all oranges that
were rotten at the START of this minute.

We process exactly those oranges.

------------------------------------------------------------
WHY queue.size()?

Suppose:

Queue before minute:

[A, B]

size = 2

A and B represent oranges that were already
rotten.

While processing A and B, they may make
new oranges rotten.

Those new oranges are added to the queue:

[A, B, C, D]

But C and D should NOT spread during the
same minute.

They should spread during the NEXT minute.

Therefore we process only the original:

size = 2

elements.

------------------------------------------------------------
STEP 4 - CHECK FOUR DIRECTIONS

For every rotten orange:

[row, col]

we check:

UP:

row - 1, col

DOWN:

row + 1, col

LEFT:

row, col - 1

RIGHT:

row, col + 1

We can store these directions as:

int[][] directions = {
    {-1, 0},
    {1, 0},
    {0, -1},
    {0, 1}
};

------------------------------------------------------------
STEP 5 - ROT FRESH ORANGES

Suppose:

grid[newRow][newCol] == 1

Then:

1. Make it rotten:

grid[newRow][newCol] = 2;

2. Decrease fresh count:

fresh--;

3. Add it to the queue:

queue.offer(new int[]{newRow, newCol});

------------------------------------------------------------
WHY MARK IT AS 2 IMMEDIATELY?

Suppose two rotten oranges both touch
the same fresh orange.

If we don't immediately mark it as rotten,
we could add the same cell to the queue
multiple times.

By doing:

grid[newRow][newCol] = 2;

immediately, we mark it as visited.

------------------------------------------------------------
MINUTES

After processing one complete BFS level:

minutes++;

That represents one minute passing.

------------------------------------------------------------
DRY RUN

grid:

[
    [2,1,1],
    [1,1,0],
    [0,1,1]
]

Initially:

Queue:

[(0,0)]

Fresh:

6

Minutes:

0

------------------------------------------------------------
MINUTE 1

Process:

(0,0)

It rots:

(0,1)
(1,0)

Queue becomes:

[(0,1), (1,0)]

Fresh:

4

minutes:

1

------------------------------------------------------------
MINUTE 2

Process:

(0,1)
(1,0)

They rot:

(0,2)
(1,1)

Queue contains newly rotten cells.

Fresh:

2

minutes:

2

------------------------------------------------------------
MINUTE 3

Process newly rotten cells.

They rot:

(2,1)

Fresh:

1

minutes:

3

------------------------------------------------------------
MINUTE 4

(2,1) rots:

(2,2)

Fresh:

0

minutes:

4

------------------------------------------------------------
ALL FRESH ORANGES ARE ROTTEN

Return:

4

------------------------------------------------------------
WHAT IF SOME ORANGES CANNOT BE REACHED?

Example:

[
    [2,0,1]
]

The rotten orange cannot reach the fresh orange
because 0 blocks the path.

After BFS:

fresh > 0

Therefore:

return -1

------------------------------------------------------------
ALGORITHM

1. Create a Queue.

2. Traverse the grid.

3. Add every rotten orange to the Queue.

4. Count every fresh orange.

5. While the Queue is not empty AND
   fresh > 0:

   a. Store current queue size.

   b. Process exactly that many oranges.

   c. Check four directions.

   d. If a neighboring cell is fresh:
        - make it rotten
        - decrement fresh
        - add it to queue

   e. Increase minutes.

6. If fresh == 0:

   return minutes

7. Otherwise:

   return -1

------------------------------------------------------------
TIME COMPLEXITY

O(m * n)

Every cell is processed at most once.

Where:

m = number of rows
n = number of columns

------------------------------------------------------------
SPACE COMPLEXITY

O(m * n)

The queue can contain up to O(m * n)
cells in the worst case.

------------------------------------------------------------
KEY CONCEPTS

✓ BFS
✓ Multi-Source BFS
✓ Queue
✓ Level Order Traversal
✓ Grid Traversal
✓ Direction Array
✓ Visited State
✓ Shortest Time/Distance

------------------------------------------------------------
PATTERN

Multiple starting points
        ↓
Put ALL sources in Queue
        ↓
BFS
        ↓
Process level by level
        ↓
Each level = one minute

============================================================
*/
import java.util.*;

public class Main {

    public static int orangesRotting(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();

        int fresh = 0;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        int minutes = 0;

        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        while (!queue.isEmpty() && fresh > 0) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] current = queue.poll();

                int row = current[0];
                int col = current[1];

                for (int[] direction : directions) {

                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    if (newRow >= 0 &&
                        newRow < rows &&
                        newCol >= 0 &&
                        newCol < cols &&
                        grid[newRow][newCol] == 1) {

                        grid[newRow][newCol] = 2;

                        fresh--;

                        queue.offer(new int[]{
                            newRow,
                            newCol
                        });
                    }
                }
            }

            minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        int[][] grid = new int[rows][cols];

        System.out.println("Enter grid:");

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int result = orangesRotting(grid);

        System.out.println("Minutes required: " + result);

        sc.close();
    }
}


