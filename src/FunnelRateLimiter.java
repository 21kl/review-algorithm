import java.util.HashMap;
import java.util.*;

/**
 * 漏斗限流算法
 */
public class FunnelRateLimiter {
    static class Funnel {
        int capacity;
        int leftQuota;
        Float leakingRate;
        long lastTime;

        public Funnel(int capacity, Float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.lastTime = System.currentTimeMillis();
        }

        public void makeSpace() {
            long nowT = System.currentTimeMillis();
            int deltaQuota = (int) (nowT * leakingRate);
            //时间过长导致int溢出
            if (deltaQuota < 0) {
                this.leftQuota = this.capacity;
                this.lastTime = System.currentTimeMillis();
                return;
            }
            //时间太短就直接返回
            if (deltaQuota < 1) {
                return;
            }
            //否则就将流出的都补进去
            this.leftQuota += deltaQuota;
            this.lastTime = System.currentTimeMillis();
            if (this.leftQuota > this.capacity) {
                this.leftQuota = capacity;
            }
        }

        public boolean watering(int quota) {
            makeSpace();
            if (this.leftQuota > quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }

    public static Map<String, Funnel> funnels = new HashMap<>();

    public boolean isActionAllowed(String userid, String actionKey, int capacity, Float leakingRate) {

        String key = String.format("%s:%s", userid, actionKey);

        Funnel funnel = funnels.get(key);
        if (funnel == null) {
            Funnel temp = new Funnel(capacity, leakingRate);
            funnels.put(key, funnel);
            return true;
        }
        return funnel.watering(1);
    }
}
