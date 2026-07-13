/*
============================================================
LEETCODE 1291 - Sequential Digits

Problem

An integer has sequential digits if every digit is exactly
one greater than the previous digit.

Example

1234

4567

6789

Given two integers

low

and

high,

return all sequential digit numbers in the range
[low, high].

The answer should be sorted in increasing order.

------------------------------------------------------------
Example

Input

low = 100

high = 300

Output

[123,234]

------------------------------------------------------------
Example

Input

low = 1000

high = 13000

Output

[1234,2345,3456,4567,5678,6789,12345]

------------------------------------------------------------
Intuition

Sequential digit numbers always follow a fixed pattern.

Example

12

23

34

45

...

123

234

345

...

1234

2345

...

Instead of checking every number between

low

and

high,

we directly generate only valid sequential numbers.

Whenever a generated number lies within the given range,

store it in the answer.

------------------------------------------------------------
Approach

1. Start every number with digits

1 to 9.

2. Continuously append the next digit.

Example

Start

3

↓

34

↓

345

↓

3456

↓

34567

3. Whenever the generated number lies inside

[low, high]

store it.

4. Stop when the next digit becomes greater than 9.

------------------------------------------------------------
Algorithm

1. Create an empty answer list.

2. Choose starting digit from

1 to 9.

3. Generate sequential numbers.

4. If

number >= low

and

number <= high

store it.

5. Continue until digit becomes 10.

6. Sort the answer.

7. Return answer.

------------------------------------------------------------
Why It Works

Every sequential digit number is generated exactly once.

Example

Start with

2

↓

23

↓

234

↓

2345

↓

23456

No valid sequential number is missed.

No duplicate numbers are generated.

Finally,

sorting guarantees increasing order.

------------------------------------------------------------
Time Complexity

There are only

36

possible sequential numbers.

Generation

O(1)

Sorting

O(1)

Overall

O(1)

------------------------------------------------------------
Space Complexity

O(1)

Only a few extra variables are used.

------------------------------------------------------------
Functions Used

Collections.sort()

Sorts the generated sequential numbers.

============================================================
*/


import java.util.*;

public class Main {

    public static List<Integer> sequentialDigits(int low, int high) {

        List<Integer> result = new ArrayList<>();

        for (int start = 1; start <= 9; start++) {

            int num = start;
            int nextDigit = start + 1;

            while (nextDigit <= 9) {

                num = num * 10 + nextDigit;

                if (num >= low && num <= high) {
                    result.add(num);
                }

                nextDigit++;
            }
        }

        Collections.sort(result);

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter low: ");
        int low = sc.nextInt();

        System.out.print("Enter high: ");
        int high = sc.nextInt();

        List<Integer> ans = sequentialDigits(low, high);

        System.out.println("Sequential Digits:");

        for (int num : ans) {
            System.out.print(num + " ");
        }

        sc.close();
    }
}
