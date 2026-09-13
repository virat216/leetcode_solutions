/*
LeetCode 835 - Image Overlap

BRUTE FORCE:
------------
Try every possible translation of img1.

For every translation:
1. Shift img1.
2. Compare every cell with img2.
3. Count overlapping 1s.
4. Keep the maximum.

Time:  O(n^4)
Space: O(1)


OPTIMAL APPROACH:
-----------------
Only cells containing 1 are important.

Store the coordinates of all 1s in both matrices.

For every pair:

    p1 = (i, j) from img1
    p2 = (x, y) from img2

The translation required to make p1 overlap p2 is:

    dx = x - i
    dy = y - j

Therefore, every pair of 1s produces a translation (dx, dy).

If the same translation occurs multiple times,
all those pairs overlap under the same shift.

So we store:

    translation -> frequency

and the maximum frequency is the maximum overlap.


WHY THIS APPROACH:
------------------
Instead of trying every translation and scanning the
entire matrix, we directly determine which translation
would make each pair of 1s overlap.

The problem becomes a frequency-counting problem on
coordinate differences.


DRY RUN:
--------
img1 1s:
(0,0), (0,1), (1,1)

img2 1s:
(0,1), (1,0), (1,1)

For every pair, calculate:

    dx = x - i
    dy = y - j

If a translation appears twice, that means two pairs
of 1s overlap using that same translation.

The largest frequency is the answer.


COMPLEXITY:
-----------
Let k1 = number of 1s in img1.
Let k2 = number of 1s in img2.

Building coordinate lists:
Time: O(n^2)

Comparing every pair of 1s:
Time: O(k1 * k2)

Overall:
Time: O(n^2 + k1 * k2)

Worst case:
Time: O(n^4)

Space:
O(k1 * k2) for the translation frequency map
in the worst case.


PATTERN TO REMEMBER:
--------------------
For translation/shift problems involving points:

    Point A -> Point B
          ↓
    coordinate difference
          ↓
    (dx, dy)
          ↓
    frequency map
          ↓
    maximum frequency
*/
import java.util.*;

public class Main {

    public static int largestOverlap(int[][] img1, int[][] img2) {

        int n = img1.length;

        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();

        // Store coordinates of 1s
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (img1[i][j] == 1) {
                    ones1.add(new int[]{i, j});
                }

                if (img2[i][j] == 1) {
                    ones2.add(new int[]{i, j});
                }
            }
        }

        Map<String, Integer> map = new HashMap<>();

        int answer = 0;

        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {

                int dx = p2[0] - p1[0];
                int dy = p2[1] - p1[1];

                String key = dx + "," + dy;

                int count = map.getOrDefault(key, 0) + 1;

                map.put(key, count);

                answer = Math.max(answer, count);
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        int[][] img1 = {
            {1, 1, 0},
            {0, 1, 0},
            {0, 0, 0}
        };

        int[][] img2 = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 0, 1}
        };

        System.out.println(largestOverlap(img1, img2));
    }
}
