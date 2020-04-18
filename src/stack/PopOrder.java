package stack;

import java.util.Stack;

/**
 * 给定一个入栈序列，在给定一个出栈序列，判断出栈序列是否为真
 */
public class PopOrder {
    public static boolean isPopOrder(int[] pushArray, int[] popArray) {
        if (pushArray == null || popArray == null) {
            return true;
        }
        int index = 0, len = pushArray.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            stack.push(pushArray[i]);
            while (index < len && !stack.isEmpty() && stack.peek() == popArray[index]) {
                index++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] pushArray = {1, 2, 3, 4, 5}, popArray = {4, 5, 3, 2, 1};
        System.out.println(PopOrder.isPopOrder(pushArray, popArray));
    }
}
