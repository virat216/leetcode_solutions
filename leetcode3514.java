/*
============================================================
LEETCODE 3514 - Number of Unique XOR Triplets II

Problem

You are given an integer array nums.

Choose any three indices i, j and k
(repetition of indices is allowed).

Compute

nums[i] ^ nums[j] ^ nums[k]

Return the number of distinct XOR values
that can be obtained.

------------------------------------------------------------
Example

Input

nums = [1,2]

Possible XOR Values

1^1^1 = 1

1^1^2 = 2

1^2^2 = 1

2^2^2 = 2

Output

2

------------------------------------------------------------
Intuition

A brute force solution checks every triplet.

Number of triplets

n³

which is too slow.

Observe that

(a ^ b ^ c)

can be written as

(a ^ b) ^ c

So instead of generating every triplet,

1. Compute every possible XOR of two numbers.

2. Store only unique pair XOR values.

3. XOR every pair XOR with every array element.

4. Store all resulting XOR values.

Finally,

the number of stored XOR values
is the answer.

------------------------------------------------------------
Approach

1. Create a HashSet for all pair XOR values.

2. Generate

nums[i] ^ nums[j]

for every pair.

3. Store only unique pair XOR values.

4. Create a BitSet to store triplet XOR values.

5. For every pair XOR,

XOR it with every array element.

6. Count total unique XOR values.

------------------------------------------------------------
Algorithm

1. Initialize

HashSet<Integer> pairXors

BitSet answer

2. Traverse every pair

(i,j)

3. Store

nums[i] ^ nums[j]

4. Traverse every stored pair XOR.

5. XOR it with every array element.

6. Store result in BitSet.

7. Return

answer.cardinality()

------------------------------------------------------------
Dry Run

Input

nums = [1,2,3]

Pair XORs

1^1 = 0

1^2 = 3

1^3 = 2

2^2 = 0

2^3 = 1

3^3 = 0

Unique Pair XORs

{0,1,2,3}

Now XOR with every element

0^1 = 1

0^2 = 2

0^3 = 3

1^1 = 0

1^2 = 3

1^3 = 2

2^1 = 3

2^2 = 0

2^3 = 1

3^1 = 2

3^2 = 1

3^3 = 0

Unique Answers

{0,1,2,3}

Answer

4

------------------------------------------------------------
Why It Works

Every triplet XOR

a ^ b ^ c

can always be viewed as

(a ^ b) ^ c

By precomputing all pair XOR values,

we avoid generating every triplet.

HashSet removes duplicate pair XORs.

BitSet removes duplicate triplet XORs.

Thus,

every unique XOR value is counted exactly once.

------------------------------------------------------------
Time Complexity

O(n²)

Generating pair XOR values takes O(n²).

Generating final XOR values is also bounded by
O(n²).

------------------------------------------------------------
Space Complexity

O(n²)

HashSet stores unique pair XORs.

BitSet stores unique triplet XOR values.

------------------------------------------------------------
Functions Used

HashSet

Stores unique pair XOR values.

BitSet

Stores unique answers efficiently.

add()

Inserts into HashSet.

set()

Marks a value inside BitSet.

cardinality()

Returns number of set bits.

============================================================
*/

import java.util.*;

public class Main {

    public static int uniqueXorTriplets(int[] nums) {

        int n = nums.length;

        if (n == 1) {
            return 1;
        }

        Set<Integer> pairXors = new HashSet<>();
        BitSet tripletXors = new BitSet();

        for (int i = 0; i < n; i++) {

            for (int j = i; j < n; j++) {

                pairXors.add(nums[i] ^ nums[j]);
            }
        }

        for (int pairXor : pairXors) {

            for (int num : nums) {

                tripletXors.set(pairXor ^ num);
            }
        }

        return tripletXors.cardinality();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");

        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter array elements:");

        for (int i = 0; i < n; i++) {

            nums[i] = sc.nextInt();
        }

        int ans = uniqueXorTriplets(nums);

        System.out.println("Number of Unique XOR Triplets = " + ans);

        sc.close();
    }
}
