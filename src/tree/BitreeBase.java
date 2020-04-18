package tree;

import javax.swing.text.SimpleAttributeSet;
import java.util.*;

public class BitreeBase {

    private BitreeNode root;

    public BitreeBase() {
    }

    /**
     * 生成一个节点
     *
     * @param val
     * @return
     */
    public BitreeNode generateNodeByString(String val) {
        if ("#".equals(val)) return null;
        return new BitreeNode(val);
    }

    int index = 0;

    public BitreeNode createBitree(BitreeNode root, String str) {
        char ch;
        if (index < str.length()) {
            ch = str.charAt(index);
            index++;
        } else {
            return null;
        }
        if (ch == '#') {
            return null;
        } else {
            root = new BitreeNode();
            root.val = "" + ch;
            root.left = createBitree(root.left, str);
            root.right = createBitree(root.right, str);
        }
        return root;
    }

    /**
     * 前序遍历二叉树
     *
     * @param head
     */
    public static void preTraversal(BitreeNode head) {
        if (null == head) {
            return;
        }
        System.out.print(head.val + " ");
        preTraversal(head.left);
        preTraversal(head.right);
    }

    /**
     * 中序遍历二叉树
     *
     * @param head
     */
    public static void midTraversal(BitreeNode head) {
        if (null == head) {
            return;
        }
        midTraversal(head.left);
        System.out.print(head.val + " ");
        midTraversal(head.right);
    }

    /**
     * 后序遍历二叉树
     *
     * @param head
     */
    public static void postTraversal(BitreeNode head) {
        if (null == head) {
            return;
        }
        preTraversal(head.left);
        preTraversal(head.right);
        System.out.print(head.val + " ");
    }

    /**
     * 非递归版本的前序遍历
     * 用一个栈将元素都存起来，然后遍历，先将右边的节点存起来，再去将左边的节点存起来
     *
     * @param head
     */
    public static void preTraversalNonRecursive(BitreeNode head) {
        if (head == null) {
            return;
        }
        Stack<BitreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            BitreeNode cur = stack.pop();
            System.out.print(cur.val + " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 使用一个栈来保存之前的左边的节点，直到最左边的节点已经为空了，就弹出做处理，然后再去遍历弹出节点右边的节点
     *
     * @param head
     */
    public static void midTraversalNonRecursive(BitreeNode head) {
        if (head == null) {
            return;
        }
        Stack<BitreeNode> stack = new Stack<>();
        while (head != null || !stack.isEmpty()) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.val + " ");
                head = head.right;
            }
        }
    }

    /**
     * 非递归版本的后序遍历,其实原理也是很好考虑的，首先，
     * 我们s1的入栈顺序就是头->左->右，而s1弹出的顺序就是头->右->左，
     * 然后s2是s1弹出的反序，意思就是左->右->头了呗
     *
     * @param head
     */
    public static void postTraversalNonRecursive(BitreeNode head) {
        if (head == null) {
            return;
        }
        Stack<BitreeNode> stack1 = new Stack<>();
        Stack<BitreeNode> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            head = stack1.pop();
            stack2.push(head);
            if (head.left != null) stack1.push(head.left);
            if (head.right != null) stack2.push(head.right);
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }
    }

    /**
     * 利用双向链表实现层序遍历
     *
     * @param head
     */
    public static void levelTraversal(BitreeNode head) {
        LinkedList<BitreeNode> queue = new LinkedList<>();
        queue.addLast(head);
        while (!queue.isEmpty()) {
            BitreeNode temp = queue.removeFirst();
            if (null == temp) {
                continue;
            }
            System.out.print(temp.val + " ");
            if (null != temp.left) {
                queue.addLast(temp.left);
            }
            if (null != temp.right) {
                queue.addLast(temp.right);
            }
        }
    }

    /**
     * 获得树的高度
     *
     * @param head
     * @return
     */
    public static int treeHeight(BitreeNode head) {
        if (null == head) {
            return 0;
        }
        int left = treeHeight(head.left);
        int right = treeHeight(head.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 根据前序和中序构造二叉树
     *
     * @param preOrder
     * @param midOrder
     * @return
     */
    public static BitreeNode buildTreeByPreAndMidOrder(int[] preOrder, int[] midOrder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = preOrder.length;
        for (int i = 0; i < len; i++) {
            map.put(midOrder[i], i);
        }
        return buildTreeByPreAndMidOrderHelper(preOrder, map, 0, len - 1, 0);
    }

    /**
     * 根据前序和中序遍历构造二叉树
     *
     * @param preOrder
     * @param map
     * @param preStart
     * @param preEnd
     * @param midStart
     * @return
     */
    private static BitreeNode buildTreeByPreAndMidOrderHelper(int[] preOrder, HashMap<Integer, Integer> map, int preStart, int preEnd, int midStart) {
        if (preStart > preEnd) {
            return null;
        }
        int rootIndex = map.get(preOrder[preStart]);
        int len = rootIndex - midStart;
        BitreeNode root = new BitreeNode("" + preOrder[preStart]);
        root.left = buildTreeByPreAndMidOrderHelper(preOrder, map, preStart + 1, preStart + len, midStart);
        root.right = buildTreeByPreAndMidOrderHelper(preOrder, map, preStart + len + 1, preEnd, rootIndex + 1);
        return root;
    }

    /**
     * 前序遍历序列化二叉树,其中空节点使用#表示，每个节点使用！来分隔开
     *
     * @param head
     * @return
     */
    public String serialByPre(BitreeNode head) {
        if (head == null) return "#!";
        String res = head.val + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    /**
     * 反序列化二叉树，先序遍历，真正调用的函数
     *
     * @param string
     * @return
     */
    public BitreeNode reconByPreString(String string) {
        String[] values = string.split("!");
        int len = values.length;
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            queue.offer(values[i]);
        }
        return reconPreOrder(queue);
    }

    /**
     * 反序列化二叉树，先序遍历
     *
     * @param queue
     * @return
     */
    public BitreeNode reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if ("#".equals(value)) return null;
        BitreeNode head = new BitreeNode(value);
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    /**
     * 层序遍历序列化二叉树
     *
     * @param head
     * @return
     */
    public String serialByLevel(BitreeNode head) {
        if (head == null) return "#!";
        Queue<BitreeNode> queue = new LinkedList<>();
        String res = head.val + "!";
        queue.offer(head);
        while (!queue.isEmpty()) {
            BitreeNode temp = queue.poll();
            if (temp.left != null) {
                res += temp.left.val + "!";
                queue.offer(temp.left);
            } else {
                res += "#!";
            }

            if (temp.right != null) {
                res += temp.right + "!";
                queue.offer(temp.right);
            } else {
                res += "#!";
            }
        }
        return res;
    }

    /**
     * 层序遍历实现反序列化二叉树
     *
     * @param string
     * @return
     */
    public BitreeNode reconByLevelString(String string) {
        Queue<BitreeNode> queue = new LinkedList<>();
        String[] values = string.split("!");
        int index = 0;
        BitreeNode node = null;
        BitreeNode head = generateNodeByString(values[index++]);
        if (head != null) queue.offer(head);
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;
    }

    /**
     * 层序遍历获取每层元素的个数
     *
     * @param head
     * @return
     */
    public List<Integer> levelNodeByLevelOrder(BitreeNode head) {
        List<Integer> list = new ArrayList<>();
        if (head == null) return list;
        Queue<BitreeNode> queue = new LinkedList<>();
        BitreeNode node = null;
        queue.offer(head);
        list.add(1);
        while (!queue.isEmpty()) {
            int size = queue.size(), count = 0;
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                if (node.left != null) {
                    count++;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    count++;
                    queue.offer(node.right);
                }
            }
            list.add(count);
        }
        return list;
    }

    /**
     * 找出二叉排序树出现错位的两个节点
     *
     * @param head
     * @return
     */
    public BitreeNode[] getTwoErrNode(BitreeNode head) {
        BitreeNode[] errs = new BitreeNode[2];
        if (head == null) return errs;
        Stack<BitreeNode> stack = new Stack<>();
        BitreeNode pre = null;
        while (head != null && !stack.isEmpty()) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (pre != null && Integer.valueOf(pre.val) > Integer.valueOf(head.val)) {
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = head;
                }
                pre = head;
                head = head.right;
            }
        }
        return errs;
    }

    /**
     * 判断tree2是否为tree1的子树
     *
     * @param head1
     * @param head2
     * @return
     */
    public boolean isSubTree(BitreeNode head1, BitreeNode head2) {

        return false;
    }


    /**
     * 找到两个节点最近的公共祖先
     *
     * @param head
     * @param o1
     * @param o2
     * @return
     */
    public BitreeNode lowestAncestor(BitreeNode head, BitreeNode o1, BitreeNode o2) {
        if (head == null || o1 == null || o2 == null) {
            return head;
        }
        BitreeNode left = lowestAncestor(head.left, o1, o2);
        BitreeNode right = lowestAncestor(head.right, o1, o2);
        if (left != null && right != null) return head;
        return left != null ? left : right;
    }

    /**
     * 判断是否为树的子结构，首先我们需要递归遍历主树的每个节点，找到与子树头节点值相同的节点，然后递归遍历看是否为树的子结构
     *
     * @param head1
     * @param head2
     * @return
     */
    public static boolean subTree(BitreeNode head1, BitreeNode head2) {
        boolean result = false;
        if (head1 != null && head2 != null) {
            if (head1.val != head2.val) {
                result = subTreeHelper(head1, head2);
            }
            if (!result) {
                result = subTreeHelper(head1.left, head2);
            }
            if (!result) {
                result = subTreeHelper(head1.right, head2);
            }
        }
        return result;
    }

    /**
     * 用来判断节点值相同的时候是否为子树
     *
     * @param head1
     * @param head2
     * @return
     */
    private static boolean subTreeHelper(BitreeNode head1, BitreeNode head2) {
        //当子树为空的时候，说明已经遍历完成了，可以返回true了
        if (head2 == null) {
            return true;
        }
        //当主树先为空，说明匹配失败
        if (head1 == null) {
            return false;
        }
        //当主树和子树节点值不相同的时候，说明匹配失败
        if (head1.val != head2.val) {
            return false;
        }
        return subTreeHelper(head1.left, head2.left) && subTreeHelper(head1.right, head2.right);
    }

    /**
     * 将一颗二叉树变成他的镜像二叉树,其实这就是一个很完美的递归，左右节点进行交换即可
     *
     * @param head
     */
    public static void mirrorBitree(BitreeNode head) {
        if (head != null) {
            BitreeNode temp = head.left;
            head.left = head.right;
            head.right = temp;
            mirrorBitree(head.left);
            mirrorBitree(head.right);
        }
    }

    /**
     * 非递归实现镜像二叉树
     *
     * @param head
     */
    public static void mirrorBitreeNonRecursive(BitreeNode head) {
        if (head == null) {
            return;
        }
        Stack<BitreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            BitreeNode cur = stack.pop();
            if (cur.left != null || cur.right != null) {
                BitreeNode temp = cur.left;
                cur.left = cur.right;
                cur.right = temp;
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
    }

    private static List<List<Integer>> sumTargetPathResult = new ArrayList<>();
    private static List<Integer> path = new ArrayList<>();

    /**
     * 得到和为某一个数的所有路径,其实仔细想想还是很好理解的，首先，我们需要遍历到叶子节点，
     * 然后还需要满足路径的和为target，记得我们需要将已经经历过的节点都给remove掉
     *
     * @param head
     * @param target
     * @return
     */
    public static List<List<Integer>> sumTargetPath(BitreeNode head, int target) {
        if (head == null) {
            return null;
        }
        findPath(head, target);
        return sumTargetPathResult;
    }

    /**
     * 找路径和的主要方法
     *
     * @param head
     * @param target
     */
    public static void findPath(BitreeNode head, int target) {
        //递归出口
        if (head == null) {
            return;
        }
        //将头节点给添加到path里面去
        path.add(Integer.valueOf(head.val));
        //如果符合条件就将path加入结果中去
        if (head.left == null && head.right == null && target == Integer.valueOf(head.val)) {
            sumTargetPathResult.add(new ArrayList<>(path));
        }
        //左边递归
        if (head.left != null) {
            findPath(head.left, target - Integer.valueOf(head.val));
        }
        //右边递归
        if (head.right != null) {
            findPath(head.right, target - Integer.valueOf(head.val));
        }
        //将遍历过的节点丢出来
        path.remove(path.size() - 1);
    }

    /**
     * 将一个排序二叉树转化成有序的双向链表，其实就是一个中序遍历，我们使用一个pre节点来存储上一次从stack里面弹出来的元素，
     * 然后使用它的右指针指向下一个节点，下一个节点的左边指向前一个节点
     *
     * @param head
     * @return
     */
    public static BitreeNode convertDoublyLinkedList(BitreeNode head) {
        if (head == null) return null;
        Stack<BitreeNode> stack = new Stack<>();
        BitreeNode pre = null, cur = head;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre == null) {
                    pre = head = cur;
                } else {
                    pre.right = cur;
                    cur.left = pre;
                    pre = cur;
                }
                cur = cur.right;
            }
        }
        return head;
    }

    /**
     * 判断一棵树是否为平衡树
     *
     * @param head
     * @return
     */
    public static boolean boolBalanceTree(BitreeNode head) {
        return balanceTreeHelper(head) != -1;
    }

    /**
     * 其实我们只要理解什么是平衡树，然后根据平衡树的特点就能判断了，
     * 这里我们其实就是找出左右子树的高度，如果高度差大于1就返回-1，如果已经是-1了就直接返回就好了
     *
     * @param head
     * @return
     */
    public static int balanceTreeHelper(BitreeNode head) {
        if (head == null) {
            return 0;
        }
        int left = balanceTreeHelper(head.left);
        if (left == -1) {
            return -1;
        }
        int right = balanceTreeHelper(head.right);
        if (right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        BitreeBase base = new BitreeBase();
        BitreeNode node = base.createBitree(base.root, "ABC##DE##F#G");
        BitreeBase.preTraversalNonRecursive(node);
        System.out.println();
        System.out.println("-------------------------------------");
        BitreeBase.midTraversalNonRecursive(node);
        System.out.println();
        System.out.println("-------------------------------------");
        BitreeBase.postTraversalNonRecursive(node);
        System.out.println();
        System.out.println("-------------------------------------");
        BitreeBase.levelTraversal(node);
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("height->" + BitreeBase.treeHeight(node));
        System.out.println("level number-->" + base.levelNodeByLevelOrder(node));
    }
}
