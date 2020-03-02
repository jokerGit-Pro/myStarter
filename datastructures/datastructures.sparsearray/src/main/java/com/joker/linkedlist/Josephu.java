package com.joker.linkedlist;

/** 约瑟夫环形链表 */
public class Josephu {
  public static void main(String[] args) {
    CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
    circleSingleLinkedList.addBoy(5);
    circleSingleLinkedList.showBoy();

    circleSingleLinkedList.countBoy(1,2,5);

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
    Boy curBoy = null; // 辅助指针
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

  /**
   * @param startNo 表示从第几个小孩数数
   * @param countNum 数几下
   * @param nums 最初有几个节点(小孩)节点在圈中
   */
  public void countBoy(int startNo, int countNum, int nums) {
    if (first == null || startNo < 0 || startNo > nums) {
      System.out.println("数据不科学");
      return;
    }
    // 创建一个辅助指针
    Boy helper = first;
    while (true) {
      if (helper.getNext() == first) {
        break;
      }
      helper = helper.getNext();
    }
    while (true) {
      if (helper == first) {
        break;
      }
      for (int i = 0; i < countNum - 1; i++) {
        first=first.getNext();
        helper=helper.getNext();
      }
      System.out.printf("boy%d出圈 \n",first.getNo());
      first=first.getNext();
      helper.setNext(first);
    }
    System.out.printf("最后留在圈中的小孩是%d \n",first.getNo());
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
