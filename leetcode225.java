/*
============================================================
LEETCODE 225 - IMPLEMENT STACK USING QUEUES

Problem

Implement a stack using only queue operations.

The stack must support:

1. push(x)
2. pop()
3. top()
4. empty()

A stack follows:

LIFO
Last In First Out

A queue follows:

FIFO
First In First Out

The challenge is to make a queue behave like
a stack.

------------------------------------------------------------
Example

Operations:

push(1)
push(2)
push(3)

A normal queue would look like:

1 -> 2 -> 3

But a stack should behave like:

3 -> 2 -> 1

Therefore,

pop() should return 3.

------------------------------------------------------------
INTUITION

We use only ONE queue.

Whenever we push a new element,

we insert it normally at the back.

Then we rotate all the previous elements
to the back.

This puts the newly inserted element
at the front.

Example:

Before push(3)

Queue:

1 -> 2

After offer(3):

1 -> 2 -> 3

Now rotate the previous elements:

2 -> 3 -> 1

3 -> 1 -> 2

Final:

3 -> 1 -> 2

Now the front of the queue contains
the element that should be popped first.

Therefore,

the queue behaves exactly like a stack.

------------------------------------------------------------
APPROACH

Use one Queue.

For push(x):

1. Add x to the queue.

2. Store the current queue size.

3. Rotate all elements except x
   from front to back.

For pop():

Simply remove the front element.

For top():

Simply look at the front element.

For empty():

Check whether the queue is empty.

------------------------------------------------------------
PUSH OPERATION

Suppose:

queue = [1, 2]

We call:

push(3)

First:

queue.offer(3)

Queue becomes:

[1, 2, 3]

Now we need 3 at the front.

Rotate 1:

[2, 3, 1]

Rotate 2:

[3, 1, 2]

Now:

3 is at the front.

Therefore:

pop() -> 3

------------------------------------------------------------
WHY DO WE USE

while (size > 1)

Suppose:

[1, 2, 3]

We only need to rotate the old elements:

1 and 2.

We must NOT rotate 3.

After rotating 1:

[2, 3, 1]

After rotating 2:

[3, 1, 2]

Now 3 is at the front.

If we rotated 3 as well:

[1, 2, 3]

We would return to the original arrangement.

Therefore:

while (size > 1)

is important.

------------------------------------------------------------
WHY ARRAYDEQUE?

We can implement Queue using:

Queue<Integer> queue = new ArrayDeque<>();

ArrayDeque is preferred over LinkedList
for this use case because it has lower
memory overhead and efficient queue operations.

ArrayDeque does not allow null values,
but that is not a problem here because
we only store integers.

------------------------------------------------------------
ALGORITHM

push(x):

1. offer(x)
2. size = queue.size()
3. While size > 1:
      move front element to back
4. Finish

pop():

return queue.poll()

top():

return queue.peek()

empty():

return queue.isEmpty()

------------------------------------------------------------
DRY RUN

Initially:

[]

------------------------------------------------------------
push(1)

offer(1)

[1]

No rotation needed.

------------------------------------------------------------
push(2)

Before:

[1]

offer(2):

[1, 2]

Rotate 1:

[2, 1]

------------------------------------------------------------
push(3)

Before:

[2, 1]

offer(3):

[2, 1, 3]

Rotate 2:

[1, 3, 2]

Rotate 1:

[3, 2, 1]

------------------------------------------------------------
Now:

pop()

returns:

3

Queue:

[2, 1]

------------------------------------------------------------
pop()

returns:

2

Queue:

[1]

------------------------------------------------------------
top()

returns:

1

------------------------------------------------------------
TIME COMPLEXITY

push():

O(n)

Because we may rotate n-1 elements.

pop():

O(1)

top():

O(1)

empty():

O(1)

------------------------------------------------------------
SPACE COMPLEXITY

O(n)

The queue stores all n elements.

------------------------------------------------------------
KEY CONCEPTS

✓ Stack
✓ Queue
✓ FIFO vs LIFO
✓ ArrayDeque
✓ Queue Rotation
✓ Data Structure Design

============================================================
*/

import java.util.*;

class MyStack {

    Queue<Integer> queue;

    public MyStack() {
        queue = new ArrayDeque<>();
    }

    public void push(int x) {

        queue.offer(x);

        int size = queue.size();

        while (size > 1) {
            queue.offer(queue.poll());
            size--;
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

public class Main {

    public static void main(String[] args) {

        MyStack stack = new MyStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println(stack.empty());
    }
}
