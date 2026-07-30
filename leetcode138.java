/*
============================================================
LEETCODE 138 - Copy List with Random Pointer

Problem

You are given the head of a linked list.

Each node contains

1. val
2. next
3. random

The random pointer can point to

• Any node in the linked list.
• Null.

Your task is to create a deep copy of
the linked list.

A deep copy means

Every node in the copied list must be a
new node.

The copied nodes must have the same

• value
• next connection
• random connection

The copied list should not reference
any node from the original list.

Return the head of the copied list.

------------------------------------------------------------
Example

Original List

7 → 13 → 11 → 10 → 1

Random Pointers

7  → null

13 → 7

11 → 1

10 → 11

1  → 7

Copied List

7 → 13 → 11 → 10 → 1

Random Pointers

7'  → null

13' → 7'

11' → 1'

10' → 11'

1'  → 7'

Notice

Every pointer refers to copied nodes,
not original nodes.

------------------------------------------------------------
Intuition

The difficult part is copying the
random pointers.

Suppose

Original

A.random → C

If we simply copy values,

how do we know where the copied C is?

To solve this,

insert every copied node immediately
after its original node.

Example

Original

A → B → C

After copying

A → A' → B → B' → C → C'

Now,

the copy of any node is simply

original.next

This allows us to assign random pointers
without using extra space.

------------------------------------------------------------
Approach

Step 1

Insert copied nodes after every original
node.

Example

1 → 2 → 3

becomes

1 → 1' → 2 → 2' → 3 → 3'

--------------------------------------------

Step 2

Copy random pointers.

If

original.random = X

Then

copy.random = X.next

because

X.next

is the copied node.

--------------------------------------------

Step 3

Separate the original list and copied list.

Restore the original list while extracting
the copied list.

Return the copied head.

------------------------------------------------------------
Algorithm

1. Traverse the list.

Create a copied node after every
original node.

2. Traverse again.

Assign

copy.random = original.random.next

if random exists.

3. Traverse once more.

Separate both linked lists.

4. Return copied head.

------------------------------------------------------------
Dry Run

Original

1 → 2 → 3

-----------------------------------

Step 1

Insert copies

1 → 1' → 2 → 2' → 3 → 3'

-----------------------------------

Step 2

Suppose

1.random = 3

Then

1'.random = 3'

using

1.random.next

-----------------------------------

Step 3

Separate

Original

1 → 2 → 3

Copied

1' → 2' → 3'

Answer returned

1'

------------------------------------------------------------
Why It Works

Every copied node is placed immediately
after its original node.

Therefore,

the copy of any node can always be
accessed using

original.next

This makes assigning random pointers
very easy.

Finally,

both lists are separated without losing
any connections.

------------------------------------------------------------
Time Complexity

O(n)

Three linear traversals.

------------------------------------------------------------
Space Complexity

O(1)

No HashMap or extra data structure
is used.

------------------------------------------------------------
Functions Used

next

Points to the next node.

random

Points to any node or null.

============================================================
*/
class Node {

    int val;
    Node next;
    Node random;

    Node(int val) {
        this.val = val;
    }
}

public class Main {

    public static Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        // Step 1
        Node current = head;

        while (current != null) {

            Node copy = new Node(current.val);

            copy.next = current.next;
            current.next = copy;

            current = copy.next;
        }

        // Step 2
        current = head;

        while (current != null) {

            if (current.random != null) {
                current.next.random = current.random.next;
            }

            current = current.next.next;
        }

        // Step 3
        current = head;

        Node dummy = new Node(0);
        Node copyCurrent = dummy;

        while (current != null) {

            Node copy = current.next;

            current.next = copy.next;

            copyCurrent.next = copy;
            copyCurrent = copy;

            current = current.next;
        }

        return dummy.next;
    }

    public static void printList(Node head) {

        while (head != null) {

            int randomValue = (head.random == null)
                    ? -1
                    : head.random.val;

            System.out.println(
                    "Value = " + head.val +
                    ", Random = " + randomValue);

            head = head.next;
        }
    }

    public static void main(String[] args) {

        /*
            1 → 2 → 3

            Random

            1 → 3
            2 → 1
            3 → 2
        */

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        n1.next = n2;
        n2.next = n3;

        n1.random = n3;
        n2.random = n1;
        n3.random = n2;

        Node copied = copyRandomList(n1);

        System.out.println("Copied List:");

        printList(copied);
    }
}

