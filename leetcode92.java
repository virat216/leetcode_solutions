/*
============================================================
LEETCODE 92 - Reverse Linked List II

Problem

Given the head of a singly linked list
and two integers

left

and

right,

reverse the nodes from position

left

to

right

and return the modified linked list.

The reversal must be done in-place.

------------------------------------------------------------
Example

Input

head = 1 -> 2 -> 3 -> 4 -> 5

left = 2

right = 4

Output

1 -> 4 -> 3 -> 2 -> 5

------------------------------------------------------------
Intuition

We only need to reverse a portion of
the linked list.

Instead of reversing the entire list,

find the node just before the
reversal starts.

Then repeatedly remove the next node
from the unreversed part and insert it
at the beginning of the reversed part.

This is called

Head Insertion Technique.

------------------------------------------------------------
Approach

1. Create a dummy node.

2. Move

prev

to the node just before

left.

3. Let

current = prev.next

4. Repeat

(right-left)

times.

Remove

current.next

and insert it immediately after

prev.

5. Return

dummy.next

------------------------------------------------------------
Algorithm

1. If

head == null

or

left == right

Return head.

2. Create dummy node.

3. Move prev to

left-1

position.

4. current = prev.next

5. Repeat

right-left

times

next = current.next

current.next = next.next

next.next = prev.next

prev.next = next

6. Return

dummy.next

------------------------------------------------------------
Dry Run

Input

1 -> 2 -> 3 -> 4 -> 5

left = 2

right = 4

-----------------------------------

Initially

dummy

↓

0 -> 1 -> 2 -> 3 -> 4 -> 5

prev = 1

current = 2

-----------------------------------

Iteration 1

Take node

3

Move before

2

List

1 -> 3 -> 2 -> 4 -> 5

-----------------------------------

Iteration 2

Take node

4

Move before

3

List

1 -> 4 -> 3 -> 2 -> 5

Finished.

------------------------------------------------------------
Why It Works

At every iteration,

the next node is removed from the
remaining unreversed part

and inserted at the beginning of
the reversed section.

After

(right-left)

iterations,

the desired sublist becomes reversed.

------------------------------------------------------------
Time Complexity

O(n)

One traversal of the linked list.

------------------------------------------------------------
Space Complexity

O(1)

Only a few pointers are used.

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

    public static ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        ListNode current = prev.next;

        for (int i = 0; i < right - left; i++) {

            ListNode next = current.next;

            current.next = next.next;

            next.next = prev.next;

            prev.next = next;
        }

        return dummy.next;
    }

    public static ListNode createList(Scanner sc, int n) {

        if (n == 0) {
            return null;
        }

        ListNode head = new ListNode(sc.nextInt());
        ListNode current = head;

        for (int i = 1; i < n; i++) {

            current.next = new ListNode(sc.nextInt());
            current = current.next;
        }

        return head;
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

        System.out.println("Enter node values:");

        ListNode head = createList(sc, n);

        System.out.print("Enter left position: ");
        int left = sc.nextInt();

        System.out.print("Enter right position: ");
        int right = sc.nextInt();

        head = reverseBetween(head, left, right);

        System.out.println("Linked List after reversal:");

        printList(head);

        sc.close();
    }
}
