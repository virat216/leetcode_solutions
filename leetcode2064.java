/*
============================================================
LEETCODE 2064 - Minimized Maximum of Products Distributed to Any Store

Problem

You are given

n = Number of stores

quantities[i] = Number of products of the ith type.

Rules:

1. Every store can receive only one type of product.
2. A product type can be distributed among multiple stores.
3. Every product must be distributed.

Return the minimum possible value of the maximum number
of products assigned to any store.

------------------------------------------------------------
Example

Input:

n = 6

quantities = [11,6]

Output:

3

Explanation

Possible Distribution

11 -> 3 + 3 + 3 + 2

6  -> 3 + 3

Maximum products in any store = 3

This is the minimum possible answer.

------------------------------------------------------------
Intuition

The answer is not directly visible.

Instead of finding the answer, we ask:

"If every store can hold at most X products,
is it possible to distribute all products?"

If the answer is YES,

then every value greater than X will also work.

If the answer is NO,

then every value smaller than X will also fail.

This creates a monotonic pattern.

Example

Maximum Products

1  -> NO
2  -> NO
3  -> YES
4  -> YES
5  -> YES

Pattern

FFFFFFTTTTTT

Whenever we see this pattern,

we should think of

Binary Search on Answer.

------------------------------------------------------------
Approach

1. Binary search the answer between

left = 1

right = Maximum quantity.

2. Assume

mid

is the maximum products allowed in one store.

3. Calculate how many stores are needed if
each store can hold at most mid products.

Stores Needed

= ceil(quantity / mid)

4. If required stores <= available stores,

mid is a valid answer.

Try to minimize it.

5. Otherwise,

increase mid.

------------------------------------------------------------
Algorithm

1. Find the maximum value in quantities.

2. Initialize

left = 1

right = maxQuantity

3. While left < right

      mid = (left + right) / 2

      Check whether distribution is possible.

      If possible

            right = mid

      Else

            left = mid + 1

4. Return left.

------------------------------------------------------------
Why It Works

Suppose

Maximum allowed products = X.

If we can distribute all products using X,

then allowing

X+1

X+2

X+3

will also work.

Similarly,

if X cannot distribute,

then

X-1

X-2

also cannot distribute.

Therefore,

the answer space is monotonic.

Binary Search finds the first valid answer.

------------------------------------------------------------
How Stores Needed is Calculated

Suppose

11 products

Maximum per store = 3

Distribution

3

3

3

2

Stores Needed = 4

Formula

ceil(11 / 3)

Instead of using Math.ceil(),

we use

(quantity + maxProducts - 1) / maxProducts

Example

(11 + 3 - 1) / 3

= 13 / 3

= 4

------------------------------------------------------------
Time Complexity

Finding Maximum

O(m)

Binary Search

O(log(maxQuantity))

Checking Distribution

O(m)

Overall

O(m × log(maxQuantity))

where

m = quantities.length

------------------------------------------------------------
Space Complexity

O(1)

------------------------------------------------------------
Functions Used

Math.max()

Finds the maximum quantity.

============================================================
*/


import java.util.*;

public class Main {

    public static int minimizedMaximum(int n, int[] quantities) {

        int left = 1;
        int right = 0;

        for (int quantity : quantities) {
            right = Math.max(right, quantity);
        }

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (canDistribute(n, quantities, mid)) {

                right = mid;

            } else {

                left = mid + 1;
            }
        }

        return left;
    }

    public static boolean canDistribute(int n, int[] quantities, int maxProducts) {

        long storesNeeded = 0;

        for (int quantity : quantities) {

            storesNeeded += (quantity + maxProducts - 1) / maxProducts;

            if (storesNeeded > n) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of stores: ");
        int n = sc.nextInt();

        System.out.print("Enter number of product types: ");
        int m = sc.nextInt();

        int[] quantities = new int[m];

        System.out.println("Enter quantities:");

        for (int i = 0; i < m; i++) {
            quantities[i] = sc.nextInt();
        }

        int ans = minimizedMaximum(n, quantities);

        System.out.println("Minimum Possible Maximum = " + ans);

        sc.close();
    }
}
