package myenum;

/**
 * 这样就很明确什么角色可以做什么事情，调用也是非常的方便的
 */
public enum RoleOpretionImpl implements RoleOpretion {

    // 系统管理员(有A操作权限)
    ROLE_ROOT_ADMIN {
        @Override
        public String op() {
            return "ROLE_ROOT_ADMIN:" + " has AAA permission";
        }
    },

    // 订单管理员(有B操作权限)
    ROLE_ORDER_ADMIN {
        @Override
        public String op() {
            return "ROLE_ORDER_ADMIN:" + " has BBB permission";
        }
    },

    // 普通用户(有C操作权限)
    ROLE_NORMAL {
        @Override
        public String op() {
            return "ROLE_NORMAL:" + " has CCC permission";
        }
    };
}
