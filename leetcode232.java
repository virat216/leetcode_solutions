/*
============================================================
LEETCODE 232 - IMPLEMENT QUEUE USING STACKS

Problem

Implement a queue using only stack operations.

The queue must support:

1. push(x)
2. pop()
3. peek()
4. empty()

A queue follows:

FIFO
First In First Out

A stack follows:

LIFO
Last In First Out

The challenge is to make two stacks behave
like a queue.

------------------------------------------------------------
Example

Operations:

push(1)
push(2)
push(3)

Queue should behave like:

1 -> 2 -> 3

Therefore:

pop() -> 1

even though stacks normally remove the
most recently inserted element first.

------------------------------------------------------------
INTUITION

We use two stacks:

input
output

input:

Used when adding new elements.

output:

Used when removing or viewing elements.

The important idea is that when we move
elements from input to output, their order
gets reversed.

------------------------------------------------------------
EXAMPLE

Suppose we perform:

push(1)
push(2)
push(3)

input stack:

Bottom
  1
  2
  3
Top

If we move everything to output:

input:

empty

output:

Bottom
  3
  2
  1
Top

Now output.pop() returns:

1

which is exactly what a queue should return.

------------------------------------------------------------
WHY TWO STACKS?

input stack stores elements in insertion order.

output stack reverses that order.

Therefore:

input:

1 2 3

↓

transfer

output:

3 2 1

Now the oldest element,

1,

is at the top.

------------------------------------------------------------
PUSH

When push(x) is called,

simply put x into input.

Example:

push(1)
push(2)
push(3)

input:

[1, 2, 3]

No need to move anything.

Therefore push() is O(1).

------------------------------------------------------------
POP

When pop() is called,

we need the oldest element.

If output is empty,

move every element from input
to output.

Example:

input:

[1, 2, 3]

Move elements:

3 -> output
2 -> output
1 -> output

output becomes:

[3, 2, 1]

Now:

output.pop()

returns:

1

------------------------------------------------------------
IMPORTANT OPTIMIZATION

We only transfer elements when

output.isEmpty()

is true.

Suppose:

input = [4,5]
output = [3,2,1]

If we call pop():

output is NOT empty.

So we simply remove:

1

We do NOT move anything from input.

After another pop:

2

Then:

3

Only when output becomes empty do we
transfer the remaining elements.

This is what gives us

O(1)

AMORTIZED time per operation.

------------------------------------------------------------
PEEK

peek() is almost identical to pop().

The difference is:

pop()

removes the element.

peek()

only returns the element.

Therefore:

shiftStacks();

return output.peek();

------------------------------------------------------------
EMPTY

The queue is empty only when BOTH stacks
are empty.

Therefore:

input.isEmpty() && output.isEmpty()

------------------------------------------------------------
DRY RUN

Initially:

input = []
output = []

------------------------------------------------------------
push(1)

input:

[1]

output:

[]

------------------------------------------------------------
push(2)

input:

[1, 2]

output:

[]

------------------------------------------------------------
push(3)

input:

[1, 2, 3]

output:

[]

------------------------------------------------------------
pop()

output is empty.

Transfer:

input:

[]

output:

[3, 2, 1]

pop output:

1

Now:

input:

[]

output:

[3, 2]

------------------------------------------------------------
push(4)

input:

[4]

output:

[3, 2]

Notice:

We DO NOT transfer 4 immediately.

------------------------------------------------------------
pop()

output is not empty.

So:

output.pop()

returns:

2

Now:

output:

[3]

------------------------------------------------------------
pop()

returns:

3

output becomes:

[]

------------------------------------------------------------
pop()

output is empty.

Transfer:

input:

[4]

↓

output:

[4]

Return:

4

Correct queue order:

1 -> 2 -> 3 -> 4

------------------------------------------------------------
WHY IS THE COMPLEXITY AMORTIZED O(1)?

An element can be:

1. Pushed into input once.
2. Moved from input to output once.
3. Popped from output once.

So every element participates in only
a constant number of stack operations.

Therefore,

although one individual pop() can take O(n),

the average cost over all operations is:

O(1) amortized.

------------------------------------------------------------
ALGORITHM

push(x):

input.push(x)

pop():

shiftStacks()
return output.pop()

peek():

shiftStacks()
return output.peek()

empty():

return input.isEmpty() && output.isEmpty()

shiftStacks():

if output is empty:

    while input is not empty:
        output.push(input.pop())

------------------------------------------------------------
TIME COMPLEXITY

push():

O(1)

pop():

O(1) amortized

peek():

O(1) amortized

empty():

O(1)

------------------------------------------------------------
SPACE COMPLEXITY

O(n)

Both stacks together store at most n elements.

------------------------------------------------------------
KEY CONCEPTS

✓ Stack
✓ Queue
✓ FIFO vs LIFO
✓ Two Stack Technique
✓ Amortized Complexity
✓ Data Structure Design

============================================================
*/
import java.util.*;

class MyQueue {

    Stack<Integer> input;
    Stack<Integer> output;

    public MyQueue() {

        input = new Stack<>();
        output = new Stack<>();
    }

    public void push(int x) {

        input.push(x);
    }

    public int pop() {

        shiftStacks();

        return output.pop();
    }

    public int peek() {

        shiftStacks();

        return output.peek();
    }

    public boolean empty() {

        return input.isEmpty() && output.isEmpty();
    }

    private void shiftStacks() {

        if (output.isEmpty()) {

            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {

        MyQueue queue = new MyQueue();

        queue.push(1);
        queue.push(2);
        queue.push(3);

        System.out.println("Peek: " + queue.peek());

        System.out.println("Pop: " + queue.pop());

        queue.push(4);

        System.out.println("Pop: " + queue.pop());

        System.out.println("Pop: " + queue.pop());

        System.out.println("Pop: " + queue.pop());

        System.out.println("Is Empty: " + queue.empty());
    }
}
