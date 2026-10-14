import java.util.Scanner;

public class Main {

    public static String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        int nines = sum / 9;
        int remainder = sum % 9;

        for (int i = 0; i < nines; i++) {
            result.append('9');
        }

        if (remainder > 0) {
            result.append(remainder);
        }

        while (result.length() < num) {
            result.append('0');
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        int sum = sc.nextInt();

        System.out.println(maxSumOfSquares(num, sum));

        sc.close();
    }
}
/*
LeetCode 3723: Maximize Sum of Squares of Digits

BRUTE FORCE:
Generate every num-digit number, check whether its digit
sum equals sum, and select the valid number with the
maximum sum of squared digits.
This is infeasible for large num.

OPTIMAL APPROACH:
Greedy + Math.

1. If sum > 9 * num, return an empty string.
2. Calculate nines = sum / 9.
3. Calculate remainder = sum % 9.
4. Append nines copies of '9'.
5. Append remainder if it is greater than zero.
6. Append zeros until the length equals num.

WHY GREEDY WORKS:
For a fixed digit sum, concentrating the sum into as few
large digits as possible maximizes the sum of squares.
Using as many 9s as possible, then the remainder, then
zeros achieves the maximum score. Placing large digits
first also gives the largest number among optimal answers.

DRY RUN:
num = 4, sum = 20
nines = 2, remainder = 2
Build "99" -> "992" -> "9920"

Digit sum = 20
Score = 9^2 + 9^2 + 2^2 + 0^2 = 166

TIME COMPLEXITY: O(num)
SPACE COMPLEXITY: O(1) auxiliary, O(num) for output.

PATTERN:
Greedy + Math + String Construction.
*/
