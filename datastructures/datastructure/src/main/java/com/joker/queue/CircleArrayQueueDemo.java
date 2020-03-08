package com.joker.queue;

import java.util.Scanner;

/**
 * 功能描述
 *
 * @param:数组模拟环形队列
 * @return:
 * @anthor:Tom-Joker
 * @date:
 */
public class CircleArrayQueueDemo {

  public static void main(String[] args) {

    // 测试
    System.out.println("数组模拟环形队列");
    CircleArray arrayQueue = new CircleArray(4);//设置为4,其队列的有效数据最大是3,最后一个存放约定
    char key = ' '; // 接受用户输入
    Scanner scanner = new Scanner(System.in);
    boolean loop = true;

    // 输出菜单
    while (loop) {
      System.out.println("s(show):显示队列");
      System.out.println("e(exit):退出队列");
      System.out.println("a(add):添加数据到队列");
      System.out.println("g(get):从队列取出数据");
      System.out.println("h(head):查看队列头数据");
      key = scanner.next().charAt(0);
      switch (key) {
        case 's':
          arrayQueue.showQueue();
          break;
        case 'a':
          System.out.println("请输入一个数:");
          int value = scanner.nextInt();
          arrayQueue.addQueue(value);
          break;
        case 'g':
          try {
            int res = arrayQueue.getQueue();
            System.out.printf("取出的数据是%d\n", res);
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          break;
        case 'h':
          try {
            int headQueue = arrayQueue.headQueue();
            System.out.println("头数据为:" + headQueue);
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          break;
        case 'e':
          loop = false;
          break;
        default:
          break;
      }
    }
    System.out.println("程序退出");
  }
}

class CircleArray {
  private int maxSize;

  // front 指向队列的第一个元素,初始值为0
  private int front;
  // rear 指向队列最后一个元素的后一个位置,空出一个位置存放约定,rear初始值为0
  private int rear;

  private int[] arr;

  public CircleArray(int arrMaxSize) {
    maxSize = arrMaxSize;
    arr = new int[maxSize];
  }

  // 判断队列是否满
  public boolean isFull() {
    return (rear + 1) % maxSize == front;
  }

  public boolean isEmpty() {
    return rear == front;
  }
  // 添加数据到队列
  public void addQueue(int n) {
    if (isFull()) {
      System.out.println("队列已满,不能添加");
      return;
    }
    arr[rear] = n;
    rear = (rear + 1) % maxSize;
  }

  // 获取队列的数据,出队列
  public int getQueue() {
    if (isEmpty()) {
      System.out.println("队列为空,无法取数据");
      throw new RuntimeException("队列为空,取数据失败");
    }
    int value = arr[front];
    front = (front + 1) % maxSize;
    return value;
  }

  // 显示队列的所有数据
  public void showQueue() {
    // 遍历
    if (isEmpty()) {
      System.out.println("队列空的,没有数据");
      return;
    }
    // 从frint开始遍历
    for (int i = 0; i < size(); i++) {
      System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
    }
  }

  // 求出当前队列有效数据的个数
  public int size() {
    return (rear + maxSize - front) % maxSize;
  }

  // 显示队列的头元素
  public int headQueue() {
    if (isEmpty()) {
      throw new RuntimeException("队列为空,没有数据");
    }
    return arr[front];
  }
}
