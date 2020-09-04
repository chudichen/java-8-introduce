package com.jdk8.introduce.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Java Lambda表达式的一个重要用法就是简化某些匿名内部类（Anonymous Class）的写法,实际上
 * Lambda表达式并不仅仅是匿名内部类的语法糖，JVM内部是通过invokedynamic指令来实现Lambda
 * 表达式的。
 *
 * @author chudichen
 * @date 2020-09-04
 */
public class AnonymousClassTest {

    /**
     * 普通的匿名内部类实现方式
     */
    @Test
    public void normalThreadTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread run");
            }
        }).start();
    }

    /**
     * lambda匿名内部类的实现
     *
     * 给Thread类传递一个匿名Runnable对象，重载Runnable接口的run()方法，来实现相应逻辑。
     * 之前的JDK7传递匿名内部类省去了给类起名字的烦恼，但是还不够简化，JDK8的lambda可以省去
     * 为类起名字和调用的方法名。
     */
    @Test
    public void lambdaThreadSingleLineTest() {
        new Thread(() -> System.out.println("Thread run")).start();
    }

    /**
     * lambda多行需要带上{}
     */
    @Test
    public void lambdaThreadMultiLinesTest() {
        new Thread(() -> {
            System.out.println("One line");
            System.out.println("Two line");
        }).start();
    }

    /**
     * 普通的按照字符串长度方法
     *
     */
    @Test
    public void normalCompareTest() {
        List<String> list = Arrays.asList("lambda", "introduce");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1 == null) {
                    return -1;
                }
                if (s2 == null) {
                    return 1;
                }
                return s1.length() - s2.length();
            }
        });
        System.out.println(list);
    }

    /**
     * 除了省略接口名和方法名，代码中把参数表的类型也省略了。这得益于javac的类型腿短机制，
     * 编译器可以根据上下文来腿短出参数的类型，当然也可能会出现腿短失败的时候，这时候就需要
     * 手动指明参数类型了。
     */
    @Test
    public void lambdaCompareTest() {
        List<String> list = Arrays.asList("lambda", "introduce");
        Collections.sort(list, (s1, s2) -> {
            if (s1 == null) {
                return -1;
            }
            if (s2 == null) {
                return 1;
            }
            return s1.length() - s2.length();
        });
        System.out.println(list);
    }


}
