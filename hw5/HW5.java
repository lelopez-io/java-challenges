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
 *      PART3: Given an expression in postfix notation, use the stack created to
 *      - Check whether the input indeed follows a true postfix
 *      - Evaluate the postfix expression
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
        PrintWriter out = new PrintWriter(args[2]);
        String exp = args[1];
        String cmd = args[0];
        String result = "";

        switch (cmd) {
        case "2":
            result += infixToPostfix(exp);
            break;
        case "3":
            result += (isPostfix(exp) ? evalPostfix(exp) : "nv");
            break;
        default:
            System.out.println("not a valid first argument. Choose 2 or 3");
            System.exit(0);
        }

        out.println(result.trim());
        out.close();
        System.exit(0);

    }

    private static class MyStack {
        private ArrayList<String> stack;
        private int size;

        public MyStack() {
            stack = new ArrayList<String>();
            size = 0;
        }

        // Push
        public void add(String x) {
            this.size++;
            stack.add(x);
        }

        // Pop
        public String pop() {
            this.size--;
            return stack.remove(this.size());
        }

        // Peek
        public String top() {
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

        public boolean takesPrecedenceOver(String x) {
            // false means that the stack doesn't take precedence so the
            // character will be added to the stack

            // An opening bracket and anything after it will always be added.
            if (this.isEmpty() || x.equals("(") || this.top().equals("("))
                return false;
            

            // if none of special cases above are not met then we will compare
            // due to how I set up Infix to Postfis we should neve be comparing
            // brackets at this final step.
            return (this.precedenceLevelOf(x) < this.precedenceLevelOf(this.top()));

        }

        public int precedenceLevelOf(String x) {
            switch (x) {
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
            case "^":
                return 2;
            case "(":
            case ")":
                // this value never gets compared it's just so they do not get
                // swepted up by the default case.
                return 3;
            default:
                return -1;
            }
        }
    }

    private static String infixToPostfix(String exp) {
        MyStack stack = new MyStack();
        String result = "";

        for (int i = 0; i < exp.length(); i++) {
            String temp = String.valueOf(exp.charAt(i));

            // anything that is not our standerd operators will get added
            if (stack.precedenceLevelOf(temp) == -1) {
                result += temp;
                continue;
            }

            if (temp.equals(")")) {
                // if we have a closing bracket we need to pop off the stack
                // until we get to the opening brackets. Then we pop it off too.
                while (!stack.top().equals("(")) {
                    result += stack.pop();
                }
                stack.pop();
            } else if (!stack.takesPrecedenceOver(temp)) {
                // if what ever is on the stack do not take precedence over temp
                stack.add(temp);
            } else {
                // else pop off everything on that stack that takes precedence.
                while (stack.takesPrecedenceOver(temp)) {
                    result += stack.pop();
                }
                if (!stack.isEmpty()) {
                    result += stack.pop();
                }
                // once the stack doesn't take precedence, add the new operator
                stack.add(temp);
            }
        }

        // once we get to the end of our expression we make sure nothing is on
        // the stack by adding any remaining operators to our postfix expression
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    private static boolean isPostfix(String exp) {
        MyStack stack = new MyStack();

        for(int i = 0; i < exp.length(); i++) {
            String temp = String.valueOf(exp.charAt(i));

            if (stack.precedenceLevelOf(temp) == -1) {
                stack.add(temp);
            } else if (stack.size() >= 2) {
                stack.pop();
            } else {
                return false;
            }

        }

        return true;
    }

    public static String evalPostfix(String exp) {
        MyStack stack = new MyStack();
        Float tempA;
        Float tempB;
        Float tempFloat;

        for (int i = 0; i < exp.length(); i++) {
            String temp = String.valueOf(exp.charAt(i));

            if (stack.precedenceLevelOf(temp) == -1) {
                stack.add(temp);
                continue;
            } else {

                switch (temp) {
                    case "+":
                        tempB = Float.valueOf(stack.pop());
                        tempA = Float.valueOf(stack.pop());
                        tempFloat = tempA + tempB;
                        break;
                    case "-":
                        tempB = Float.valueOf(stack.pop());
                        tempA = Float.valueOf(stack.pop());
                        tempFloat = tempA - tempB;
                        break;
                    case "*":
                        tempB = Float.valueOf(stack.pop());
                        tempA = Float.valueOf(stack.pop());
                        tempFloat = tempA * tempB;
                        break;
                    case "/":
                        tempB = Float.valueOf(stack.pop());
                        tempA = Float.valueOf(stack.pop());
                        tempFloat = tempA / tempB;
                        break;
                    default :
                        tempB = Float.valueOf(stack.pop());
                        tempA = Float.valueOf(stack.pop());
                        tempFloat = (float) Math.pow(tempA, tempB);
                        break;
                }

                stack.add(Float.toString(tempFloat));

            }

        }

        float result = Float.valueOf(stack.pop());

        return String.format("%.1f", result);
    }
}
