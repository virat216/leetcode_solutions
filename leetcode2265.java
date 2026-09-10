/*
    LeetCode 2265 - Count Nodes Equal to Average of Subtree


    ============================================================
    BRUTE FORCE APPROACH
    ============================================================

    For every node:

        1. Traverse its entire subtree.
        2. Calculate the sum.
        3. Calculate the number of nodes.
        4. Calculate the average.
        5. Compare the average with node.val.

    The problem is that the same subtree is traversed repeatedly.

    For a skewed tree:

        1
         \
          2
           \
            3
             \
              4
               \
                5

    For node 1, we visit 5 nodes.

    For node 2, we visit 4 nodes.

    For node 3, we visit 3 nodes.

    Therefore:

        Time Complexity:
            O(n^2) in the worst case

        Space Complexity:
            O(h)

    where h is the height of the tree.


    ============================================================
    OPTIMAL APPROACH - POSTORDER DFS
    ============================================================

    The key observation is that the subtree of a node consists
    of:

              node
             /    \
          left    right

    If the left subtree gives us:

        leftSum
        leftCount

    and the right subtree gives us:

        rightSum
        rightCount

    then we can calculate the current subtree immediately.

    Current subtree sum:

        sum =
            leftSum + rightSum + node.val

    Current subtree count:

        count =
            leftCount + rightCount + 1


    Therefore:

        average = sum / count


    If:

        node.val == average

    then increment the answer.


    We use POSTORDER traversal:

        LEFT
        RIGHT
        ROOT

    because we need the children's subtree information before
    calculating the information for the current node.


    ============================================================
    WHY THIS APPROACH?
    ============================================================

    The brute force approach repeatedly calculates information
    that has already been calculated.

    For example, when calculating the subtree of a parent,
    we would recalculate the same information that we already
    calculated for its child.

    Instead, let every child return:

        [sum, count]

    to its parent.

    Then the parent combines those values in O(1) time.

    This is essentially:

        TREE DP + POSTORDER TRAVERSAL


    ============================================================
    DRY RUN
    ============================================================

    Tree:

                    4
                   / \
                  8   5
                 / \   \
                0   1   6


    Start DFS from root 4.


    ------------------------------------------------------------
    NODE = 0
    ------------------------------------------------------------

    0 has no children.

        sum = 0
        count = 1

        average = 0 / 1
                = 0

    0 == 0

    Therefore:

        answer = 1

    Return:

        [0, 1]


    ------------------------------------------------------------
    NODE = 1
    ------------------------------------------------------------

    1 has no children.

        sum = 1
        count = 1

        average = 1

    1 == 1

    Therefore:

        answer = 2

    Return:

        [1, 1]


    ------------------------------------------------------------
    NODE = 8
    ------------------------------------------------------------

    Left subtree:

        sum = 0
        count = 1

    Right subtree:

        sum = 1
        count = 1

    Current node:

        value = 8

    Therefore:

        sum = 0 + 1 + 8
            = 9

        count = 1 + 1 + 1
              = 3

        average = 9 / 3
                = 3

    Check:

        8 == 3 ? NO

    Return:

        [9, 3]


    ------------------------------------------------------------
    NODE = 6
    ------------------------------------------------------------

    6 has no children.

        sum = 6
        count = 1

        average = 6

    6 == 6

    Therefore:

        answer = 3

    Return:

        [6, 1]


    ------------------------------------------------------------
    NODE = 5
    ------------------------------------------------------------

    Left subtree:

        sum = 0
        count = 0

    Right subtree:

        sum = 6
        count = 1

    Current node:

        value = 5

        sum = 0 + 6 + 5
            = 11

        count = 0 + 1 + 1
              = 2

        average = 11 / 2
                = 5

    Java integer division gives:

        11 / 2 = 5

    Therefore:

        5 == 5

    answer = 4

    Return:

        [11, 2]


    ------------------------------------------------------------
    NODE = 4
    ------------------------------------------------------------

    Left subtree:

        sum = 9
        count = 3

    Right subtree:

        sum = 11
        count = 2

    Current node:

        value = 4

        sum = 9 + 11 + 4
            = 24

        count = 3 + 2 + 1
              = 6

        average = 24 / 6
                = 4

    Therefore:

        4 == 4

    answer = 5


    ============================================================
    FINAL ANSWER
    ============================================================

        5


    The nodes satisfying the condition are:

        0
        1
        6
        5
        4


    ============================================================
    COMPLEXITY
    ============================================================

    Brute Force:

        Time:
            O(n^2) worst case

        Space:
            O(h)


    Optimal Postorder DFS:

        Time:
            O(n)

        Space:
            O(h)

    Every node is visited exactly once.


    ============================================================
    KEY PATTERN TO REMEMBER
    ============================================================

    When a tree problem asks for information about every
    subtree, ask:

        "Can I calculate the parent's answer using information
         returned by its children?"

    If YES:

        Think POSTORDER + TREE DP.


    Here every node returns:

        [subtreeSum, subtreeCount]

    to its parent.


    General template:

        left  = dfs(node.left)
        right = dfs(node.right)

        sum =
            left.sum + right.sum + node.val

        count =
            left.count + right.count + 1


    Then process the current node.


    Final Complexity:

        Time  = O(n)
        Space = O(h)
*/
import java.util.*;

public class Main {

    static int answer = 0;

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int averageOfSubtree(TreeNode root) {

        answer = 0;

        dfs(root);

        return answer;
    }

    private static int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{0, 0};
        }

        // Get sum and count of left subtree
        int[] left = dfs(node.left);

        // Get sum and count of right subtree
        int[] right = dfs(node.right);

        // Calculate current subtree sum
        int sum = left[0] + right[0] + node.val;

        // Calculate current subtree node count
        int count = left[1] + right[1] + 1;

        // Check whether node value equals subtree average
        if (node.val == sum / count) {
            answer++;
        }

        // Return sum and count to parent
        return new int[]{sum, count};
    }

    public static void main(String[] args) {

        /*
                    4
                   / \
                  8   5
                 / \   \
                0   1   6
        */

        TreeNode root = new TreeNode(4);

        root.left = new TreeNode(8);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);

        root.right.right = new TreeNode(6);

        int result = averageOfSubtree(root);

        System.out.println(result);
    }
}
