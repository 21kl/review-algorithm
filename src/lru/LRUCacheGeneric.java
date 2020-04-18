package lru;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 使用泛型来实现最近最久未使用的缓存算法
 */
public class LRUCacheGeneric<K, V> implements Iterable<K> {
    private Node head;
    private Node tail;
    private HashMap<K, Node> map;
    private int MAX_SIZE = 4;

    private class Node {
        Node pre;
        Node next;
        K k;
        V v;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    public HashMap<K, Node> getMap() {
        return map;
    }

    /**
     * 构造函数对头尾节点和哈希表进行初始化
     */
    public LRUCacheGeneric() {
        this.map = new HashMap<>(MAX_SIZE * 3 / 4);
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 删除链表中的某一个节点,其实就是删除最近最久未使用的那个节点
     *
     * @param node
     */
    private void unlink(Node node) {
        Node pre = node.pre;
        Node next = node.next;

        pre.next = next;
        next.pre = pre;

        node.pre = null;
        node.next = null;
    }

    /**
     * 头节点其实是不干任何事情的，我们需要插入到头节点的后一个节点中去
     * 其实流程就是先将头节点的下一个节点给保存下来，然后将我们需要插入的节点插入到头节点和头节点之后的那个节点之中去
     *
     * @param node
     */
    private void appendHead(Node node) {
        Node next = head.next;
//        head.next = node;
//        node.next = next;
//        next.pre = node;
//        node.pre = head;
        node.next = next;
        next.pre = node;
        node.pre = head;
        head.next = node;
    }

    /**
     * 其实是删除尾节点的前一个节点
     *
     * @return
     */
    private Node removeTail() {
        Node node = tail.pre;
        Node pre = node.pre;

        tail.pre = pre;
        pre.next = tail;

        node.pre = null;
        node.next = null;

        return node;
    }

    /**
     * 获取的过程其实很简单，需要先判断需要获取的是否在缓存中
     * 然后需要删除那个节点然后将该节点放到双向队列的头部
     *
     * @param key
     * @return
     */
    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Node node = map.get(key);
        unlink(node);
        appendHead(node);
        return node.v;
    }

    /**
     * 再原来的情况下加入一个元素
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        //如果包含，先删除那个节点
        if (map.containsKey(key)) {
            Node node = map.get(key);
            unlink(node);
        }
        //再将节点放入hashmap和添加到头部
        Node node = new Node(key, value);
        map.put(key, node);
        appendHead(node);
        //如果大小超过了的话，就需要删除尾部的节点
        if (map.size() > MAX_SIZE) {
            Node removeNode = removeTail();
            map.remove(removeNode);
        }
    }

    /**
     * 其实这个部分可以不需要，但是为了实现迭代器模式，我们也可以加入进来
     *
     * @return
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private Node cur = head.next;

            @Override
            public boolean hasNext() {
                return cur != tail;
            }

            @Override
            public K next() {
                Node node = cur.next;
                cur = cur.next;
                return node.k;
            }
        };
    }
}
