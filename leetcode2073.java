/*
============================================================
LEETCODE 2073 - TIME NEEDED TO BUY TICKETS

Problem

We are given:

tickets[i] = number of tickets person i wants

k = index of the person we are interested in.

Every second:

1. The person at the front buys one ticket.
2. If they still need tickets, they go to the back.
3. If they have no tickets left, they leave the queue.

We need to find the total time until person k
buys all their tickets.

------------------------------------------------------------
Example

tickets = [2, 3, 2]

k = 2

Person 2 needs:

2 tickets

------------------------------------------------------------
Queue initially:

[0, 1, 2]

Person 0 buys one:

tickets = [1, 3, 2]

Person 1 buys one:

tickets = [1, 2, 2]

Person 2 buys one:

tickets = [1, 2, 1]

Person 0 buys one:

tickets = [0, 2, 1]

Person 1 buys one:

tickets = [0, 1, 1]

Person 2 buys one:

tickets = [0, 1, 0]

Person 2 is finished.

Total time = 6

------------------------------------------------------------
IMPORTANT OBSERVATION

We don't actually need to simulate the queue.

Suppose:

tickets[k] = 2

Person k needs 2 turns.

Every person before or at k gets the opportunity
to buy at most 2 tickets before person k finishes.

Therefore:

for i <= k:

number of turns =
min(tickets[i], tickets[k])

------------------------------------------------------------
WHAT ABOUT PEOPLE AFTER k?

People after k also get turns.

But when person k buys their final ticket,

the process stops immediately.

Therefore, people after k get one fewer opportunity.

So:

for i > k:

number of turns =
min(tickets[i], tickets[k] - 1)

------------------------------------------------------------
WHY i <= k IS DIFFERENT

Consider:

tickets = [2, 3, 2]

k = 2

Person 0 is before person k.

They can buy:

2 tickets

Person 1 is also before person k.

They can buy:

2 tickets

Person 2 buys:

2 tickets

So:

i <= k

uses:

min(tickets[i], tickets[k])

------------------------------------------------------------
WHY i > k GETS ONE LESS TURN

Suppose:

tickets = [2, 3, 2]

k = 2

There is no person after k in this example.

Consider instead:

tickets = [2, 3, 2, 5]

k = 2

Person 3 is after person k.

Person 3 can buy a ticket during the first
round and second round.

But during the second round,

person k buys their final ticket before
person 3 gets another turn.

Therefore person 3 gets at most:

tickets[k] - 1

turns.

So:

min(tickets[i], tickets[k] - 1)

------------------------------------------------------------
FORMULA

For every i:

if i <= k:

time += min(tickets[i], tickets[k])

otherwise:

time += min(tickets[i], tickets[k] - 1)

------------------------------------------------------------
DRY RUN

tickets = [2, 3, 2, 5]

k = 2

tickets[k] = 2

------------------------------------------------------------
i = 0

i <= k

min(2, 2) = 2

time = 2

------------------------------------------------------------
i = 1

i <= k

min(3, 2) = 2

time = 4

------------------------------------------------------------
i = 2

i <= k

min(2, 2) = 2

time = 6

Person k finishes here.

------------------------------------------------------------
i = 3

i > k

min(5, 2 - 1)

= min(5, 1)

= 1

time = 7

------------------------------------------------------------
ANSWER

7

------------------------------------------------------------
WHY THIS IS OPTIMAL

A direct queue simulation may repeatedly enqueue
and dequeue the same person.

Instead, we calculate exactly how many times each
person can buy a ticket.

Therefore we only need one traversal of the array.

------------------------------------------------------------
ALGORITHM

1. Set:

time = 0

2. Traverse every person.

3. If:

i <= k

add:

min(tickets[i], tickets[k])

4. Otherwise add:

min(tickets[i], tickets[k] - 1)

5. Return time.

------------------------------------------------------------
TIME COMPLEXITY

O(n)

We traverse the array once.

------------------------------------------------------------
SPACE COMPLEXITY

O(1)

Only one variable is used.

------------------------------------------------------------
KEY CONCEPTS

✓ Array
✓ Queue Simulation Observation
✓ Greedy Counting
✓ Math
✓ One Pass

============================================================
*/
import java.util.*;

public class Main {

    public static int timeRequiredToBuy(int[] tickets, int k) {

        int time = 0;

        for (int i = 0; i < tickets.length; i++) {

            if (i <= k) {
                time += Math.min(tickets[i], tickets[k]);
            } else {
                time += Math.min(tickets[i], tickets[k] - 1);
            }
        }

        return time;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of people: ");
        int n = sc.nextInt();

        int[] tickets = new int[n];

        System.out.println("Enter tickets:");

        for (int i = 0; i < n; i++) {
            tickets[i] = sc.nextInt();
        }

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        int result = timeRequiredToBuy(tickets, k);

        System.out.println("Time required: " + result);

        sc.close();
    }
}

