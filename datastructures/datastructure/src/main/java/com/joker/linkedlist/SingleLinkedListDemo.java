package com.joker.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

  public static void main(String[] args) {
    HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
    HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
    HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
    HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
    SingleLinkedList singleLinkedList = new SingleLinkedList();
    //      singleLinkedList.add(heroNode1);
    //      singleLinkedList.add(heroNode4);
    //      singleLinkedList.add(heroNode3);
    //      singleLinkedList.add(heroNode2);

    // 按编号插入
    singleLinkedList.addByOrder(heroNode1);
    singleLinkedList.addByOrder(heroNode4);
    singleLinkedList.addByOrder(heroNode3);
    singleLinkedList.addByOrder(heroNode2);



    // 显示
      System.out.println("反转前:");
    singleLinkedList.list();
    SingleLinkedList.reversLinkedList(singleLinkedList.getHead());
      System.out.println("反转后:");
      singleLinkedList.list();

      System.out.println("逆向输出:");
      SingleLinkedList.reversPrint(singleLinkedList.getHead());
      System.out.println("链表:");
      singleLinkedList.list();


    HeroNode newHeroNode = new HeroNode(3, "吴用", "智障");
    singleLinkedList.update(newHeroNode);
    // 显示
    System.out.println("修改后");
    singleLinkedList.list();
    System.out.println("删除前链表的有效数据:" + SingleLinkedList.getLength(singleLinkedList.getHead()));

    singleLinkedList.del(1);
    singleLinkedList.del(4);
    //      singleLinkedList.del(2);
    // 显示
    System.out.println("删除后");
    System.out.println("删除后链表的有效数据:" + SingleLinkedList.getLength(singleLinkedList.getHead()));
    singleLinkedList.list();

    System.out.println(
        "倒数第一个节点的数据为:" + SingleLinkedList.findIndexNode(singleLinkedList.getHead(), 2));
  }
}

// 定义SingleLinkedList管理英雄
class SingleLinkedList {
  // 先初始化一个头结点,头结点不要动,头结点不存放数据
  private HeroNode head = new HeroNode(0, "", "");

  public HeroNode getHead() {
    return head;
  }

  public void setHead(HeroNode head) {
    this.head = head;
  }

  // 添加节点到单项列表
  public void add(HeroNode heroNode) {
    // 辅助节点temp
    HeroNode temp = head;
    while (true) {
      if (temp.next == null) {
        break;
      }
      temp = temp.next;
    }
    temp.next = heroNode;
  }

  public void addByOrder(HeroNode heroNode) {
    HeroNode temp = head;
    boolean flag = false; // 标识是否已经添加;
    while (true) {
      if (temp.next == null) { // temp已经在链表的最后
        break;
      }
      if (temp.next.no > heroNode.no) {
        break;
      } else if (temp.next.no == heroNode.no) {
        flag = true;
        break;
      }
      temp = temp.next;
    }
    // 判断flag
    if (flag) {
      System.out.printf("编号为%d的英雄已经存在,不能插入了", heroNode.no);
    } else {
      heroNode.next = temp.next;
      temp.next = heroNode;
    }
  }

  public void del(int no) {
    HeroNode temp = head;
    boolean flag = false;
    while (true) {
      if (temp.next == null) { // 已经到链表的最后
        break;
      }
      if (temp.next.no == no) {
        flag = true;
        break;
      }
      temp = temp.next;
    }
    if (flag) {
      temp.next = temp.next.next;
    } else {
      System.out.printf("没有找到编号为%d的英雄,无法删除", no);
    }
  }

  // 修改节点
  public void update(HeroNode newHeeoNode) {
    if (head.next == null) {
      System.out.println("链表为空");
      return;
    }
    HeroNode temp = head.next;
    boolean flag = false;
    while (true) {
      if (temp == null) {
        break;
      }
      if (temp.no == newHeeoNode.no) {
        flag = true;
        break;
      }
      temp = temp.next;
    }
    if (flag) {
      temp.name = newHeeoNode.name;
      temp.nickName = newHeeoNode.nickName;
    } else {
      System.out.printf("没有找到标号为 %d 的英雄,不能修改", newHeeoNode.no);
    }
  }

  // 获取单链表的节点的有效个数
  public static int getLength(HeroNode head) {
    if (head.next == null) {
      return 0;
    }
    int length = 0;
    HeroNode cur = head.next;
    while (cur != null) {
      length++;
      cur = cur.next;
    }
    return length;
  }

  // 查找单链表中的倒数第k个节点
  public static HeroNode findIndexNode(HeroNode head, int index) {
    if (head.next == null) {
      return null;
    }
    int size = getLength(head);
    if (index < 0 || index > size) {
      return null;
    }
    HeroNode cur = head.next;
    for (int i = 0; i < size - index; i++) {
      cur = cur.next;
    }
    return cur;
  }

  // 显示链表
  public void list() {
    // 判断链表是否为空
    if (head.next == null) {
      System.out.printf("链表为空");
      return;
    }
    HeroNode temp = head.next;
    while (true) {
      // 判断是否到链表最后
      if (temp == null) {
        break;
      }
      System.out.println(temp);
      temp = temp.next;
    }
  }

  // 实现单链表的反转
  public static void reversLinkedList(HeroNode head) {
    if (head.next == null || head.next.next == null) {
      return;
    }
    HeroNode cur = head.next;
    HeroNode next=null;
    HeroNode newHeroHead=new HeroNode(0,"","");

    while (cur!=null){
        next=cur.next;
        cur.next=newHeroHead.next;
        newHeroHead.next=cur;
        cur=next;
    }
    head.next=newHeroHead.next;
  }

  //逆序打印单链表(不改变单链表的顺序)
    public static void reversPrint(HeroNode head){
      if(head.next==null){
          return;
      }
        Stack<HeroNode> stack = new Stack<>();
      HeroNode cur=head.next;
      while (cur!=null){
          stack.push(cur);
          cur=cur.next;
      }
      while (stack.size()>0){
          System.out.println(stack.pop());
      }
    }
}

// 定义HeroNode,每个HeroNode就是一个节点
class HeroNode {

  public int no;
  public String name;
  public String nickName;
  public HeroNode next;

  public HeroNode(int no, String name, String nickName) {
    this.no = no;
    this.name = name;
    this.nickName = nickName;
  }

  @Override
  public String toString() {
    return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + ", nickName='" + nickName + '}';
  }
}
