/*
============================================================
LEETCODE 141 - Linked List Cycle

Problem

Given the head of a linked list,

determine whether the linked list has
a cycle.

Return

true

if there is a cycle,

otherwise return

false.

A cycle exists if some node can be
reached again by continuously following
the next pointers.

------------------------------------------------------------
Example 1

Input

3 -> 2 -> 0 -> -4
     ^         |
     |_________|

Output

true

------------------------------------------------------------
Example 2

Input

1 -> 2

Output

false

------------------------------------------------------------
Intuition

A brute-force solution would store every
visited node inside a HashSet.

If we visit a node again,

a cycle exists.

Although this works,

it requires O(n) extra space.

Instead,

use Floyd's Cycle Detection Algorithm
(Tortoise and Hare).

Use two pointers.

1. Slow Pointer

Moves one step at a time.

2. Fast Pointer

Moves two steps at a time.

If a cycle exists,

they will eventually meet.

If the fast pointer reaches null,

there is no cycle.

------------------------------------------------------------
Approach

1. Initialize

slow = head

fast = head

2. Traverse the linked list.

3. Move

slow by one step.

Move

fast by two steps.

4. If

slow == fast

return true.

5. If the loop ends,

return false.

------------------------------------------------------------
Algorithm

1. If

head == null

return false.

2. Create

slow = head

fast = head

3. While

fast != null

and

fast.next != null

Move

slow = slow.next

fast = fast.next.next

4. If

slow == fast

return true.

5. Return false.

------------------------------------------------------------
Dry Run

Input

1 -> 2 -> 3 -> 4 -> 5
     ^              |
     |______________|

-----------------------------------

Initially

Slow = 1

Fast = 1

-----------------------------------

Iteration 1

Slow = 2

Fast = 3

-----------------------------------

Iteration 2

Slow = 3

Fast = 5

-----------------------------------

Iteration 3

Slow = 4

Fast = 3

-----------------------------------

Iteration 4

Slow = 5

Fast = 5

Both pointers meet.

Answer

true

------------------------------------------------------------
Why It Works

If there is no cycle,

the fast pointer reaches null.

If there is a cycle,

the fast pointer moves faster than
the slow pointer.

Eventually,

the fast pointer catches the slow pointer,
so both pointers meet.

Hence,

meeting implies a cycle exists.

------------------------------------------------------------
Time Complexity

O(n)

Each node is visited at most a constant
number of times.

------------------------------------------------------------
Space Complexity

O(1)

Only two pointers are used.

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

    public static boolean hasCycle(ListNode head) {

        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");

        int n = sc.nextInt();

        if (n == 0) {
            System.out.println("Cycle Exists: false");
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

        System.out.println("Cycle Exists: " + hasCycle(nodes[0]));

        sc.close();
    }
}


