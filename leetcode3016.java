/*
============================================================
LEETCODE 3016 - Minimum Number of Pushes to Type Word II

Problem

You are given a string word consisting
of lowercase English letters.

There are 8 telephone keys.

You can assign the 26 letters to these
keys in any order.

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

Unlike Part I,

you are free to rearrange the letters
among the keys.

Return the minimum number of pushes
needed to type the given word.

------------------------------------------------------------
Example 1

Input

word = "abcde"

Output

5

Explanation

Every character appears once.

Assign each character to the first
position of a different key.

Each requires one push.

Answer = 5

------------------------------------------------------------
Example 2

Input

word = "aabbccddeeffgghhiiii"

Output

24

Explanation

The letter

i

appears most frequently.

Assign it to a first position on one key.

Similarly,

assign the next most frequent letters
to first positions.

This minimizes the total number of pushes.

------------------------------------------------------------
Intuition

In Part I,

the letter positions were fixed.

In Part II,

we can choose where every letter goes.

To minimize total pushes,

letters occurring most frequently
should require the fewest pushes.

Therefore,

1. Count the frequency of every letter.

2. Sort frequencies in descending order.

3. Assign

First 8 most frequent letters

→ 1 push

Next 8

→ 2 pushes

Next 8

→ 3 pushes

Remaining letters

→ 4 pushes

------------------------------------------------------------
Approach

1. Count the frequency of all 26 letters.

2. Sort the frequency array.

3. Traverse frequencies from largest
to smallest.

4. For every frequency,

multiply it by

(position / 8) + 1

5. Add the result to the answer.

------------------------------------------------------------
Algorithm

1. Create frequency array of size 26.

2. Count occurrences of every letter.

3. Sort the array.

4. Traverse from index 25 to 0.

5. Skip frequencies equal to zero.

6. Compute pushes

(indexAssigned / 8) + 1

7. Add

frequency × pushes

8. Return answer.

------------------------------------------------------------
Dry Run

Input

word = "aaabbc"

Frequencies

a = 3

b = 2

c = 1

Sorted

1 2 3

Traverse from back

3

Assigned Pushes = 1

Answer = 3

--------------------------

2

Assigned Pushes = 1

Answer = 5

--------------------------

1

Assigned Pushes = 1

Answer = 6

Final Answer

6

------------------------------------------------------------
Why It Works

Each letter contributes

frequency × pushes

to the answer.

To minimize the total,

the largest frequencies should receive
the smallest push counts.

Sorting frequencies in descending order
achieves exactly that.

------------------------------------------------------------
Time Complexity

O(n)

Counting frequencies

+

Sorting 26 elements

O(26 log 26)

which is constant.

Overall

O(n)

------------------------------------------------------------
Space Complexity

O(1)

Frequency array has fixed size 26.

------------------------------------------------------------
Functions Used

charAt()

Returns the character at an index.

Arrays.sort()

Sorts the frequency array.

============================================================
*/
import java.util.*;

public class Main {

    public static int minimumPushes(String word) {

        int[] freq = new int[26];

        for (int i = 0; i < word.length(); i++) {
            freq[word.charAt(i) - 'a']++;
        }

        Arrays.sort(freq);

        int pushes = 0;
        int position = 0;

        for (int i = 25; i >= 0; i--) {

            if (freq[i] == 0) {
                break;
            }

            pushes += freq[i] * ((position / 8) + 1);
            position++;
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

