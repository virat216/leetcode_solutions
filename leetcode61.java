/*
============================================================
LEETCODE 61 - ROTATE LIST

Problem

Given the head of a linked list,

rotate the list to the right by

k

places.

------------------------------------------------------------
Example 1

Input

head = 1 -> 2 -> 3 -> 4 -> 5

k = 2

Output

4 -> 5 -> 1 -> 2 -> 3

------------------------------------------------------------
Example 2

Input

head = 0 -> 1 -> 2

k = 4

Output

2 -> 0 -> 1

------------------------------------------------------------
Intuition

Rotating the list one time means

Move the last node to the front.

Doing this

k

times would take

O(n × k)

which is inefficient.

Instead,

make the linked list circular.

Then,

find the new tail and break the circle.

------------------------------------------------------------
Approach

1. Find the length of the linked list.

2. Find the last node.

3. Compute

k = k % length

because rotating

length

times gives the same list.

4. Connect the last node to the head.

Now the list becomes circular.

5. Move

length - k

steps to find the new tail.

6. The next node becomes the new head.

7. Break the circle.

------------------------------------------------------------
Algorithm

1. Handle edge cases.

2. Traverse the list.

Find

length

and

tail.

3. Compute

k = k % length.

4. If

k == 0

Return head.

5. Connect

tail.next = head.

6. Move

length - k

steps.

7. Store

newHead = newTail.next.

8. Break

newTail.next = null.

9. Return

newHead.

------------------------------------------------------------
Dry Run

Input

1 -> 2 -> 3 -> 4 -> 5

k = 2

-----------------------------------

Length = 5

Tail = 5

-----------------------------------

k = 2 % 5

= 2

-----------------------------------

Create circle

1 -> 2 -> 3 -> 4 -> 5
^                     |
|_____________________|

-----------------------------------

steps = 5 - 2 = 3

Starting from tail

5 -> 1

1 -> 2

2 -> 3

newTail = 3

-----------------------------------

newHead = 4

Break

3.next = null

Answer

4 -> 5 -> 1 -> 2 -> 3

------------------------------------------------------------
Why It Works

Making the list circular removes the
need to move the last node repeatedly.

After moving exactly

length - k

steps,

the next node naturally becomes the
new head.

Breaking the circle restores the linked
list with the required rotation.

------------------------------------------------------------
Time Complexity

O(n)

One traversal to compute length.

One traversal to locate the new tail.

------------------------------------------------------------
Space Complexity

O(1)

Only a few pointers are used.

------------------------------------------------------------
Functions Used

next

Points to the next node.

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

    public static ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Find length and tail
        ListNode tail = head;
        int length = 1;

        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Effective rotations
        k = k % length;

        if (k == 0) {
            return head;
        }

        // Make circular
        tail.next = head;

        // Find new tail
        int steps = length - k;
        ListNode newTail = tail;

        while (steps-- > 0) {
            newTail = newTail.next;
        }

        // New head
        ListNode newHead = newTail.next;

        // Break the circle
        newTail.next = null;

        return newHead;
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

        head = rotateRight(head, k);

        System.out.println("Rotated Linked List:");

        printList(head);

        sc.close();
    }
}
