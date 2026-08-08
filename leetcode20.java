/*
============================================================
LEETCODE 20 - VALID PARENTHESES

Problem

Given a string s containing only:

'('  ')'
'{'  '}'
'['  ']'

determine whether the string is valid.

A string is valid if:

1. Every opening bracket has a corresponding
   closing bracket.

2. Brackets are closed in the correct order.

3. Every closing bracket matches the most
   recent unmatched opening bracket.

------------------------------------------------------------
Example 1

Input:

s = "()"

Output:

true

------------------------------------------------------------
Example 2

Input:

s = "()[]{}"

Output:

true

------------------------------------------------------------
Example 3

Input:

s = "(]"

Output:

false

------------------------------------------------------------
Example 4

Input:

s = "([{}])"

Output:

true

------------------------------------------------------------
INTUITION

This problem follows the LIFO pattern.

The last opening bracket we encounter
must be the first bracket that gets closed.

For example:

([{}])

Opening brackets:

(
(
[
{
 
The last opening bracket is '{'.

Therefore, the first closing bracket
must be '}'.

This is exactly how a Stack works.

------------------------------------------------------------
APPROACH

Use a Stack.

When we encounter an opening bracket:

(
{
[

push it into the stack.

When we encounter a closing bracket:

)
}
]

we check the top of the stack.

The top must contain its matching
opening bracket.

------------------------------------------------------------
CASE 1 - OPENING BRACKET

If the character is:

(
{
[

push it.

Example:

s = "({"

Stack:

[
(
{

------------------------------------------------------------
CASE 2 - CLOSING BRACKET

Suppose we encounter:

}

The top of the stack must be:

{

If it is not,

the string is invalid.

------------------------------------------------------------
DRY RUN

Input:

s = "({[]})"

------------------------------------------------------------
Character '('

Opening bracket.

Push it.

Stack:

[(]

Actually:

(
 
------------------------------------------------------------
Character '{'

Push it.

Stack:

(
{

------------------------------------------------------------
Character '['

Push it.

Stack:

(
{
[

------------------------------------------------------------
Character ']'

Top is '['.

They match.

Pop '['.

Stack:

(
{

------------------------------------------------------------
Character '}'

Top is '{'.

They match.

Pop '{'.

Stack:

(

------------------------------------------------------------
Character ')'

Top is '('.

They match.

Pop '('.

Stack:

empty

------------------------------------------------------------
End of string

Stack is empty.

Therefore:

true

------------------------------------------------------------
INVALID EXAMPLE

Input:

s = "([)]"

------------------------------------------------------------
'('

Push.

Stack:

(

------------------------------------------------------------
'['

Push.

Stack:

(
[

------------------------------------------------------------
')'

Top is '['.

But ')' should match '('.

Mismatch.

Therefore:

false

------------------------------------------------------------
IMPORTANT EDGE CASE

Suppose:

s = "]"

There is no opening bracket.

Stack is empty.

Therefore:

return false.

------------------------------------------------------------
ANOTHER EDGE CASE

Suppose:

s = "((("

All characters are opening brackets.

Stack:

(
(
(

At the end, the stack is not empty.

Therefore:

return false.

------------------------------------------------------------
WHY DO WE CHECK stack.isEmpty()?

Consider:

s = "()[]"

Every opening bracket gets matched.

At the end:

Stack = empty

Therefore the string is valid.

But:

s = "(( )"

contains an unmatched '('.

Stack is not empty.

Therefore it is invalid.

------------------------------------------------------------
ALGORITHM

1. Create a Stack<Character>.

2. Traverse every character of s.

3. If it is an opening bracket:

   push it.

4. Otherwise:

   If stack is empty:

       return false.

   Pop the top.

5. Check whether the popped opening bracket
   matches the current closing bracket.

6. If it doesn't match:

   return false.

7. After processing the entire string:

   return stack.isEmpty().

------------------------------------------------------------
TIME COMPLEXITY

O(n)

Every character is processed exactly once.

------------------------------------------------------------
SPACE COMPLEXITY

O(n)

In the worst case, the string contains
only opening brackets.

------------------------------------------------------------
KEY CONCEPTS

✓ Stack
✓ LIFO
✓ Matching Pairs
✓ Bracket Validation
✓ String Traversal

------------------------------------------------------------
PATTERN

Whenever a problem asks about:

"matching brackets"

"nested brackets"

"balanced parentheses"

think:

Stack

============================================================
*/
import java.util.*;

public class Main {

    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            if (ch == '(' || ch == '{' || ch == '[') {

                stack.push(ch);

            } else {

                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {

                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter parentheses string: ");

        String s = sc.nextLine();

        boolean result = isValid(s);

        System.out.println("Result: " + result);

        sc.close();
    }
}


