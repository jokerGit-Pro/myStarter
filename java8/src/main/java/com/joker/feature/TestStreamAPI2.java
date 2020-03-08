package com.joker.feature;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/** Stream 的三个操作
 * 1.创建Stream
 * 2.中间操作
 * 3.终止操作
 *
 */

/**
 * map相当于List.add(Object object)  {11,22,{33,44,55}}
 * flatMap 相当于 list.addAll(Collection coll); {11,22,33,44,55}
 */

public class TestStreamAPI2 {

  List<Employee> employees =
      Arrays.asList(
          new Employee("张三", 18, 9999.99),
          new Employee("李四", 58, 5555.55),
          new Employee("王五", 26, 3333.33),
          new Employee("赵六", 36, 6666.66),
          new Employee("田七", 12, 8888.88),
          new Employee("田七", 12, 8888.88),
          new Employee("田七", 12, 8888.88),
          new Employee("田七", 12, 8888.88));

  // 中间操作

  /**
   * 筛选与切片 filter-接收Lambda,从流中排除某些元素. limit-截断流,使元素不超过给定数量 skip(n)-跳过n个元素,返回一个扔掉前n个元素的流,若流中元素不足n个
   * 则返回一个空流,与limit互补 distinct-筛选,通过流所生成元素的hashCode()和equals()去除重复元素
   */
  @Test // Stream API内部迭代
  public void test() {
    Stream<Employee> stream =
        employees.stream()
            .filter(
                e -> {
                  System.out.println("Stream API 的中间操作");
                  return e.getAge() > 35;
                });

    // 终止操作
    stream.forEach(System.out::println);
  }

  @Test // 外部迭代
  public void test2() {
    Iterator<Employee> iterator = employees.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  @Test
  public void test3() {
    employees.stream().filter(e -> e.getSalary() > 5000).limit(2).forEach(System.out::println);
  }

  @Test
  public void test4() {
    employees.stream()
        .filter(e -> e.getSalary() > 5000)
        .skip(2)
        .distinct()
        .forEach(System.out::println);
  }

  /** 映射 map--接收Lambda,将元素转换成其他形式或提取信息,接收一个函数作为参数 flatMap--接受一个函数作为参数,将流中的每个值换成另一个流,然后把左右流连接成一个流 */
  @Test
  public void test5() {
    List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
    list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

    employees.stream().map(Employee::getName).forEach(System.out::println);
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    Stream<Stream<Character>> stream = list.stream().map(TestStreamAPI2::filterChara);

    stream.forEach(
        sm -> {
          sm.forEach(System.out::println);
        });
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    Stream<Character> characterStream = list.stream().flatMap(TestStreamAPI2::filterChara);
    characterStream.forEach(System.out::println);
  }

  public static Stream<Character> filterChara(String str) {
    List<Character> list = new ArrayList();
    for (Character ch : str.toCharArray()) {
      list.add(ch);
    }
    return list.stream();
  }
    /**
     * 排序
     * sorted()--自然排序(Comparable)
     * sorted(Comparator com)--定制排序()
     *
     */
    @Test
    public void test7(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream().sorted().forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");

        employees.stream()
                .sorted((e1,e2)->{
                   if(e1.getAge()==(e2.getAge())){
                       return e1.getName().compareTo(e2.getName());
                   }else{
                       return e1.getAge();
                   }
                });
    }

}
