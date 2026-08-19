/*
============================================================
LEETCODE 1386 - CINEMA SEAT ALLOCATION

Problem

A cinema has n rows.

Each row has 10 seats:

1 2 3 4 5 6 7 8 9 10

A family requires 4 consecutive seats.

A family can sit in one of these groups:

2 3 4 5

4 5 6 7

6 7 8 9

The reserved seats cannot be used.

Return the maximum number of families that can be
seated.

------------------------------------------------------------
IMPORTANT OBSERVATION

For a completely empty row:

1 2 3 4 5 6 7 8 9 10

We can place TWO families:

Family 1:

2 3 4 5

Family 2:

6 7 8 9

Therefore:

empty row -> 2 families

------------------------------------------------------------
WHAT ABOUT RESERVED SEATS?

We only need to process rows that contain
reserved seats.

Why?

If a row has no reserved seats, we already know
the answer:

2 families.

Therefore:

number of empty rows = n - number of rows
                       containing reservations

These rows contribute:

(n - map.size()) * 2

------------------------------------------------------------
BITMASK

Each row has only 10 seats.

We can represent the reserved seats of a row
using bits.

For example:

seat 2 reserved

1 << 2

seat 5 reserved

1 << 5

If multiple seats are reserved:

mask = mask | (1 << seat)

------------------------------------------------------------
WHY USE HASHMAP?

We don't want to create an array of size n because
n can be very large.

Instead:

HashMap<Integer, Integer>

stores:

row -> reserved seat bitmask

Example:

row 3:

seats 2 and 7 are reserved

mask contains bits for:

2 and 7

------------------------------------------------------------
THE THREE POSSIBLE FAMILY GROUPS

Left group:

2 3 4 5

Right group:

6 7 8 9

Middle group:

4 5 6 7

We represent each group as a bitmask.

------------------------------------------------------------
LEFT MASK

Seats:

2 3 4 5

Therefore:

(1 << 2)
|
(1 << 3)
|
(1 << 4)
|
(1 << 5)

------------------------------------------------------------
RIGHT MASK

Seats:

6 7 8 9

Therefore:

(1 << 6)
|
(1 << 7)
|
(1 << 8)
|
(1 << 9)

------------------------------------------------------------
MIDDLE MASK

Seats:

4 5 6 7

Therefore:

(1 << 4)
|
(1 << 5)
|
(1 << 6)
|
(1 << 7)

------------------------------------------------------------
HOW DO WE CHECK WHETHER A GROUP IS AVAILABLE?

Suppose:

mask = reserved seats

and:

left = seats 2,3,4,5

We check:

(mask & left) == 0

If this is true:

No seat required by the family is reserved.

Therefore the family can sit there.

------------------------------------------------------------
CASE 1

Both left and right groups are available.

Example:

No reservations in seats 2-9.

Then we can place:

Family 1:

2 3 4 5

Family 2:

6 7 8 9

Therefore:

+2 families

------------------------------------------------------------
CASE 2

Only one side is available.

For example:

seat 5 is reserved.

Then:

Left group:

2 3 4 5

is unavailable.

Right group:

6 7 8 9

is available.

Therefore:

+1 family

------------------------------------------------------------
CASE 3

Neither left nor right is available.

But the middle group might still be available.

Middle:

4 5 6 7

If it is free:

+1 family

Otherwise:

+0 families

------------------------------------------------------------
WHY CAN WE CHECK ONLY THESE GROUPS?

A group of 4 consecutive seats must be positioned
inside seats 2 through 9.

The only relevant arrangements are:

2 3 4 5
4 5 6 7
6 7 8 9

These cover every possible way to seat a family.

------------------------------------------------------------
DRY RUN

Suppose:

n = 3

reservedSeats:

[
    [1, 2],
    [1, 3],
    [1, 8]
]

------------------------------------------------------------
ROW 1

Reserved:

2, 3, 8

Left group:

2 3 4 5

Unavailable because 2 and 3 are reserved.

Right group:

6 7 8 9

Unavailable because 8 is reserved.

Middle group:

4 5 6 7

Available.

Therefore:

row 1 -> 1 family

------------------------------------------------------------
ROW 2

No reserved seats.

Therefore:

row 2 -> 2 families

------------------------------------------------------------
ROW 3

No reserved seats.

Therefore:

row 3 -> 2 families

------------------------------------------------------------
TOTAL

1 + 2 + 2

= 5

------------------------------------------------------------
ALGORITHM

1. Create a HashMap:

   row -> reserved seat bitmask

2. Traverse reservedSeats.

3. For every reservation:

   set the corresponding bit in that row's mask.

4. Calculate families from completely empty rows:

   (n - map.size()) * 2

5. For every row containing reservations:

   Check left group.

   Check right group.

6. If both are available:

   answer += 2

7. Otherwise, if either side is available
   OR the middle group is available:

   answer += 1

8. Return answer.

------------------------------------------------------------
TIME COMPLEXITY

O(r)

where:

r = number of reserved seats

We process every reservation once.

Then we process each affected row once.

------------------------------------------------------------
SPACE COMPLEXITY

O(r)

The HashMap stores only rows containing
reserved seats.

------------------------------------------------------------
KEY CONCEPTS

✓ Bit Manipulation
✓ Bitmask
✓ HashMap
✓ Greedy
✓ Set Intersection
✓ Row-wise Processing

------------------------------------------------------------
IMPORTANT BIT OPERATION

(mask & group) == 0

means:

No reserved seat overlaps with the required
family seats.

Therefore the family can be placed.

------------------------------------------------------------
PATTERN

Small fixed number of positions
        ↓
Represent using bits
        ↓
Bitmask
        ↓
Check overlap using &
        ↓
(mask & group) == 0
        ↓
Group is available

============================================================
*/
import java.util.*;

public class Main {

    public static int maxNumberOfFamilies(
            int n,
            int[][] reservedSeats) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {

            int row = seat[0];
            int col = seat[1];

            map.put(
                row,
                map.getOrDefault(row, 0) | (1 << col)
            );
        }

        int answer = (n - map.size()) * 2;

        int left =
                (1 << 2) |
                (1 << 3) |
                (1 << 4) |
                (1 << 5);

        int right =
                (1 << 6) |
                (1 << 7) |
                (1 << 8) |
                (1 << 9);

        int middle =
                (1 << 4) |
                (1 << 5) |
                (1 << 6) |
                (1 << 7);

        for (int mask : map.values()) {

            boolean canLeft = (mask & left) == 0;

            boolean canRight = (mask & right) == 0;

            if (canLeft && canRight) {

                answer += 2;

            } else if (
                canLeft ||
                canRight ||
                (mask & middle) == 0
            ) {

                answer += 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int n = sc.nextInt();

        System.out.print("Enter number of reserved seats: ");
        int m = sc.nextInt();

        int[][] reservedSeats = new int[m][2];

        System.out.println("Enter reserved seats:");

        for (int i = 0; i < m; i++) {

            reservedSeats[i][0] = sc.nextInt();
            reservedSeats[i][1] = sc.nextInt();
        }

        int result = maxNumberOfFamilies(n, reservedSeats);

        System.out.println(
            "Maximum number of families: " + result
        );

        sc.close();
    }
}

