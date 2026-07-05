/*
============================================================
LEETCODE 1301 - Number of Paths with Max Score

Problem

We are given an n × n board where:

'E' -> Starting Cell (Top Left)
'S' -> Destination Cell (Bottom Right)
'X' -> Blocked Cell
'1' to '9' -> Score of that cell

We can only move

1. Down
2. Right
3. Diagonally Down-Right

(While solving from S to E, these become
Up, Left and Up-Left.)

Our task is to return:

1. Maximum score possible while reaching E.
2. Number of paths that give this maximum score.

If no valid path exists, return {0,0}.

------------------------------------------------------------
Intuition

Suppose we stand at some cell.

To reach the destination, we have only three choices.

1. Down
2. Right
3. Diagonal

Instead of exploring every path (which is exponential),
we calculate the answer for every cell only once.

For every cell we store

1. Maximum score obtainable from this cell.
2. Number of ways to obtain that maximum score.

These two values are enough to calculate the answer
for all previous cells.

This is a classic Dynamic Programming problem.

------------------------------------------------------------
Approach

We maintain two DP arrays.

score[i][j]

Stores the maximum score obtainable from cell (i,j)
to destination.

ways[i][j]

Stores the number of maximum-score paths from
cell (i,j) to destination.

Initially

score[][] = -1

because every cell is unreachable.

Destination

S

has

score = 0

because reaching destination gives no extra score.

ways = 1

because one path already exists
(the destination itself).

Now process the board from bottom-right
towards top-left.

For every cell,

look at

1. Down
2. Right
3. Diagonal

Choose the neighbour having maximum score.

If multiple neighbours have same score,
add their number of ways.

Finally,

add the current cell value to score.

Ignore

E

S

and

X

because they don't contribute to score.

------------------------------------------------------------
Algorithm

1. Create score[][] initialized with -1.
2. Create ways[][] initialized with 0.
3. Set destination score = 0.
4. Set destination ways = 1.
5. Traverse board from bottom-right.
6. Ignore blocked cells.
7. Check three possible neighbours.
8. Update maximum score.
9. Update number of ways.
10. Add current digit to score.
11. Return

{score[0][0], ways[0][0]}

If score[0][0] == -1

return

{0,0}

------------------------------------------------------------
Why It Works

Suppose we already know

maximum score

for every neighbour.

The current cell simply chooses
the best among them.

If only one neighbour gives maximum score,

copy

its score

and

its number of ways.

If multiple neighbours give the same maximum score,

all those paths are valid.

Hence,

add their number of ways.

Since every state depends only on already
computed states,

Dynamic Programming guarantees correctness.

------------------------------------------------------------
Time Complexity

There are

n²

cells.

Every cell checks only

3 neighbours.

Therefore

Time Complexity

O(n²)

------------------------------------------------------------
Space Complexity

Two DP arrays

score

ways

each of size

n × n

Therefore

Space Complexity

O(n²)

------------------------------------------------------------
Functions Used

Arrays.fill()

Initializes score array with -1.

Character.isDigit()

Checks whether current cell contains a digit.

Math.max()

Used while comparing scores.

substring()

(Not used.)

============================================================
*/

import java.util.*;

public class leetcode1301 {

    private static final int MOD = 1_000_000_007;

    public static int[] pathsWithMaxScore(List<String> board) {

        int n = board.size();

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        for (int[] row : score) {
            Arrays.fill(row, -1);
        }

        score[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                char current = board.get(i).charAt(j);

                if (current == 'X' || current == 'S') {
                    continue;
                }

                update(i, j, i + 1, j, score, ways);
                update(i, j, i, j + 1, score, ways);
                update(i, j, i + 1, j + 1, score, ways);

                if (score[i][j] != -1 && Character.isDigit(current)) {
                    score[i][j] += current - '0';
                }
            }
        }

        if (score[0][0] == -1) {
            return new int[]{0, 0};
        }

        return new int[]{score[0][0], ways[0][0]};
    }

    private static void update(int i,
                               int j,
                               int x,
                               int y,
                               int[][] score,
                               int[][] ways) {

        int n = score.length;

        if (x >= n || y >= n || score[x][y] == -1) {
            return;
        }

        if (score[x][y] > score[i][j]) {

            score[i][j] = score[x][y];
            ways[i][j] = ways[x][y];

        } else if (score[x][y] == score[i][j]) {

            ways[i][j] = (ways[i][j] + ways[x][y]) % MOD;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter board size: ");
        int n = sc.nextInt();

        sc.nextLine();

        List<String> board = new ArrayList<>();

        System.out.println("Enter board:");

        for (int i = 0; i < n; i++) {
            board.add(sc.nextLine());
        }

        int[] ans = pathsWithMaxScore(board);

        System.out.println("Maximum Score : " + ans[0]);
        System.out.println("Number of Paths : " + ans[1]);

        sc.close();
    }
}



