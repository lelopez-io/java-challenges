
///////////////////////////////// [Homework 5] /////////////////////////////////
/*  REQUIREMENTS: 
 *      PART1: Create a class stack
 *      - top(): returns the element at the top of the stack without removal.
 *      - pop(): remove the element at the top of the stack and returns it.
 *      - add(): add on element to the top of the stack.
 *      - size(): returns the size of the stack.
 *      [Only the last element of the stack is accessible, 
 *          ex. if all items are to be printed they must all be popped off]
 * 
 *      PART2: Write a function that converts an Expression in infix to postfix 
 *          format. Should support the following operations +,-,*,/, and ^ with
 *          parentheses and follow the ording of BODMAS.
 * 
 *  INPUT: 
 *      - Program should take three arguments
 *          1. Part Number
 *          2. Expression (as a string with no spaces)
 *          3. Output File Name
 * 
 *  OUTPUT: 
 *      - A file that contains the postfix of the expression if valid
 *          otherwise file contains "nv"
 *      
 * */
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

public class HW5 {
    public static void main(String[] args) throws IOException {

        String exp = "(2+3)^4*5";
        String result = infixToPostfix(exp);

        System.out.println(result);

    }

    private static class MyStack {
        private ArrayList<Character> stack;

        private int size;

        public MyStack() {
            stack = new ArrayList<Character>();
            size = 0;
        }

        // Push
        public void add(char x) {
            stack.add(x);
            this.size++;
        }

        // Pop
        public char pop() {
            this.size--;
            return stack.remove(this.size());
        }

        // Peek
        public char top() {
            return stack.get(this.size() - 1);
        }

        // Get Size
        public int size() {
            return size;
        }

        // Check if Empty
        public boolean isEmpty() {
            return (this.size() == 0) ? true : false;
        }

        public boolean takesPrec(char x) {
            if (this.isEmpty())
                return false;
            if (x == '(')
                return false;

            char last = this.top();
            return (this.precedenceLevel(x) < this.precedenceLevel(last));
        }

        public int precedenceLevel(char x) {
            switch (x) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
            case '^':
                return 2;
            case '(':
            case ')':
                return -2;
            default:
                return -1;
            }
        }
    }

    private static String infixToPostfix(String exp) {
        String result = "";
        MyStack stack = new MyStack();

        for (int i = 0; i < exp.length(); i++) {
            char temp = exp.charAt(i);

            if (stack.precedenceLevel(temp) == -1) {
                result += temp;
                continue;
            }

            if (temp == ')') {
                while (stack.top() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            } else if (!stack.takesPrec(temp)) {
                stack.add(temp);
            } else {
                while (stack.takesPrec(temp)) {
                    result += stack.pop();
                }
                stack.add(temp);
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}
