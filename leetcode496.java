/*
============================================================
LEETCODE 496 - NEXT GREATER ELEMENT I

Problem

You are given two arrays:

nums1
nums2

nums1 is a subset of nums2.

For every element in nums1,

find the first element to its right in nums2
that is greater than it.

If no greater element exists,

return -1.

------------------------------------------------------------
Example

nums1 = [4, 1, 2]

nums2 = [1, 3, 4, 2]

For 4:

Elements to its right:

2

No element is greater than 4.

Answer = -1

For 1:

Elements to its right:

3, 4, 2

The first greater element is 3.

Answer = 3

For 2:

No element exists to its right.

Answer = -1

Therefore:

[-1, 3, -1]

------------------------------------------------------------
BRUTE FORCE APPROACH

For every element in nums1,

1. Find that element in nums2.
2. Start searching to its right.
3. Find the first greater element.

This can take:

O(n * m)

which is inefficient.

------------------------------------------------------------
OPTIMAL APPROACH

Use:

1. Monotonic Stack
2. HashMap

The stack helps us find the next greater
element for every number in nums2.

The HashMap stores:

number -> next greater element

------------------------------------------------------------
MONOTONIC STACK

We maintain a decreasing stack.

Example:

nums2 = [1, 3, 4, 2]

Start:

stack = []

------------------------------------------------------------
Process 1

Stack is empty.

Push 1.

stack:

[1]

------------------------------------------------------------
Process 3

3 > 1

Therefore,

3 is the next greater element of 1.

Store:

1 -> 3

Pop 1.

Push 3.

stack:

[3]

------------------------------------------------------------
Process 4

4 > 3

Therefore,

4 is the next greater element of 3.

Store:

3 -> 4

Pop 3.

Push 4.

stack:

[4]

------------------------------------------------------------
Process 2

2 < 4

Push 2.

stack:

[4, 2]

------------------------------------------------------------
Remaining elements

4 and 2

have no greater element to their right.

Therefore:

4 -> -1
2 -> -1

------------------------------------------------------------
HASHMAP

After processing nums2:

map:

1 -> 3
3 -> 4

4 -> -1
2 -> -1

Now we simply traverse nums1.

For every nums1[i],

look up its answer in the HashMap.

------------------------------------------------------------
DRY RUN

nums1 = [4, 1, 2]

nums2 = [1, 3, 4, 2]

------------------------------------------------------------
Process 1

stack:

[1]

------------------------------------------------------------
Process 3

3 > 1

map:

1 -> 3

stack:

[3]

------------------------------------------------------------
Process 4

4 > 3

map:

3 -> 4

stack:

[4]

------------------------------------------------------------
Process 2

2 < 4

stack:

[4, 2]

------------------------------------------------------------
Now process nums1

4:

map does not contain 4

answer = -1

1:

map contains 1 -> 3

answer = 3

2:

map does not contain 2

answer = -1

Final:

[-1, 3, -1]

------------------------------------------------------------
WHY DOES THE STACK WORK?

Suppose we have:

[2, 5]

When we encounter 5,

5 is greater than 2.

Therefore 5 is immediately the next
greater element of 2.

We don't need to search further.

This is why we can remove 2 from the stack.

Every element is pushed once and popped
at most once.

------------------------------------------------------------
WHY IS IT O(n)?

Each element in nums2:

1. Is pushed into the stack exactly once.
2. Can be popped at most once.

Therefore,

total stack operations = O(n).

HashMap lookup for nums1 is O(1) average.

Overall:

O(n + m)

------------------------------------------------------------
ALGORITHM

1. Create HashMap.

2. Create monotonic decreasing stack.

3. Traverse nums2.

4. While stack is not empty AND

   stack.peek() < current number

   store the current number as the
   next greater element.

5. Push current number.

6. Traverse nums1.

7. Get each answer from the HashMap.

8. If it doesn't exist, return -1.

------------------------------------------------------------
TIME COMPLEXITY

O(n + m)

where:

n = nums2.length
m = nums1.length

------------------------------------------------------------
SPACE COMPLEXITY

O(n)

HashMap + Stack.

------------------------------------------------------------
KEY CONCEPT

This is a classic:

MONOTONIC STACK

problem.

Whenever you see:

"Next greater element"

"Next smaller element"

"Previous greater element"

"Previous smaller element"

think about using a monotonic stack.

============================================================
*/
import java.util.*;

public class Main {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int num : nums2) {

            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }

            stack.push(num);
        }

        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }

        return ans;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of nums1: ");
        int n = sc.nextInt();

        int[] nums1 = new int[n];

        System.out.println("Enter nums1:");

        for (int i = 0; i < n; i++) {
            nums1[i] = sc.nextInt();
        }

        System.out.print("Enter size of nums2: ");
        int m = sc.nextInt();

        int[] nums2 = new int[m];

        System.out.println("Enter nums2:");

        for (int i = 0; i < m; i++) {
            nums2[i] = sc.nextInt();
        }

        int[] result = nextGreaterElement(nums1, nums2);

        System.out.println("Answer:");

        System.out.println(Arrays.toString(result));

        sc.close();
    }
}


