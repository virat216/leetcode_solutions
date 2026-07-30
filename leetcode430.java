/*
============================================================
LEETCODE 430 - Flatten a Multilevel Doubly Linked List

Problem

You are given the head of a multilevel
doubly linked list.

Each node has

prev
next
child

A child pointer may point to another
doubly linked list.

Flatten the list so that all the nodes
appear in a single-level doubly linked list.

After flattening,

every child pointer should become null.

Return the head of the flattened list.

------------------------------------------------------------
Example

Input

1 ⇄ 2 ⇄ 3 ⇄ 4 ⇄ 5 ⇄ 6
        |
        7 ⇄ 8 ⇄ 9 ⇄ 10
            |
            11 ⇄ 12

Output

1 ⇄ 2 ⇄ 3 ⇄ 7 ⇄ 8 ⇄ 11 ⇄ 12 ⇄ 9 ⇄ 10 ⇄ 4 ⇄ 5 ⇄ 6

------------------------------------------------------------
Intuition

Whenever we encounter a node having
a child,

the child list should come immediately
after that node.

To achieve this,

1. Find the last node (tail) of the child list.

2. Connect this tail to the current node's
original next node.

3. Connect the current node to the child.

4. Remove the child pointer.

Repeat until the entire list is traversed.

Everything is done in-place.

------------------------------------------------------------
Approach

1. Start from the head.

2. Traverse each node.

3. If the current node has no child,
move to the next node.

4. Otherwise,

find the tail of the child list.

5. Connect

childTail.next = current.next

6. Update prev pointer if current.next exists.

7. Connect

current.next = current.child

8. Update child's prev pointer.

9. Set

current.child = null

10. Continue traversal.

------------------------------------------------------------
Algorithm

1. current = head

2. While current != null

    If current.child == null

        Move to current.next

    Else

        Find childTail

        childTail.next = current.next

        If current.next exists

            current.next.prev = childTail

        current.next = current.child

        current.child.prev = current

        current.child = null

3. Return head.

------------------------------------------------------------
Dry Run

Original List

1 ⇄ 2 ⇄ 3 ⇄ 4

        |

        7 ⇄ 8

-----------------------------------

Current = 2

Child exists.

Find child tail.

Tail = 8

-----------------------------------

Connect

8.next = 3

3.prev = 8

-----------------------------------

Connect

2.next = 7

7.prev = 2

-----------------------------------

Remove

2.child = null

Now list becomes

1 ⇄ 2 ⇄ 7 ⇄ 8 ⇄ 3 ⇄ 4

Continue traversal.

Answer

1 ⇄ 2 ⇄ 7 ⇄ 8 ⇄ 3 ⇄ 4

------------------------------------------------------------
Why It Works

Whenever a child list is found,

it is inserted between

current

and

current.next.

The original next node is not lost because
it is first connected to the tail of the
child list.

Since every child pointer is removed,

the final list becomes a normal
doubly linked list.

------------------------------------------------------------
Time Complexity

O(n)

Every node is visited at most a constant
number of times.

------------------------------------------------------------
Space Complexity

O(1)

No extra stack or auxiliary data structure
is used.

------------------------------------------------------------
Functions Used

next

Moves to the next node.

prev

Points to the previous node.

child

Points to the child list.

============================================================
*/

class Node {

    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node(int val) {
        this.val = val;
    }
}

public class Main {

    public static Node flatten(Node head) {

        Node current = head;

        while (current != null) {

            if (current.child == null) {

                current = current.next;

            } else {

                Node childTail = current.child;

                while (childTail.next != null) {
                    childTail = childTail.next;
                }

                childTail.next = current.next;

                if (current.next != null) {
                    current.next.prev = childTail;
                }

                current.next = current.child;
                current.child.prev = current;
                current.child = null;

                current = current.next;
            }
        }

        return head;
    }

    public static void printList(Node head) {

        while (head != null) {

            System.out.print(head.val);

            if (head.next != null) {
                System.out.print(" ⇄ ");
            }

            head = head.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        /*
              1 ⇄ 2 ⇄ 3 ⇄ 4
                  |
                  7 ⇄ 8
        */

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n1.next = n2;
        n2.prev = n1;

        n2.next = n3;
        n3.prev = n2;

        n3.next = n4;
        n4.prev = n3;

        n2.child = n7;

        n7.next = n8;
        n8.prev = n7;

        Node head = flatten(n1);

        System.out.println("Flattened List:");

        printList(head);
    }
}
