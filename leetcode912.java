/*
============================================================
LEETCODE 912 - Sort an Array

Problem

Given an integer array nums,

sort the array in ascending order
and return it.

You must solve the problem with an
algorithm having O(n log n) time complexity.

------------------------------------------------------------
Example

Input

nums = [5,2,3,1]

Output

[1,2,3,5]

------------------------------------------------------------
Intuition

Sorting can be done using many algorithms.

Merge Sort is one of the best choices because

• It always runs in O(n log n) time.

• It follows the Divide and Conquer technique.

The main idea is

1. Divide the array into two halves.

2. Sort each half recursively.

3. Merge the two sorted halves.

------------------------------------------------------------
Approach

1. Find the middle index.

2. Recursively sort the left half.

3. Recursively sort the right half.

4. Merge the two sorted halves.

The merge step compares the smallest
remaining element from both halves
and places the smaller one into
the original array.

------------------------------------------------------------
Algorithm

mergeSort(left, right)

1. If left >= right

return.

2. Find middle index.

3. Sort left half.

4. Sort right half.

5. Merge both halves.

Merge Function

1. Copy left half into temporary array.

2. Copy right half into temporary array.

3. Compare elements one by one.

4. Place smaller element into original array.

5. Copy remaining elements.

------------------------------------------------------------
Dry Run

Input

[5,2,3,1]

Divide

          [5,2,3,1]
          /       \
      [5,2]      [3,1]
      /   \      /   \
    [5] [2]   [3] [1]

Merge

[5] + [2]

↓

[2,5]

Merge

[3] + [1]

↓

[1,3]

Final Merge

[2,5] + [1,3]

↓

Compare

2 and 1

Take 1

Array

[1]

Compare

2 and 3

Take 2

Array

[1,2]

Compare

5 and 3

Take 3

Array

[1,2,3]

Remaining

5

Final

[1,2,3,5]

------------------------------------------------------------
Why It Works

Each recursive call sorts a smaller
portion of the array.

Eventually,

every subarray contains only one element,
which is already sorted.

During merging,

two sorted arrays are combined into
one larger sorted array.

Repeating this process finally
sorts the complete array.

------------------------------------------------------------
Time Complexity

Best Case

O(n log n)

Average Case

O(n log n)

Worst Case

O(n log n)

------------------------------------------------------------
Space Complexity

O(n)

Temporary arrays are used while merging.

------------------------------------------------------------
Functions Used

mergeSort()

Recursively divides the array.

merge()

Combines two sorted arrays.

Arrays

Temporary arrays store left and right halves.

============================================================
*/



import java.util.*;

public class Main {

    public static void mergeSort(int[] nums, int left, int right) {

        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(nums, left, mid);

        mergeSort(nums, mid + 1, right);

        merge(nums, left, mid, right);
    }

    public static void merge(int[] nums, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = nums[left + i];
        }

        for (int j = 0; j < n2; j++) {
            rightArray[j] = nums[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {

            if (leftArray[i] <= rightArray[j]) {
                nums[k++] = leftArray[i++];
            } else {
                nums[k++] = rightArray[j++];
            }
        }

        while (i < n1) {
            nums[k++] = leftArray[i++];
        }

        while (j < n2) {
            nums[k++] = rightArray[j++];
        }
    }

    public static int[] sortArray(int[] nums) {

        mergeSort(nums, 0, nums.length - 1);

        return nums;
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

        sortArray(nums);

        System.out.println("\nSorted Array:");

        for (int num : nums) {
            System.out.print(num + " ");
        }

        sc.close();
    }
}
