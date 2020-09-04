package com.jdk8.introduce.lambda;

import org.junit.jupiter.api.Test;

/**
 * 自定义函数的使用
 *
 * @author chudichen
 * @date 2020-09-04
 */
public class CustomFunctionTest {

    @Test
    public void customTest() {
        ConsumerInterface<String> consumer = str -> System.out.println(str);
        consumer.accept("Custom test");
    }


    /**
     * 自定义接口,使用FunctionalInterface修饰是可选的，只要有一个抽象方法即可。
     *
     * @param <T>
     */
    @FunctionalInterface
    interface ConsumerInterface<T> {

        /**
         * 调用的方法
         *
         * @param t 参数
         */
        void accept(T t);
    }
}