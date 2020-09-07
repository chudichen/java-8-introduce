package com.jdk8.introduce.lambda;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * 为了引入Lambda表达式，Java8引入了{@link java.util.function}包，里面包含了常用的接口函数，
 * 这是Lambda表达式的基础，Java集合框架也新增部分接口，以便与Lambda表达式对接。
 *
 * Java8为容器新增了一些有用的方法，这些方法有些是为了完善原有功能，有些是为了引入函数式编程，学习
 * 和使用这些方法有助于我们写出更加简介有效的代码。
 *
 * 函数接口虽然很多，但绝大多数情况下我们不需要知道它们的名字，编写lambda表达式的时候，类型腿短已经
 * 帮助我们完成了。
 *
 * @author chudichen
 * @date 2020-09-04
 */
public class CollectionTest {

    /*
     * ----------------------- foreach() 的用法 -----------------------
     */

    /**
     * 普通的{@link java.util.ArrayList}foreach实现
     */
    @Test
    public void normalListForeachTest() {
        List<String> list = Arrays.asList("lambda", "introduce");
        for (String str : list) {
            System.out.println(str);
        }
    }

    /**
     * 匿名内部类的foreach实现
     */
    @Test
    public void anonymousClassForeachTest() {
        List<String> list = Arrays.asList("lambda", "introduce");
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

    /**
     * 传入一个lambda表达式给foreach()方法，我们不需要知道调用的{@link java.util.function.Consumer}
     * 类以及{@code accept()}方法，一切交给类型推导
     */
    @Test
    public void lambdaForeachTest() {
        List<String> list = Arrays.asList("lambda", "introduce");
        list.forEach(s -> System.out.println(s));
    }

    /*
     * ----------------------- removeIf() 的用法 -----------------------
     */

    /**
     * 删除掉容器中所有满足filter指定条件的元素
     *
     */
    @Test
    public void normalRemoveIfTest() {
        List<String> list = new ArrayList<>(Arrays.asList("lambda", "apple", "juice", "rabbit", "app"));
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().length() > 3) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    /**
     * 匿名内部类的removeIf()
     */
    @Test
    public void anonymousClassRemoveIfTest() {
        List<String> list = new ArrayList<>(Arrays.asList("lambda", "apple", "juice", "rabbit", "app"));
        list.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 3;
            }
        });
        System.out.println(list);
    }

    /**
     * 使用lambda不需要记忆Predicate接口名字和test()方法名字
     */
    @Test
    public void lambdaRemoveIfTest() {
        List<String> list = new ArrayList<>(Arrays.asList("lambda", "apple", "juice", "rabbit", "app"));
        list.removeIf(s -> s.length() > 3);
        System.out.println(list);
    }

    /*
     * ----------------------- replaceAll() 的用法 -----------------------
     */

    /**
     * {@code replaceAll())的作用为对于每个元素都执行一个operator操作，并用操作结果替换原来的元素，
     * 在Java8之前，没有特别优雅的做法
     */
    @Test
    public void normalReplaceAllTest() {
        List<String> list = new ArrayList<>(Arrays.asList("lambda", "apple", "juice", "rabbit", "app"));
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (str.length() > 3) {
                list.set(i, str.toUpperCase());
            }
        }
        System.out.println(list);
    }

    /**
     * 匿名内部类的使用
     */
    @Test
    public void anonymousClassReplaceAllTest() {
        List<String> list = new ArrayList<>(Arrays.asList("lambda", "apple", "juice", "rabbit", "app"));
        list.replaceAll(new UnaryOperator<String>() {
            @Override
            public String apply(String s) {
                if (s.length() > 3) {
                    s = s.toUpperCase();
                }
                return s;
            }
        });
    }

    /**
     * lambda表达式的做法
     */
    @Test
    public void lambdaReplaceAllTest() {
        List<String> list = new ArrayList<>(Arrays.asList("lambda", "apple", "juice", "rabbit", "app"));
        list.replaceAll(s -> {
            if (s.length() > 3) {
                s = s.toUpperCase();
            }
            return s;
        });
    }

    /*
     * ----------------------- sort() 的用法 -----------------------
     */

    /**
     * 在Java8之前，需要使用匿名内部类的方式来实现
     */
    @Test
    public void normalSortTest() {
        List<String> list = Arrays.asList("lambda", "apple", "juice", "rabbit", "app");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.length() - str2.length();
            }
        });
        System.out.println(list);
    }

    /**
     * lambda的实现
     */
    @Test
    public void lambdaSortTest() {
        List<String> list = Arrays.asList("lambda", "apple", "juice", "rabbit", "app");
        list.sort((str1, str2) -> str1.length() - str2.length());
        System.out.println(list);
    }
}
