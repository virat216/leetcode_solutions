/*
============================================================
LEETCODE 155 - MIN STACK

Problem

Design a stack that supports:

1. push(val)
2. pop()
3. top()
4. getMin()

The important requirement is:

getMin() must return the minimum element
in O(1) time.

------------------------------------------------------------
NORMAL STACK

A normal stack can easily perform:

push() -> O(1)
pop()  -> O(1)
top()  -> O(1)

But getMin() is difficult.

Suppose:

stack = [5, 3, 7, 2]

To find the minimum, we would have to scan
the entire stack:

5
3
7
2

Minimum = 2

That takes:

O(n)

But the problem requires:

O(1)

------------------------------------------------------------
INTUITION

We use TWO stacks.

1. stack
2. minStack

The normal stack stores all values.

The minStack stores the minimum value
at every level of the stack.

------------------------------------------------------------
EXAMPLE

push(5)

stack:

[5]

minStack:

[5]

Minimum = 5

------------------------------------------------------------
push(3)

stack:

[5, 3]

The current minimum is:

min(3, 5) = 3

minStack:

[5, 3]

------------------------------------------------------------
push(7)

stack:

[5, 3, 7]

Current minimum:

min(7, 3) = 3

minStack:

[5, 3, 3]

------------------------------------------------------------
push(2)

stack:

[5, 3, 7, 2]

Current minimum:

min(2, 3) = 2

minStack:

[5, 3, 3, 2]

------------------------------------------------------------
NOW getMin()

Instead of searching the entire stack,

we simply do:

minStack.peek()

which returns:

2

Therefore:

getMin() = O(1)

------------------------------------------------------------
WHAT DOES minStack STORE?

At every position,

minStack stores the minimum value
from the bottom up to that position.

Example:

stack:

5
3
7
2

minStack:

5
3
3
2

So:

minStack[0] = minimum of [5]

minStack[1] = minimum of [5,3]

minStack[2] = minimum of [5,3,7]

minStack[3] = minimum of [5,3,7,2]

------------------------------------------------------------
PUSH

When we push val:

1. Push val into stack.

2. Find the new minimum:

min(val, minStack.peek())

3. Push that minimum into minStack.

For the first element,

minStack is empty.

So simply push val.

------------------------------------------------------------
CODE

if (minStack.isEmpty()) {
    minStack.push(val);
} else {
    minStack.push(
        Math.min(val, minStack.peek())
    );
}

------------------------------------------------------------
POP

When we pop from the main stack,

we must also pop from minStack.

Why?

Because both stacks must represent
the same number of elements.

Example:

stack:

[5, 3, 7, 2]

minStack:

[5, 3, 3, 2]

After pop():

stack:

[5, 3, 7]

minStack:

[5, 3, 3]

Current minimum:

3

------------------------------------------------------------
TOP

top() simply returns:

stack.peek()

------------------------------------------------------------
GET MIN

getMin() simply returns:

minStack.peek()

No traversal is required.

------------------------------------------------------------
DRY RUN

Operations:

push(5)
push(3)
push(7)
push(2)
getMin()
pop()
getMin()

------------------------------------------------------------
push(5)

stack:

[5]

minStack:

[5]

------------------------------------------------------------
push(3)

stack:

[5,3]

minStack:

[5,3]

------------------------------------------------------------
push(7)

stack:

[5,3,7]

minStack:

[5,3,3]

------------------------------------------------------------
push(2)

stack:

[5,3,7,2]

minStack:

[5,3,3,2]

------------------------------------------------------------
getMin()

minStack.peek()

= 2

------------------------------------------------------------
pop()

Remove 2 from both stacks.

stack:

[5,3,7]

minStack:

[5,3,3]

------------------------------------------------------------
getMin()

= 3

------------------------------------------------------------
WHY DO WE NEED TWO STACKS?

If we only use one stack,

getMin() would require scanning
all elements.

That would be:

O(n)

The second stack stores the minimum
information so that getMin() becomes:

O(1)

------------------------------------------------------------
ALGORITHM

push(val):

1. Push val into stack.
2. If minStack is empty:
      push val.
3. Otherwise:
      push min(val, minStack.peek()).

pop():

1. stack.pop()
2. minStack.pop()

top():

return stack.peek()

getMin():

return minStack.peek()

------------------------------------------------------------
TIME COMPLEXITY

push():

O(1)

pop():

O(1)

top():

O(1)

getMin():

O(1)

------------------------------------------------------------
SPACE COMPLEXITY

O(n)

Both stacks can contain n elements.

------------------------------------------------------------
KEY CONCEPTS

✓ Stack
✓ Auxiliary Stack
✓ Minimum Tracking
✓ LIFO
✓ O(1) Operations

------------------------------------------------------------
PATTERN

Need:

Stack operations
+
Track minimum in O(1)

↓

Two Stacks

↓

Main Stack
+
Minimum Stack

============================================================
*/

import java.util.*;

class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {

        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {

        stack.push(val);

        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            minStack.push(Math.min(val, minStack.peek()));
        }
    }

    public void pop() {

        stack.pop();
        minStack.pop();
    }

    public int top() {

        return stack.peek();
    }

    public int getMin() {

        return minStack.peek();
    }
}

public class Main {

    public static void main(String[] args) {

        MinStack minStack = new MinStack();

        minStack.push(5);
        minStack.push(3);
        minStack.push(7);
        minStack.push(2);

        System.out.println("Minimum: " + minStack.getMin());

        minStack.pop();

        System.out.println("Top: " + minStack.top());

        System.out.println("Minimum: " + minStack.getMin());
    }
}
