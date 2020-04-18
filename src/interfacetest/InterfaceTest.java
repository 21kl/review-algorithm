package interfacetest;

/**
 * 主要为了测试java8中接口的默认方法
 */
public interface InterfaceTest {
    void func1();

    default void func2(){
        System.out.println("func2");
    }
}
