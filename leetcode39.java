/*
============================================================
LEETCODE 39 - Combination Sum

Problem

Given an array of distinct integers candidates and a target
integer, return all unique combinations of candidates where
the chosen numbers sum to the target.

A number may be chosen unlimited times.

The order of combinations does not matter.

------------------------------------------------------------
Example

Input:

candidates = [2,3,6,7]

target = 7

Output:

[[2,2,3],[7]]

------------------------------------------------------------
Intuition

For every number, we have two choices:

1. Include the current number.
2. Exclude the current number.

Since a number can be used unlimited times,
after including it we stay on the same index.

After excluding it, we move to the next index.

This naturally forms a recursion tree,
making Backtracking the ideal approach.

------------------------------------------------------------
Approach

1. Start from index 0.

2. At every index, make two recursive calls.

   • Include the current number.
     - Add it to the current combination.
     - Reduce the target.
     - Stay on the same index.

   • Exclude the current number.
     - Remove it from the current combination.
     - Move to the next index.

3. If target becomes 0,
   store the current combination.

4. If target becomes negative or all numbers are used,
   stop exploring that path.

------------------------------------------------------------
Algorithm

1. Create an empty result list.

2. Start recursion from index 0.

3. If target == 0
      Store current combination.

4. If target < 0
      Return.

5. If index reaches array length
      Return.

6. Include current number.

7. Backtrack.

8. Exclude current number.

9. Return result.

------------------------------------------------------------
Why It Works

At every step we explore every possible choice.

Since every element has only two decisions
(include or exclude),

every valid combination is explored exactly once.

Because we always move forward after excluding an element,
duplicate combinations like

[2,3,2]

are never generated.

Hence every answer is unique.

------------------------------------------------------------
Time Complexity

Worst Case

O(2^(target / minimumCandidate))

The recursion tree grows exponentially depending on
the target value.

------------------------------------------------------------
Space Complexity

O(target / minimumCandidate)

Due to recursion stack and current combination list.

------------------------------------------------------------
Functions Used

add()

Adds current number to the combination.

remove()

Removes the last inserted number while backtracking.

new ArrayList<>(current)

Creates a copy of the current combination before storing.

============================================================
*/

import java.util.*;

public class Main {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();

        backtrack(candidates, target, 0, new ArrayList<>(), result);

        return result;
    }

    public static void backtrack(int[] candidates,
                                 int target,
                                 int index,
                                 List<Integer> current,
                                 List<List<Integer>> result) {

        if (target == 0) {

            result.add(new ArrayList<>(current));
            return;
        }

        if (target < 0 || index == candidates.length) {
            return;
        }

        current.add(candidates[index]);

        backtrack(candidates,
                  target - candidates[index],
                  index,
                  current,
                  result);

        current.remove(current.size() - 1);

        backtrack(candidates,
                  target,
                  index + 1,
                  current,
                  result);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of candidates: ");
        int n = sc.nextInt();

        int[] candidates = new int[n];

        System.out.println("Enter candidates:");

        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }

        System.out.print("Enter target: ");
        int target = sc.nextInt();

        List<List<Integer>> ans = combinationSum(candidates, target);

        System.out.println("Possible Combinations:");

        for (List<Integer> list : ans) {
            System.out.println(list);
        }

        sc.close();
    }
}
