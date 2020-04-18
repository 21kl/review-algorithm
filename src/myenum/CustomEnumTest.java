package myenum;

/**
 * 对自定义的枚举类进行测试
 */
public class CustomEnumTest {
    public static void main(String[] args) {
        CustomEnum user = CustomEnum.ROLE_NORMAL;
        System.out.println(user);
        System.out.println(user.getRoleCode());
        System.out.println(user.getRoleName());
        System.out.println(CustomEnum.getCodeByRoleName(user.getRoleName()));
    }
}
