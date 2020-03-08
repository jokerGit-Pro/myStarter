package com.joker.stack;

import java.util.Scanner;

/**
 * 数组模拟栈
 */
public class ArrayStackDemo {
  public static void main(String[] args) {
    ArrayStack stack = new ArrayStack(4);
    String key = "";
    boolean loop = true; // 控制是否退出菜单
    Scanner scanner = new Scanner(System.in);

    while (loop) {
      System.out.println("s(show):显示栈");
      System.out.println("e(exit):退出程序");
      System.out.println("p(push):添加数据");
      System.out.println("q(pop):取出数据");
      System.out.println("请输入你的选择");

      key=scanner.next();
        switch (key){
            case "s":
                stack.list();
                break;
            case "p":
                System.out.println("请输入一个数");
                int anInt = scanner.nextInt();
                stack.push(anInt);
                break;
            case "q":
                try{
                    int pop = stack.pop();
                    System.out.println("取出的数据是:"+pop);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "e":
                scanner.close();
                System.out.println("退出程序");
                loop=false;
                break;
            default:
                break;
        }
    }
      System.out.println("已经退出程序");
  }
}

class ArrayStack {

  private int maxSize;
  private int[] stack;
  private int top = -1;

  public ArrayStack(int maxSize) {
    this.maxSize = maxSize;
    stack = new int[this.maxSize];
  }

  // 栈满
  public boolean isFull() {
    return top == maxSize - 1;
  }

  // 栈空
  public boolean isEmpty() {
    return top == -1;
  }

  // 入栈
  public void push(int value) {
    if (isFull()) {
      System.out.println("栈满了,无法push数据");
      return;
    }
    top++;
    stack[top] = value;
  }

  // 出栈
  public int pop() {
    if (isEmpty()) {
      throw new RuntimeException("栈空,没有数据~~");
    }
    int value = stack[top];
    top--;
    return value;
  }

  public void list() {
    if (isEmpty()) {
      System.out.println("栈空,没有数据...");
      return;
    }
    for (int i = top; i >= 0; i--) {
      System.out.printf("stack[%d]=%d\n", i, stack[i]);
    }
  }
}
