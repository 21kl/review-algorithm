package stack;

import java.util.Stack;

/**
 * 使用两个栈实现一个队列的功能
 */
public class TwoStackAsAQueue {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public int push(Integer node) {
        return stack1.push(node);
    }

    /**
     * 如果pop的栈为空的话，就将push的元素全部放入进去,思想还是很巧妙的
     *
     * @return
     */
    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
