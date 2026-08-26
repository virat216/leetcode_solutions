/*
============================================================
LEETCODE 2904 - SHORTEST AND LEXICOGRAPHICALLY SMALLEST
BEAUTIFUL STRING

Problem

Given a binary string s and an integer k,

find the shortest substring that contains exactly
k occurrences of '1'.

If multiple substrings have the same shortest length,
return the lexicographically smallest one.

If no such substring exists, return "".

------------------------------------------------------------
EXAMPLE

Input:

s = "100011001"
k = 2

We need a substring containing exactly two '1's.

Possible candidates include:

"100011"
"11001"

The shortest valid substring is:

"11001"

Therefore:

answer = "11001"

------------------------------------------------------------
IMPORTANT OBSERVATION

We need:

1. Exactly k ones.
2. Minimum length.
3. If lengths are equal, lexicographically smallest.

This is a classic:

SLIDING WINDOW

problem.

------------------------------------------------------------
SLIDING WINDOW

We maintain:

[left ... right]

and count how many '1's are inside the window.

Variable:

ones

stores the number of '1's in the current window.

------------------------------------------------------------
STEP 1 - EXPAND RIGHT

Move right from:

0 -> n - 1

Whenever:

s.charAt(right) == '1'

increment:

ones++

------------------------------------------------------------
STEP 2 - IF ONES > K

If:

ones > k

the current window is invalid.

We move left forward until:

ones <= k

Whenever we remove a '1':

ones--

------------------------------------------------------------
STEP 3 - WHEN ONES == K

Now the window contains exactly k ones.

But we want the SHORTEST possible substring.

So remove unnecessary leading zeroes.

For example:

window:

0001100

The zeroes at the beginning don't contribute
to the number of ones.

So we move left past them:

0001100
   ↑

until the substring starts with '1'.

This gives:

1100

------------------------------------------------------------
WHY REMOVE LEADING ZEROES?

Suppose:

window = "001101"

It contains exactly 3 ones.

But:

"1101"

also contains exactly 3 ones.

"1101" is shorter.

Therefore, whenever we have exactly k ones,
we should remove leading zeroes.

------------------------------------------------------------
CANDIDATE

After removing leading zeroes:

current = s.substring(left, right + 1)

Now current contains exactly k ones and is
the shortest valid substring ending at right.

------------------------------------------------------------
CHOOSING THE ANSWER

We compare the candidate with the current answer.

First:

If answer is empty:

take current.

Otherwise:

If current.length() < answer.length():

take current.

If lengths are equal:

take the lexicographically smaller string.

------------------------------------------------------------
LEXICOGRAPHICAL ORDER

For binary strings of equal length:

"1001" < "1010"

because at the first different position:

0 < 1

Therefore, when lengths are equal, we use:

current.compareTo(answer) < 0

------------------------------------------------------------
DRY RUN

s = "10100101"

k = 2

------------------------------------------------------------
RIGHT = 0

Character:

1

ones = 1

Not enough yet.

------------------------------------------------------------
RIGHT = 1

Character:

0

ones = 1

------------------------------------------------------------
RIGHT = 2

Character:

1

ones = 2

We now have exactly k = 2 ones.

Current window:

"101"

This is a valid candidate.

answer:

"101"

------------------------------------------------------------
RIGHT = 3

Character:

0

ones = 2

Current window:

"1010"

It is valid, but longer than:

"101"

So answer remains:

"101"

------------------------------------------------------------
RIGHT = 4

Character:

0

ones = 2

Current window:

"10100"

Still valid but longer.

------------------------------------------------------------
RIGHT = 5

Character:

1

ones = 3

Now:

ones > k

So move left.

Remove:

s[left] = '1'

ones becomes:

2

left moves forward.

Current window:

"001"

Remove leading zeroes.

The valid candidate becomes:

"001"

But after removing leading zeroes:

"1"

However, this contains only one '1'.

This illustrates why the window should be managed
carefully: we only remove leading zeroes AFTER
the window has exactly k ones, and the left boundary
must still preserve the first required '1'.

------------------------------------------------------------
IMPORTANT OPTIMIZATION

A cleaner way is to track the positions of '1's.

Suppose the positions of ones are:

p0, p1, p2, ...

Any substring containing exactly k ones must
start somewhere between:

previous one + 1

and:

first one

and end somewhere between:

last required one

and:

next one - 1

For minimum length, we always start at the first
required '1' and end at the last required '1'.

Therefore, for every group of k consecutive ones,
we can consider:

s.substring(firstOnePosition,
             lastOnePosition + 1)

This substring contains exactly k ones and has
minimum possible length for that group.

------------------------------------------------------------
EVEN BETTER OBSERVATION

Once we know the positions of all '1's, we can
consider every group of k consecutive ones.

Example:

s = "100110101"

Positions of ones:

1, 2, 4, 6

k = 2

Groups:

(1, 2) -> "11"

(2, 4) -> "001"

(4, 6) -> "0101"

The shortest is:

"11"

------------------------------------------------------------
WHICH APPROACH IS OPTIMAL?

Because the problem asks for the shortest substring
with exactly k ones, we can solve it in O(n) using
a sliding window.

We don't actually need to create substrings for
every candidate.

We can track:

start
end
length

and compare characters only when lengths are equal.

However, given the problem constraints, the standard
sliding-window solution with substring comparison
is accepted and is much easier to understand.

------------------------------------------------------------
ALGORITHM

1. Set:

   left = 0
   ones = 0
   answer = ""

2. Traverse the string using right.

3. If s[right] == '1':

   ones++

4. While:

   ones > k

   move left forward.

5. If:

   ones == k

   remove unnecessary leading zeroes.

6. Create the current substring.

7. Update answer if:

   current is shorter

   OR

   current has the same length and is
   lexicographically smaller.

8. Return answer.

------------------------------------------------------------
TIME COMPLEXITY

The sliding-window traversal itself is:

O(n)

String creation and comparison can make the practical
complexity higher in the worst case.

Given the constraints of the problem, this approach
is sufficient.

------------------------------------------------------------
SPACE COMPLEXITY

O(n)

for storing the candidate/result strings.

------------------------------------------------------------
KEY CONCEPTS

✓ Sliding Window
✓ Two Pointers
✓ Frequency Counting
✓ Binary String
✓ Lexicographical Comparison
✓ Minimum Length Substring

------------------------------------------------------------
PATTERN

Expand right
     ↓
Count ones
     ↓
ones > k?
     ↓
Move left
     ↓
ones == k
     ↓
Remove unnecessary zeroes
     ↓
Compare candidate
     ↓
Track shortest / lexicographically smallest

============================================================
*/
import java.util.*;

public class Main {

    public static String shortestBeautifulSubstring(
            String s, int k) {

        int left = 0;
        int ones = 0;

        String answer = "";

        for (int right = 0; right < s.length(); right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            while (ones > k) {

                if (s.charAt(left) == '1') {
                    ones--;
                }

                left++;
            }

            if (ones == k) {

                while (s.charAt(left) == '0') {
                    left++;
                }

                String current =
                    s.substring(left, right + 1);

                if (answer.isEmpty() ||
                    current.length() < answer.length() ||
                    (current.length() == answer.length() &&
                     current.compareTo(answer) < 0)) {

                    answer = current;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter binary string: ");
        String s = sc.nextLine();

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        String result =
            shortestBeautifulSubstring(s, k);

        System.out.println(
            "Shortest beautiful substring: " + result
        );

        sc.close();
    }
}

