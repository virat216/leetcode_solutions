/*
============================================================
LEETCODE 3014 - Minimum Number of Pushes to Type Word I

Problem

You are given a string word.

There are 8 telephone keys available.

Each key can store multiple characters.

Typing the

1st character

assigned to a key requires

1 push.

Typing the

2nd character

assigned to the same key requires

2 pushes.

Similarly,

3rd character requires 3 pushes,

4th character requires 4 pushes.

Assign the letters to the keys such that
the total number of pushes needed to type
the word is minimum.

Return the minimum number of pushes.

------------------------------------------------------------
Example 1

Input

word = "abcde"

Output

5

Explanation

Each character can be placed on a separate key.

Each requires only one push.

Total = 5

------------------------------------------------------------
Example 2

Input

word = "xycdefghij"

Output

12

Explanation

First 8 characters

1 push each

Remaining 2 characters

2 pushes each

Answer

8 + 2 + 2 = 12

------------------------------------------------------------
Intuition

There are only

8 keys.

To minimize the number of pushes,

the earliest characters should require
the fewest pushes.

Therefore,

Assign

First 8 characters

→ 1 push each

Next 8 characters

→ 2 pushes each

Next 8 characters

→ 3 pushes each

Remaining characters

→ 4 pushes each

Instead of writing multiple conditions,

observe the pattern.

For every character at index i,

Number of pushes

=

(i / 8) + 1

------------------------------------------------------------
Approach

1. Initialize answer = 0.

2. Traverse every character of the word.

3. For index i,

compute

(i / 8) + 1

and add it to the answer.

4. Return the answer.

------------------------------------------------------------
Algorithm

1. Create

pushes = 0

2. Traverse

i = 0 to n-1

3. Add

(i / 8) + 1

to pushes.

4. Return pushes.

------------------------------------------------------------
Dry Run

Input

word = "abcdefghij"

Length = 10

-----------------------------------

Index

0

Pushes

(0 / 8) + 1 = 1

Total = 1

-----------------------------------

Index

1

Pushes

1

Total = 2

...

-----------------------------------

Index

7

Pushes

1

Total = 8

-----------------------------------

Index

8

Pushes

(8 / 8) + 1

= 2

Total = 10

-----------------------------------

Index

9

Pushes

2

Total = 12

Answer

12

------------------------------------------------------------
Why It Works

Every group of

8 characters

shares the same number of pushes.

Indices

0–7

need

1 push.

Indices

8–15

need

2 pushes.

Indices

16–23

need

3 pushes.

Indices

24–25

need

4 pushes.

The formula

(i / 8) + 1

automatically determines the correct
number of pushes for every character.

------------------------------------------------------------
Time Complexity

O(n)

The word is traversed exactly once.

------------------------------------------------------------
Space Complexity

O(1)

Only one integer variable is used.

------------------------------------------------------------
Functions Used

length()

Returns the length of the string.

============================================================
*/

import java.util.*;

public class Main {

    public static int minimumPushes(String word) {

        int pushes = 0;

        for (int i = 0; i < word.length(); i++) {
            pushes += (i / 8) + 1;
        }

        return pushes;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the word: ");

        String word = sc.next();

        System.out.println("Minimum Pushes = " + minimumPushes(word));

        sc.close();
    }
}

