package com.joker.feature;

public class ForDog {

  public static void main(String[] args) {

    for (int i = 1; i < 10; i++) { {
        for (int j = 1; j <= i; j++) {
          System.out.print("我才是你爹\t");
        }
        System.out.println();
      }
      //
    }

    for (int i = 1; i < 10; i++) {
      for (int j = 1; j <= i; j++) {
        System.out.printf("%d*%d=%d\t",i,j,i*j);
      }
      System.out.println();
    }

//      for (int i = 1; i < 10; i++) {
//          for (int j = 1; j <= i; j++) {
//              cout << j << "×" << i << "=" << j * i << "\t ";
//          }
//          cout << endl;
//      }

  }
}
