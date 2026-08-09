/*
============================================================
LEETCODE 146 - LRU CACHE

Problem

Design a data structure that follows the rules of an
LRU (Least Recently Used) Cache.

The cache has a fixed capacity.

It supports:

1. get(key)
2. put(key, value)

------------------------------------------------------------
WHAT IS LRU?

LRU means:

Least Recently Used

When the cache becomes full, we remove the key that
has not been used for the longest time.

Example:

capacity = 2

put(1, 10)
put(2, 20)

Cache:

1 -> 2

Now:

get(1)

Key 1 was recently used.

Order becomes:

2 -> 1

Now:

put(3, 30)

Cache is full.

Key 2 is the least recently used.

So we remove 2.

Final:

1 -> 3

------------------------------------------------------------
REQUIRED COMPLEXITY

The problem expects:

get()  -> O(1)
put()  -> O(1)

A normal array, queue, or singly linked list cannot
efficiently achieve this because we need to:

1. Find a key quickly.
2. Remove a key quickly.
3. Move a key to the most recently used position.
4. Remove the least recently used key.

Therefore we combine:

HashMap + Doubly Linked List

------------------------------------------------------------
WHY HASHMAP?

The HashMap stores:

key -> Node

Example:

map:

1 -> Node(1, 10)
2 -> Node(2, 20)

This allows us to find a key in O(1) average time.

------------------------------------------------------------
WHY DOUBLY LINKED LIST?

The linked list maintains the usage order.

We maintain:

HEAD
 ↓
Most Recently Used
 ↓
...
 ↓
Least Recently Used
 ↓
TAIL

Example:

HEAD -> 3 -> 1 -> 2 -> TAIL

Here:

3 = Most Recently Used

2 = Least Recently Used

When the cache is full, we remove:

tail.prev

which is the least recently used node.

------------------------------------------------------------
WHY DOUBLY LINKED LIST?

Every Node contains:

prev
next

Therefore, if we already have a reference to a Node,
we can remove it directly.

Suppose:

A <-> B <-> C

We want to remove B.

Using:

B.prev
B.next

we can connect:

A <-> C

This takes O(1).

------------------------------------------------------------
DUMMY HEAD AND TAIL

We create two dummy nodes:

head
tail

They do not contain actual cache data.

Initially:

head <-> tail

After adding nodes:

head <-> 1 <-> 2 <-> tail

The purpose of dummy nodes is to avoid special cases
when inserting or removing nodes at the beginning
or end of the list.

------------------------------------------------------------
MOST RECENTLY USED

The node immediately after head is always:

Most Recently Used

Example:

head -> 3 -> 1 -> 2 -> tail

3 is the most recently used.

------------------------------------------------------------
LEAST RECENTLY USED

The node immediately before tail is always:

Least Recently Used

Example:

head -> 3 -> 1 -> 2 -> tail

2 is the least recently used.

Therefore:

tail.prev

gives the LRU node.

------------------------------------------------------------
NODE CLASS

Each cache entry is represented by:

class Node {

    int key;
    int value;

    Node prev;
    Node next;
}

We store both key and value.

The key is important because when we remove the
LRU node from the linked list, we also need to remove
its key from the HashMap.

------------------------------------------------------------
GET OPERATION

get(key)

First check whether the key exists.

If it doesn't:

return -1

If it exists:

1. Get the Node from the HashMap.
2. Remove it from its current position.
3. Insert it at the front.
4. Return its value.

Why move it to the front?

Because accessing a key makes it recently used.

------------------------------------------------------------
Example

Current:

head -> 1 -> 2 -> 3 -> tail

Suppose:

get(2)

2 is accessed.

Remove 2:

head -> 1 -> 3 -> tail

Insert 2 at front:

head -> 2 -> 1 -> 3 -> tail

Now 2 is the most recently used.

------------------------------------------------------------
PUT OPERATION

There are two cases.

CASE 1:

Key already exists.

Example:

put(2, 50)

If 2 already exists:

1. Get its Node.
2. Update its value.
3. Remove it from its current position.
4. Insert it at the front.

Because updating a key also makes it recently used.

------------------------------------------------------------
CASE 2:

Key doesn't exist.

Create a new Node.

Put it into the HashMap.

Insert it at the front.

Then check whether:

map.size() > capacity

If yes,

the cache has exceeded its capacity.

Remove:

tail.prev

because it is the least recently used node.

Also remove its key from the HashMap.

------------------------------------------------------------
REMOVE FUNCTION

private void remove(Node node)

Suppose:

A <-> B <-> C

node = B

We do:

node.prev.next = node.next

which means:

A.next = C

Then:

node.next.prev = node.prev

which means:

C.prev = A

Result:

A <-> C

B is removed.

------------------------------------------------------------
INSERT AT FRONT

Suppose:

head -> A -> B -> tail

We want to insert C immediately after head.

First:

C.next = head.next

C.next = A

Then:

C.prev = head

Then:

head.next.prev = C

A.prev = C

Finally:

head.next = C

Result:

head -> C -> A -> B -> tail

------------------------------------------------------------
DRY RUN

capacity = 2

------------------------------------------------------------
put(1, 10)

Cache:

head -> 1 -> tail

Map:

1 -> Node(1,10)

------------------------------------------------------------
put(2, 20)

Cache:

head -> 2 -> 1 -> tail

2 = MRU

1 = LRU

------------------------------------------------------------
get(1)

1 exists.

Move 1 to front.

Cache:

head -> 1 -> 2 -> tail

Now:

1 = MRU

2 = LRU

Return:

10

------------------------------------------------------------
put(3, 30)

Insert 3:

head -> 3 -> 1 -> 2 -> tail

Size = 3

Capacity = 2

Remove:

tail.prev

which is 2.

Final:

head -> 3 -> 1 -> tail

Map contains:

3
1

------------------------------------------------------------
get(2)

2 is not in the map.

Return:

-1

------------------------------------------------------------
WHY HASHMAP + LINKED LIST?

HashMap:

Find node in O(1)

Doubly Linked List:

Remove node in O(1)

Move node in O(1)

Remove LRU in O(1)

Together:

get()  -> O(1)
put()  -> O(1)

------------------------------------------------------------
ALGORITHM

GET:

1. Check map.
2. If absent, return -1.
3. Get node.
4. Remove node.
5. Insert node at front.
6. Return value.

PUT:

1. If key exists:
      update value
      remove node
      insert at front

2. Otherwise:
      create node
      add to map
      insert at front

3. If size > capacity:
      remove tail.prev
      remove its key from map

------------------------------------------------------------
TIME COMPLEXITY

get():

O(1) average

put():

O(1) average

------------------------------------------------------------
SPACE COMPLEXITY

O(capacity)

The HashMap and linked list together store
at most capacity cache entries.

------------------------------------------------------------
KEY CONCEPTS

✓ HashMap
✓ Doubly Linked List
✓ LRU
✓ Dummy Nodes
✓ O(1) Lookup
✓ O(1) Insertion
✓ O(1) Deletion

------------------------------------------------------------
PATTERN

Need:

Fast lookup
+
Fast deletion
+
Fast insertion
+
Maintain order

↓

HashMap + Doubly Linked List

============================================================
*/
import java.util.*;

public class Main {

    static class LRUCache {

        class Node {
            int key;
            int value;
            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final int capacity;
        private final HashMap<Integer, Node> map;
        private final Node head;
        private final Node tail;

        public LRUCache(int capacity) {

            this.capacity = capacity;
            map = new HashMap<>();

            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {

            if (!map.containsKey(key)) {
                return -1;
            }

            Node node = map.get(key);

            remove(node);
            insertAtFront(node);

            return node.value;
        }

        public void put(int key, int value) {

            if (map.containsKey(key)) {

                Node node = map.get(key);

                node.value = value;

                remove(node);
                insertAtFront(node);

            } else {

                Node node = new Node(key, value);

                map.put(key, node);
                insertAtFront(node);

                if (map.size() > capacity) {

                    Node lru = tail.prev;

                    remove(lru);
                    map.remove(lru.key);
                }
            }
        }

        private void remove(Node node) {

            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void insertAtFront(Node node) {

            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }
    }

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);

        System.out.println(cache.get(1));

        cache.put(3, 30);

        System.out.println(cache.get(2));
        System.out.println(cache.get(3));

        cache.put(4, 40);

        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
