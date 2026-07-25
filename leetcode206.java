/*
============================================================
LEETCODE 206 - Reverse Linked List

Problem

Given the head of a singly linked list,

reverse the linked list and return
the new head.

------------------------------------------------------------
Example

Input

1 -> 2 -> 3 -> 4 -> 5

Output

5 -> 4 -> 3 -> 2 -> 1

------------------------------------------------------------
Intuition

Every node points to its next node.

To reverse the linked list,

each node should point to its previous node.

While changing the pointer,

we must not lose the remaining part of the list.

Therefore,

we maintain three pointers.

1. prev

Previous node.

2. current

Current node.

3. next

Stores the next node before
changing the link.

------------------------------------------------------------
Approach

1. Initialize

prev = null

current = head

2. Traverse the linked list.

3. Save the next node.

4. Reverse the current node's link.

5. Move prev one step ahead.

6. Move current one step ahead.

7. Continue until current becomes null.

8. Return prev because it becomes
the new head.

------------------------------------------------------------
Algorithm

1. Create

prev = null

current = head

2. While current is not null

Store

next = current.next

Reverse link

current.next = prev

Move pointers

prev = current

current = next

3. Return prev

------------------------------------------------------------
Dry Run

Input

1 -> 2 -> 3 -> 4 -> 5

Initially

prev = null

current = 1

----------------------------------

Step 1

next = 2

1 -> null

prev = 1

current = 2

----------------------------------

Step 2

next = 3

2 -> 1

prev = 2

current = 3

----------------------------------

Step 3

next = 4

3 -> 2 -> 1

prev = 3

current = 4

----------------------------------

Step 4

next = 5

4 -> 3 -> 2 -> 1

prev = 4

current = 5

----------------------------------

Step 5

next = null

5 -> 4 -> 3 -> 2 -> 1

prev = 5

current = null

Loop Ends

Answer

5 -> 4 -> 3 -> 2 -> 1

------------------------------------------------------------
Why It Works

The pointer

next

stores the remaining linked list.

So even after reversing

current.next,

the remaining nodes are not lost.

After every iteration,

one more node becomes part of
the reversed list.

When current becomes null,

prev points to the completely
reversed linked list.

------------------------------------------------------------
Time Complexity

O(n)

Every node is visited exactly once.

------------------------------------------------------------
Space Complexity

O(1)

Only three pointers are used.

------------------------------------------------------------
Functions Used

next

Accesses the next node.

============================================================
*/
import java.util.*;

class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class Main {

    public static ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode current = head;

        while (current != null) {

            ListNode next = current.next;

            current.next = prev;

            prev = current;

            current = next;
        }

        return prev;
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

        System.out.print("\nOriginal Linked List:\n");

        printList(head);

        head = reverseList(head);

        System.out.print("\nReversed Linked List:\n");

        printList(head);

        sc.close();
    }
}

