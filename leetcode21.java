/*
============================================================
LEETCODE 21 - Merge Two Sorted Lists

Problem

You are given the heads of two sorted
linked lists.

Merge the two lists into one sorted
linked list and return its head.

The merged list should be made by
splicing together the nodes of the
first two lists.

------------------------------------------------------------
Example

Input

List1

1 -> 2 -> 4

List2

1 -> 3 -> 4

Output

1 -> 1 -> 2 -> 3 -> 4 -> 4

------------------------------------------------------------
Intuition

Both linked lists are already sorted.

Instead of copying values into a new list,

compare the current nodes of both lists.

Attach the smaller node to the merged list.

Move the corresponding pointer forward.

Repeat until one list becomes empty.

Finally,

attach the remaining nodes of the other list.

------------------------------------------------------------
Approach

1. Create a dummy node.

2. Maintain a pointer

current

which always points to the last node
of the merged list.

3. Compare

list1.val

and

list2.val

4. Attach the smaller node.

5. Move the corresponding pointer.

6. Move current forward.

7. After the loop,

attach the remaining list.

8. Return

dummy.next

------------------------------------------------------------
Algorithm

1. Create

dummy node

2. Set

current = dummy

3. While both lists are not empty

Compare current nodes.

Attach the smaller node.

Move that list forward.

Move current forward.

4. Attach remaining nodes.

5. Return

dummy.next

------------------------------------------------------------
Dry Run

List1

1 -> 2 -> 4

List2

1 -> 3 -> 4

-----------------------------------

dummy

↓

0

-----------------------------------

Compare

1 and 1

Choose list1

Merged

1

-----------------------------------

Compare

2 and 1

Choose list2

Merged

1 -> 1

-----------------------------------

Compare

2 and 3

Choose list1

Merged

1 -> 1 -> 2

-----------------------------------

Compare

4 and 3

Choose list2

Merged

1 -> 1 -> 2 -> 3

-----------------------------------

Compare

4 and 4

Choose list1

Merged

1 -> 1 -> 2 -> 3 -> 4

-----------------------------------

List1 becomes null

Attach remaining

4

Answer

1 -> 1 -> 2 -> 3 -> 4 -> 4

------------------------------------------------------------
Why It Works

Since both lists are already sorted,

the smallest available node is always
at the head of one of the two lists.

By repeatedly choosing the smaller node,

the merged list remains sorted.

The dummy node simplifies handling
the head of the merged list.

------------------------------------------------------------
Time Complexity

O(n + m)

n = length of list1

m = length of list2

Each node is visited exactly once.

------------------------------------------------------------
Space Complexity

O(1)

No extra linked list is created.

Only pointers are used.

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

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {

                current.next = list1;
                list1 = list1.next;

            } else {

                current.next = list2;
                list2 = list2.next;
            }

            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return dummy.next;
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

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes in List 1: ");
        int n1 = sc.nextInt();

        System.out.println("Enter elements of List 1 (sorted):");
        ListNode list1 = createList(sc, n1);

        System.out.print("Enter number of nodes in List 2: ");
        int n2 = sc.nextInt();

        System.out.println("Enter elements of List 2 (sorted):");
        ListNode list2 = createList(sc, n2);

        ListNode merged = mergeTwoLists(list1, list2);

        System.out.println("Merged Sorted List:");
        printList(merged);

        sc.close();
    }
}
