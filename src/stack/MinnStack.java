package stack;

import javafx.scene.chart.StackedAreaChart;

import java.util.Stack;

/**
 * 返回栈中的最小值，要求时间复杂度为O(1)
 */
public class MinnStack {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    /**
     * 原理很简单，当辅助栈为空或者辅助栈的栈顶元素比进入的元素大的上海就需要将进栈的元素放入辅助栈当中去
     *
     * @param node
     */
    public void push(int node) {
        stack1.push(node);
        if (stack2.isEmpty()) {
            stack2.push(node);
        } else if (stack2.peek() <= node) {
            stack2.push(node);
        }
    }

    /**
     * 出栈的话，当两个栈的栈顶元素相同的时候需要将辅助栈中的元素也弹出来
     */
    public void pop() {
        if (stack1.peek() == stack2.peek()) {
            stack2.pop();
        }
        stack1.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int min() {
        return stack2.peek();
    }
}
