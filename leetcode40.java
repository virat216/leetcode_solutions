/*
============================================================
LEETCODE 40 - Combination Sum II

Problem

Given an array of integers candidates and a target integer,
return all unique combinations where the chosen numbers
sum to the target.

Each number may be used only ONCE.

The solution set must not contain duplicate combinations.

------------------------------------------------------------
Example

Input:

candidates = [10,1,2,7,6,1,5]

target = 8

Output:

[
 [1,1,6],
 [1,2,5],
 [1,7],
 [2,6]
]

------------------------------------------------------------
Intuition

Unlike Combination Sum (LeetCode 39),

each element can be used only once.

Also,

the input array may contain duplicate values.

To avoid duplicate combinations,

we first sort the array.

Sorting places duplicate numbers together,
making them easy to skip.

At every recursive call,

we try every candidate starting from the current index.

If two adjacent numbers are equal,
we skip the second one at the same recursion level.

------------------------------------------------------------
Approach

1. Sort the array.

2. Start recursion from index 0.

3. Iterate through all candidates.

4. Skip duplicate numbers.

5. If current number is greater than target,
   stop exploring because the array is sorted.

6. Choose current number.

7. Move to the next index
   because each element can be used only once.

8. Backtrack.

------------------------------------------------------------
Algorithm

1. Sort candidates.

2. Create an empty result list.

3. Start recursion.

4. If target == 0

      Store the current combination.

5. Traverse from current index.

6. Skip duplicate numbers.

7. If current number > target

      Break.

8. Include current number.

9. Recurse using

      i + 1

10. Backtrack.

------------------------------------------------------------
Why It Works

Sorting groups duplicate numbers together.

Example

[1,1,2,5,6,7,10]

Suppose we are at

index = 0

Choose first

1

This explores all combinations beginning
with the first 1.

If we again start another branch using
the second 1,

the generated combinations will be identical.

Therefore,

we skip duplicate values at the same recursion level.

Also,

after selecting a number,

we move to

i + 1

instead of

i

because each element can be used only once.

------------------------------------------------------------
Time Complexity

Sorting

O(n log n)

Backtracking

O(2^n)

Overall

O(n log n + 2^n)

------------------------------------------------------------
Space Complexity

O(n)

Recursion stack + current combination.

(Result list is not included.)

------------------------------------------------------------
Functions Used

Arrays.sort()

Sorts the array so duplicate values become adjacent.

add()

Adds current number into the combination.

remove()

Removes the last inserted number while backtracking.

new ArrayList<>(current)

Stores a copy of the current combination.

============================================================
*/

import java.util.*;

public class Main {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);

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

        for (int i = index; i < candidates.length; i++) {

            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (candidates[i] > target) {
                break;
            }

            current.add(candidates[i]);

            backtrack(candidates,
                      target - candidates[i],
                      i + 1,
                      current,
                      result);

            current.remove(current.size() - 1);
        }
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

        List<List<Integer>> ans = combinationSum2(candidates, target);

        System.out.println("Possible Combinations:");

        for (List<Integer> list : ans) {
            System.out.println(list);
        }

        sc.close();
    }
}
