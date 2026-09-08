/*
    LeetCode 901 - Online Stock Span


    ============================================================
    BRUTE FORCE APPROACH
    ============================================================

    For every incoming price, look backward one day at a time.

    Continue while:

        previousPrice <= currentPrice

    Stop when we find:

        previousPrice > currentPrice


    Example:

        prices = [100, 80, 60, 70, 60, 75, 85]

    For price = 75:

        60 <= 75  -> count
        70 <= 75  -> count
        60 <= 75  -> count
        80 > 75   -> stop

    Therefore:

        span = 4


    If prices are strictly increasing:

        [10, 20, 30, 40, 50]

    then every new price scans almost the entire previous
    array.

    Therefore:

        Time Complexity:
            O(n^2)

        Space Complexity:
            O(n)


    ============================================================
    OPTIMAL APPROACH - MONOTONIC STACK
    ============================================================

    Instead of storing every previous day individually, store:

        (price, span)

    where:

        price = stock price

        span = number of consecutive days represented by this
               stack entry


    The stack maintains prices in decreasing order.


    For every new price:

        span = 1

    because today's price itself always contributes 1 day.


    Then:

        while stack is not empty
              AND stack.top.price <= current price:

            span += stack.pop().span


    Why can we add the complete span?

    Suppose the stack contains:

        (60, 1)

    and the current price is:

        70

    Since:

        60 <= 70

    today's price can absorb that entire entry.

    If an entry is:

        (70, 2)

    then that entry already represents two consecutive days
    whose prices are <= 70.

    If today's price is 75:

        70 <= 75

    so BOTH days represented by (70, 2) are also valid for
    today's span.

    Therefore:

        span += poppedSpan


    Finally push:

        (currentPrice, span)


    ============================================================
    WHY <= AND NOT <
    ============================================================

    The problem counts previous prices that are:

        <= today's price

    Therefore equal prices must also be popped.

    Example:

        prices = [80, 80]

    For the second 80:

        previous 80 <= current 80

    Therefore:

        span = 2


    So the condition must be:

        stack.peek()[0] <= price


    NOT:

        stack.peek()[0] < price


    ============================================================
    DRY RUN
    ============================================================

    Input:

        [100, 80, 60, 70, 60, 75, 85]


    ------------------------------------------------------------
    PRICE = 100
    ------------------------------------------------------------

        span = 1

        Stack is empty.

        Push:

            (100, 1)

        Answer = 1


    ------------------------------------------------------------
    PRICE = 80
    ------------------------------------------------------------

        span = 1

        Top = 100

        100 <= 80 ? NO

        Push:

            (80, 1)

        Answer = 1


    ------------------------------------------------------------
    PRICE = 60
    ------------------------------------------------------------

        span = 1

        Top = 80

        80 <= 60 ? NO

        Push:

            (60, 1)

        Answer = 1


    ------------------------------------------------------------
    PRICE = 70
    ------------------------------------------------------------

        span = 1

        Top = 60

        60 <= 70 -> YES

        Pop (60, 1)

        span = 1 + 1
             = 2


        New top = 80

        80 <= 70 -> NO

        Push:

            (70, 2)

        Answer = 2


    ------------------------------------------------------------
    PRICE = 60
    ------------------------------------------------------------

        span = 1

        Top = 70

        70 <= 60 -> NO

        Push:

            (60, 1)

        Answer = 1


    ------------------------------------------------------------
    PRICE = 75
    ------------------------------------------------------------

        span = 1

        Top = 60

        60 <= 75 -> YES

        Pop (60, 1)

        span = 1 + 1
             = 2


        Top = 70

        70 <= 75 -> YES

        Pop (70, 2)

        span = 2 + 2
             = 4


        Top = 80

        80 <= 75 -> NO

        Push:

            (75, 4)

        Answer = 4


    ------------------------------------------------------------
    PRICE = 85
    ------------------------------------------------------------

        span = 1

        Top = 75

        75 <= 85 -> YES

        Pop (75, 4)

        span = 1 + 4
             = 5


        Top = 80

        80 <= 85 -> YES

        Pop (80, 1)

        span = 5 + 1
             = 6


        Top = 100

        100 <= 85 -> NO

        Push:

            (85, 6)

        Answer = 6


    ============================================================
    FINAL ANSWER
    ============================================================

        [1, 1, 1, 2, 1, 4, 6]


    ============================================================
    COMPLEXITY
    ============================================================

    Brute Force:

        Time:
            O(n^2)

        Space:
            O(n)


    Optimal Monotonic Stack:

        Time:
            O(n) overall

        Space:
            O(n)


    WHY IS TIME O(n) EVEN THOUGH THERE IS A WHILE LOOP?

    Every price:

        1. Is pushed exactly once.
        2. Can be popped at most once.

    Therefore, across all next() calls, the total number of
    stack operations is O(n).

    This is called AMORTIZED O(1) per call.


    ============================================================
    KEY PATTERN TO REMEMBER
    ============================================================

    This is a classic:

        MONOTONIC STACK + SPAN COMPRESSION

    Whenever you see:

        "For the current element, look backward and find how
         many consecutive previous elements satisfy a condition"

    think:

        MONOTONIC STACK


    The special trick in this problem is that we store:

        (value, span)

    instead of just:

        value


    That allows us to skip entire groups of already-processed
    elements instead of checking them individually.

*/
import java.util.*;

public class Main {

    static class StockSpanner {

        private Deque<int[]> stack;

        public StockSpanner() {
            stack = new ArrayDeque<>();
        }

        public int next(int price) {

            int span = 1;

            while (!stack.isEmpty() && stack.peek()[0] <= price) {

                // Add the entire span represented by the
                // popped element.
                span += stack.pop()[1];
            }

            stack.push(new int[]{price, span});

            return span;
        }
    }

    public static void main(String[] args) {

        StockSpanner stockSpanner = new StockSpanner();

        int[] prices = {
            100, 80, 60, 70, 60, 75, 85
        };

        for (int price : prices) {
            System.out.print(stockSpanner.next(price) + " ");
        }
    }
}
