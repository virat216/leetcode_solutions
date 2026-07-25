/*
============================================================
LEETCODE 3536 - Maximum Product of Two Digits

Problem

You are given a positive integer n.

Find the two largest digits present in n
and return their product.

------------------------------------------------------------
Example 1

Input

n = 4315

Digits

4 3 1 5

Largest digits

5 and 4

Output

20

------------------------------------------------------------
Example 2

Input

n = 2291

Digits

2 2 9 1

Largest digits

9 and 2

Output

18

------------------------------------------------------------
Intuition

A brute-force solution would

1. Extract all digits.

2. Store them in an array.

3. Sort the array.

4. Multiply the last two digits.

Although this works, sorting is unnecessary.

Instead,

while extracting digits,

maintain

• Largest digit

• Second largest digit

Whenever a larger digit is found,

update both values.

------------------------------------------------------------
Approach

1. Initialize

largest = 0

secondLargest = 0

2. Extract digits one by one.

digit = n % 10

3. If digit > largest

Move largest to secondLargest.

Update largest.

4. Otherwise,

if digit > secondLargest

Update secondLargest.

5. Remove last digit.

n /= 10

6. Return

largest × secondLargest

------------------------------------------------------------
Algorithm

1. Initialize two variables.

2. Traverse every digit.

3. Update largest and second largest.

4. Return their product.

------------------------------------------------------------
Dry Run

Input

n = 4315

largest = 0

secondLargest = 0

---------------------

Digit = 5

largest = 5

secondLargest = 0

---------------------

Digit = 1

largest = 5

secondLargest = 1

---------------------

Digit = 3

largest = 5

secondLargest = 3

---------------------

Digit = 4

largest = 5

secondLargest = 4

Answer

5 × 4

=

20

------------------------------------------------------------
Why It Works

Every digit is examined exactly once.

The variables

largest

and

secondLargest

always store the two biggest digits seen so far.

After processing all digits,

they represent the two largest digits
of the number.

Their product is therefore the maximum
possible product.

------------------------------------------------------------
Time Complexity

O(log n)

A number with d digits is processed in
d iterations.

------------------------------------------------------------
Space Complexity

O(1)

Only two integer variables are used.

------------------------------------------------------------
Functions Used

%

Extracts the last digit.

/

Removes the last digit.

============================================================
*/
import java.util.*;

public class Main {

    public static int maxProduct(int n) {

        int largest = 0;
        int secondLargest = 0;

        while (n > 0) {

            int digit = n % 10;

            if (digit > largest) {

                secondLargest = largest;
                largest = digit;

            } else if (digit > secondLargest) {

                secondLargest = digit;
            }

            n /= 10;
        }

        return largest * secondLargest;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");

        int n = sc.nextInt();

        int ans = maxProduct(n);

        System.out.println("Maximum Product = " + ans);

        sc.close();
    }
}


