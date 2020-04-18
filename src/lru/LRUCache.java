package lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 利用linkedHashMap来实现最近最久未使用的缓存
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final static int MAX_ENTRIE = 3;

    LRUCache() {
        super(MAX_ENTRIE, 0.75f, true);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_ENTRIE;
    }

}
