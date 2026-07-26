/*
============================================================
LEETCODE 142 - Linked List Cycle II

Problem

Given the head of a linked list,

return the node where the cycle begins.

If there is no cycle,

return null.

You must solve it using

O(1)

extra space.

------------------------------------------------------------
Example

Input

3 -> 2 -> 0 -> -4
     ^         |
     |_________|

Output

Node with value 2

------------------------------------------------------------
Intuition

A brute-force solution would store every
visited node inside a HashSet.

Whenever a node is visited again,

it is the starting point of the cycle.

Although this works,

it requires

O(n)

extra space.

Instead,

use Floyd's Cycle Detection Algorithm
(Tortoise and Hare).

It uses two pointers.

1. Slow Pointer

Moves one node at a time.

2. Fast Pointer

Moves two nodes at a time.

If a cycle exists,

both pointers must eventually meet.

------------------------------------------------------------
Approach

Phase 1

Detect whether a cycle exists.

Move

slow = slow.next

fast = fast.next.next

If they meet,

a cycle exists.

Otherwise,

return null.

------------------------------------------------------------

Phase 2

Find the starting node of the cycle.

Create another pointer

entry = head

Move

entry

and

slow

one step at a time.

The node where they meet
is the beginning of the cycle.

------------------------------------------------------------
Algorithm

1. Initialize

slow = head

fast = head

2. Traverse

while fast != null
and fast.next != null

3. Move

slow by one step

fast by two steps

4. If

slow == fast

Cycle exists.

5. Create

entry = head

6. Move

entry

and

slow

one step at a time.

7. Return

entry

8. If loop finishes,

return null.

------------------------------------------------------------
Dry Run

Linked List

1 -> 2 -> 3 -> 4 -> 5
     ^              |
     |______________|

------------------------------------

Initially

Slow = 1

Fast = 1

------------------------------------

Iteration 1

Slow = 2

Fast = 3

------------------------------------

Iteration 2

Slow = 3

Fast = 5

------------------------------------

Iteration 3

Slow = 4

Fast = 3

------------------------------------

Iteration 4

Slow = 5

Fast = 5

Pointers Meet

------------------------------------

Phase 2

Entry = Head = 1

Slow = 5

Move Both

Entry = 2

Slow = 2

Both Meet

Answer

Cycle starts at node 2

------------------------------------------------------------
Why It Works

Suppose

L

=

distance from head to cycle start.

C

=

length of cycle.

When slow and fast meet,

the fast pointer has travelled exactly
twice the distance of the slow pointer.

Using this relationship,

it can be mathematically proved that

if one pointer starts from the head
and another starts from the meeting point,

moving both one step at a time,

they will meet exactly at the
starting node of the cycle.

------------------------------------------------------------
Time Complexity

O(n)

Each node is visited at most a constant
number of times.

------------------------------------------------------------
Space Complexity

O(1)

Only three pointers are used.

------------------------------------------------------------
Functions Used

next

Moves to the next node.

============================================================
*/
import java.util.*;

class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class Main {

    public static ListNode detectCycle(ListNode head) {

        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {

                ListNode entry = head;

                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }

                return entry;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");

        int n = sc.nextInt();

        if (n == 0) {
            System.out.println("Linked List is empty.");
            sc.close();
            return;
        }

        ListNode[] nodes = new ListNode[n];

        System.out.println("Enter node values:");

        for (int i = 0; i < n; i++) {
            nodes[i] = new ListNode(sc.nextInt());
        }

        for (int i = 0; i < n - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        System.out.print("Enter index where tail connects (-1 for no cycle): ");

        int pos = sc.nextInt();

        if (pos != -1) {
            nodes[n - 1].next = nodes[pos];
        }

        ListNode cycleStart = detectCycle(nodes[0]);

        if (cycleStart == null) {
            System.out.println("No Cycle Found");
        } else {
            System.out.println("Cycle starts at node: " + cycleStart.val);
        }

        sc.close();
    }
}


