/*
============================================================
LEETCODE 1081 - Smallest Subsequence of Distinct Characters

Problem

Given a string s,

return the lexicographically smallest subsequence
that contains every distinct character exactly once.

A subsequence is obtained by deleting zero or more
characters without changing the relative order of
the remaining characters.

------------------------------------------------------------
Example 1

Input

s = "bcabc"

Output

"abc"

Explanation

Distinct characters are

a, b, c

Possible subsequences are

bca

cab

bac

abc

Among all valid subsequences,

"abc"

is lexicographically smallest.

------------------------------------------------------------
Example 2

Input

s = "cbacdcbc"

Output

"acdb"

------------------------------------------------------------
Intuition

We want the smallest possible string.

Whenever we encounter a smaller character,

we would like to place it before larger characters.

However,

we can remove a larger character only if it
appears again later in the string.

Otherwise,

we would lose that character forever.

Therefore,

we maintain

1. Last occurrence of every character.

2. A stack to build the answer.

3. A visited array to avoid duplicates.

------------------------------------------------------------
Approach

Step 1

Store the last occurrence of every character.

Example

s = "cbacdcbc"

Last occurrences

a -> 2

b -> 6

c -> 7

d -> 4

------------------------------------------------------------

Step 2

Traverse the string.

If the current character is already included,

skip it.

------------------------------------------------------------

Step 3

Otherwise,

while

1. Stack is not empty.

2. Stack top is larger than current character.

3. Stack top appears again later.

remove it.

------------------------------------------------------------

Step 4

Push the current character into the stack.

Mark it as visited.

------------------------------------------------------------

Step 5

Reverse the stack to obtain the answer.

------------------------------------------------------------
Algorithm

1. Store last occurrence of every character.

2. Create

visited[]

stack

3. Traverse the string.

4. Skip already visited characters.

5. Remove larger characters that occur later.

6. Push current character.

7. Reverse stack and return answer.

------------------------------------------------------------
Dry Run

Input

s = "cbacdcbc"

Last Occurrence

c -> 7

b -> 6

a -> 2

d -> 4

------------------------------------------------------------

Read 'c'

Stack

[c]

------------------------------------------------------------

Read 'b'

b < c

c appears later

Pop c

Push b

Stack

[b]

------------------------------------------------------------

Read 'a'

a < b

b appears later

Pop b

Push a

Stack

[a]

------------------------------------------------------------

Read 'c'

Stack

[a,c]

------------------------------------------------------------

Read 'd'

Stack

[a,c,d]

------------------------------------------------------------

Read 'c'

Already visited

Skip

------------------------------------------------------------

Read 'b'

Top = d

d does NOT appear later

Cannot remove.

Push b

Stack

[a,c,d,b]

------------------------------------------------------------

Read 'c'

Already visited

Skip

------------------------------------------------------------

Final Answer

acdb

------------------------------------------------------------
Why It Works

The stack always maintains the smallest possible
lexicographical order.

Whenever a smaller character arrives,

we remove larger characters only if they
can be added again later.

The visited array guarantees that every
character appears exactly once.

Therefore,

the final stack forms the smallest valid
subsequence.

------------------------------------------------------------
Time Complexity

O(n)

Each character is pushed into the stack
at most once.

Each character is popped from the stack
at most once.

------------------------------------------------------------
Space Complexity

O(1)

Since there are only 26 lowercase letters,

lastIndex[26]

visited[26]

stack stores at most 26 characters.

------------------------------------------------------------
Functions Used

charAt()

Accesses characters of the string.

ArrayDeque

Implements the stack.

StringBuilder

Constructs the final answer.

reverse()

Reverses the string built from the stack.

============================================================
*/



import java.util.*;

public class Main {

    public static String smallestSubsequence(String s) {

        int[] lastIndex = new int[26];

        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] visited = new boolean[26];

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (visited[ch - 'a']) {
                continue;
            }

            while (!stack.isEmpty()
                    && stack.peek() > ch
                    && lastIndex[stack.peek() - 'a'] > i) {

                visited[stack.pop() - 'a'] = false;
            }

            stack.push(ch);
            visited[ch - 'a'] = true;
        }

        StringBuilder ans = new StringBuilder();

        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        return ans.reverse().toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the string: ");

        String s = sc.next();

        String ans = smallestSubsequence(s);

        System.out.println("Smallest Subsequence = " + ans);

        sc.close();
    }
}

