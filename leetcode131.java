/*
============================================================
LEETCODE 131 - Palindrome Partitioning

Problem

Given a string s, partition the string such that every
substring of the partition is a palindrome.

Return all possible palindrome partitions.

------------------------------------------------------------
Example

Input:

s = "aab"

Output:

[
 [a,a,b],
 [aa,b]
]

------------------------------------------------------------
Intuition

We need to divide the string into multiple substrings.

For every substring,

we first check whether it is a palindrome.

If it is,

we include it in the current partition and continue
partitioning the remaining string.

If it is not,

we skip it.

Since we need to generate ALL possible partitions,

Backtracking is the ideal approach.

------------------------------------------------------------
Approach

1. Start partitioning from index 0.

2. Try every possible substring starting from
   the current index.

3. Check whether the substring is a palindrome.

4. If it is,

      • Add it to the current partition.

      • Recur for the remaining string.

5. After recursion,

      Remove the last substring
      (Backtracking).

6. If we reach the end of the string,

      Store the current partition.

------------------------------------------------------------
Algorithm

1. Create an empty result list.

2. Start recursion from index 0.

3. For every ending index:

      • Extract substring.

      • Check palindrome.

      • If palindrome:

            Add substring.

            Recur from next index.

            Remove substring.

4. If start reaches string length,

      Store the current partition.

------------------------------------------------------------
Why It Works

At every index,

we explore every possible substring.

Example

"aab"

Possible first substrings

"a"

"aa"

"aab"

Only palindrome substrings are explored.

This guarantees that every partition stored
contains only palindrome substrings.

Since every possible cut is explored exactly once,

all valid partitions are generated.

------------------------------------------------------------
Time Complexity

O(N × 2^N)

where

N = Length of string.

Backtracking explores all partitions,
and palindrome checking costs O(N).

------------------------------------------------------------
Space Complexity

O(N)

Recursion stack + current partition.

(Result list is not included.)

------------------------------------------------------------
Functions Used

substring()

Extracts substring.

add()

Adds palindrome substring.

remove()

Removes last substring while backtracking.

new ArrayList<>(current)

Stores a copy of the current partition.

============================================================
*/

import java.util.*;

public class Main {

    public static List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();

        backtrack(s, 0, new ArrayList<>(), result);

        return result;
    }

    public static void backtrack(String s,
                                 int start,
                                 List<String> current,
                                 List<List<String>> result) {

        if (start == s.length()) {

            result.add(new ArrayList<>(current));
            return;
        }

        for (int end = start; end < s.length(); end++) {

            if (isPalindrome(s, start, end)) {

                current.add(s.substring(start, end + 1));

                backtrack(s,
                          end + 1,
                          current,
                          result);

                current.remove(current.size() - 1);
            }
        }
    }

    public static boolean isPalindrome(String s, int left, int right) {

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string: ");

        String s = sc.next();

        List<List<String>> ans = partition(s);

        System.out.println("Palindrome Partitions:");

        for (List<String> list : ans) {
            System.out.println(list);
        }

        sc.close();
    }
}
