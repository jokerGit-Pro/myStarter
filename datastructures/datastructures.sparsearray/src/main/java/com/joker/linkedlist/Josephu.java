package com.joker.linkedlist;

public class Josephu {
  public static void main(String[] args) {
      CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
      circleSingleLinkedList.addBoy(30);
      circleSingleLinkedList.showBoy();
  }
}

class CircleSingleLinkedList {
  // 创建first节点
  private Boy first = null;

  public void addBoy(int nums) {
    if (nums < 2) {
      System.out.println("nums不能小于2");
      return;
    }
    Boy curBoy = null;//辅助指针
    for (int i = 1; i <= nums; i++) {
      Boy boy = new Boy(i);
      if (i == 1) {
        first = boy;
        first.setNext(first);
        curBoy = first;
      } else {
        curBoy.setNext(boy);
        boy.setNext(first);
        curBoy = boy;
      }
    }
  }

  public void showBoy() {
    if (first == null) {
      System.out.println("链表为空");
      return;
    }
    Boy curBoy = first;
    while (true) {
      System.out.printf("boy编号为 %d \n", curBoy.getNo());
      if (curBoy.getNext() == first) {
        break;
      }
      curBoy = curBoy.getNext();
    }
  }
}

class Boy {
  private int no;
  private Boy next;

  public Boy(int no) {
    this.no = no;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public Boy getNext() {
    return next;
  }

  public void setNext(Boy next) {
    this.next = next;
  }
}
