package com.joker.feature;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 1.Stream的三个操作步鄹
 * 1.创建Stream
 * 2.中间操作
 * 3.终端操作
 */
public class TestStream {

    @Test
    public void test1(){
        //1.通过Collection系列集合提供的stream()或parallelSream()
        List<String> list=new ArrayList();
        Stream<String> stream = list.stream();

        //2.通过Arrays中的静态方法stream()获取数组流
        Integer[] ints=new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(ints);

        //3.通过streamAPO中的静态方法
        Stream<String> stream2 = Stream.of("aaa", "bb", "cc");

        //4.创建无限流
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        iterate.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(()->Math.random()).limit(5).forEach(System.out::println);



    }
}
