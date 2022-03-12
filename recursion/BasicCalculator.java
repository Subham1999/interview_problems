import java.util.List;
import java.util.Stack;

/**
 * @author subham-santra
 */
public class BasicCalculator {

    class Solution {
        public int calculate(String s) {

        }

        /**
         * @param exp
         * @return
         */
        int eval(List<String> exp) {
            final int len = exp.size();
            Stack<Integer> stack = new Stack<>();
            Stack<Integer> evalStack = new Stack<>();

            for (int i = 0; i < len; ++i) {
                if (exp.get(i).equals("(")) {
                    stack.push(i);
                } else {
                    if (exp.get(i).equals(")")) {
                        int res = eval0(exp, stack.peek() + 1, i - 1);
                        evalStack.push(res);
                    } else {
                        if (isNumber(exp.get(i))) {
                            evalStack.push(Integer.parseInt(exp.get(i)));
                        } else {
                            final int second = evalStack.pop();
                            final int first = evalStack.pop();
                            switch (exp.get(i)) {
                                case "+" -> evalStack.push(first + second);
                                case "-" -> evalStack.push(first - second);
                                case "*" -> evalStack.push(first * second);
                                case "/" -> evalStack.push(first / second);
                            }
                        }
                    }
                }
            }
        }

        private boolean isNumber(String string) {
            int left = 0;
            int right = string.length();
            while (left <= right) {
                if (!Character.isDigit(string.charAt(left))) {
                    return false;
                }
                if (!Character.isDigit(string.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }

        int eval0(char[] exp, int i, int j) {
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
