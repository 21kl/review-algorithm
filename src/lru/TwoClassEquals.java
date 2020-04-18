package lru;

/**
 * 主要写一个判断方法看看两个类是否相等
 */
public class TwoClassEquals {
    private int x, y, z;

    /**
     * 首先判断引用是否相同，再判断是否为相同的类型，之后再判断属性值是否相同
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        TwoClassEquals that = (TwoClassEquals) obj;
        if (this.x != that.x) return false;
        if (this.y != that.y) return false;
        return this.z == that.z;
    }
}
