package myenum;

/**
 * 自定义类型的枚举类
 */
public enum CustomEnum {
    ROLE_ROOT_ADMIN("系统管理员",100),  // 系统管理员

    ROLE_ORDER_ADMIN("订单管理员",200), // 订单管理员

    ROLE_NORMAL("普通用户",300);     // 普通用户

    private String roleName;
    private Integer roleCode;

    /**
     * 自定义构造函数
     * @param roleName
     * @param roleCode
     */
    CustomEnum(String roleName,Integer roleCode){
        this.roleCode = roleCode;
        this.roleName = roleName;
    }


    //自定义方法

    public String getRoleName() {
        return roleName;
    }

    public Integer getRoleCode() {
        return roleCode;
    }

    /**
     * 通过用户名来获取用户的权限名
     * @param roleName
     * @return
     */
    public static Integer getCodeByRoleName(String roleName){
        for(CustomEnum user: CustomEnum.values()){
            if(user.getRoleName().equals(roleName)){
                return user.getRoleCode();
            }
        }
        return null;
    }
}
