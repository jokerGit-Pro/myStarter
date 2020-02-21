package com.joker.queue;

import java.util.Scanner;

/**
 *功能描述
 *@param:数组模拟队列
 *@return:
 *@anthor:Tom-Joker
 *@date: 
 */
public class ArrayQueueDemo {

  public static void main(String[] args) {
    ArrayQueue arrayQueue = new ArrayQueue(3);
    char key=' ';//接受用户输入
    Scanner scanner = new Scanner(System.in);
    boolean loop=true;

    //输出菜单
    while (loop){
      System.out.println("s(show):显示队列");
      System.out.println("e(exit):退出队列");
      System.out.println("a(add):添加数据到队列");
      System.out.println("g(get):从队列取出数据");
      System.out.println("h(head):查看队列头数据");
      key=scanner.next().charAt(0);
      switch (key){
        case 's':
          arrayQueue.showQueue();
          break;
        case 'a':
          System.out.println("请输入一个数:");
          int value = scanner.nextInt();
          arrayQueue.addQueue(value);
          break;
        case 'g':
          try{
            int res = arrayQueue.getQueue();
            System.out.printf("取出的数据是%d\n",res);
          }catch (Exception e){
            System.out.println(e.getMessage());
          }
          break;
        case 'h':
          try{
            int headQueue = arrayQueue.headQueue();
            System.out.println("头数据为:"+headQueue);
          }catch (Exception e){
            System.out.println(e.getMessage());
          }
          break;
        case 'e':
          loop=false;
          break;
        default:
            break;
      }
    }
    System.out.println("程序退出");

  }
}

class ArrayQueue{

  private int maxSize;
  private int front;
  private int rear;
  private int[] arr;

  //创建队列的构造器
  public ArrayQueue(int arrMaxSize){
    maxSize=arrMaxSize;
    front=-1;//指向数据队列的前一个位置
    rear=-1;//指向队列尾的具体位置
    arr=new int[arrMaxSize];
  }

  //判断队列是否满
  public boolean isFull(){
    return rear==maxSize-1;
  }

  //判断队列是否为空
  public boolean isEmpty(){
    return rear==front;
  }

  //添加数据队列
  public void addQueue(int n){
    if(isFull()){
      System.out.println("队列满了,不能添加数据");
      return;
    }

    arr[++rear]=n;
  }

  //取数据
  public int getQueue(){
    if(isEmpty()){
      System.out.println("队列为空");
      throw new RuntimeException("队列为空,不能取数据");
    }
    front++;
    return arr[front];
  }

  //显示队列的所有数据
  public  void showQueue(){
    if(isEmpty()){
      System.out.println("队列是空,没有数据");
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      System.out.printf("arr[%d]=%d\n",i,arr[i]);
    }
  }

  //显示队列头数据
  public int headQueue(){
    if(isEmpty()){
      System.out.println("队列为空,没有数据");
      throw new RuntimeException("队列空,无数据");
    }
    return arr[front+1];
  }
}
