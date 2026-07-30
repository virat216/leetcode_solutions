/*
============================================================
LEETCODE 234 - Palindrome Linked List

Problem

Given the head of a singly linked list,

return

true

if it is a palindrome,

otherwise return

false.

A palindrome reads the same
from left to right and right to left.

------------------------------------------------------------
Example 1

Input

1 -> 2 -> 2 -> 1

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

A palindrome has identical first and
second halves when one half is reversed.

Instead of storing the values in an array,

we can solve the problem using O(1)
extra space.

Steps

1. Find the middle of the linked list.

2. Reverse the second half.

3. Compare both halves node by node.

If every value matches,

the linked list is a palindrome.

------------------------------------------------------------
Approach

1. Find the middle using slow and
fast pointers.

2. Reverse the second half of the list.

3. Compare the first half with the
reversed second half.

4. If every node matches,

return true.

Otherwise,

return false.

------------------------------------------------------------
Algorithm

1. If the list has 0 or 1 node,

return true.

2. Initialize

slow = head

fast = head

3. Move

slow one step

fast two steps

until fast reaches the end.

4. Reverse the list starting from slow.

5. Compare

head

and

reversed second half.

6. If any values differ,

return false.

7. Return true.

------------------------------------------------------------
Dry Run

Input

1 -> 2 -> 2 -> 1

-----------------------------------

Find Middle

Slow = 2

-----------------------------------

Reverse Second Half

2 -> 1

becomes

1 -> 2

-----------------------------------

Compare

First Half

1 -> 2

Second Half

1 -> 2

1 == 1 ✔

2 == 2 ✔

Answer

true

------------------------------------------------------------
Why It Works

The slow pointer divides the list
into two halves.

Reversing the second half makes it
possible to compare both halves from
left to right.

If every corresponding node has the
same value,

the linked list is a palindrome.

------------------------------------------------------------
Time Complexity

O(n)

Finding middle

O(n)

Reversing second half

O(n)

Comparing halves

O(n)

Overall

O(n)

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

    public static boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev = null;
        ListNode current = slow;

        while (current != null) {

            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        ListNode first = head;
        ListNode second = prev;

        while (second != null) {

            if (first.val != second.val) {
                return false;
            }

            first = first.next;
            second = second.next;
        }

        return true;
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

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        System.out.println("Enter node values:");

        ListNode head = createList(sc, n);

        System.out.println("Palindrome: " + isPalindrome(head));

        sc.close();
    }
}
