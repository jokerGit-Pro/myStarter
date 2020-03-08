package com.joker.feature;

import org.junit.Test;

public class TestLambda {

  public void op(Long l1, Long l2, MyFunction2<Long, Long> mf) {
    System.out.println(mf.getValue(l1, l2));
  }

  @Test
  public void test3(){
    op(1L,2L,(x,y)->x+y);
    op(3L,4L,(x,y)->x*y);
  }

  @Test
  public void test2(){
    String strHandle = strHandle("\n\n\n\n\n\n bagagaggsaaaf \t", (str) -> str.trim());
    System.out.println(strHandle);

    String handle = strHandle("asfdafsfa", str -> str.toUpperCase());
    System.out.println(handle);
  }


  public String strHandle(String str,MyFunction my){
    return my.getValue(str);
  }


}
