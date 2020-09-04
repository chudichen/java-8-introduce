package com.jdk8.introduce.lambda;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 规约操作(reduction operation)又被称作折叠操作(fold)，是通过某个连接动作将
 * 所有的元素汇总成一个结果的过程。元素求和求最大值或最小值、求出元素总个数、将所有
 * 元素转换成一个列表或集合，都属于规约操作。Stream类库有两个通用的规约操作reduce()
 * 和collect()
 *
 * @author chudichen
 * @date 2020-09-04
 */
public class ReduceTest {

    /*
     * ----------------------- reduce() 的用法 -----------------------
     */

    /**
     * 使用reduce求出最长的单词
     */
    @Test
    public void reduceFindMaxLengthTest() {
        Stream<String> stream = Stream.of("interest", "time", "food", "rabbit", "app");
        Optional<String> result = stream.reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2);
        System.out.println(result.get());
    }

    /**
     * 求单词总长度
     */
    @Test
    public void reduceAccumulatorTest() {
        Stream<String> stream = Stream.of("interest", "time", "food", "rabbit", "app");
        Integer result = stream.reduce(
                // 初始值
                0,
                // 累加
                (sum, str) -> sum + str.length(),
                // 拼接器，在并行的时候才会用到
                (a, b) -> a + b
        );
        System.out.println(result);
    }

    /*
     * ----------------------- collect中toList 的用法 -----------------------
     */

    /**
     * stream转list
     */
    @Test
    public void toListTest() {
        Stream<String> stream = Stream.of("interest", "time", "food", "rabbit", "app");
        List<String> result = stream.collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    public void toArrayListTest() {
        Stream<String> stream = Stream.of("interest", "time", "food", "rabbit", "app");
        List<String> result = stream.collect(Collectors.toCollection(ArrayList::new));
        System.out.println(result);
    }

    /*
     * ----------------------- collect中toMap 的用法 -----------------------
     */

    /**
     * 使用toMap生成的收集器，用户需要制定key和value
     */
    @Test
    public void toMapTest() {
        Stream<Student> stream = Stream.of(
                new Student("Tom", 1, 75),
                new Student("Allen", 1, 80),
                new Student("Scott", 2, 65),
                new Student("Michael", 2, 95));

        Map<String, Student> result = stream.collect(Collectors.toMap(Student::getName, Function.identity()));
        System.out.println(result);
    }

    /**
     * 使用partitioningBy，可以按照条件将数据分成两个部分
     */
    @Test
    public void partitioningByTest() {
        Stream<Student> stream = Stream.of(
                new Student("Tom", 1, 55),
                new Student("Allen", 1, 80),
                new Student("Scott", 2, 65),
                new Student("Michael", 2, 95));

        Map<Boolean, List<Student>> result = stream.collect(Collectors.partitioningBy(student -> student.getScore() > 60));
        System.out.println(result);
    }

    /**
     * 使用groupingBy标示按照条件进行分组
     */
    @Test
    public void groupingByTest() {
        Stream<Student> stream = Stream.of(
                new Student("Tom", 1, 55),
                new Student("Allen", 1, 80),
                new Student("Scott", 2, 65),
                new Student("Michael", 2, 95));

        Map<Integer, List<Student>> result = stream.collect(Collectors.groupingBy(Student::getClassNum));
        System.out.println(result);
    }

    /**
     * 增强的groupingBy
     */
    @Test
    public void groupingByEnhanceTest() {
        Stream<Student> stream = Stream.of(
                new Student("Tom", 1, 55),
                new Student("Allen", 1, 80),
                new Student("Scott", 2, 65),
                new Student("Michael", 2, 95));

        Map<Integer, List<String>> result = stream
                .collect(Collectors
                        .groupingBy(
                                Student::getClassNum,
                                Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println(result);
    }

    static class Student {
        private String name;
        private Integer classNum;
        private Integer score;

        public Student(String name, Integer classNum, Integer score) {
            this.name = name;
            this.classNum = classNum;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getClassNum() {
            return classNum;
        }

        public void setClassNum(Integer classNum) {
            this.classNum = classNum;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", classNum=" + classNum +
                    ", score=" + score +
                    '}';
        }
    }
}
