/*
============================================================
LEETCODE 2 - ADD TWO NUMBERS

Problem

You are given two non-empty linked lists.

Each linked list represents a non-negative
integer.

The digits are stored in reverse order.

Each node contains exactly one digit.

Add the two numbers and return the sum
as a linked list.

------------------------------------------------------------
Example

Input

l1 = 2 -> 4 -> 3

l2 = 5 -> 6 -> 4

Numbers

342

465

Output

7 -> 0 -> 8

Because

342 + 465 = 807

------------------------------------------------------------
Intuition

This problem is exactly like elementary
school addition.

Start from the least significant digit.

Add

Digit1

+

Digit2

+

Carry

Create a new node for the current digit.

Pass the carry to the next addition.

Continue until

Both lists become null

and

Carry becomes 0.

------------------------------------------------------------
Approach

1. Create a dummy node.

2. Maintain a pointer

current

which always points to the last node
of the answer.

3. Initialize

carry = 0

4. Traverse both linked lists together.

5. Compute

sum = carry

Add values from l1 and l2
if available.

6. Create a node

sum % 10

7. Update

carry = sum / 10

8. Move all pointers forward.

9. Return

dummy.next

------------------------------------------------------------
Algorithm

1. Create

dummy node

current = dummy

carry = 0

2. While

l1 != null

or

l2 != null

or

carry != 0

3. Compute

sum

4. Create node

sum % 10

5. Update carry

6. Move pointers

7. Return

dummy.next

------------------------------------------------------------
Dry Run

Input

l1

2 -> 4 -> 3

l2

5 -> 6 -> 4

-----------------------------------

carry = 0

sum = 2 + 5

= 7

Answer

7

carry = 0

-----------------------------------

sum = 4 + 6

= 10

Digit

0

carry = 1

Answer

7 -> 0

-----------------------------------

sum = 3 + 4 + carry

= 8

Digit

8

carry = 0

Answer

7 -> 0 -> 8

Finished.

------------------------------------------------------------
Another Example

l1

9 -> 9

l2

1

-----------------------------------

9 + 1

=10

Digit

0

carry=1

-----------------------------------

9 + carry

=10

Digit

0

carry=1

-----------------------------------

Only carry remains

Digit

1

Answer

0 -> 0 -> 1

------------------------------------------------------------
Why It Works

At every step,

we perform exactly the same operation
as manual addition.

Current Digit

=

(sum % 10)

Carry

=

(sum / 10)

The loop continues until

both linked lists

and

carry

are exhausted.

------------------------------------------------------------
Time Complexity

O(max(m,n))

where

m

=

length of l1

n

=

length of l2

Each node is visited exactly once.

------------------------------------------------------------
Space Complexity

O(max(m,n))

The returned linked list stores the answer.

Auxiliary Space

O(1)

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

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {

            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            current.next = new ListNode(sum % 10);

            carry = sum / 10;

            current = current.next;
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

        System.out.print("Enter number of nodes in first list: ");
        int n1 = sc.nextInt();

        System.out.println("Enter digits of first number:");

        ListNode l1 = createList(sc, n1);

        System.out.print("Enter number of nodes in second list: ");
        int n2 = sc.nextInt();

        System.out.println("Enter digits of second number:");

        ListNode l2 = createList(sc, n2);

        ListNode result = addTwoNumbers(l1, l2);

        System.out.println("Result:");

        printList(result);

        sc.close();
    }
}

