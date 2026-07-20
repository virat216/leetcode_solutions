/*
============================================================
LEETCODE 90 - Subsets II

Problem

Given an integer array nums that may contain
duplicate elements,

return all possible subsets (the power set).

The solution set must NOT contain duplicate subsets.

Return the subsets in any order.

------------------------------------------------------------
Example

Input

nums = [1,2,2]

Output

[]

[1]

[2]

[1,2]

[2,2]

[1,2,2]

------------------------------------------------------------
Intuition

For every element,

we have two choices

1. Include it.

2. Exclude it.

This is exactly the Subsets problem.

However,

duplicate numbers can generate identical subsets.

Example

nums = [1,2,2]

If we choose

the first 2

or

the second 2

independently,

both can produce

[1,2]

which creates duplicate answers.

To avoid this,

sort the array first.

After sorting,

duplicate numbers become adjacent.

Whenever two consecutive numbers are equal,

only the first one should start a new recursive
branch.

------------------------------------------------------------
Approach

1. Sort the array.

2. Start backtracking from index 0.

3. Every recursive call represents one subset.

4. Add the current subset into the answer.

5. Iterate through remaining elements.

6. Skip duplicate elements at the same
recursion level.

7. Include current element.

8. Recurse for remaining elements.

9. Remove the element (Backtrack).

------------------------------------------------------------
Algorithm

1. Sort nums.

2. Create an empty answer list.

3. Call

backtrack(0)

4. Add current subset into answer.

5. Traverse from current index.

6. Skip duplicates using

if(i > index && nums[i] == nums[i-1])

7. Choose current element.

8. Recurse.

9. Undo the choice.

------------------------------------------------------------
Dry Run

nums

[1,2,2]

After Sorting

[1,2,2]

Start

[]

↓

Answer

[]

--------------------------------------------

Choose

1

Subset

[1]

↓

Answer

[]

[1]

--------------------------------------------

Choose

2

Subset

[1,2]

↓

Answer

[]

[1]

[1,2]

--------------------------------------------

Choose second

2

Subset

[1,2,2]

↓

Answer

[]

[1]

[1,2]

[1,2,2]

Backtrack

--------------------------------------------

Return to

[]

Now

i = 2

nums[2] == nums[1]

Duplicate

Skip

Finished.

Final Answer

[]

[1]

[1,2]

[1,2,2]

[2]

[2,2]

------------------------------------------------------------
Why It Works

Sorting places duplicate numbers together.

The condition

if(i > index && nums[i] == nums[i-1])

ensures that only the first duplicate starts a
new recursive branch.

The remaining duplicates at the same recursion
level are skipped.

This guarantees that every unique subset is
generated exactly once.

------------------------------------------------------------
Time Complexity

O(n × 2^n)

There are

2^n

possible subsets.

Copying every subset requires

O(n).

------------------------------------------------------------
Space Complexity

O(n)

Recursion depth is at most

n.

(Excluding output space.)

------------------------------------------------------------
Functions Used

Arrays.sort()

Sorts the array.

ArrayList

Stores subsets.

add()

Adds an element.

remove()

Backtracks.

============================================================
*/

import java.util.*;

public class Main {

    public static void backtrack(int[] nums,
                                 int index,
                                 List<Integer> subset,
                                 List<List<Integer>> result) {

        result.add(new ArrayList<>(subset));

        for (int i = index; i < nums.length; i++) {

            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }

            subset.add(nums[i]);

            backtrack(nums, i + 1, subset, result);

            subset.remove(subset.size() - 1);
        }
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        backtrack(nums, 0, new ArrayList<>(), result);

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");

        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter array elements:");

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        List<List<Integer>> ans = subsetsWithDup(nums);

        System.out.println("\nUnique Subsets:");

        for (List<Integer> subset : ans) {
            System.out.println(subset);
        }

        sc.close();
    }
}


