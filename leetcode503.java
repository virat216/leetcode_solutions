/*
============================================================
LEETCODE 503 - NEXT GREATER ELEMENT II

Problem

You are given a circular integer array nums.

For every element,

find the first element to its right that is
greater than it.

If no greater element exists,

return -1.

------------------------------------------------------------
WHAT DOES CIRCULAR ARRAY MEAN?

After reaching the last element,

we go back to the first element.

Example:

nums = [1, 2, 1]

Normally:

1 -> 2 -> 1

But because the array is circular:

1 -> 2 -> 1 -> 1 -> 2 -> 1 -> ...

Therefore, for the last element 1,

we can continue searching from the beginning.

The answer is:

[2, -1, 2]

------------------------------------------------------------
INTUITION

This is a variation of:

LeetCode 496 - Next Greater Element I

The main difference is:

LeetCode 496:
Normal array

LeetCode 503:
Circular array

We can handle the circular nature by traversing
the array TWICE.

------------------------------------------------------------
WHY TWO PASSES?

Suppose:

nums = [1, 2, 1]

First pass:

1 2 1

Second pass:

1 2 1

Together:

1 2 1 1 2 1

This allows elements near the end of the
array to find greater elements near the
beginning.

------------------------------------------------------------
MONOTONIC STACK

We use a decreasing stack.

The stack stores INDICES whose next greater
element has not been found yet.

Example:

nums = [2, 1, 3]

Process:

2

stack:

[0]

Then:

1 < 2

Push index 1.

stack:

[0, 1]

Then:

3 > 1

Therefore:

answer[1] = 3

Pop 1.

Now:

3 > 2

Therefore:

answer[0] = 3

Pop 0.

------------------------------------------------------------
WHY STORE INDICES?

We need to update:

ans[index]

Therefore, we need to know the position of
the element.

So instead of:

Stack<Integer>

containing values,

we store:

Stack<Integer>

containing indices.

Then:

nums[stack.peek()]

gives us the corresponding value.

------------------------------------------------------------
CIRCULAR ARRAY

We simulate two passes using:

for (int i = 0; i < 2 * n; i++)

But i can become larger than n - 1.

So we calculate the actual array index using:

int index = i % n;

Example:

n = 3

i = 0 -> index = 0
i = 1 -> index = 1
i = 2 -> index = 2
i = 3 -> index = 0
i = 4 -> index = 1
i = 5 -> index = 2

Therefore:

0 1 2 0 1 2

represents two complete passes.

------------------------------------------------------------
IMPORTANT

We only push indices during the FIRST pass.

Why?

Because we only need each original element
to be waiting for its next greater element once.

Therefore:

if (i < n) {
    stack.push(index);
}

During the second pass, we only use the elements
already in the stack to resolve their answers.

------------------------------------------------------------
DRY RUN

nums = [1, 2, 1]

n = 3

Initial:

ans = [-1, -1, -1]

stack = []

------------------------------------------------------------
i = 0

index = 0

nums[0] = 1

Stack empty.

Push index 0.

stack:

[0]

------------------------------------------------------------
i = 1

index = 1

nums[1] = 2

2 > 1

Therefore:

ans[0] = 2

Pop index 0.

Stack:

[]

Push index 1.

stack:

[1]

------------------------------------------------------------
i = 2

index = 2

nums[2] = 1

1 < 2

Push index 2.

stack:

[1, 2]

------------------------------------------------------------
SECOND PASS

i = 3

index = 0

nums[0] = 1

Compare with stack top:

nums[2] = 1

1 is NOT greater than 1.

Nothing happens.

------------------------------------------------------------
i = 4

index = 1

nums[1] = 2

Compare:

nums[2] = 1

2 > 1

Therefore:

ans[2] = 2

Pop index 2.

------------------------------------------------------------
i = 5

index = 2

nums[2] = 1

Compare with:

nums[1] = 2

1 < 2

Nothing happens.

------------------------------------------------------------
FINAL ANSWER

[2, -1, 2]

------------------------------------------------------------
WHY DOES INDEX 1 HAVE -1?

nums[1] = 2

There is no element greater than 2
anywhere in the circular array.

Therefore:

ans[1] = -1

------------------------------------------------------------
ALGORITHM

1. Create an answer array filled with -1.

2. Create a monotonic decreasing stack.

3. Traverse from:

   0 to 2 * n - 1

4. Convert the virtual index into a real index:

   i % n

5. While the current element is greater than
   the element represented by the stack top:

   update the answer and pop the stack.

6. During the first pass only,
   push the current index.

7. Return the answer.

------------------------------------------------------------
TIME COMPLEXITY

O(n)

Although we loop 2n times,

each index is pushed once and popped at most once.

Therefore:

O(2n) = O(n)

------------------------------------------------------------
SPACE COMPLEXITY

O(n)

The stack can contain up to n indices.

------------------------------------------------------------
KEY CONCEPTS

✓ Monotonic Stack
✓ Circular Array
✓ Next Greater Element
✓ Index Stack
✓ Modulo Operator
✓ Two Pass Technique

------------------------------------------------------------
PATTERN

Next Greater Element

        ↓

Monotonic Stack

        ↓

Circular Array

        ↓

Traverse 2 × n

        ↓

index = i % n

============================================================
*/
import java.util.*;

public class Main {

    public static int[] nextGreaterElements(int[] nums) {

        int n = nums.length;

        int[] ans = new int[n];

        Arrays.fill(ans, -1);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 2 * n; i++) {

            int index = i % n;

            while (!stack.isEmpty() &&
                   nums[stack.peek()] < nums[index]) {

                ans[stack.pop()] = nums[index];
            }

            if (i < n) {
                stack.push(index);
            }
        }

        return ans;
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

        int[] result = nextGreaterElements(nums);

        System.out.println("Answer:");

        System.out.println(Arrays.toString(result));

        sc.close();
    }
}

