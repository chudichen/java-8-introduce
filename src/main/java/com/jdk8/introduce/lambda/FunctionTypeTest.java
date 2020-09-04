package com.jdk8.introduce.lambda;

import org.junit.jupiter.api.Test;

import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;

/**
 * lambda基本使用
 *
 * @author chudichen
 * @date 2020-09-04
 */
public class FunctionTypeTest {

    /**
     * 声明类型
     */
    @Test
    public void typeDeclarationTest() {
        IntBinaryOperator intBinaryOperator = (int a, int b) -> a + b;
        int result = intBinaryOperator.applyAsInt(1, 2);
        System.out.println(result);
    }

    /**
     * 不声明类型
     */
    @Test
    public void typeNotDeclarationTest() {
        ToIntBiFunction<Integer, Integer> function = (a, b) -> a + b;
        int result = function.applyAsInt(1, 2);
        System.out.println(result);
    }




}
