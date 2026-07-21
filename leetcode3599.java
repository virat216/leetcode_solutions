/*
============================================================
LEETCODE 3499 - Maximize Active Section with Trade I

Problem

You are given a binary string s.

'1' represents an active section.

'0' represents an inactive section.

You can perform one trade to maximize the number
of active sections.

Return the maximum number of active sections
after the trade.

------------------------------------------------------------
Example

Input

s = "001110100"

Output

7

------------------------------------------------------------
Intuition

First count how many sections are already active.

Now observe that the only useful trade is to
connect two groups of '0's.

If two consecutive groups of zeros are merged,
we gain

length of first zero group

+

length of second zero group

new active sections.

Therefore,

while traversing the string,

keep track of

1. Total number of '1's.

2. Previous zero group length.

3. Current zero group length.

The maximum sum of two adjacent zero groups
gives the maximum gain.

------------------------------------------------------------
Approach

1. Traverse the string.

2. Count consecutive groups.

3. Whenever a group of '1's is found,

add its length to the answer.

4. Whenever a group of '0's is found,

compare

previousZeroGroup + currentZeroGroup

with the current maximum.

5. Update the previous zero group.

6. Return

totalOnes + maximumGain.

------------------------------------------------------------
Algorithm

1. Initialize

ones = 0

previousZeroGroup = very small value

maximumGain = 0

2. Traverse the string by groups.

3. If current group is '1',

add its length to ones.

4. Otherwise,

maximumGain =
max(maximumGain,
previousZeroGroup + currentGroupLength)

5. Update previousZeroGroup.

6. Return

ones + maximumGain.

------------------------------------------------------------
Dry Run

Input

001110100

Groups

00

111

0

1

00

Ones

3 + 1 = 4

Zero Groups

2

1

2

Gain

First zero group

2

Second zero group

1

Gain = 3

Third zero group

2

Gain = 1 + 2 = 3

Maximum Gain = 3

Answer

4 + 3 = 7

------------------------------------------------------------
Why It Works

The total number of active sections already
present never decreases.

The only improvement comes from joining
adjacent zero groups through one trade.

While scanning the string once,

we always know

• Total active sections.

• Previous zero group.

• Current zero group.

Thus we can compute the best possible gain
in linear time.

------------------------------------------------------------
Time Complexity

O(n)

The string is traversed exactly once.

------------------------------------------------------------
Space Complexity

O(1)

Only a few integer variables are used.

------------------------------------------------------------
Functions Used

charAt()

Reads characters of the string.

Math.max()

Keeps track of the best gain.

============================================================
*/
import java.util.*;

public class Main {

    public static int maxActiveSectionsAfterTrade(String s) {

        int n = s.length();

        int ones = 0;
        int previousZeroGroup = Integer.MIN_VALUE;
        int maxGain = 0;

        int i = 0;

        while (i < n) {

            int j = i;

            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            int length = j - i;

            if (s.charAt(i) == '1') {

                ones += length;

            } else {

                maxGain = Math.max(maxGain, previousZeroGroup + length);
                previousZeroGroup = length;
            }

            i = j;
        }

        return ones + maxGain;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter binary string: ");

        String s = sc.next();

        int ans = maxActiveSectionsAfterTrade(s);

        System.out.println("Maximum Active Sections = " + ans);

        sc.close();
    }
}


