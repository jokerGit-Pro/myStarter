package com.joker.feature;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8 内置的四大函数式接口
 *
 * <p>Consumer<T> :消费性接口 void accept(T t);
 *
 * <p>Supplier<T> 供给型接口 T get();
 * Function<T,R> : 函数型接口 R apply(T t);
 * Predication<T> : 断言型接口 boolean test(T t);
 */
public class TestLambda3 {
  // Consumer 消费性接口
  @Test
  public void test1() {
    happy(10000, (m) -> System.out.print("大保健消费" + m));
  }

  public void happy(double money, Consumer<Double> con) {
    con.accept(money);
  }

  // Supplier 供给型接口
  public List<Integer> getNumList(int num, Supplier<Integer> sup) {
    List<Integer> list = new ArrayList();
    for (int i = 0; i < num; i++) {
      Integer e = sup.get();
      list.add(e);
    }
    return list;
  }

  @Test
  public void test2(){
      List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
      for (Integer inte :numList ) {
      System.out.println(inte);
      }
  }

  //Function 函数型接口
  public String strHandle(String str, Function<String,String> fun){
      return fun.apply(str);
  }

  @Test
  public void test3(){
      String strHandle = strHandle(" \t\t '刘妞妞'是个八嘎", str -> str.trim());
    System.out.println(strHandle);
  }

  //Predicate<T> :断言型接口 boolean test(T t);
  public List<String> filterStr(List<String> list, Predicate<String> pre){
      List<String> strList=new ArrayList();
      for (String str :list ) {
          if(pre.test(str)){
              strList.add(str);
          }
      }
      return strList;
  }

  @Test
  public void test4(){
      List<String> asList = Arrays.asList("hello", "liuniuniu", "joker", "spring", "mysql", "mycat", "nginx");
      List<String> list = filterStr(asList, s -> s.contains("o"));
      for (String li :list ) {
      System.out.println(li);
      }
  }
}
