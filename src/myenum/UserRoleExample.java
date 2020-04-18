package myenum;

public class UserRoleExample {
    public static void main(String[] args) {
        UserRole role1 = UserRole.ROLE_NORMAL;
        UserRole role2 = UserRole.ROLE_ORDER_ADMIN;
        UserRole role3 = UserRole.ROLE_ROOT_ADMIN;
        //values()方法返回所有枚举常量的数组集合
        System.out.println("values()方法");
        for(UserRole role:UserRole.values()){
            System.out.println(role);
        }
        //ordinal()方法返回枚举常量的序数
        System.out.println("ordinal()方法返回枚举常量的序数");
        System.out.println(role1.ordinal());
        System.out.println(role2.ordinal());
        System.out.println(role3.ordinal());
        //name()方法获得枚举常量的名称
        System.out.println("name()方法");
        System.out.println(role1.name());
        System.out.println(role2.name());
        System.out.println(role3.name());

    }
}
