/*
============================================================
LEETCODE 3090 - MAXIMUM LENGTH SUBSTRING WITH TWO OCCURRENCES

Problem

Given a string s, find the length of the longest substring
where every character appears at most TWO times.

------------------------------------------------------------
EXAMPLE

Input:

s = "bcbbbcba"

Output:

4

One valid longest substring is:

"bcbb"

Frequencies:

b -> 3

So "bcbb" is actually invalid.

Instead, a valid longest substring is:

"bcbb"

Wait:

b appears 3 times.

Therefore we need to consider the valid windows carefully.

The important rule is:

Every character can appear AT MOST 2 times.

------------------------------------------------------------
INTUITION

This is a:

Sliding Window + Frequency Array

problem.

We maintain a window:

[left ... right]

Inside this window:

every character must have frequency <= 2.

------------------------------------------------------------
WHY SLIDING WINDOW?

We need the:

LONGEST SUBSTRING

that satisfies a condition.

Instead of checking every possible substring,
we use two pointers:

left
right

The right pointer expands the window.

If the window becomes invalid, we move
the left pointer forward until the window
becomes valid again.

------------------------------------------------------------
FREQUENCY ARRAY

The string contains lowercase English letters.

Therefore there are only:

26 characters

So instead of using a HashMap, we can use:

int[] freq = new int[26];

For a character:

'a' -> index 0
'b' -> index 1
'c' -> index 2
...
'z' -> index 25

We calculate:

s.charAt(right) - 'a'

------------------------------------------------------------
EXAMPLE

Suppose:

s = "aabb"

Frequency after processing:

a -> 2
b -> 2

The substring is valid.

------------------------------------------------------------
WHEN DOES THE WINDOW BECOME INVALID?

Suppose:

s = "aaab"

When we add the third 'a':

frequency:

a -> 3

But every character can appear at most 2 times.

Therefore the window becomes invalid.

We shrink it from the left.

------------------------------------------------------------
SHRINKING THE WINDOW

We use:

while (freq[s.charAt(right) - 'a'] > 2)

Remove the character at left:

freq[s.charAt(left) - 'a']--;

Then:

left++;

Continue until the frequency becomes <= 2.

------------------------------------------------------------
WHY DO WE ONLY CHECK THE RIGHT CHARACTER?

Before adding s[right], the window is already valid.

Therefore the only character whose frequency
can become invalid after adding the new character
is:

s[right]

So we only need to check:

freq[s.charAt(right) - 'a'] > 2

------------------------------------------------------------
DRY RUN

s = "aabbcc"

------------------------------------------------------------
right = 0

Character:

a

Window:

"a"

Frequency:

a -> 1

Valid.

Length:

1

------------------------------------------------------------
right = 1

Character:

a

Window:

"aa"

Frequency:

a -> 2

Valid.

Length:

2

------------------------------------------------------------
right = 2

Character:

b

Window:

"aab"

Frequencies:

a -> 2
b -> 1

Valid.

Length:

3

------------------------------------------------------------
right = 3

Character:

b

Window:

"aabb"

Frequencies:

a -> 2
b -> 2

Valid.

Length:

4

------------------------------------------------------------
right = 4

Character:

c

Window:

"aabbc"

Frequencies:

a -> 2
b -> 2
c -> 1

Valid.

Length:

5

------------------------------------------------------------
right = 5

Character:

c

Window:

"aabbcc"

Frequencies:

a -> 2
b -> 2
c -> 2

Valid.

Length:

6

Answer:

6

------------------------------------------------------------
ANOTHER EXAMPLE

s = "aaab"

k = 2

------------------------------------------------------------
right = 0

Window:

"a"

a -> 1

Valid.

------------------------------------------------------------
right = 1

Window:

"aa"

a -> 2

Valid.

------------------------------------------------------------
right = 2

Window:

"aaa"

a -> 3

Invalid.

------------------------------------------------------------
SHRINK

Remove s[left]:

'a'

Frequency:

a -> 2

Move:

left = 1

Current window:

"aa"

Valid.

------------------------------------------------------------
right = 3

Add 'b':

Window:

"aab"

Frequency:

a -> 2
b -> 1

Valid.

Maximum length:

3

Answer:

3

------------------------------------------------------------
ALGORITHM

1. Create:

   int[] freq = new int[26]

2. Set:

   left = 0
   maxLength = 0

3. Traverse the string using right.

4. Increase the frequency of s[right].

5. If the frequency becomes greater than 2:

   move left forward

   and decrease the corresponding frequency.

6. Once the window becomes valid:

   calculate:

   right - left + 1

7. Update maxLength.

8. Return maxLength.

------------------------------------------------------------
WHY USE A WHILE LOOP?

We use:

while (freq[s.charAt(right) - 'a'] > 2)

rather than if.

Because we must keep shrinking until the
window becomes valid.

------------------------------------------------------------
TIME COMPLEXITY

O(n)

The right pointer moves forward once.

The left pointer also moves forward only.

Therefore every character is processed
at most a constant number of times.

------------------------------------------------------------
SPACE COMPLEXITY

O(1)

The frequency array always has exactly:

26

elements.

------------------------------------------------------------
KEY CONCEPTS

✓ Sliding Window
✓ Two Pointers
✓ Frequency Array
✓ Substring
✓ Character Counting
✓ Longest Valid Window

------------------------------------------------------------
PATTERN

Longest Substring
        ↓
Frequency Constraint
        ↓
Sliding Window
        ↓
Frequency Array
        ↓
Expand right
        ↓
If frequency > 2
        ↓
Move left
        ↓
Track maximum length

============================================================
*/


import java.util.*;

public class Main {

    public static int maximumLengthSubstring(String s) {

        int[] freq = new int[26];

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            freq[s.charAt(right) - 'a']++;

            while (freq[s.charAt(right) - 'a'] > 2) {

                freq[s.charAt(left) - 'a']--;

                left++;
            }

            maxLength = Math.max(
                maxLength,
                right - left + 1
            );
        }

        return maxLength;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string: ");

        String s = sc.nextLine();

        int result = maximumLengthSubstring(s);

        System.out.println(
            "Maximum length: " + result
        );

        sc.close();
    }
}
