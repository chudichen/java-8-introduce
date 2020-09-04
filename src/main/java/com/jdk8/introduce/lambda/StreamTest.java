package com.jdk8.introduce.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 你可能没有意识到Java对函数式编程的重视程度，看看Java8加入函数式编程扩充多少功能就清楚了。
 * Java8之所以花费这么大功夫引入函数式编程原因有二：
 *     1. 代码简洁：函数式编程写出的代码简洁且意图明确，使用stream接口让你从此告别for循环。
 *     2. 多核友好：Java函数式编程使得编写并行程序从未如何简单，你需要的全部就是调用一下parallel方法
 *
 * Steam对于Java8之前是个陌生的东西，Stream并不是一种数据接口，它只是数据源的一种视图。
 * 这里的数据源可以是一个数组，Java容器或I/O channel等。
 *
 * @author chudichen
 * @date 2020-09-04
 */
public class StreamTest {

    /**
     * foreach对stream进行遍历
     */
    @Test
    public void foreachTest() {
        Stream<String> stream = Stream.of("lambda", "apple", "juice", "rabbit", "app");
        stream.forEach(str -> System.out.println(str));
    }

    /**
     * 没有结束操作不会执行
     */
    @Test
    public void filterWithoutEndOperationTest() {
        Stream<String> stream = Stream.of("lambda", "apple", "juice", "rabbit", "app");
        stream.filter(str -> {
            System.out.println(str);
            return str.length() > 3;
        });
    }

    /**
     * 过滤操作
     */
    @Test
    public void filterTest() {
        Stream<String> stream = Stream.of("lambda", "apple", "juice", "rabbit", "app");
        stream.filter(str -> str.length() > 3).forEach(System.out::println);
    }

    /**
     * 去重操作
     */
    @Test
    public void distinctTest() {
        Stream<String> stream = Stream.of("lambda", "lambda", "lambda", "rabbit", "app");
        stream.distinct().forEach(System.out::println);
    }

    /**
     * map操作:返回一个当前所有元素执行mapper之后的结果组成的Stream
     */
    @Test
    public void mapTest() {
        Stream<String> stream = Stream.of("lambda", "lambda", "lambda", "rabbit", "app");
        stream.map(str -> str.toUpperCase()).forEach(System.out::println);
    }

    /**
     * flatMap:对每次元素执行mapper操作，并把mapper之后返回的stream组成一个新的stream
     */
    @Test
    public void flatMapTest() {
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8));
        stream.flatMap(list -> list.stream()).forEach(System.out::println);
    }
}
