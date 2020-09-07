package com.jdk8.introduce.lambda;

import org.junit.jupiter.api.Test;

import java.util.OptionalInt;
import java.util.stream.Stream;

/**
 * 串行流
 *
 * 我们达能能够想到，应该采用某种方式记录用户的每一步操作，当用户调用结束时将之前
 * 记录的操作叠加到一起在一次迭代中全部执行掉。沿着这个思路，有几个问题需要解决：
 * 1. 用户的操作如何记录？
 * 2. 操作如何叠加？
 * 3. 叠加之后的操作如何执行？
 * 4. 执行后的结果（如果有）在哪里？
 *
 * 操作如何记录？
 * 一个完整的操作是<数据来源， 操作， 回调函数>构成的三元组。Stream中使用Stage
 * 的概念来描述一个完整的操作，并用某种实例化后的{@link java.util.stream.PipelineHelper}
 * 来代表Stage，将具有先后顺序的各个Stage连在一起构成了整个流水线。
 * 这些Stream对象以双向链表的形式组织在一起，构成了整个流水线，由于每个Stage都
 * 记录了前一个Stage和本次的操作以及回调函数，依靠这种结构就能建立起对数据源的所有操作。
 *
 * 操作如何叠加？
 * 要想让流水线起到应有的作用我们需要一种将所有操作叠加到一起的方案。你可能觉得很简单，
 * 只需要从流水线的head开始一次执行每一步操作就行了。但是你忽略了前面一个Stage并不知道
 * 后面的Stage到底执行了哪种操作，以及回调函数是哪种形式。换句话说，只有当前Stage本身才
 * 知道如何执行自己包含的动作。这就需要某种协议来协调相邻Stage之间的调用关系。
 * 这种协议是由{@link java.util.stream.Sink}，每个Stage都会将自己的操作封装到一个Sink里，
 * 前一个Stage只需要调用后一个Stage的{@code accept()}方法即可。并不需要知道内部是如何处理的。
 * 对于有状态的操作，Sink的{@code begin()}和{@code end()}也是必须实现的。
 * 比如{@link java.util.stream.Stream#sorted()}是一个有状态的中间操作，其对应的Sink.begin()
 * 方法可能创建一个盛放结果的容器，而accept()方法则是将元素添加到该容器，最后使用end()对容器
 * 进行排序。对于短路操作还需要判断cancellationRequest()
 * 有了Sink的包装，Stage之间的调用问题就解决了，执行的之后只需要从流水线依次调用每个Stage的Sink{
 * begin(), accept(), cancellationRequested(), end()}方法就可以了。accept的逻辑可能是
 * 1. 使用当前Sink包装的毁掉函数处理u。2. 将处理结果传递给流水线下游的Sink。
 *
 * 叠加之后如何执行操作：
 * 最原始的动力就是就是结束操作（Terminal Operation），一旦调用了某个结束操作，就会出发整个流水线
 * 的执行。直观的说就是流水线链表不会再向后延伸了。
 * AbstractPipeline.opWrapSink(int flags, Sink downstream)，该方法返回一个新的包含了当前Stage
 * 代表的操作以及能够将结果传递给downstream的Sink对象。
 *
 * @author chudichen
 * @date 2020-09-07
 */
public class StreamPipelineTest {

    @Test
    public void getLongestStringTest() {
        Stream<String> strings = Stream.of("Apple", "Ant", "Book");

        OptionalInt longestStringLengthStartingWithA = strings
                .filter(s -> s.startsWith("A"))
                .mapToInt(String::length)
                .max();
        System.out.println(longestStringLengthStartingWithA.getAsInt());
    }
}
