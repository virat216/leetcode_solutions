/*
    LeetCode 134 - Gas Station


    ============================================================
    BRUTE FORCE APPROACH
    ============================================================

    Try every station as a possible starting point.

    For each start:

        tank = 0

        Traverse all n stations.

        At every station:

            tank += gas[i]
            tank -= cost[i]

        If tank becomes negative:

            this starting point fails.

        If we complete all n stations:

            return start


    There can be n possible starting points.

    Each starting point can require O(n) traversal.

    Therefore:

        Time Complexity:
            O(n^2)

        Space Complexity:
            O(1)


    ============================================================
    OPTIMAL APPROACH - GREEDY
    ============================================================

    Maintain:

        totalGas
        totalCost
        tank
        start


    First important observation:

        If totalGas < totalCost

    then it is impossible to complete the complete circuit.

    Therefore:

        return -1


    Second and most important observation:

    Suppose we start from station 'start' and eventually
    reach station i where:

        tank < 0

    This means:

        start -> ... -> i

    cannot be a valid journey.

    But we can also eliminate every station between start
    and i as a possible starting point.

    Why?

    Suppose we tried a station j between start and i.

    The gas accumulated from j to i would be less than or
    equal to what we accumulated from start to i.

    Since the journey already failed with start:

        accumulated gas < 0

    starting later cannot magically produce enough gas to
    overcome this failure.

    Therefore, all these candidates can be skipped.

    So when:

        tank < 0

    we simply do:

        start = i + 1
        tank = 0


    ============================================================
    DRY RUN
    ============================================================

    Input:

        gas  = [1, 2, 3, 4, 5]
        cost = [3, 4, 5, 1, 2]


    Calculate the net gain at every station:

        gas[i] - cost[i]

        -2  -2  -2  +3  +3


    Initially:

        start = 0
        tank = 0


    ------------------------------------------------------------
    i = 0
    ------------------------------------------------------------

        tank += gas[0] - cost[0]

        tank = 0 + 1 - 3
             = -2

        tank < 0

    Therefore station 0 cannot be the answer.

    Set:

        start = 1
        tank = 0


    ------------------------------------------------------------
    i = 1
    ------------------------------------------------------------

        tank = 0 + 2 - 4
             = -2

        tank < 0

    Station 1 also cannot work.

    Set:

        start = 2
        tank = 0


    ------------------------------------------------------------
    i = 2
    ------------------------------------------------------------

        tank = 0 + 3 - 5
             = -2

        tank < 0

    Station 2 cannot work.

    Set:

        start = 3
        tank = 0


    ------------------------------------------------------------
    i = 3
    ------------------------------------------------------------

        tank = 0 + 4 - 1
             = 3

        tank >= 0

    Continue.


    ------------------------------------------------------------
    i = 4
    ------------------------------------------------------------

        tank = 3 + 5 - 2
             = 6

        tank >= 0

    Continue.


    ------------------------------------------------------------
    Now consider the circular part
    ------------------------------------------------------------

    From station 4 we go to station 0.

        tank = 6 + 1 - 3
             = 4


    Then station 1:

        tank = 4 + 2 - 4
             = 2


    Then station 2:

        tank = 2 + 3 - 5
             = 0


    We successfully complete the circuit.

    Therefore:

        answer = 3


    ============================================================
    WHY DOES THE GREEDY APPROACH WORK?
    ============================================================

    Whenever tank becomes negative at i, we know that the
    current start cannot reach i+1.

    Every station between the current start and i also cannot
    be a valid starting point.

    Therefore we safely jump:

        start = i + 1

    instead of testing all those stations individually.


    ============================================================
    COMPLEXITY
    ============================================================

    Brute Force:

        Time:
            O(n^2)

        Space:
            O(1)


    Optimal Greedy:

        Time:
            O(n)

        Space:
            O(1)


    Every station is visited exactly once.


    ============================================================
    KEY PATTERN TO REMEMBER
    ============================================================

    This is a classic GREEDY problem.

    Remember these two rules:

    1. If:

           totalGas < totalCost

       answer = -1


    2. If:

           tank < 0

       then:

           start = i + 1
           tank = 0


    The most important insight is:

        "If I cannot reach the next station from start,
         none of the stations I passed since start can
         be a valid starting point."


    This lets us eliminate multiple candidates at once.

*/
import java.util.*;

public class Main {

    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int totalGas = 0;
        int totalCost = 0;

        int tank = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {

            totalGas += gas[i];
            totalCost += cost[i];

            tank += gas[i] - cost[i];

            if (tank < 0) {

                // Current start and every station between
                // start and i cannot be a valid starting point.
                start = i + 1;

                // Start fresh from the next station.
                tank = 0;
            }
        }

        // If total gas is less than total cost,
        // no starting point can complete the circuit.
        if (totalGas < totalCost) {
            return -1;
        }

        return start;
    }

    public static void main(String[] args) {

        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};

        int answer = canCompleteCircuit(gas, cost);

        System.out.println(answer);
    }
}
