/*
============================================================
LEETCODE 25 - Reverse Nodes in k-Group

Problem

Given the head of a linked list and an
integer k,

reverse the nodes of the list k at a time
and return the modified list.

If the number of remaining nodes is
less than k,

leave them unchanged.

You must use only constant extra space.

------------------------------------------------------------
Example 1

Input

head = 1 -> 2 -> 3 -> 4 -> 5

k = 2

Output

2 -> 1 -> 4 -> 3 -> 5

------------------------------------------------------------
Example 2

Input

head = 1 -> 2 -> 3 -> 4 -> 5

k = 3

Output

3 -> 2 -> 1 -> 4 -> 5

------------------------------------------------------------
Intuition

Instead of reversing the entire linked list,

reverse only one group of k nodes.

After reversing one group,

connect it to the previously reversed group.

Repeat until fewer than k nodes remain.

------------------------------------------------------------
Approach

1. Create a dummy node.

2. Let

prevGroup

point to the node before the current group.

3. Find the kth node.

If fewer than k nodes remain,

stop.

4. Reverse the current group.

5. Connect the reversed group.

6. Move to the next group.

------------------------------------------------------------
Algorithm

1. Create dummy node.

2. prevGroup = dummy

3. Find kth node.

4. If kth == null

Return answer.

5. Store

groupNext = kth.next

6. Reverse nodes between

prevGroup.next

and

groupNext.

7. Connect reversed group.

8. Move prevGroup to the end of
the reversed group.

9. Repeat.

------------------------------------------------------------
Dry Run

Input

1 -> 2 -> 3 -> 4 -> 5

k = 2

-----------------------------------

Initially

dummy

↓

0 -> 1 -> 2 -> 3 -> 4 -> 5

-----------------------------------

First Group

1 -> 2

Reverse

2 -> 1

List becomes

0 -> 2 -> 1 -> 3 -> 4 -> 5

-----------------------------------

Second Group

3 -> 4

Reverse

4 -> 3

List becomes

0 -> 2 -> 1 -> 4 -> 3 -> 5

-----------------------------------

Only one node remains.

Stop.

Answer

2 -> 1 -> 4 -> 3 -> 5

------------------------------------------------------------
Why It Works

Every iteration reverses exactly one
group of k nodes.

The reversed group is connected back to
the already processed list.

Nodes remaining fewer than k are never
reversed.

------------------------------------------------------------
Time Complexity

O(n)

Every node is visited once.

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

    public static ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroup = dummy;

        while (true) {

            ListNode kth = prevGroup;

            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }

            if (kth == null) {
                break;
            }

            ListNode groupNext = kth.next;

            ListNode prev = groupNext;
            ListNode current = prevGroup.next;

            while (current != groupNext) {

                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            ListNode temp = prevGroup.next;
            prevGroup.next = kth;
            prevGroup = temp;
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

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        head = reverseKGroup(head, k);

        System.out.println("Linked List after reversing:");

        printList(head);

        sc.close();
    }
}

