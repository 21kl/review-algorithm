package lru;

import java.util.Iterator;

/**
 * other包下的一个测试类
 */
public class OtherMain {
    public static void main(String[] args) {
//        LRUCacheGeneric<Integer,Integer> cacheGeneric = new LRUCacheGeneric<>();
        LRU<Integer,Integer> cacheGeneric = new LRU<>(3);
        cacheGeneric.put(1,1);
        cacheGeneric.put(2,2);
        cacheGeneric.put(3,3);
        System.out.println("init display");
        displayIterator(cacheGeneric);
        System.out.println(cacheGeneric.displayList());
        cacheGeneric.get(1);
        System.out.println("get 1 display");
        displayIterator(cacheGeneric);
        System.out.println(cacheGeneric.displayList());
        cacheGeneric.put(4,4);
        System.out.println("put 4 display");
        displayIterator(cacheGeneric);
        System.out.println(cacheGeneric.displayList());
//        System.out.println(cacheGeneric.getMap());
    }
    public static void displayIterator(LRU<Integer,Integer> map){
        Iterator<Integer> it = map.iterator();
        while (it.hasNext()){
            System.out.print(it.next()+" ");
        }
        System.out.println();
    }
}
