/*
============================================================
SORT A STACK IN ASCENDING ORDER

Problem

Given a stack of integers, sort the stack in ascending
order.

The smallest element must be at the BOTTOM and the
largest element must be at the TOP.

Example:

Input:

Top
 ↓
3
1
4
2

Output:

Top
 ↓
4
3
2
1

------------------------------------------------------------
APPROACH

We use one additional stack:

temp

The original stack is:

st

We repeatedly remove elements from st and insert them
into temp in sorted order.

------------------------------------------------------------
WHY DO WE NEED temp?

Suppose:

st = [1, 3, 2, 4]

We pop one element at a time.

For every element, we find its correct position
inside temp.

If the elements on top of temp are smaller than the
current value, we temporarily move them back to st.

------------------------------------------------------------
IMPORTANT CONDITION

while (!temp.isEmpty() && temp.peek() < value)

If the top of temp is smaller than value, move it
back to the original stack.

This allows value to be placed above all smaller
elements.

------------------------------------------------------------
EXAMPLE

Suppose:

value = 4

and:

temp:

Top
 ↓
2
1

Since:

2 < 4

move 2 back to st.

Then:

1 < 4

move 1 back to st.

Now temp is empty.

Push 4:

temp:

Top
 ↓
4

------------------------------------------------------------
ANOTHER EXAMPLE

Suppose:

temp:

Top
 ↓
4
2
1

and:

value = 3

We check:

4 < 3 ?

NO

Therefore we can directly push 3.

temp becomes:

Top
 ↓
3
4
2
1

But remember that the stack is represented from
top to bottom.

After all elements are processed, we transfer
everything back to st.

------------------------------------------------------------
FINAL TRANSFER

At the end, temp contains the elements in the
opposite order.

We move all elements from temp back to st.

The final stack becomes:

Top
 ↓
4
3
2
1

Therefore:

smallest -> bottom
largest  -> top

------------------------------------------------------------
ALGORITHM

1. Create an auxiliary stack temp.

2. While st is not empty:

   a. Pop the top element from st.

   b. Store it in value.

   c. While temp is not empty AND
      temp.peek() < value:

      move temp.pop() back to st.

   d. Push value into temp.

3. Move every element from temp back to st.

4. Return/use st.

------------------------------------------------------------
WHY temp.peek() < value?

We want the largest element at the top of the
FINAL stack.

During construction, temp is maintained in the
opposite orientation.

Therefore, smaller elements are moved out of the
way when inserting a larger value.

------------------------------------------------------------
DRY RUN

Input:

st = [1, 3, 4, 2]

Assume the right side is the TOP.

------------------------------------------------------------
STEP 1

Pop 2.

temp:

[2]

------------------------------------------------------------
STEP 2

Pop 4.

temp.peek() = 2

2 < 4

Move 2 back to st.

Push 4.

temp:

[4]

------------------------------------------------------------
STEP 3

Pop 3.

temp.peek() = 4

4 < 3 -> false

Push 3.

temp:

[4, 3]

------------------------------------------------------------
STEP 4

Pop 1.

temp.peek() = 3

3 < 1 -> false

Push 1.

temp:

[4, 3, 1]

After all elements are processed, transfer
everything back to st.

Final:

Top
 ↓
4
3
2
1

------------------------------------------------------------
TIME COMPLEXITY

Worst case:

O(n²)

An element may be moved between the two stacks
multiple times.

------------------------------------------------------------
SPACE COMPLEXITY

O(n)

We use one auxiliary stack.

------------------------------------------------------------
KEY CONCEPTS

✓ Stack
✓ Auxiliary Stack
✓ Sorting
✓ LIFO
✓ Temporary Storage

------------------------------------------------------------
PATTERN

Original Stack
      ↓
Pop element
      ↓
Find correct position
      ↓
Auxiliary Stack
      ↓
Transfer back
      ↓
Sorted Stack

============================================================
*/
import java.util.*;

class Solution {

    public void sortStack(Stack<Integer> st) {

        Stack<Integer> temp = new Stack<>();

        while (!st.isEmpty()) {

            int value = st.pop();

            while (!temp.isEmpty() && temp.peek() < value) {
                st.push(temp.pop());
            }

            temp.push(value);
        }

        while (!temp.isEmpty()) {
            st.push(temp.pop());
        }
    }
}

