package list;

import com.sun.org.apache.bcel.internal.generic.LNEG;

import java.awt.image.DataBufferDouble;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ListBase {
    //单链表的节点
    static class ListNode {
        private ListNode next;
        private int val;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    //双向链表的节点
    static class DoublyListNode {
        private int val;
        private DoublyListNode prev;
        private DoublyListNode next;

        public DoublyListNode() {
        }

        public DoublyListNode(int val) {
            this.val = val;
            this.prev = new DoublyListNode();
            this.next = new DoublyListNode();
        }
    }

    /**
     * 生成一条长度为n的链表
     *
     * @param n
     * @return
     */
    public ListNode createNNodeList(int n) {
        ListNode temp = new ListNode();
        ListNode head = null;
        for (int i = 0; i < n; i++) {
            ListNode newNode = new ListNode(i);
            if (null == head) {
                head = temp = newNode;
            } else {
                temp.next = newNode;
                temp = newNode;
            }
        }
        return head;
    }

    /**
     * 传入一个数组生成一条链表
     *
     * @param array
     * @return
     */
    public static ListNode createNNodeList(int[] array) {
        if (null == array) {
            return null;
        }
        int n = array.length;
        ListNode head = null, cur = null;
        for (int i = 0; i < n; i++) {
            ListNode temp = new ListNode(array[i]);
            if (null == head) {
                head = cur = temp;
            } else {
                cur.next = temp;
                cur = temp;
            }
        }
        return head;
    }

    /**
     * 打印链表
     *
     * @param head
     */
    public static void dispListNode(ListNode head) {
        ListNode temp = head;
        while (null != temp) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * 合并两个有序链表，非递归的做法
     *
     * @param head1
     * @param head2
     * @return
     */
    public ListNode megerListNonRecursive(ListNode head1, ListNode head2) {
        if (null == head1) {
            return head2;
        } else if (null == head2) {
            return head1;
        }
        ListNode pNode = null, result = null;
        while (null != head1 && null != head2) {
            if (head1.val <= head2.val) {
                if (null == result) {
                    pNode = result = head1;
                } else {
                    pNode.next = head1;
                    pNode = pNode.next;
                }
                head1 = head1.next;
            } else {
                if (null == result) {
                    result = pNode = head2;
                } else {
                    pNode.next = head2;
                    pNode = pNode.next;
                }
                head2 = head2.next;
            }
        }
        if (null != head1) {
            pNode.next = head1;
        } else if (null != head2) {
            pNode.next = head2;
        }
        return result;
    }

    /**
     * 使用递归版本的合并两个链表
     *
     * @param head1
     * @param head2
     * @return
     */
    public ListNode megerListRecursive(ListNode head1, ListNode head2) {
        if (null == head1) {
            return head2;
        }
        if (null == head2) {
            return head1;
        }
        if (head1.val <= head2.val) {
            head1.next = megerListRecursive(head1.next, head2);
            return head1;
        } else {
            head2.next = megerListRecursive(head1, head2.next);
            return head2;
        }
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public static ListNode reseverList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode cur = head, pre = null, res = null, temp = null;
        while (null != cur) {
            temp = cur.next;
            if (null == temp) {
                res = cur;
            }
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return res;
    }

    /**
     * k组链表进行反转
     * @param head
     * @param k
     * @return
     */
    public static ListNode reseverListByKGroup(ListNode head,int k){
        if(head == null)return null;
        ListNode cur = head,kth = head;
        for (int i = 0; i < k; i++) {
            //没到k组但是链表已经到头了则直接返回
            if(kth == null){
                return head;
            }
            kth = kth.next;
        }
//        cur = kth.next;
//        kth.next = null;
        ListNode newHead = reseverListByRange(cur,kth);
        kth.next = reseverListByKGroup(kth,k);
        return newHead;
    }

    /**
     * 反转从start到end之间的链表
     * @param start
     * @param end
     * @return
     */
    public static ListNode reseverListByRange(ListNode start,ListNode end){
        ListNode cur = start,pre = null, temp = null,res = null;
        while (cur != end){
            temp = cur.next;
            if (temp == null)res = temp;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return start;
    }
    /**
     * 两个链表的公共节点
     *
     * @param head1
     * @param head2
     * @return
     */
    public ListNode sameListNode(ListNode head1, ListNode head2) {
        //假设他们之间的公共部分是a的长度，然后非公共部分一个为b，一个为c，那么，只要一起走过a+b+c的路程就肯定是第一个节点
        ListNode p1 = head1, p2 = head2;
        while (p1 != p2) {
            p1 = (p1 == null) ? head2 : p1.next;
            p2 = (p2 == null) ? head1 : p2.next;
        }
        return p1;
    }

    /**
     * 返沪倒数第k个节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode findKThNode(ListNode head, int k) {
        //判断边界条件
        if (k < 0 || null == head) {
            return null;
        }
        ListNode cur = head, res = head;
        //先让当前节点向前走k个节点，之后在一直走到末尾即可得到倒数第k个节点
        for (int i = 0; i < k; i++) {
            if (null == cur) {
                return null;
            }
            cur = cur.next;
        }
        while (null != cur) {
            cur = cur.next;
            res = res.next;
        }
        return res;
    }

    /**
     * 含有环的链表中环的入口节点
     *
     * @param head
     * @return
     */
    public ListNode entryNodeOfLoopList(ListNode head) {

        //使用快慢指针来做，首先，使用一个节点速度为1，另外一个节点的速度为2，那么，如果有环他们肯定在环的某一个点会相遇
        //其次，可以通过证明推导得知，从快慢指针相遇点到环入口和从开始节点到环入口肯定是会相遇的
        ListNode fast = head, low = head;
        while (null != fast && null != fast.next) {
            fast = fast.next.next;
            low = low.next;
            if (low == fast) {
                break;
            }
        }
        low = head;
        while (low != fast) {
            low = low.next;
            fast = fast.next;
        }
        return low;
    }

    /**
     * 删除链表中重复的连续的元素
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicate(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode pHead = new ListNode(-1);
        ListNode pre = pHead, cur = pHead.next;
        while (null != cur) {
            if (null != cur.next && cur.val == cur.next.val) {
                while (null != cur.next && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }
        return pHead.next;
    }

    /**
     * 对一个链表进行排序，时间复杂度为O(nlogn)，可以来分析一下，其实，满足时间复杂度的只有三种排序，那就是快排，堆排和归并排序，但是，
     * 快速排序是基于交换的，需要大量的移动节点的操作，而堆排利用的是数组和满二叉树的特性，更加不适合二，所以，留给我们的就只剩下归并排序了
     * 但是如何来进行归并排序呢，归并其实就是分治的思想，先将一个问题分解为一个一个ied小问题，分而治之，最后将已经解决的小问题进行合并即可得到所求的解。
     * 那么，我们的排序也可以分为两步，那就只需要解决两个问题，分如何找到中间的节点，这里我们采用快慢指针来实现，其次就是治，那就更加简单了，直接合并就完事了
     * @param head
     * @return
     */
    public static ListNode mergeSort(ListNode head){
        if(head==null||head.next==null)return head;
        ListNode slow = head,fast = head.next;
        //使用快慢指针得到中间的节点
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode last = slow.next;
        slow.next = null;
        ListNode first = mergeSort(head);
        ListNode second = mergeSort(last);
        ListNode res = megerList(first,second);
        return res;
    }

    /**
     * 递归合并两个排序链表
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode megerList(ListNode head1,ListNode head2){
        if(head1==null)return head2;
        if(head2==null)return head1;
        if(head1.val<head2.val){
            head1.next = megerList(head1.next,head2);
            return head1;
        }else {
            head2.next = megerList(head1,head2.next);
            return head2;
        }
    }
    //方便测试
    public static void main(String[] args) {
        ListNode head = new ListNode();
        int[] array = {4,2,1,3,9,6,2};
        head = ListBase.createNNodeList(array);
        ListBase.dispListNode(head);
        head = ListBase.mergeSort(head);
        ListBase.dispListNode(head);
        head = ListBase.reseverListByKGroup(head,2);
        ListBase.dispListNode(head);
    }
}
