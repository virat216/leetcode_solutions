/*
============================================================
LEETCODE 876 - Middle of the Linked List

Approach used = fast and slow pointer traversing

Problem

Given the head of a singly linked list,

return the middle node of the linked list.

If there are two middle nodes,

return the second middle node.

------------------------------------------------------------
Example 1

Input

1 -> 2 -> 3 -> 4 -> 5

Output

3

------------------------------------------------------------
Example 2

Input

1 -> 2 -> 3 -> 4 -> 5 -> 6

Output

4

Explanation

There are two middle nodes

3 and 4

The problem asks us to return

the second middle node.

------------------------------------------------------------
Intuition

A brute-force solution would

1. Traverse the linked list.

2. Count the total number of nodes.

3. Traverse again until the middle.

This requires two traversals.

Instead,

we can find the middle in a single traversal
using two pointers.

------------------------------------------------------------
Approach

Use two pointers

1. Slow Pointer

Moves one step at a time.

2. Fast Pointer

Moves two steps at a time.

Initially,

both start at the head.

When the fast pointer reaches the end,

the slow pointer will be exactly at the
middle node.

------------------------------------------------------------
Algorithm

1. Initialize

slow = head

fast = head

2. Traverse while

fast != null

and

fast.next != null

3. Move

slow = slow.next

fast = fast.next.next

4. Return

slow

------------------------------------------------------------
Dry Run

Input

1 -> 2 -> 3 -> 4 -> 5

Initially

Slow = 1

Fast = 1

-------------------------

Iteration 1

Slow = 2

Fast = 3

-------------------------

Iteration 2

Slow = 3

Fast = 5

-------------------------

Next

Fast.next == null

Stop

Answer

3

------------------------------------------------------------

Dry Run (Even Length)

Input

1 -> 2 -> 3 -> 4 -> 5 -> 6

Initially

Slow = 1

Fast = 1

-------------------------

Iteration 1

Slow = 2

Fast = 3

-------------------------

Iteration 2

Slow = 3

Fast = 5

-------------------------

Iteration 3

Slow = 4

Fast = null

Stop

Answer

4

------------------------------------------------------------
Why It Works

The fast pointer moves twice as fast
as the slow pointer.

When the fast pointer reaches the end,

the slow pointer has covered exactly
half the distance.

Therefore,

the slow pointer always points to the
middle node.

For an even number of nodes,

the slow pointer naturally reaches the
second middle node,

which matches the problem statement.

------------------------------------------------------------
Time Complexity

O(n)

The linked list is traversed only once.

------------------------------------------------------------
Space Complexity

O(1)

Only two pointers are used.

------------------------------------------------------------
Functions Used

next

Moves to the next node in the linked list.

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

    public static ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void printList(ListNode head) {

        while (head != null) {

            System.out.print(head.val);

            if (head.next != null) {
                System.out.print(" -> ");
            }

            head = head.next;
        }

        System.out.println();
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

        System.out.println("Enter node values:");

        ListNode head = new ListNode(sc.nextInt());
        ListNode current = head;

        for (int i = 1; i < n; i++) {

            current.next = new ListNode(sc.nextInt());
            current = current.next;
        }

        System.out.print("\nLinked List: ");
        printList(head);

        ListNode middle = middleNode(head);

        System.out.println("Middle Node = " + middle.val);

        sc.close();
    }
}
