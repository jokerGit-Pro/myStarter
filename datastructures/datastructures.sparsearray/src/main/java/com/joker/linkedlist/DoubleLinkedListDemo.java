package com.joker.linkedlist;

public class DoubleLinkedListDemo {
  public static void main(String[] args) {

    HeroNode2 heroNode1 = new HeroNode2(2, "宋江", "及时雨");
    HeroNode2 heroNode2 = new HeroNode2(5, "卢俊义", "玉麒麟");
    HeroNode2 heroNode3 = new HeroNode2(9, "吴用", "智多星");
    HeroNode2 heroNode4 = new HeroNode2(26, "林冲", "豹子头");

    DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
    doubleLinkedList.add(heroNode1);
    doubleLinkedList.add(heroNode2);
    doubleLinkedList.add(heroNode3);
    doubleLinkedList.add(heroNode4);

    doubleLinkedList.list();
    HeroNode2 newHero = new HeroNode2(5, "公孙胜", "入云龙");
    doubleLinkedList.update(newHero);
    System.out.println("修改后数据:");
    doubleLinkedList.list();

    System.out.println("插入数据");
      HeroNode2 heroNode = new HeroNode2(30, "苍井空", "苍老湿");
      doubleLinkedList.addByOrder(heroNode);
    System.out.println("插入数据后");
    doubleLinkedList.list();
  }
}

class DoubleLinkedList {
  private HeroNode2 head = new HeroNode2(0, "", "");

  public HeroNode2 getHead() {
    return head;
  }

  // 显示链表
  public void list() {
    // 判断链表是否为空
    if (head.next == null) {
      System.out.printf("链表为空");
      return;
    }
    HeroNode2 temp = head.next;
    while (true) {
      // 判断是否到链表最后
      if (temp == null) {
        break;
      }
      System.out.println(temp);
      temp = temp.next;
    }
  }

  // 删除节点
  public void del(int no) {

    if (head.next == null) {
      System.out.println("链表为空,无法删除");
      return;
    }

    HeroNode2 temp = head.next;
    boolean flag = false;
    while (true) {
      if (temp == null) { // 已经到链表的最后
        break;
      }
      if (temp.no == no) {
        flag = true;
        break;
      }
      temp = temp.next;
    }
    if (flag) {
      temp.pre.next = temp.next;
      if (temp.next != null) {
        temp.next.pre = temp.pre;
      }
    } else {
      System.out.printf("没有找到编号为%d的英雄,无法删除", no);
    }
  }

  // 修改节点
  public void update(HeroNode2 newHeeoNode) {
    if (head.next == null) {
      System.out.println("链表为空");
      return;
    }
    HeroNode2 temp = head.next;
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

  public void add(HeroNode2 heroNode) {
    HeroNode2 temp = head;
    // 遍历链表
    while (true) {
      if (temp.next == null) {
        break;
      }

      temp = temp.next;
    }
    temp.next = heroNode;
    heroNode.pre = temp;
  }

  public void addByOrder(HeroNode2 heroNode) {
    HeroNode2 temp = head;
    boolean flag = false;
    while (true){
        if(temp.next==null){
            break;
        }
        if(temp.next.no>heroNode.no){
            break;
        }else if(temp.next.no==heroNode.no){
            flag=true;
            break;
        }
        temp=temp.next;
    }

    if(flag){
      System.out.printf("编号为%d的英雄已经存在,无法添加",heroNode.no);
    }else {
        if(temp.next==null){
            temp.next=heroNode;
            heroNode.pre=temp;
            heroNode.next=null;
        }else{
            HeroNode2 heroNe=temp.next;
            temp.next=heroNode;
            heroNode.pre=temp;
            heroNode.next=heroNe;
        }

    }
  }
}

// 定义HeroNode,每个HeroNode就是一个节点
class HeroNode2 {

  public int no;
  public String name;
  public String nickName;
  public HeroNode2 next;
  public HeroNode2 pre;

  public HeroNode2(int no, String name, String nickName) {
    this.no = no;
    this.name = name;
    this.nickName = nickName;
  }

  @Override
  public String toString() {
    return "HeroNode2{" + "no=" + no + ", name='" + name + '\'' + ", nickName='" + nickName + '}';
  }
}
